package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Computer;
import org.entities.Manufacturer;
import org.entities.Processor;
import org.persistence.ComputersDAO;
import org.persistence.ManufacturersDAO;
import org.persistence.ProcessorsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.enterprise.inject.Model;
import org.interceptors.LoggedInvocation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class ComputerProcessors {
    @Inject
    private ComputersDAO computersDAO;

    @Inject
    private ProcessorsDAO processorsDAO;

    @Inject
    private ManufacturersDAO manufacturersDAO;

    @Getter
    private List<Processor> availableProcessors;

    @Getter @Setter
    private Computer computer;

    private void loadProcessors(){
        availableProcessors = processorsDAO.loadAll();
        availableProcessors.removeAll(computer.getProcessors());
    }

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer computerId = Integer.parseInt(requestParameters.get("computerId"));
        this.computer = computersDAO.findOne(computerId);
        loadProcessors();
    }

    @LoggedInvocation
    @Transactional
    public String addProcessor(Processor processor) {
        Manufacturer temp = manufacturersDAO.findOne(this.computer.getManufacturer().getId());
        processor.getComputers().add(this.computer);
        computersDAO.update(this.computer);
        processorsDAO.update(processor);
        return "computerProcessors?faces-redirect=true&computerId=" + this.computer.getId();
    }
}
