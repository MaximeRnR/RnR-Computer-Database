package com.excilys.formation.controller;

import java.io.IOException;
// Import required java libraries
import java.util.List;

import javax.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.validator.ParameterValidator;

@Controller
@RequestMapping("/add")
public class AddComputerController {

    @Autowired
    private ComputerService computerService;
    @Autowired
    private CompanyService companyService;

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     */
    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


    /**
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doGet() {
        ModelAndView model = new ModelAndView();
        model.setViewName("addComputer");
        populateCompanies(model);
        return model;
    }

    /**
     * @param computerDTO ComputerDTO
     * @throws ServletException servletExc
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doPost(@ModelAttribute ComputerDTO computerDTO) {

        ModelAndView model = new ModelAndView();
        model.setViewName("addComputer");
        populateCompanies(model);
        if (isValid(computerDTO)) {
            process(computerDTO, model);
        } else {
            error(computerDTO, model);
        }
        return model;

    }

    /**
     */
    public void destroy() {
        // do nothing.
    }



    /**
     * @param computerDTO {@link ComputerDTO}
     * @return boolean  true/false
     */
    private boolean isValid(ComputerDTO computerDTO) {

        return ParameterValidator.checkName(computerDTO.getName())
                && ParameterValidator.checkDateIntroduced(computerDTO.getDateIntroduced())
                && ParameterValidator.checkDateDiscontinued(computerDTO.getDateDiscontinued());
    }

    /**
     * Do the stuff.
     * @param computerDTO {@link ComputerDTO}
     * @param model model
     */
    private void process(ComputerDTO computerDTO, ModelAndView model) {

        computerService.create(computerDTO);
        model.addObject("success", 1);

    }

    /**
     * @param computerDTO {@link ComputerDTO}
     * @param model model
     */
    private void error(ComputerDTO computerDTO, ModelAndView model) {

        logger.error("AddServlet : " + computerDTO.toString());
        model.addObject("error", 1);

    }

    /**
     * @param model model
     */
    private void populateCompanies(ModelAndView model) {
        List<CompanyDTO> companiesDto = companyService.findAll();
        model.addObject("companiesDto", companiesDto);

    }
}