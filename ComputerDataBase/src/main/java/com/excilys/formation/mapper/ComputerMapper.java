package com.excilys.formation.mapper;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.ui.ComputerModelUI;
import com.excilys.formation.util.ComputerDBException;

public class ComputerMapper {
    private Computer cp;
    private ComputerModelUI cpmui;
    private Company cy;

    public ComputerMapper(Computer cp) throws ComputerDBException {

        this.cp = cp;
        this.cy = CompanyDAO.COMPANYDAO.find(cp.getManufacturer());
        this.cpmui = new ComputerModelUI.Builder().id(this.cp.getId()).name(this.cp.getName())
                .di(this.cp.getdIntroduced()).dd(this.cp.getdDiscontinued())
                .cymui(new CompanyMapper(this.cy).getCymui()).build();

    }

    public ComputerMapper(ComputerModelUI cpmui) throws ComputerDBException {

        this.cpmui = cpmui;
        this.cy = new CompanyMapper(cpmui.getCymui()).getCy();
        this.cp = new Computer.Builder().id(this.cpmui.getId()).name(this.cpmui.getName())
                .di(this.cpmui.getdIntroduced()).dd(this.cpmui.getdDiscontinued())
                .manufacturer(cpmui.getCymui().getId()).build();

    }

    public Computer getCp() {
        return cp;
    }

    public void setCp(Computer cp) {
        this.cp = cp;
    }

    public ComputerModelUI getCpmui() {
        return cpmui;
    }

    public void setCpmui(ComputerModelUI cpmui) {
        this.cpmui = cpmui;
    }

}
