package com.excilys.formation.servlet;

import java.io.IOException;

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

// Extend HttpServlet class
@WebServlet(name = "DashboardServlet", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 458516061330357823L;

    private ComputerService computerService;
    private List<ComputerDTO> computersDto;
    private int nbPages;
    private int indexPage;
    private int nbComputers;
    private Page page;

    /**
     * @throws ServletException serlvetexcp
     */
    public void init() throws ServletException {
        page = new Page();
        computerService = ComputerService.COMPUTERSERVICE;
        nbPages = computerService.getNumberOfPageOfAllComputers(page);
        indexPage = page.getIndex();
        nbComputers = computerService.getCountOfAllComputers();
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            computerService.delete(request.getParameter("selection"));
        }

        if (request.getParameter("action") != null) {
            if (request.getParameter("action").equals("next")) {
                page.next();
            }
            if (request.getParameter("action").equals("page")) {
                nbPages = computerService.getNumberOfPageOfAllComputers(page);
                if (Integer.parseInt(request.getParameter("num")) < 0
                        || Integer.parseInt(request.getParameter("num")) > nbPages) {
                    System.out.println("tentative");
                } else {
                    page.setIndex(Integer.parseInt(request.getParameter("num")));
                }
            }
            if (request.getParameter("action").equals("previous")) {
                page.previous();
            }
            if (request.getParameter("action").equals("change_max_obj")) {
                if (Integer.parseInt(request.getParameter("num")) != 10
                        && Integer.parseInt(request.getParameter("num")) != 50
                        && Integer.parseInt(request.getParameter("num")) != 100) {
                    System.out.println("tentative");

                } else {
                    page.setMaxNumberOfObject(Integer.parseInt(request.getParameter("num")));
                    if (request.getParameter("search") != null && request.getParameter("search") != ""
                            && request.getParameter("by") != null && request.getParameter("by") != "") {

                        if (request.getParameter("by").equals("cp")) {
                            nbPages = computerService.getNumberOfPageOfComputersByName(request.getParameter("search"), page);
                            request.setAttribute("search", request.getParameter("search"));
                            request.setAttribute("by", request.getParameter("by"));
                        }
                        if (request.getParameter("by").equals("cy")) {
                            nbPages = computerService.getNumberOfPageOfComputersByCompanyName(request.getParameter("search"), page);
                            request.setAttribute("search", request.getParameter("search"));
                            request.setAttribute("by", request.getParameter("by"));
                        }

                    } else {
                        nbPages = computerService.getNumberOfPageOfAllComputers(page);
                    }
                    page.setIndex(0);
                }

            }
        }
        indexPage = page.getIndex();
        if (request.getParameter("search") != null && request.getParameter("search") != ""
                && request.getParameter("by") != null && request.getParameter("by") != "") {

            if (request.getParameter("by").equals("cp")) {
                nbComputers = computerService.getCountOfComputersByName(request.getParameter("search"));
                computersDto = computerService.getPageOfComputersByName(request.getParameter("search"), page);
                request.setAttribute("search", request.getParameter("search"));
                request.setAttribute("by", request.getParameter("by"));
            }
            if (request.getParameter("by").equals("cy")) {
                nbComputers = computerService.getCountOfComputersByCompanyName(request.getParameter("search"));
                computersDto = computerService.getPageOfComputersByCompanyName(request.getParameter("search"), page);
                request.setAttribute("search", request.getParameter("search"));
                request.setAttribute("by", request.getParameter("by"));
            }

        } else {
            nbComputers = computerService.getCountOfAllComputers();
            computersDto = computerService.getPageOfComputers(page);
        }
        request.setAttribute("nbComputers", nbComputers);
        request.setAttribute("index", indexPage);
        request.setAttribute("computersDto", computersDto);
        request.setAttribute("nbpage", nbPages);
        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    /**
     */
    public void destroy() {
        // do nothing.
    }
}