package com.excilys.formation.console.dto;

//Model company
public class CompanyDTO {
    private long id;
    private String name;

    /**
     * @param id Id
     * @param name Name
     */
    public CompanyDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @param id Id
     */
    public CompanyDTO(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Company [id=" + id + ", name=" + name + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
