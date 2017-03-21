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
    private int indexPage;
    private int count;
 
    public void init() throws ServletException
    {
        cpS = new ComputerService();
        nbPage = cpS.pageNumber();
        indexPage  = Page.PAGE.getIndex();
        count = cpS.count();
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {   
        System.out.println(Page.PAGE.getIndex());
        count = cpS.count();
        indexPage  = Page.PAGE.getIndex();
        System.out.println("get");
        lcpui = cpS.Page();
        request.setAttribute("count", count);
        request.setAttribute("index", indexPage);
        request.setAttribute("lcpui", lcpui);
        request.setAttribute("nbpage", nbPage);
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
    }
    
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {   
       if(request.getParameter("action")!=null){
        if(request.getParameter("action").equals("next")){
            Page.PAGE.next();
        }
        if(request.getParameter("action").equals("page")){
            nbPage = cpS.pageNumber();
            if(Integer.parseInt(request.getParameter("num"))<0 ||
                    Integer.parseInt(request.getParameter("num"))> nbPage){
                System.out.println("tentative");
            }else{
            Page.PAGE.setIndex(Integer.parseInt(request.getParameter("num")));
            }
        }
        if(request.getParameter("action").equals("previous")){
            Page.PAGE.previous();
        }
        if(request.getParameter("action").equals("change_max_obj")){
            if(Integer.parseInt(request.getParameter("num"))!= 10 &&
                    Integer.parseInt(request.getParameter("num"))!=50 &&
                            Integer.parseInt(request.getParameter("num"))!=100){
                System.out.println("tentative");
            
            }else{
                 Page.setMAX_NUMBER_OF_OBJECT(Integer.parseInt(request.getParameter("num")));
                 nbPage = cpS.pageNumber();
                 Page.PAGE.setIndex(0);
            }
            
        }
       }
        lcpui = cpS.Page();
        System.out.println(Page.PAGE.getIndex());
        System.out.println("post");
        indexPage  = Page.PAGE.getIndex();
        count = cpS.count();
        request.setAttribute("count", count);
        request.setAttribute("index", indexPage);
        request.setAttribute("lcpui", lcpui);
        request.setAttribute("nbpage", nbPage);
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request,response);
    }

    
    
    
    public void destroy()
    {
        // do nothing.
    }
}