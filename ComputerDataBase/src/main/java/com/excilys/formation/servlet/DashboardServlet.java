package com.excilys.formation.servlet;
// Import required java libraries
import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.excilys.formation.service.ComputerService;
import com.excilys.formation.ui.ComputerModelUI;
import com.excilys.formation.ui.Page;

// Extend HttpServlet class
@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 458516061330357823L;

    
    private ComputerService cpS;
    private List<ComputerModelUI> lcpui;
    private int nbPage;
 
    public void init() throws ServletException
    {
        cpS = new ComputerService();
        nbPage = cpS.pageNumber();
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {   
        System.out.println(Page.PAGE.getIndex());
        System.out.println("get");
        lcpui = cpS.Page();
        request.setAttribute("lcpui", lcpui);
        request.setAttribute("nbpage", nbPage);
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
    }
    
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {   
        if(request.getParameter("action").equals("next")){
            Page.PAGE.next();
            lcpui = cpS.Page();
        }
        if(request.getParameter("action").equals("page")){

            Page.PAGE.setIndex(Integer.parseInt(request.getParameter("num")));
            lcpui = cpS.Page();
        }
        System.out.println(Page.PAGE.getIndex());
        System.out.println("post");
        request.setAttribute("lcpui", lcpui);
        request.setAttribute("nbpage", nbPage);
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
    }

    
    
    
    public void destroy()
    {
        // do nothing.
    }
}