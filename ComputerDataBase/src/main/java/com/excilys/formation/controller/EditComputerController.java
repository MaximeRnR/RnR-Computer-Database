package com.excilys.formation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

// Import required java libraries

import javax.websocket.server.PathParam;

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

@Controller
@RequestMapping("/edit")
public class EditComputerController {

    @Autowired
    private ComputerService computerService;
    @Autowired
    private CompanyService companyService;


    private Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }


    /**
     * @param id id
     * @throws ServletException servletExc
     * @throws IOException ioexc
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doGet(@PathParam("id") long id) {
        ModelAndView model = new ModelAndView();
        model.setViewName("editComputer");

        if (id != 0) {
            ComputerDTO computerDTO = computerService.findById(id);
            model.addObject("computer", computerDTO);
            List<CompanyDTO> companiesDto = companyService.findAll();
            model.addObject("companiesDto", companiesDto);
        }

        return model;
    }

    /**
     * @param computerDTO ComputerDTO
     * @throws ServletException servletExc
     * @throws IOException ioexc
     * @return model model
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doPost(@ModelAttribute ComputerDTO computerDTO) throws ServletException, IOException {

        ModelAndView model = new ModelAndView();
        model.setViewName("editComputer");

        long id = computerDTO.getId();
        String name = computerDTO.getName();
        String dI = computerDTO.getDateIntroduced();
        String dD = computerDTO.getDateDiscontinued();
        long companyId = computerDTO.getCydtoId();
        List<CompanyDTO> companiesDto = companyService.findAll();
        String companyName = "";
        for (int i = 0; i < companiesDto.size(); i++) {
            if (companiesDto.get(i).getId() == companyId) {
                companyName = companiesDto.get(i).getName();
            }
        }
        CompanyDTO company;
        ComputerDTO computer;
        if (companyId == 0 && companyName != "") {
            company = new CompanyDTO(0);
        } else {
            company = new CompanyDTO(companyId, companyName);
        }
        if (id != 0) {
            if (name != null && name.matches("^[a-zA-Z0-9 -._]+$") && !name.isEmpty()) {
                if ((dI != null && dI.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) || dI == "") {
                    if ((dD != null && dD.matches("^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$")) || dD == "") {
                        computer = new ComputerDTO.Builder().id(id)
                                .name(name)
                                .di(dI)
                                .dd(dD)
                                .cydtoId(company.getId())
                                .cydtoName(company.getName())
                                .build();
                        computerService.update(computer);
                        model.addObject("success", 1);
                    } else {
                        //System.out.println("Nope dd");
                        logger.error("EDITSERVLET dD" + dD);
                        model.addObject("error", 1);
                    }
                } else {
                    //System.out.println("Nope di");
                    logger.error("EDITSERVLET dI" + dI);
                    model.addObject("error", 1);
                }
            } else {
                //System.out.println("Nope name ");
                logger.error("EDITSERVLET name" + name);
                model.addObject("error", 1);
            }
        } else {
            //System.out.println("Nope id ");
            logger.error("EDITSERVLET iD " + id);
            model.addObject("companiesDto", companiesDto);
            model.addObject("error", 1);
        }
        return model;

    }
    /**
     */
    public void destroy() {
        // do nothing.
    }
}