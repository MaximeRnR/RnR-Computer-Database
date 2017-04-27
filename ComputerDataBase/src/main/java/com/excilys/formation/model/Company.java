package com.excilys.formation.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "company")
public class Company {

    @Id
    private long id;

    private String name;

    /**
     * @param id Id
     * @param name Name
     */
    public Company(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @param id Id
     */
    public Company(long id) {
        this.id = id;
    }

    /**
     */
    public Company() {
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
