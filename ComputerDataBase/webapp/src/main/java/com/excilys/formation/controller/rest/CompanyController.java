package com.excilys.formation.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.service.CompanyService;

@RestController
@RequestMapping(value="/api")
public class CompanyController {
    
    //ALL
    
    @Autowired
    private CompanyService companyService;

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }


    @RequestMapping(value="/companies", method=RequestMethod.GET)
    @ResponseBody
    public List<CompanyDTO> getAllCompanies(){
        return companyService.findAll();
    }

    @RequestMapping(value="/companies/count", method=RequestMethod.GET)
    @ResponseBody
    public int getCountOfAllCompany(){
        return companyService.findAll().size();
    }
    
    
    @RequestMapping(value="/company/{id}", method=RequestMethod.GET)
    @ResponseBody
    public CompanyDTO getCompany(@PathVariable Long id){
        return companyService.findById(id);
    }
    
    
}
