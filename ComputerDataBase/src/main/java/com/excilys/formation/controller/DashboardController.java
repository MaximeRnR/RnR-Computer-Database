package com.excilys.formation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Import required java libraries

import java.util.Map;

import javax.servlet.ServletException;

import com.excilys.formation.controller.request.DashboardRequest;
import com.excilys.formation.controller.response.DashboardResponse;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.validator.ParameterValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// Extend HttpServlet class
@Controller("DashboardServlet")
@RequestMapping("/dashboard")
public class DashboardController {



    @Autowired
    private DashboardResponse dashboardResponse;

    public void setDashboardResponse(DashboardResponse dashboardResponse) {
        this.dashboardResponse = dashboardResponse;
    }

    @Autowired
    private ComputerService computerService;

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


    /**
     * @param dashboardRequest DashboardRequest
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doGet(@ModelAttribute DashboardRequest dashboardRequest)  {

        ModelAndView model = new ModelAndView();
        model.setViewName("dashboard");


        try {
            Map<String, String> errors = isValid(dashboardRequest, dashboardResponse);

            if (errors.size() == 2) {
                dashboardRequest.setPage("1");
                dashboardRequest.setMaxObj("10");
            }
            if (errors.size() == 1) {
                if (errors.containsKey("pageNumber")) {
                    dashboardRequest.setPage("1");
                }
                if (errors.containsKey("maxObj")) {
                    dashboardRequest.setMaxObj("10");
                }
            }
            dashboardRequest.setPage((Integer.parseInt(dashboardRequest.getPage()) - 1) + "");
            dashboardResponse.populateDefaultResponse(dashboardRequest);
        } catch (NumberFormatException e) {
            logger.error("DashServlet NumberFormatException" + dashboardRequest.getPage() + " or " + dashboardRequest.getMaxObj());
        }

        if (ParameterValidator.checkSearch(dashboardRequest.getSearch())
                && ParameterValidator.checkBy(dashboardRequest.getBy())) {
            process(dashboardRequest, dashboardResponse);

        } else {
            dashboardResponse.populateDefaultResponse(dashboardRequest);

        }
        model.addObject("model", dashboardResponse);
        return model;
    }

    /**
     * @param dashboardRequest DashboardRequest
     * @param dashboardResponse DashboardResponse
     * @return map map
     */
    private Map<String, String> isValid(DashboardRequest dashboardRequest, DashboardResponse dashboardResponse) {
        Map<String, String> errors = new HashMap<>();
        if (ParameterValidator.checkPageNumber(dashboardRequest.getPage(), dashboardResponse.getNbPages()) &&
                ParameterValidator.checkMaxObj(dashboardRequest.getMaxObj())) {
            return errors;
        }
        if (!ParameterValidator.checkPageNumber(dashboardRequest.getPage(), dashboardResponse.getNbPages())) {
            errors.put("pageNumber", "false");
        }

        if (!ParameterValidator.checkMaxObj(dashboardRequest.getMaxObj())) {
            errors.put("maxObj", "false");
        }
        return errors;
    }


    /**
     * @param dashboardRequest DashboardRequest
     * @param selection String
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doPost(@ModelAttribute DashboardRequest dashboardRequest, @RequestParam("selection") String selection) {

        if (selection != null && selection.matches("^[0-9,]+$")) {

            String[] ids = selection.split(",");
            List<Long> idTab = new ArrayList<Long>();
            for (int i = 0; i < ids.length; i++) {
                idTab.add(i, (long) Integer.parseInt(ids[i]));
            }
            computerService.delete(idTab);
        }
        return new ModelAndView("redirect:/dashboard");
    }


    /**
     */
    public void destroy() {
        // do nothing.
    }


    /**
     * Do the stuff.
     * @param dashboardResponse DashboardResponse
     * @param dashboardRequest DashboardRequest
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    private void process(DashboardRequest dashboardRequest, DashboardResponse dashboardResponse)  {

        if (dashboardRequest.getBy().equals("cp")) {
            dashboardResponse.getNbPagesByName(dashboardRequest);
            if (dashboardResponse.getNbPages() < dashboardResponse.getPageComputer().getIndex()) {
                dashboardResponse.getPageComputer().setIndex(0);
            }
            dashboardResponse.populateByNameResponse(dashboardRequest);
        }
        if (dashboardRequest.getBy().equals("cy")) {
            dashboardResponse.getNbPagesByCompanyName(dashboardRequest);
            if (dashboardResponse.getNbPages() < dashboardResponse.getPageComputer().getIndex()) {
                dashboardResponse.getPageComputer().setIndex(0);
            }
            dashboardResponse.populateByCompanyNameResponse(dashboardRequest);
        }
    }
}