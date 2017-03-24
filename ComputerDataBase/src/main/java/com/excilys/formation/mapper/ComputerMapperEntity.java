package com.excilys.formation.mapper;

import com.excilys.formation.entity.CompanyEntity;
import com.excilys.formation.entity.ComputerEntity;
import com.excilys.formation.model.Computer;
import com.excilys.formation.persistence.CompanyDAO;
import com.excilys.formation.util.ComputerDBException;

public class ComputerMapperEntity {
    private ComputerEntity cpE;
    private Computer cp;
    private CompanyEntity cyE;

    /**
     * @param cpE ComputerEntity
     * @throws ComputerDBException cdbex
     */
    public ComputerMapperEntity(ComputerEntity cpE) throws ComputerDBException {

        if (cpE != null) {
            this.cpE = cpE;
            this.cyE = new CompanyMapperEntity(CompanyDAO.COMPANYDAO.find(cpE.getManufacturer())).getCyE();
            this.cp = new Computer.Builder()
                    .id(this.cpE.getId())
                    .name(this.cpE.getName())
                    .di(this.cpE.getdIntroduced())
                    .dd(this.cpE.getdDiscontinued())
                    .cy(new CompanyMapperEntity(this.cyE).getCy())
                    .build();
        } else {
            this.cpE = null;
            this.cyE = null;
            this.cp = null;
        }
    }

    /**
     * @param cp Computer
     * @throws ComputerDBException cdbex
     */
    public ComputerMapperEntity(Computer cp) throws ComputerDBException {

        if (cp != null) {
            this.cp = cp;
            this.cyE  = new CompanyMapperEntity(cp.getCy()).getCyE();
            this.cpE = new ComputerEntity.Builder()
                    .id(this.cp.getId())
                    .name(this.cp.getName())
                    .di(this.cp.getdIntroduced())
                    .dd(this.cp.getdDiscontinued())
                    .manufacturer(cp.getCy().getId())
                    .build();
        } else {
            this.cpE = null;
            this.cyE = null;
            this.cp = null;
        }

    }

    public ComputerEntity getCpE() {
        return cpE;
    }


    public Computer getCp() {
        return cp;
    }


}
