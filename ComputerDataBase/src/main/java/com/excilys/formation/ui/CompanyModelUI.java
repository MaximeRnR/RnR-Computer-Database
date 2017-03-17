package com.excilys.formation.ui;

//Model company
public class CompanyModelUI {
    private long id;
    private String name;

    public CompanyModelUI(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyModelUI(long id) {
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
