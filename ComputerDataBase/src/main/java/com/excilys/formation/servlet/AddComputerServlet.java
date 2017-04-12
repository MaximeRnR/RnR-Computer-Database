package com.excilys.formation.servlet;

import java.io.IOException;
// Import required java libraries
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;

// Extend HttpServlet class
@WebServlet(name = "AddComputer", urlPatterns = { "/add" })
public class AddComputerServlet extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 6735463320851848975L;
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    private ComputerService computerService;
    private CompanyService companyService;
    private List<CompanyDTO> companiesDto;
    private CompanyDTO company;
    private ComputerDTO computer;

    /**
     * @throws ServletException serlvetexcp
     */
    public void init() throws ServletException {
        computerService = ComputerService.COMPUTERSERVICE;
        companyService = CompanyService.COMPANYSERVICE;
        companiesDto = companyService.findAll();

    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("companiesDto", companiesDto);
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
        String dD = request.getParameter("dD");
        companiesDto = companyService.findAll();
        request.setAttribute("companiesDto", companiesDto);
        if (Integer.parseInt(request.getParameter("companyId")) == 0 || request.getParameter("companyId") == null) {
            company = new CompanyDTO(0);
        } else {
            company = new CompanyDTO(Integer.parseInt(request.getParameter("companyId")));
        }
        if (name != null && !name.isEmpty() && name.matches("^[a-zA-Z0-9 -._]+$")) {
            if ((dI != null && dI.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) || dI == "") {
                if ((dD != null && dD.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) || dD == "") {
                    computer = new ComputerDTO.Builder().name(request.getParameter("name")).di(dI).dd(dD).cydto(company).build();
                    computerService.create(computer);
                    request.setAttribute("success", 1);
                    request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
                } else {
                    //System.out.println("Nope");
                    logger.error("ADDSERVLET dD");
                    request.setAttribute("error", 1);
                    request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
                }
            } else {
                //System.out.println("Nope");
                logger.error("ADDSERVLET dI");
                request.setAttribute("error", 1);
                request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
            }
        } else {
            //System.out.println("Nope");
            logger.error("ADDSERVLET name " + name);
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