package com.excilys.formation.servlet;
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet(name = "EditComputerServlet", urlPatterns = {"/edit"})
public class EditComputerServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 458516061330357823L;


    public void init() throws ServletException
    {
        
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        request.getRequestDispatcher("/views/editComputer.jsp").
        forward(request,response);
    }

    public void destroy()
    {
        // do nothing.
    }
}