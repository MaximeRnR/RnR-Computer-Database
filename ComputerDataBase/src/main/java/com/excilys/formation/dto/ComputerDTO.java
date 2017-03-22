package com.excilys.formation.dto;

import java.time.LocalDate;

//Model Computer
public class ComputerDTO {
    private long id;
    private String name;
    private LocalDate dIntroduced;
    private LocalDate dDiscontinued;
    private CompanyDTO cydto;

    // Constructor
    /**
     * @param id Id
     * @param name Name
     * @param dIntroduced Di
     * @param dDiscontinued DD
     * @param cydto Cydto
     */
    private ComputerDTO(long id, String name, LocalDate dIntroduced, LocalDate dDiscontinued, CompanyDTO cydto) {
        this.id = id;
        this.name = name;
        this.dIntroduced = dIntroduced;
        this.dDiscontinued = dDiscontinued;
        this.cydto = cydto;
    }

    @Override
    public String toString() {
        return "ComputerDTO [id=" + id + ", name=" + name + ", dIntroduced=" + dIntroduced + ", dDiscontinued="
                + dDiscontinued + ", cydto=" + cydto + "]";
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
     * @return CompanyDTO
     */
    public CompanyDTO getCydto() {
        return cydto;
    }

    /**
     * @param cydto CompanyDTO
     */
    public void setCydto(CompanyDTO cydto) {
        this.cydto = cydto;
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
        private CompanyDTO cydto;

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
         * @param cydto CompanyDTO
         * @return builder Builder
         */
        public Builder cydto(CompanyDTO cydto) {
            this.cydto = cydto;
            return this;

        }

        /**
         * @return ComputerDTO ComputerDTO
         */
        public ComputerDTO build() {
            return new ComputerDTO(this.id, this.name, this.dIntroduced, this.dDiscontinued, this.cydto);

        }
    }

}
