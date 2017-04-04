package com.excilys.formation.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Computer;
import com.excilys.formation.util.ComputerDBException;

public class ComputerMapperService {
    private Computer computer;
    private ComputerDTO cpdto;

    /**
     * @param computer Computer
     * @throws ComputerDBException cdbex
     */
    public ComputerMapperService(Computer computer) throws ComputerDBException {

        this.computer = computer;
        this.cpdto = new ComputerDTO.Builder()
                .id(this.computer.getId())
                .name(this.computer.getName())
                .di(null)
                .dd(null)
                .cydto(new CompanyMapperService(this.computer.getCy()).getCompanyDto())
                .build();
        if (this.computer.getdIntroduced() != null) {
            this.cpdto.setdIntroduced(this.computer.getdIntroduced().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (this.computer.getdDiscontinued() != null) {
            this.cpdto.setdDiscontinued(this.computer.getdDiscontinued().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

    }

    /**
     * @param cpdto ComputerDTO
     * @throws ComputerDBException cdbex
     */
    public ComputerMapperService(ComputerDTO cpdto) throws ComputerDBException {

        this.cpdto = cpdto;
        this.computer = new Computer.Builder()
                .id(this.cpdto.getId())
                .name(this.cpdto.getName())
                .di(null)
                .dd(null)
                .cy(new CompanyMapperService(this.cpdto.getCydto()).getCompany())
                .build();
        try {
            if (this.cpdto.getdIntroduced() != null) {
                this.computer.setdIntroduced(LocalDate.parse(this.cpdto.getdIntroduced(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            if (this.cpdto.getdDiscontinued() != null) {
                this.computer.setdDiscontinued(LocalDate.parse(this.cpdto.getdDiscontinued(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        } catch (DateTimeParseException e) {
            try {
                if (this.cpdto.getdIntroduced() != null) {
                    this.computer.setdIntroduced(LocalDate.parse(this.cpdto.getdIntroduced(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                if (this.cpdto.getdDiscontinued() != null) {
                    this.computer.setdDiscontinued(LocalDate.parse(this.cpdto.getdDiscontinued(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
            } catch (DateTimeParseException e2) {
            }
        }
    }

    public Computer getComputer() {
        return computer;
    }
    public ComputerDTO getComputerDto() {
        return cpdto;
    }


}