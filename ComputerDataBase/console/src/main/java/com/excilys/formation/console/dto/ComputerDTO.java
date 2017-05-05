package com.excilys.formation.console.dto;

import javax.ws.rs.Produces;



public class ComputerDTO {
    private long id;
    private String name;
    private String dateIntroduced;
    private String dateDiscontinued;
    private long cydtoId;
    private String cydtoName;

    // Constructor
    /**
     * @param id Id
     * @param name Name
     * @param dateIntroduced Di
     * @param dateDiscontinued DD
     * @param cydtoId CydtoId
     * @param cydtoName cydtoName
     */
    public ComputerDTO(long id, String name, String dateIntroduced, String dateDiscontinued, Long cydtoId, String cydtoName) {
        this.id = id;
        this.name = name;
        this.dateIntroduced = dateIntroduced;
        this.dateDiscontinued = dateDiscontinued;
        this.cydtoId = cydtoId;
        this.cydtoName = cydtoName;
    }
    /**
     * Default constructor.
     */
    public ComputerDTO() {

    }

    @Override
    public String toString() {
        return "ComputerDTO [id=" + id + ", name=" + name + ", dateIntroduced=" + dateIntroduced + ", dateDiscontinued="
                + dateDiscontinued + ", cydtoId=" + cydtoId + ", cydtoName=" + cydtoName + "]";
    }
    public String getCydtoName() {
        return cydtoName;
    }
    public void setCydtoName(String cydtoName) {
        this.cydtoName = cydtoName;
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
    public String getDateIntroduced() {
        return dateIntroduced;
    }

    /**
     * @param dIntroduced Date intro
     */
    public void setDateIntroduced(String dIntroduced) {
        this.dateIntroduced = dIntroduced;
    }

    /**
     * @return Date discontinued
     */
    public String getDateDiscontinued() {
        return dateDiscontinued;
    }

    /**
     * @param dDiscontinued Date discontinued
     */
    public void setDateDiscontinued(String dDiscontinued) {
        this.dateDiscontinued = dDiscontinued;
    }

    /**
     * @return CompanyDTO
     */
    public long getCydtoId() {
        return cydtoId;
    }

    /**
     * @param cydto CompanyDTO
     */
    public void setCydtoId(long cydto) {
        this.cydtoId = cydto;
    }

    /**
     * @author excilys
     *
     */
    public static class Builder {
        private long id;
        private String name;
        private String dateIntroduced;
        private String dateDiscontinued;
        private long cydto;
        private String cydtoName;

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
        public Builder di(String di) {
            this.dateIntroduced = di;
            return this;

        }

        /**
         * @param dd Date discontinued
         * @return builder Builder
         */
        public Builder dd(String dd) {
            this.dateDiscontinued = dd;
            return this;

        }

        /**
         * @param cydto CompanyDTO
         * @return builder Builder
         */
        public Builder cydtoId(long cydto) {
            this.cydto = cydto;
            return this;

        }

        /**
         * @param cydtoName cydtoname
         * @return builder Builder
         */
        public Builder cydtoName(String cydtoName) {
            this.cydtoName = cydtoName;
            return this;

        }

        /**
         * @return ComputerDTO ComputerDTO
         */
        public ComputerDTO build() {
            return new ComputerDTO(this.id, this.name, this.dateIntroduced, this.dateDiscontinued, this.cydto, this.cydtoName);

        }
    }

}
