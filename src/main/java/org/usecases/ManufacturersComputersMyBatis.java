package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.mybatis.model.Computer;
import org.mybatis.model.Manufacturer;
import org.mybatis.dao.ComputerMapper;
import org.mybatis.dao.ManufacturerMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class ManufacturersComputersMyBatis {
    @Inject
    private ManufacturerMapper manufacturerMapper;

    @Inject
    private ComputerMapper computerMapper;

    @Getter @Setter
    private Manufacturer manufacturer;

    @Getter
    private List<Computer> allComputers;

    @Getter
    private Integer manufacturerId;

    @Getter @Setter
    private Computer computerToCreate = new Computer();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        manufacturerId = Integer.parseInt(requestParameters.get("manufacturerId"));
        this.manufacturer = manufacturerMapper.selectByPrimaryKey(manufacturerId);
        this.allComputers = computerMapper.selectComputerByManufacturerId(manufacturerId);
    }

    @Transactional
    public String createComputer() {
        computerToCreate.setManufacturerId(manufacturerId);
        computerMapper.insert(computerToCreate);
        return "computers?faces-redirect=true&manufacturerId=" + manufacturerId;
    }
}
