package com.excilys.formation.controller.request;


import org.springframework.stereotype.Component;


/**
 */
@Component
public class DashboardRequest {
    private String search;
    private String by;
    private String page;
    private String maxObj;

    /**
     */
    private DashboardRequest() {
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

}