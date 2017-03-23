package com.excilys.formation.model;

import java.time.LocalDate;

//Model Computer
public class Computer {
    private long id;
    private String name;
    private LocalDate dIntroduced;
    private LocalDate dDiscontinued;
    private Company cy;

    // Constructor
    /**
     * @param id Id
     * @param name Name
     * @param dIntroduced Di
     * @param dDiscontinued DD
     * @param cy Cy
     */
    private Computer(long id, String name, LocalDate dIntroduced, LocalDate dDiscontinued, Company cy) {
        this.id = id;
        this.name = name;
        this.dIntroduced = dIntroduced;
        this.dDiscontinued = dDiscontinued;
        this.cy = cy;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", dIntroduced=" + dIntroduced + ", dDiscontinued="
                + dDiscontinued + ", cy=" + cy + "]";
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
        return dIntroduced;
    }

    /**
     * @param dIntroduced Date intro
     */
    public void setdIntroduced(LocalDate dIntroduced) {
        this.dIntroduced = dIntroduced;
    }

    /**
     * @return Date discontinued
     */
    public LocalDate getdDiscontinued() {
        return dDiscontinued;
    }

    /**
     * @param dDiscontinued Date discontinued
     */
    public void setdDiscontinued(LocalDate dDiscontinued) {
        this.dDiscontinued = dDiscontinued;
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
        private LocalDate dIntroduced;
        private LocalDate dDiscontinued;
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
            this.dIntroduced = di;
            return this;

        }

        /**
         * @param dd Date discontinued
         * @return builder Builder
         */
        public Builder dd(LocalDate dd) {
            this.dDiscontinued = dd;
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
            return new Computer(this.id, this.name, this.dIntroduced, this.dDiscontinued, this.cy);

        }
    }

}
