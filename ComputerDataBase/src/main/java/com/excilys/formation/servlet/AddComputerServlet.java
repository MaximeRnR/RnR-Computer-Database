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
import com.excilys.formation.validator.ParameterValidator;

// Extend HttpServlet class
@WebServlet(name = "AddComputerServlet", urlPatterns = { "/add" })
public class AddComputerServlet extends HttpServlet {

    /**
     */
    private static final long serialVersionUID = 6735463320851848975L;
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    private ComputerDTO computer;


    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        populateCompanies(request);
        request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        populateCompanies(request);
        Parameters parameters = new Parameters(request, response);
        if (isValid(parameters)) {
            process(parameters, request, response);
        } else {
            error(parameters, request, response);
        }

    }

    /**
     */
    public void destroy() {
        // do nothing.
    }



    /**
     * @param parameters parameters
     * @return boolean  true/false
     */
    private boolean isValid(Parameters parameters) {

        return ParameterValidator.checkName(parameters.getName())
                && ParameterValidator.checkDateIntroduced(parameters.getDateIntroduced())
                && ParameterValidator.checkDateDiscontinued(parameters.getDateDiscontinued());
    }

    /**
     * Do the stuff.
     * @param parameters parameters
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    private void process(Parameters parameters, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        computer = new ComputerDTO.Builder()
                .name(parameters.getName())
                .di(parameters.getDateIntroduced())
                .dd(parameters.getDateDiscontinued())
                .cydto(parameters.getCompanyDTO())
                .build();
        ComputerService.INSTANCE.create(computer);
        request.setAttribute("success", 1);
        request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp")
            .forward(request, response);

    }

    /**
     * @param parameters parameters
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    private void error(Parameters parameters, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.error("AddServlet : " + parameters.toString());
        request.setAttribute("error", 1);
        request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);

    }

    /**
     * @param request request
     */
    private void populateCompanies(HttpServletRequest request) {
        List<CompanyDTO> companiesDto = CompanyService.INSTANCE.findAll();
        request.setAttribute("companiesDto", companiesDto);

    }

    private class Parameters {
        private String name;
        private String dateIntroduced;
        private String dateDiscontinued;
        private CompanyDTO companyDto;

        /**
         * @param request request
         * @param response response
         */
        private Parameters(HttpServletRequest request, HttpServletResponse response) {

            name = request.getParameter("name");
            dateIntroduced = request.getParameter("dI");
            dateDiscontinued = request.getParameter("dD");
            if (Integer.parseInt(request.getParameter("companyId")) == 0 || request.getParameter("companyId") == null) {
                companyDto = new CompanyDTO(0);
            } else {
                companyDto = new CompanyDTO(Integer.parseInt(request.getParameter("companyId")));
            }


        }

        public String getName() {
            return name;
        }

        public String getDateIntroduced() {
            return dateIntroduced;
        }

        public String getDateDiscontinued() {
            return dateDiscontinued;
        }

        public CompanyDTO getCompanyDTO() {
            return companyDto;
        }

        @Override
        public String toString() {
            return "Parameters [name=" + name + ", dateIntroduced=" + dateIntroduced + ", dateDiscontinued="
                    + dateDiscontinued + ", companyDto=" + companyDto + "]";
        }


    }
}