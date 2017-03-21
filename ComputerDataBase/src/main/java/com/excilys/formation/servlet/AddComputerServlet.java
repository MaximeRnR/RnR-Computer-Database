package com.excilys.formation.servlet;
// Import required java libraries
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.ui.CompanyModelUI;
import com.excilys.formation.ui.ComputerModelUI;

// Extend HttpServlet class
@WebServlet(name = "AddComputerServlet", urlPatterns = {"/add"})
public class AddComputerServlet extends HttpServlet {



    /**
     * 
     */
    private static final long serialVersionUID = 6735463320851848975L;

    private ComputerService cpS;
    private CompanyService cyS;
    private List<CompanyModelUI> lcyui;
    private CompanyModelUI cy;
    private ComputerModelUI cp;

    public void init() throws ServletException
    {
        cpS = new ComputerService();
        cyS = new CompanyService();
        lcyui = cyS.findAll();


    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        request.setAttribute("lcyui", lcyui);
        request.getRequestDispatcher("/views/addComputer.jsp").
        forward(request,response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
    {
        System.out.println(request.getAttribute("dI"));
        System.out.println(request.getAttribute("dD"));
        System.out.println(request.getAttribute("companyId"));
        String name = request.getParameter("name");
        String dI = request.getParameter("dI");
        String dD = request.getParameter("dD");
        if(Integer.parseInt(request.getParameter("companyId"))==0 || request.getParameter("companyId") == null){
            cy = new CompanyModelUI(0);
        }else{
            cy = new CompanyModelUI(Integer.parseInt(request.getParameter("companyId")));
        }
        if(name != null && !name.isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate di;
            LocalDate dd;
            if(dI != null && !dI.isEmpty()){
                di = LocalDate.parse(dI, formatter);
            }else
                di = null;
            if(dD != null && !dD.isEmpty()){
                dd = LocalDate.parse(dD, formatter);
            }else
                dd = null;
            cp = new ComputerModelUI.Builder().name(request.getParameter("name")).di(di).dd(dd).cymui(cy).build();
            cpS.createComputer(cp);
            
            request.setAttribute("success", 1 );
            request.getRequestDispatcher("/views/addComputer.jsp").
            forward(request,response);
        }else{
            System.out.println("Nope");
            request.setAttribute("error", 1 );
            request.getRequestDispatcher("/views/addComputer.jsp").
            forward(request,response);
        }


    }

    public void destroy()
    {
        // do nothing.
    }
}