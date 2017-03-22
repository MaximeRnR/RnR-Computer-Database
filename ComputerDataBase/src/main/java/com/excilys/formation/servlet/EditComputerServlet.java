package com.excilys.formation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

// Import required java libraries

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Extend HttpServlet class
@WebServlet(name = "EditComputerServlet", urlPatterns = { "/edit" })
public class EditComputerServlet extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 458516061330357823L;

    /**
     * @throws ServletException serlvetexcp
     */
    public void init() throws ServletException {

    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/editComputer.jsp").forward(request, response);
    }

    /**
     */
    public void destroy() {
        // do nothing.
    }
}