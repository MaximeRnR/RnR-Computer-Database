package com.excilys.formation.ui;

import java.time.LocalDate;

//Model Computer
public class ComputerModelUI {
    private long id;
    private String name;
    private LocalDate dIntroduced;
    private LocalDate dDiscontinued;
    private CompanyModelUI cymui;

    // Constructor
    private ComputerModelUI(long id, String name, LocalDate dIntroduced, LocalDate dDiscontinued,
            CompanyModelUI cymui) {
        this.id = id;
        this.name = name;
        this.dIntroduced = dIntroduced;
        this.dDiscontinued = dDiscontinued;
        this.cymui = cymui;
    }

    @Override
    public String toString() {
        return "ComputerModelUI [id=" + id + ", name=" + name + ", dIntroduced=" + dIntroduced + ", dDiscontinued="
                + dDiscontinued + ", cymui=" + cymui + "]";
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

    public CompanyModelUI getCymui() {
        return cymui;
    }

    public void setCymui(CompanyModelUI cymui) {
        this.cymui = cymui;
    }

    public static class Builder {
        private long id;
        private String name;
        private LocalDate dIntroduced;
        private LocalDate dDiscontinued;
        private CompanyModelUI cymui;

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

        public Builder cymui(CompanyModelUI cymui) {
            this.cymui = cymui;
            return this;

        }

        public ComputerModelUI build() {
            return new ComputerModelUI(this.id, this.name, this.dIntroduced, this.dDiscontinued, this.cymui);

        }
    }

}
