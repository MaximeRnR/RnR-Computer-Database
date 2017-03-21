package com.excilys.formation.model;

import java.time.LocalDate;

//Model Computer
public class Computer {
    private long id;
    private String name;
    private LocalDate dIntroduced;
    private LocalDate dDiscontinued;
    private long manufacturer;

    // Constructor with id
    private Computer(long id, String name, LocalDate dIntroduced, LocalDate dDiscontinued, long manufacturer) {
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

    public LocalDate getdIntroduced() {
        return dIntroduced;
    }

    public void setdIntroduced(LocalDate dIntroduced) {
        this.dIntroduced = dIntroduced;
    }

    public LocalDate getdDiscontinued() {
        return dDiscontinued;
    }

    public void setdDiscontinued(LocalDate dDiscontinued) {
        this.dDiscontinued = dDiscontinued;
    }

    public long getManufacturer() {
        return manufacturer;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Computer other = (Computer) obj;
        if (dDiscontinued == null) {
            if (other.dDiscontinued != null)
                return false;
        } else if (!dDiscontinued.equals(other.dDiscontinued))
            return false;
        if (dIntroduced == null) {
            if (other.dIntroduced != null)
                return false;
        } else if (!dIntroduced.equals(other.dIntroduced))
            return false;
        if (id != other.id)
            return false;
        if (manufacturer != other.manufacturer)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public static class Builder {
        private long id;
        private String name;
        private LocalDate dIntroduced;
        private LocalDate dDiscontinued;
        private long manufacturer;

        public Builder() {

        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;

        }

        public Builder di(LocalDate di) {
            this.dIntroduced = di;
            return this;

        }

        public Builder dd(LocalDate dd) {
            this.dDiscontinued = dd;
            return this;

        }

        public Builder manufacturer(long manu) {
            this.manufacturer = manu;
            return this;

        }

        public Computer build() {
            return new Computer(this.id, this.name, this.dIntroduced, this.dDiscontinued, this.manufacturer);

        }
    }

}
