package com.excilys.formation.model;

//Model company
public class Company {
    private long id;
    private String name;

    /**
     * Default.
     */
    public Company() {
    }

    /**
     * @param id Id
     * @param name Name
     */
    public Company(long id, String name) {
        this.id = id;
        this.name = name;
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
