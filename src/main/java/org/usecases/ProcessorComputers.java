package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Processor;
import org.persistence.ProcessorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import org.interceptors.LoggedInvocation;

import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Model
public class ProcessorComputers implements org.usecases.Processor, Serializable {

    @Inject
    private ProcessorsDAO processorsDAO;

    @Getter @Setter
    private Processor processor;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer processorId = Integer.parseInt(requestParameters.get("processorId"));
        this.processor = processorsDAO.findOne(processorId);
    }

    @LoggedInvocation
    @Transactional
    public String updateProcessorSpeed(double speed) {
        try{
            processorsDAO.update(this.processor);
        } catch (OptimisticLockException e) {
            return "/processorComputer.xhtml?faces-redirect=true&processorId=" + this.processor.getId() + "&error=optimistic-lock-exception";
        }
        return "processorComputer?processorId=" + this.processor.getId() + "&faces-redirect=true";
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void tempProcessorTransaction() {
        Processor temp = processorsDAO.findOne(this.processor.getId());
        temp.setSpeed(3.5);
    }
}
