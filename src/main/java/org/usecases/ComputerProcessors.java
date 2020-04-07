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
import java.util.Map;

@Model
public class ComputerProcessors {

    @Inject
    private ProcessorsDAO processorsDAO;

    @Inject
    private ComputersDAO computersDAO;

    @Getter @Setter
    private Computer computer;

    @Getter @Setter
    private Processor processorToCreate = new Processor();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer computerId = Integer.parseInt(requestParameters.get("computerId"));
        this.computer = computersDAO.findOne(computerId);
    }

    @Transactional
    public String createProcessor() {
        ArrayList<Computer> computersList = (ArrayList<Computer>) processorToCreate.getComputers();
        computersList.add(this.computer);
        processorToCreate.setComputers(computersList);
        processorsDAO.persist(processorToCreate);

        /* Sitoj vietoj neveikia nes sukuria papildoma Computer kai daugiau nei 1 procesorius viename
        *  sukurtas papildomas visada identiskas jau buvusiam, tiesiog duplikuoja*/
        ArrayList<Processor> processorsList = (ArrayList<Processor>) this.computer.getProcessors();
        processorsList.add(processorToCreate);
        this.computer.setProcessors(processorsList);
        return "computerProcessors?faces-redirect=true&computerId=" + this.computer.getId();
    }
}
