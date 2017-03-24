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
        cpS = ComputerService.COMPUTERSERVICE;
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

        System.out.println("get");
        if (request.getParameter("search") != null && request.getParameter("search") != "") {
            count = cpS.countLike(request.getParameter("search"));
            lcpdto = cpS.like(request.getParameter("search"));
            nbPage = cpS.pageNumberSearch(request.getParameter("search"));
            request.setAttribute("search", request.getParameter("search"));
        } else {
            count = cpS.count();
            lcpdto = cpS.page();
        }
        indexPage = Page.PAGE.getIndex();
        System.out.println(indexPage);
        request.setAttribute("count", count);
        request.setAttribute("index", indexPage);
        request.setAttribute("lcpdto", lcpdto);
        request.setAttribute("nbpage", nbPage);
        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("post");
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
                    if (request.getParameter("search") != null && request.getParameter("search") != "") {
                        nbPage = cpS.pageNumberSearch(request.getParameter("search"));
                    } else {
                        nbPage = cpS.pageNumber();
                    }
                    Page.PAGE.setIndex(0);
                }

            }
        }
        System.out.println(Page.PAGE.getIndex());
        indexPage = Page.PAGE.getIndex();
        if (request.getParameter("search") != null && request.getParameter("search") != "") {
            count = cpS.countLike(request.getParameter("search"));
            lcpdto = cpS.like(request.getParameter("search"));
            request.setAttribute("search", request.getParameter("search"));
        } else {
            count = cpS.count();
            lcpdto = cpS.page();
        }
        request.setAttribute("count", count);
        request.setAttribute("index", indexPage);
        request.setAttribute("lcpdto", lcpdto);
        request.setAttribute("nbpage", nbPage);
        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    /**
     */
    public void destroy() {
        // do nothing.
    }
}