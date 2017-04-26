package com.excilys.formation.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

//Model Computer
@Entity(name = "computer")
public class Computer {
    @Id
    private long id;

    private String name;

    @Type(type = "LocalDateType")
    @Column(name = "`introduced`")
    private LocalDate dateIntroduced;

    @Type(type = "LocalDateType")
    @Column(name = "`discontinued`")
    private LocalDate dateDiscontinued;

    @ManyToOne()
    private Company cy;

    // Constructor
    /**
     * @param id Id
     * @param name Name
     * @param dateIntroduced Di
     * @param dateDiscontinued DD
     * @param cy Cy
     */
    private Computer(long id, String name, LocalDate dateIntroduced, LocalDate dateDiscontinued, Company cy) {
        this.id = id;
        this.name = name;
        this.dateIntroduced = dateIntroduced;
        this.dateDiscontinued = dateDiscontinued;
        this.cy = cy;
    }

    /**
     * DEfault Constructor.
     */
    public Computer() {
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", dateIntroduced=" + dateIntroduced + ", dateDiscontinued="
                + dateDiscontinued + ", cy=" + cy + "]";
    }

    /**
     * @return Id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id Id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Date intro
     */
    public LocalDate getdIntroduced() {
        return dateIntroduced;
    }

    /**
     * @param dateIntroduced Date intro
     */
    public void setdIntroduced(LocalDate dateIntroduced) {
        this.dateIntroduced = dateIntroduced;
    }

    /**
     * @return Date discontinued
     */
    public LocalDate getdDiscontinued() {
        return dateDiscontinued;
    }

    /**
     * @param dateDiscontinued Date discontinued
     */
    public void setdDiscontinued(LocalDate dateDiscontinued) {
        this.dateDiscontinued = dateDiscontinued;
    }

    /**
     * @return Company
     */
    public Company getCy() {
        return cy;
    }

    /**
     * @param cy Company
     */
    public void setCy(Company cy) {
        this.cy = cy;
    }

    /**
     * @author excilys
     *
     */
    public static class Builder {
        private long id;
        private String name;
        private LocalDate dateIntroduced;
        private LocalDate dateDiscontinued;
        private Company cy;

        /**
         *
         */
        public Builder() {

        }

        /**
         * @param id ID
         * @return builder Builder
         */
        public Builder id(long id) {
            this.id = id;
            return this;
        }

        /**
         * @param name Name
         * @return builder Builder
         */
        public Builder name(String name) {
            this.name = name;
            return this;

        }
        /**
         * @param di Date intro
         * @return builder Builder
         */
        public Builder di(LocalDate di) {
            this.dateIntroduced = di;
            return this;

        }

        /**
         * @param dd Date discontinued
         * @return builder Builder
         */
        public Builder dd(LocalDate dd) {
            this.dateDiscontinued = dd;
            return this;

        }

        /**
         * @param cy Company
         * @return builder Builder
         */
        public Builder cy(Company cy) {
            this.cy = cy;
            return this;

        }

        /**
         * @return Computer Computer
         */
        public Computer build() {
            return new Computer(this.id, this.name, this.dateIntroduced, this.dateDiscontinued, this.cy);

        }
    }

}
