package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

// Import required java libraries

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
@WebServlet(name = "EditComputer", urlPatterns = { "/edit" })
public class EditComputerServlet extends HttpServlet {
    private ComputerService computerService;
    private CompanyService companyService;
    private List<CompanyDTO> companiesDto;
    private CompanyDTO company;
    private ComputerDTO computer;
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


    /**
     */
    private static final long serialVersionUID = 458516061330357823L;

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
        if (request.getParameter("id") != null) {
            long id = Integer.parseInt(request.getParameter("id"));
            //System.out.println(id);
            computer = computerService.findById(id);
            request.setAttribute("computer", computer);
        }
        request.setAttribute("companiesDto", companiesDto);
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
        String dI = request.getParameter("introduced");
        String dD = request.getParameter("discontinued");
        companiesDto = companyService.findAll();
        request.setAttribute("companiesDto", companiesDto);
        if (Integer.parseInt(request.getParameter("companyId")) == 0 || request.getParameter("companyId") == null) {
            company = new CompanyDTO(0);
        } else {
            company = new CompanyDTO(Integer.parseInt(request.getParameter("companyId")));
        }
        if (Integer.parseInt(sid) != 0 && sid != null) {
            if (name != null && name.matches("^[a-zA-Z0-9 -._]+$") && !name.isEmpty()) {
                if ((dI != null && dI.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) || dI == "") {
                    if ((dD != null && dD.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) || dD == "") {
                        computer = new ComputerDTO.Builder().id(Integer.parseInt(sid)).name(request.getParameter("name")).di(dI).dd(dD).cydto(company).build();
                        computerService.update(computer);
                        request.setAttribute("success", 1);
                        request.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
                    } else {
                        //System.out.println("Nope dd");
                        logger.error("EDITSERVLET dD");
                        request.setAttribute("error", 1);
                        request.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
                    }
                } else {
                    //System.out.println("Nope di");
                    logger.error("EDITSERVLET dI");
                    request.setAttribute("error", 1);
                    request.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
                }
            } else {
                //System.out.println("Nope name ");
                logger.error("EDITSERVLET name");
                request.setAttribute("error", 1);
                request.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
            }
        } else {
            //System.out.println("Nope id ");
            logger.error("EDITSERVLET iD " + sid);
            request.setAttribute("companiesDto", companiesDto);
            request.setAttribute("error", 1);
            request.getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
        }


    }
    /**
     */
    public void destroy() {
        // do nothing.
    }
}