package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Import required java libraries

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.ui.Page;
import com.excilys.formation.validator.ParameterValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Extend HttpServlet class
@Controller
@RequestMapping("/dashboard")
//@WebServlet(name = "DashboardServlet", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {

    @Autowired
    private ComputerService computerService;

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    /**
     */
    private static final long serialVersionUID = 458516061330357823L;

    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    /**
     * @throws ServletException serlvetexcp
     */
    public void init() throws ServletException {
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Parameters parameters = new Parameters(request, response);
        Attributes attributes = new Attributes();
        populateDefault(request);
        try {
            Map<String, String> errors = isValid(parameters, attributes);
            if (errors.size() == 0) {
                attributes.getPage().setIndex(Integer.parseInt(parameters.getPageNumber()) - 1);
                request.setAttribute("maxObj", parameters.getMaxObj());
                attributes.getPage().setMaxNumberOfObject(Integer.parseInt(parameters.getMaxObj()));
            } else {
                if (errors.size() == 1) {
                    if (errors.containsKey("pageNumber")) {
                        request.setAttribute("maxObj", parameters.getMaxObj());
                        attributes.getPage().setMaxNumberOfObject(Integer.parseInt(parameters.getMaxObj()));
                    }
                    if (errors.containsKey("maxObj")) {
                        attributes.getPage().setIndex(Integer.parseInt(parameters.getPageNumber()) - 1);
                    }
                }
            }
        } catch (NumberFormatException e) {
            logger.error("DashServlet NumberFormatException" + parameters.getPageNumber() + " or " + parameters.getMaxObj());
        }

        if (ParameterValidator.checkSearch(parameters.getSearch())
                && ParameterValidator.checkBy(parameters.getBy())) {
            process(parameters, request, response, attributes);

        } else {
            attributes.setNbComputers(computerService.getCountOfAllComputers());
            attributes.setComputersDto(computerService.getPageOfComputers(attributes.getPage()));
            attributes.setNbPages(computerService.getNumberOfPageOfAllComputers(attributes.getPage()));
        }
        attributes.setIndexPage(attributes.getPage().getIndex());
        populateRequest(request, attributes);
        request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    /**
     * @param parameters parameters
     * @param attributes attributes
     * @return map map
     */
    private Map<String, String> isValid(Parameters parameters, Attributes attributes) {
        Map<String, String> errors = new HashMap<>();
        if (ParameterValidator.checkPageNumber(parameters.getPageNumber(), attributes.getNbPages()) &&
                ParameterValidator.checkMaxObj(parameters.getMaxObj())) {
            return errors;
        }
        if (!ParameterValidator.checkPageNumber(parameters.getPageNumber(), attributes.getNbPages())) {
            errors.put("pageNumber", "false");
        }

        if (!ParameterValidator.checkMaxObj(parameters.getMaxObj())) {
            errors.put("maxObj", "false");
        }
        return errors;
    }

    /**
     * @param request request
     * @param response response
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("selection") != null && request.getParameter("selection").matches("^[0-9,]+$")) {
            String[] selection = request.getParameter("selection").split(",");
            List<Long> idTab = new ArrayList<Long>();
            for (int i = 0; i < selection.length; i++) {
                idTab.add(i, (long) Integer.parseInt(selection[i]));
            }
            computerService.delete(idTab);
        }

        this.doGet(request, response);
    }

    /**
     */
    public void destroy() {
        // do nothing.
    }


    /**
     * Do the stuff.
     * @param parameters parameters
     * @param request request
     * @param response response
     * @param attributes attributes
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    private void process(Parameters parameters, HttpServletRequest request, HttpServletResponse response, Attributes attributes) throws ServletException, IOException {

        if (parameters.getBy().equals("cp")) {
            attributes.setNbPages(computerService.getNumberOfPageOfComputersByName(parameters.getSearch(), attributes.getPage()));
            if (attributes.getNbPages() < attributes.getPage().getIndex()) {
                attributes.getPage().setIndex(0);
            }
            attributes.setNbComputers(computerService.getCountOfComputersByName(parameters.getSearch()));
            attributes.setComputersDto(computerService.getPageOfComputersByName(parameters.getSearch(), attributes.getPage()));
            request.setAttribute("search", parameters.getSearch());
            request.setAttribute("by", parameters.getBy());
        }
        if (parameters.getBy().equals("cy")) {
            attributes.setNbPages(computerService.getNumberOfPageOfComputersByCompanyName(parameters.getSearch(), attributes.getPage()));
            if (attributes.getNbPages() < attributes.getPage().getIndex()) {
                attributes.getPage().setIndex(0);
            }
            attributes.setNbComputers(computerService.getCountOfComputersByCompanyName(parameters.getSearch()));
            attributes.setComputersDto(computerService.getPageOfComputersByCompanyName(parameters.getSearch(), attributes.getPage()));
            request.setAttribute("search", parameters.getSearch());
            request.setAttribute("by", parameters.getBy());
        }
    }

    /**
     * @param request request
     * @param attributes attributes
     */
    private void populateRequest(HttpServletRequest request, Attributes attributes) {

        request.setAttribute("nbComputers", attributes.getNbComputers());
        request.setAttribute("index", attributes.getIndexPage());
        request.setAttribute("computersDto", attributes.getComputersDto());
        request.setAttribute("nbpage", attributes.getNbPages());
    }


    /**
     * @param request request
     */
    private void populateDefault(HttpServletRequest request) {

        request.setAttribute("search", "");
        request.setAttribute("by", "");
        request.setAttribute("maxObj", 10);
    }

    /**
     */
    private class Parameters {
        private String search;
        private String by;
        private String maxObj;
        private String pageNumber;

        /**
         * @param request request
         * @param response response
         */
        private Parameters(HttpServletRequest request, HttpServletResponse response) {

            search = request.getParameter("search");
            by = request.getParameter("by");
            maxObj = request.getParameter("maxObj");
            pageNumber = request.getParameter("page");
        }

        public String getSearch() {
            return search;
        }

        public String getBy() {
            return by;
        }

        public String getMaxObj() {
            return maxObj;
        }

        public String getPageNumber() {
            return pageNumber;
        }

        @Override
        public String toString() {
            return "Parameters [search=" + search + ", by=" + by + ", maxObj=" + maxObj + ", page=" + pageNumber + "]";
        }
    }

    /**
     */
    private class Attributes {
        int nbPages;
        int indexPage;
        int nbComputers;
        Page page;
        private List<ComputerDTO> computersDto;

        /**
         */
        private Attributes() {
            nbComputers = computerService.getCountOfAllComputers();
            page = new Page();
            nbPages = computerService.getNumberOfPageOfAllComputers(page);
            computersDto = computerService.getPageOfComputers(page);
        }

        public int getNbPages() {
            return nbPages;
        }

        public void setNbPages(int nbPages) {
            this.nbPages = nbPages;
        }

        public int getIndexPage() {
            return indexPage;
        }

        public void setIndexPage(int indexPage) {
            this.indexPage = indexPage;
        }

        public int getNbComputers() {
            return nbComputers;
        }

        public void setNbComputers(int nbComputers) {
            this.nbComputers = nbComputers;
        }

        public Page getPage() {
            return page;
        }

        public List<ComputerDTO> getComputersDto() {
            return computersDto;
        }

        public void setComputersDto(List<ComputerDTO> computersDto) {
            this.computersDto = computersDto;
        }
    }
}