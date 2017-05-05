package com.excilys.formation.controller.rest;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.ui.Page;

@RestController
@RequestMapping(value="/api")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    @RequestMapping(value="/computer/{id}", method=RequestMethod.GET)
    @ResponseBody
    public ComputerDTO getComputer(@PathVariable Long id){
        return computerService.findById(id);
    }

    //ALL


    @RequestMapping(value="/computers", method=RequestMethod.GET)
    @ResponseBody
    public List<ComputerDTO> getPageOfComputers(@RequestParam int page){
        Page pageOfComputer= new Page();
        pageOfComputer.setIndex(page);
        return computerService.getPageOfComputers(pageOfComputer);
    }

    @RequestMapping(value="/computers/count", method=RequestMethod.GET)
    @ResponseBody
    public int getCountOfAllComputers(){
        return computerService.getCountOfAllComputers();
    }

    @RequestMapping(value="/computers/pages/count", method=RequestMethod.GET)
    @ResponseBody
    public int getNumberOfPageOfAllComputers() {
        Page pageOfComputer= new Page();
        return computerService.getNumberOfPageOfAllComputers(pageOfComputer);
    }


    //BY NAME

    @RequestMapping(value="/computers/search/computer", method=RequestMethod.GET)
    @ResponseBody
    public List<ComputerDTO> getPageOfComputersByName(@RequestParam String search, @RequestParam int page) {
        Page pageOfComputer= new Page();
        pageOfComputer.setIndex(page);
        return computerService.getPageOfComputersByName(search, pageOfComputer);

    }

    @RequestMapping(value="/computers/search/computer/count", method=RequestMethod.GET)
    @ResponseBody
    public int getCountOfComputersByName(@RequestParam String search) {
        return computerService.getCountOfComputersByName(search);
    }


    @RequestMapping(value="/computers/search/computer/pages/count", method=RequestMethod.GET)
    @ResponseBody
    public int getNumberOfPageOfComputersByName(@RequestParam String search) {
        Page pageOfComputer= new Page();
        return computerService.getNumberOfPageOfComputersByName(search, pageOfComputer);
    }


    //BY COMPANY NAME
    

    @RequestMapping(value="/computers/search/company", method=RequestMethod.GET)
    @ResponseBody
    public List<ComputerDTO> getPageOfComputersByCompanyName(@RequestParam String search, @RequestParam int page) {
        Page pageOfComputer= new Page();
        pageOfComputer.setIndex(page);
        return computerService.getPageOfComputersByCompanyName(search, pageOfComputer);

    }

    @RequestMapping(value="/computers/search/company/count", method=RequestMethod.GET)
    @ResponseBody
    public int getCountOfComputersByCompanyName(@RequestParam String search) {
        return computerService.getCountOfComputersByCompanyName(search);
    }


    @RequestMapping(value="/computers/search/company/pages/count", method=RequestMethod.GET)
    @ResponseBody
    public int getNumberOfPageOfComputersByCompanyName(@RequestParam String search) {
        Page pageOfComputer= new Page();
        return computerService.getNumberOfPageOfComputersByCompanyName(search, pageOfComputer);
    }

    // CREATE 
    
    @RequestMapping(value="/computer/create", method=RequestMethod.POST)
    @ResponseBody
    public long create(@RequestBody ComputerDTO computerDto) {
       return computerService.create(computerDto);
    }
    
    //DELETE
    
    @RequestMapping(value="/computer/delete/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void create(@PathVariable long id) {
       
       List<Long> ids = new ArrayList<>();
       ids.add(id);
       computerService.delete(ids);
    }
    
    //UPDATE
    
    @RequestMapping(value="/computer/update", method=RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody ComputerDTO computerDto) {
       computerService.update(computerDto);
    }
    
    

}
