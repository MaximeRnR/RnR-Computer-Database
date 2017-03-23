package com.excilys.formation.entity;

import java.time.LocalDate;


//Model Computer
public class ComputerEntity {
    private long id;
    private String name;
    private LocalDate dIntroduced;
    private LocalDate dDiscontinued;
    private long manufacturer;

    // Constructor with id
    /**
     * @param id Id
     * @param name Name
     * @param dIntroduced dI
     * @param dDiscontinued dD
     * @param manufacturer CompanyId
     */
    private ComputerEntity(long id, String name, LocalDate dIntroduced, LocalDate dDiscontinued, long manufacturer) {
        this.id = id;
        this.name = name;
        this.dIntroduced = dIntroduced;
        this.dDiscontinued = dDiscontinued;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", dIntroduced=" + dIntroduced + ", dDiscontinued="
                + dDiscontinued + ", manufacturer=" + manufacturer + "]";
    }

    /**
     * @return id Id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return name
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
     * @return dIntroduced Date Intro
     */
    public LocalDate getdIntroduced() {
        return dIntroduced;
    }

    /**
     * @param dIntroduced Date Intro
     */
    public void setdIntroduced(LocalDate dIntroduced) {
        this.dIntroduced = dIntroduced;
    }

    /**
     * @return dDiscontinued Date discontinued
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
     * @return manufacturer CompanyID
     */
    public long getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer CompanyID
     */
    public void setManufacturer(long manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dDiscontinued == null) ? 0 : dDiscontinued.hashCode());
        result = prime * result + ((dIntroduced == null) ? 0 : dIntroduced.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + (int) (manufacturer ^ (manufacturer >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ComputerEntity other = (ComputerEntity) obj;
        if (dDiscontinued == null) {
            if (other.dDiscontinued != null) {
                return false;
            }
        } else if (!dDiscontinued.equals(other.dDiscontinued)) {
            return false;
        }
        if (dIntroduced == null) {
            if (other.dIntroduced != null) {
                return false;
            }
        } else if (!dIntroduced.equals(other.dIntroduced)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (manufacturer != other.manufacturer) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
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
        private long manufacturer;

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
        * @param di Date Intro
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
        * @param manu COmpanyID
        * @return builder Builder
        */

        public Builder manufacturer(long manu) {
            this.manufacturer = manu;
            return this;

        }

        /**
         * @return Computer Computer
         */
        public ComputerEntity build() {
            return new ComputerEntity(this.id, this.name, this.dIntroduced, this.dDiscontinued, this.manufacturer);

        }
    }

}
