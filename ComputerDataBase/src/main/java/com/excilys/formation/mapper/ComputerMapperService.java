package com.excilys.formation.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Computer;
import com.excilys.formation.util.ComputerDBException;

public class ComputerMapperService {
    private Computer cp;
    private ComputerDTO cpdto;

    /**
     * @param cp Computer
     * @throws ComputerDBException cdbex
     */
    public ComputerMapperService(Computer cp) throws ComputerDBException {

        this.cp = cp;
        this.cpdto = new ComputerDTO.Builder()
                .id(this.cp.getId())
                .name(this.cp.getName())
                .di(null)
                .dd(null)
                .cydto(new CompanyMapperService(this.cp.getCy()).getCydto())
                .build();
        if (this.cp.getdIntroduced() != null) {
            this.cpdto.setdIntroduced(this.cp.getdIntroduced().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (this.cp.getdDiscontinued() != null) {
            this.cpdto.setdDiscontinued(this.cp.getdDiscontinued().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

    }

    /**
     * @param cpdto ComputerDTO
     * @throws ComputerDBException cdbex
     */
    public ComputerMapperService(ComputerDTO cpdto) throws ComputerDBException {

        this.cpdto = cpdto;
        this.cp = new Computer.Builder()
                .id(this.cpdto.getId())
                .name(this.cpdto.getName())
                .di(null)
                .dd(null)
                .cy(new CompanyMapperService(this.cpdto.getCydto()).getCy())
                .build();
        try {
            if (this.cpdto.getdIntroduced() != null) {
                this.cp.setdIntroduced(LocalDate.parse(this.cpdto.getdIntroduced(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            if (this.cpdto.getdDiscontinued() != null) {
                this.cp.setdDiscontinued(LocalDate.parse(this.cpdto.getdDiscontinued(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        } catch (DateTimeParseException e) {
            try {
                if (this.cpdto.getdIntroduced() != null) {
                    this.cp.setdIntroduced(LocalDate.parse(this.cpdto.getdIntroduced(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                if (this.cpdto.getdDiscontinued() != null) {
                    this.cp.setdDiscontinued(LocalDate.parse(this.cpdto.getdDiscontinued(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
            } catch (DateTimeParseException e2) {
            }
        }
    }

    public Computer getCp() {
        return cp;
    }
    public ComputerDTO getCpdto() {
        return cpdto;
    }


}