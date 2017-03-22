package com.excilys.formation.mapper;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.util.ComputerDBException;

public class ComputerMapper {
    private Computer cp;
    private ComputerDTO cpdto;
    private Company cy;

    /**
     * @param cp Computer
     * @throws ComputerDBException cdbex
     */
    public ComputerMapper(Computer cp) throws ComputerDBException {

        this.cp = cp;
        this.cy = CompanyDAO.COMPANYDAO.find(cp.getManufacturer());
        this.cpdto = new ComputerDTO.Builder().id(this.cp.getId()).name(this.cp.getName()).di(this.cp.getdIntroduced())
                .dd(this.cp.getdDiscontinued()).cydto(new CompanyMapper(this.cy).getCydto()).build();

    }

    /**
     * @param cpdto ComputerDTO
     * @throws ComputerDBException cdbex
     */
    public ComputerMapper(ComputerDTO cpdto) throws ComputerDBException {

        this.cpdto = cpdto;
        this.cy = new CompanyMapper(cpdto.getCydto()).getCy();
        this.cp = new Computer.Builder().id(this.cpdto.getId()).name(this.cpdto.getName())
                .di(this.cpdto.getdIntroduced()).dd(this.cpdto.getdDiscontinued())
                .manufacturer(cpdto.getCydto().getId()).build();

    }

    public Computer getCp() {
        return cp;
    }

    public void setCp(Computer cp) {
        this.cp = cp;
    }

    public ComputerDTO getCpdto() {
        return cpdto;
    }

    public void setCpdto(ComputerDTO cpdto) {
        this.cpdto = cpdto;
    }

}
