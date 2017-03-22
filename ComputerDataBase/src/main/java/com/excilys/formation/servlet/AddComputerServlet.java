package com.excilys.formation.servlet;

import java.io.IOException;
// Import required java libraries
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        cpS = new ComputerService();
        cyS = new CompanyService();
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
        request.getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
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
        String dD = request.getParameter("dD");
        lcydto = cyS.findAll();
        request.setAttribute("lcydto", lcydto);
        if (Integer.parseInt(request.getParameter("companyId")) == 0 || request.getParameter("companyId") == null) {
            cy = new CompanyDTO(0);
        } else {
            cy = new CompanyDTO(Integer.parseInt(request.getParameter("companyId")));
        }
        if (name != null && !name.isEmpty() && name.matches("^[a-zA-Z ]+$")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate di = null;
            LocalDate dd = null;
            if (dI != null && !dI.isEmpty() && dI.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) {
                try {
                    di = LocalDate.parse(dI, formatter);
                    if (di.isAfter(LocalDate.now()) || di.getYear() > 2025) {
                        System.out.println("Nope");
                        request.setAttribute("lcydto", lcydto);
                        request.setAttribute("error", 1);
                        request.getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Nope");
                    request.setAttribute("lcydto", lcydto);
                    request.setAttribute("error", 1);
                    request.getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
                }
            }
            if (dD != null && !dD.isEmpty() && dI.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) {
                try {
                    dd = LocalDate.parse(dI, formatter);
                    if (dd.isBefore(di) || dd.getYear() > 2025) {
                        System.out.println("Nope");
                        request.setAttribute("lcydto", lcydto);
                        request.setAttribute("error", 1);
                        request.getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Nope");
                    request.setAttribute("lcydto", lcydto);
                    request.setAttribute("error", 1);
                    request.getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
                }
            }
            cp = new ComputerDTO.Builder().name(request.getParameter("name")).di(di).dd(dd).cydto(cy).build();
            cpS.createComputer(cp);
            request.setAttribute("success", 1);
            request.getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
        } else {
            System.out.println("Nope");
            request.setAttribute("error", 1);
            request.getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
        }


    }

    /**
     */
    public void destroy() {
        // do nothing.
    }
}