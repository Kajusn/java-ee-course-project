package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Computer;
import org.entities.Manufacturer;
import org.persistence.ComputersDAO;
import org.persistence.ManufacturersDAO;
import javax.faces.context.FacesContext;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class ManufacturersComputers {
    @Inject
    private ManufacturersDAO manufacturersDAO;

    @Inject
    private ComputersDAO computersDAO;

    @Getter @Setter
    private Manufacturer manufacturer;

    @Getter @Setter
    private Computer computerToCreate = new Computer();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer manufacturerId = Integer.parseInt(requestParameters.get("manufacturerId"));
        this.manufacturer = manufacturersDAO.findOne(manufacturerId);
    }

    @Transactional
    public String createComputer() {
        computerToCreate.setManufacturer(this.manufacturer);
        computersDAO.persist(computerToCreate);
        return "/computers.xhtml?faces-redirect=true&manufacturerId=" + this.manufacturer.getId();
    }
}
