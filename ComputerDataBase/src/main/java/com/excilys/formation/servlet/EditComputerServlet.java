package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

// Import required java libraries

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;

// Extend HttpServlet class
@WebServlet(name = "EditComputerServlet", urlPatterns = { "/edit" })
public class EditComputerServlet extends HttpServlet {
    private ComputerService cpS;
    private CompanyService cyS;
    private List<CompanyDTO> lcydto;
    private CompanyDTO cy;
    private ComputerDTO cp;

    /**
     */
    private static final long serialVersionUID = 458516061330357823L;

    /**
     * @throws ServletException serlvetexcp
     */
    public void init() throws ServletException {
        cpS = ComputerService.COMPUTERSERVICE;
        cyS = CompanyService.COMPANYSERVICE;
        lcydto = cyS.findAll();

    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            long id = Integer.parseInt(request.getParameter("id"));
            cp = cpS.find(id);
            request.setAttribute("cp", cp);
            }
        request.setAttribute("lcydto", lcydto);
        request.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("id");
        String name = request.getParameter("name");
        String dI = request.getParameter("dI");
        String dD = request.getParameter("dD");
        long id = 0;
        lcydto = cyS.findAll();
        request.setAttribute("lcydto", lcydto);
        if (Integer.parseInt(request.getParameter("companyId")) == 0 || request.getParameter("companyId") == null) {
            cy = new CompanyDTO(0);
        } else {
            cy = new CompanyDTO(Integer.parseInt(request.getParameter("companyId")));
        }
        if (Integer.parseInt(sid) == 0 || request.getParameter(sid) == null) {
            System.out.println("Nope");
            request.setAttribute("lcydto", lcydto);
            request.setAttribute("error", 1);
            request.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
        } else {
            id = Integer.parseInt(request.getParameter("id"));
        }
        if (name != null && !name.isEmpty() && name.matches("^[a-zA-Z ]+$")) {
            if (dI != null && !dI.isEmpty() && dI.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) {
                if (dD != null && !dD.isEmpty() && dI.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) {
                    cp = new ComputerDTO.Builder()
                            .id(id)
                            .name(request.getParameter("name"))
                            .di(dI)
                            .dd(dD)
                            .cydto(cy)
                            .build();
                    cpS.update(cp);
                    request.setAttribute("success", 1);
                    request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
                }
            }
        } else {
            System.out.println("Nope");
            request.setAttribute("error", 1);
            request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
        }



    }
    /**
     */
    public void destroy() {
        // do nothing.
    }
}