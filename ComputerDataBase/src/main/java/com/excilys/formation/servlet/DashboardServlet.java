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

    private ComputerService cpS;
    private List<ComputerDTO> lcpdto;
    private int nbPage;
    private int indexPage;
    private int count;

    /**
    * @throws ServletException serlvetexcp
    */
    public void init() throws ServletException {
        cpS = new ComputerService();
        nbPage = cpS.pageNumber();
        indexPage = Page.PAGE.getIndex();
        count = cpS.count();
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(Page.PAGE.getIndex());
        count = cpS.count();
        indexPage = Page.PAGE.getIndex();
        System.out.println("get");
        lcpdto = cpS.page();
        request.setAttribute("count", count);
        request.setAttribute("index", indexPage);
        request.setAttribute("lcpdto", lcpdto);
        request.setAttribute("nbpage", nbPage);
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") != null) {
            if (request.getParameter("action").equals("next")) {
                Page.PAGE.next();
            }
            if (request.getParameter("action").equals("page")) {
                nbPage = cpS.pageNumber();
                if (Integer.parseInt(request.getParameter("num")) < 0
                        || Integer.parseInt(request.getParameter("num")) > nbPage) {
                    System.out.println("tentative");
                } else {
                    Page.PAGE.setIndex(Integer.parseInt(request.getParameter("num")));
                }
            }
            if (request.getParameter("action").equals("previous")) {
                Page.PAGE.previous();
            }
            if (request.getParameter("action").equals("change_max_obj")) {
                if (Integer.parseInt(request.getParameter("num")) != 10
                        && Integer.parseInt(request.getParameter("num")) != 50
                        && Integer.parseInt(request.getParameter("num")) != 100) {
                    System.out.println("tentative");

                } else {
                    Page.setMAXNUMBEROFOBJECTS(Integer.parseInt(request.getParameter("num")));
                    nbPage = cpS.pageNumber();
                    Page.PAGE.setIndex(0);
                }

            }
        }
        lcpdto = cpS.page();
        System.out.println(Page.PAGE.getIndex());
        System.out.println("post");
        indexPage = Page.PAGE.getIndex();
        count = cpS.count();
        request.setAttribute("count", count);
        request.setAttribute("index", indexPage);
        request.setAttribute("lcpdto", lcpdto);
        request.setAttribute("nbpage", nbPage);
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }

    /**
     */
    public void destroy() {
        // do nothing.
    }
}