package com.excilys.formation.servlet;

import java.io.IOException;
// Import required java libraries
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;

// Extend HttpServlet class
@WebServlet(name = "AddComputerServlet", urlPatterns = { "/add" })
public class AddComputerServlet extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 6735463320851848975L;

    private ComputerService cpS;
    private CompanyService cyS;
    private List<CompanyDTO> lcydto;
    private CompanyDTO cy;
    private ComputerDTO cp;

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
        request.setAttribute("lcydto", lcydto);
        request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String dI = request.getParameter("dI");
        System.out.println("DI vaut :" + dI);
        String dD = request.getParameter("dD");
        lcydto = cyS.findAll();
        request.setAttribute("lcydto", lcydto);
        if (Integer.parseInt(request.getParameter("companyId")) == 0 || request.getParameter("companyId") == null) {
            cy = new CompanyDTO(0);
        } else {
            cy = new CompanyDTO(Integer.parseInt(request.getParameter("companyId")));
        }
        if (name != null && !name.isEmpty() && name.matches("^[a-zA-Z ]+$")) {
            if ((dI != null && dI.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) || dI == "") {
                if ((dD != null && dD.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) || dD == "") {
                    cp = new ComputerDTO.Builder().name(request.getParameter("name")).di(dI).dd(dD).cydto(cy).build();
                    System.out.println(cp.toString());
                    cpS.createComputer(cp);
                    request.setAttribute("success", 1);
                    request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
                } else {
                    System.out.println("Nope");
                    request.setAttribute("error", 1);
                    request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
                }
            } else {
                System.out.println("Nope");
                request.setAttribute("error", 1);
                request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
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