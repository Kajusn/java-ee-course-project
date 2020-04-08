package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Computer;
import org.entities.Processor;
import org.persistence.ComputersDAO;
import org.persistence.ProcessorsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.enterprise.inject.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class ComputerProcessors {
    @Inject
    private ComputersDAO computersDAO;

    @Inject
    private ProcessorsDAO processorsDAO;

    @Getter @Setter
    private Computer computer;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer computerId = Integer.parseInt(requestParameters.get("computerId"));
        this.computer = computersDAO.findOne(computerId);
    }

    @Transactional
    public String addProcessor(Processor processor) {
        processor.getComputers().add(this.computer);
        computersDAO.update(this.computer);
        processorsDAO.update(processor);
        return "computerProcessors?faces-redirect=true&computerId=" + this.computer.getId();
    }
}
