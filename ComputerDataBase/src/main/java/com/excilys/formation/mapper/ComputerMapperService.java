package com.excilys.formation.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.model.Computer;
import com.excilys.formation.util.ComputerDBException;

public enum ComputerMapperService {
    INSTANCE;
    private CompanyMapperService CompanyMapper = CompanyMapperService.INSTANCE;

    /**
     * @param computer Computer
     * @throws ComputerDBException cdbex
     * @return ComputerDTO computerdto
     */
    public ComputerDTO toComputerDto(Computer computer) throws ComputerDBException {

        if (computer == null) {
            return new ComputerDTO.Builder().build();
        } else {
             ComputerDTO.Builder computerDtoB = new ComputerDTO.Builder()
                    .id(computer.getId())
                    .name(computer.getName())
                    .di(null)
                    .dd(null)
                    .cydtoId(CompanyMapper.toCompanyDto(computer.getCy()).getId())
                    .cydtoName(CompanyMapper.toCompanyDto(computer.getCy()).getName());
            if (computer.getdIntroduced() != null) {
                computerDtoB.di(computer.getdIntroduced().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            if (computer.getdDiscontinued() != null) {
                computerDtoB.dd(computer.getdDiscontinued().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            return computerDtoB.build();
        }
    }

    /**
     * @param computerDto ComputerDTO
     * @throws ComputerDBException cdbex
     * @return Computer cp
     */
    public Computer toComputer(ComputerDTO computerDto) throws ComputerDBException {

        if (computerDto == null) {
            return new Computer.Builder().build();
        } else {
            CompanyDTO companyDTO = new CompanyDTO(computerDto.getCydtoId(), computerDto.getCydtoName());
            Computer.Builder computerB = new Computer.Builder()
                    .id(computerDto.getId())
                    .name(computerDto.getName())
                    .di(null)
                    .dd(null)
                    .cy(CompanyMapper.toCompany(companyDTO));
            try {
                if (computerDto.getDateIntroduced() != null && !computerDto.getDateIntroduced().equals("")) {
                    computerB.di(LocalDate.parse(computerDto.getDateIntroduced(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
                if (computerDto.getDateDiscontinued() != null && !computerDto.getDateDiscontinued().equals("")) {
                    computerB.dd(LocalDate.parse(computerDto.getDateDiscontinued(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
                return computerB.build();
            } catch (DateTimeParseException e) {
                try {
                    if (computerDto.getDateIntroduced() != null) {
                        computerB.di(LocalDate.parse(computerDto.getDateIntroduced(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    }
                    if (computerDto.getDateDiscontinued() != null) {
                        computerB.dd(LocalDate.parse(computerDto.getDateDiscontinued(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    }
                    return computerB.build();
                } catch (DateTimeParseException e2) {
                    throw new ComputerDBException(e);
                }
            }
        }
    }

}