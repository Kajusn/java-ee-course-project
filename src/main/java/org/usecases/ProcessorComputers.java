package org.usecases;

import lombok.Getter;
import lombok.Setter;
import org.entities.Processor;
import org.persistence.ProcessorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class ProcessorComputers {

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
}
