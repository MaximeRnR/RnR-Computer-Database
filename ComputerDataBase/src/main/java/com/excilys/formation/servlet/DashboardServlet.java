package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;

// Import required java libraries

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.ui.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Extend HttpServlet class
@WebServlet(name = "DashboardServlet", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 458516061330357823L;

    private ComputerService computerService;
    private List<ComputerDTO> computersDto;
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    /**
     * @throws ServletException serlvetexcp
     */
    public void init() throws ServletException {

        computerService = ComputerService.COMPUTERSERVICE;
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int nbPages;
        int indexPage;
        int nbComputers = computerService.getCountOfAllComputers();
        Page page;
        page = new Page();
        nbPages = computerService.getNumberOfPageOfAllComputers(page);
        request.setAttribute("search", "");
        request.setAttribute("by", "");
        request.setAttribute("maxObj", 10);
        if (request.getParameter("page") != null && request.getParameter("page") != "") {
            if (Integer.parseInt(request.getParameter("page")) < 1
                    || Integer.parseInt(request.getParameter("page")) > nbPages + 1) {
                //System.out.println("tentative");
                logger.error("DASHSERVLET page");
            } else {
                page.setIndex(Integer.parseInt(request.getParameter("page")) - 1);
            }
        }
        if (request.getParameter("maxObj") != null && request.getParameter("maxObj") != "") {
            if (Integer.parseInt(request.getParameter("maxObj")) != 10
                    && Integer.parseInt(request.getParameter("maxObj")) != 50
                    && Integer.parseInt(request.getParameter("maxObj")) != 100) {
                //System.out.println("tentative2");
                logger.error("DASHSERVLET maxObj");

            } else {
                request.setAttribute("maxObj", request.getParameter("maxObj"));
                page.setMaxNumberOfObject(Integer.parseInt(request.getParameter("maxObj")));
            }
        }
        if (request.getParameter("search") != null && request.getParameter("search") != ""
                && request.getParameter("by") != null && request.getParameter("by") != "") {
            if (request.getParameter("by").equals("cp")) {
                nbPages = computerService.getNumberOfPageOfComputersByName(request.getParameter("search"), page);
                if (nbPages < page.getIndex()) {
                    page.setIndex(0);
                }
                nbComputers = computerService.getCountOfComputersByName(request.getParameter("search"));
                computersDto = computerService.getPageOfComputersByName(request.getParameter("search"), page);
                request.setAttribute("search", request.getParameter("search"));
                request.setAttribute("by", request.getParameter("by"));
            }
            if (request.getParameter("by").equals("cy")) {
                nbPages = computerService.getNumberOfPageOfComputersByCompanyName(request.getParameter("search"), page);
                if (nbPages < page.getIndex()) {
                    page.setIndex(0);
                }
                nbComputers = computerService.getCountOfComputersByCompanyName(request.getParameter("search"));
                computersDto = computerService.getPageOfComputersByCompanyName(request.getParameter("search"), page);
                request.setAttribute("search", request.getParameter("search"));
                request.setAttribute("by", request.getParameter("by"));
            }

        } else {
            nbComputers = computerService.getCountOfAllComputers();
            computersDto = computerService.getPageOfComputers(page);
            nbPages = computerService.getNumberOfPageOfAllComputers(page);
        }
        indexPage = page.getIndex();
        request.setAttribute("nbComputers", nbComputers);
        request.setAttribute("index", indexPage);
        request.setAttribute("computersDto", computersDto);
        request.setAttribute("nbpage", nbPages);
        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("selection") != null && request.getParameter("selection").matches("^[0-9,]+$")) {
           String[] selection = request.getParameter("selection").split(",");
           List<Long> idTab = new ArrayList<Long>();
            for (int i = 0; i < selection.length; i++) {
                idTab.add(i, (long) Integer.parseInt(selection[i]));
            }
            computerService.delete(idTab);
        }

        this.doGet(request, response);
    }

    /**
     */
    public void destroy() {
        // do nothing.
    }
}