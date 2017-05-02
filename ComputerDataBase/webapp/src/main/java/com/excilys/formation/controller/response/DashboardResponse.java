package com.excilys.formation.controller.response;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.controller.request.DashboardRequest;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.ui.Page;

/**
 */
@Component
public class DashboardResponse {
    private int nbPages;
    private int index;
    private int nbComputers;
    private String search;
    private String by;
    private String page;
    private String maxObj;
    private List<ComputerDTO> computersDto;
    private Page pageComputer;

    @Autowired
    private ComputerService computerService;

    public void setComputerService(ComputerService computerService) {
        this.computerService = computerService;
    }

    /**
     */
    private DashboardResponse() {
        pageComputer = new Page();

    }

    /**
     * @param dashboardRequest DashboardRequest
     */
    public void populateDefaultResponse(DashboardRequest dashboardRequest) {


        pageComputer.setIndex(Integer.parseInt(dashboardRequest.getPage()));
        pageComputer.setMaxNumberOfObject(Integer.parseInt(dashboardRequest.getMaxObj()));
        nbPages = computerService.getNumberOfPageOfAllComputers(pageComputer);
        computersDto = computerService.getPageOfComputers(pageComputer);
        nbComputers = computerService.getCountOfAllComputers();
        index = pageComputer.getIndex();
        search = dashboardRequest.getSearch();
        by = dashboardRequest.getBy();
        page = dashboardRequest.getPage();
        maxObj = dashboardRequest.getMaxObj();
    }

    /**
     * @param dashboardRequest DashboardRequest
     */
    public void populateByNameResponse(DashboardRequest dashboardRequest) {


        pageComputer.setIndex(Integer.parseInt(dashboardRequest.getPage()));
        pageComputer.setMaxNumberOfObject(Integer.parseInt(dashboardRequest.getMaxObj()));
        nbPages = computerService.getNumberOfPageOfComputersByName(dashboardRequest.getSearch(), pageComputer);
        computersDto = computerService.getPageOfComputersByName(dashboardRequest.getSearch(), pageComputer);
        nbComputers = computerService.getCountOfComputersByName(dashboardRequest.getSearch());
        index = pageComputer.getIndex();
        search = dashboardRequest.getSearch();
        by = dashboardRequest.getBy();
        page = dashboardRequest.getPage();
        maxObj = dashboardRequest.getMaxObj();
    }

    /**
     * @param dashboardRequest DashboardRequest
     */
    public void populateByCompanyNameResponse(DashboardRequest dashboardRequest) {


        pageComputer.setIndex(Integer.parseInt(dashboardRequest.getPage()));
        pageComputer.setMaxNumberOfObject(Integer.parseInt(dashboardRequest.getMaxObj()));
        nbPages = computerService.getNumberOfPageOfComputersByCompanyName(dashboardRequest.getSearch(), pageComputer);
        computersDto = computerService.getPageOfComputersByCompanyName(dashboardRequest.getSearch(), pageComputer);
        nbComputers = computerService.getCountOfComputersByCompanyName(dashboardRequest.getSearch());
        index = pageComputer.getIndex();
        search = dashboardRequest.getSearch();
        by = dashboardRequest.getBy();
        page = dashboardRequest.getPage();
        maxObj = dashboardRequest.getMaxObj();
    }

    /**
     * @param dashboardRequest DashboardRequest
     */
    public void getNbPagesByName(DashboardRequest dashboardRequest) {


        nbPages = computerService.getNumberOfPageOfComputersByName(dashboardRequest.getSearch(), pageComputer);

    }

    /**
     * @param dashboardRequest DashboardRequest
     */
    public void getNbPagesByCompanyName(DashboardRequest dashboardRequest) {


        nbPages = computerService.getNumberOfPageOfComputersByCompanyName(dashboardRequest.getSearch(), pageComputer);

    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getNbComputers() {
        return nbComputers;
    }

    public void setNbComputers(int nbComputers) {
        this.nbComputers = nbComputers;
    }

    public Page getPageComputer() {
        return pageComputer;
    }

    public List<ComputerDTO> getComputersDto() {
        return computersDto;
    }

    public void setComputersDto(List<ComputerDTO> computersDto) {
        this.computersDto = computersDto;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getMaxObj() {
        return maxObj;
    }

    public void setMaxObj(String maxObj) {
        this.maxObj = maxObj;
    }

    public ComputerService getComputerService() {
        return computerService;
    }

    public void setPageComputer(Page pageComputer) {
        this.pageComputer = pageComputer;
    }
}