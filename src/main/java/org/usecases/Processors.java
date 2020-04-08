package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Computer;
import org.entities.Processor;
import org.persistence.ComputersDAO;
import org.persistence.ProcessorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class Processors {
    @Inject
    private ProcessorsDAO processorsDAO;

    @Getter @Setter
    private Processor processorToCreate = new Processor();

    @Getter
    private List<Processor> allProcessors;

    @PostConstruct
    public void init() {
        loadAllProcessors();
    }

    @Transactional
    public String createProcessor(){
        processorsDAO.persist(processorToCreate);
        return "processors?faces-redirect=true";
    }

    private void loadAllProcessors(){
        this.allProcessors = processorsDAO.loadAll();
    }
}
