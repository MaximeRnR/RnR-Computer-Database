package com.excilys.formation.servlet;
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet(name = "AddComputerServlet", urlPatterns = {"/add"})
public class AddComputerServlet extends HttpServlet {



    /**
     * 
     */
    private static final long serialVersionUID = 6735463320851848975L;

    public void init() throws ServletException
    {
        
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        request.getRequestDispatcher("/views/addComputer.jsp").
        forward(request,response);
    }

    public void destroy()
    {
        // do nothing.
    }
}