package org.usecases;
import org.interceptors.LoggedInvocation;
import org.services.ProcessorSpeedGenerator;
import org.services.SpeedGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateProcessorSpeed implements Serializable{

    @Inject
    SpeedGenerator processorSpeedGenerator;

    private CompletableFuture<Double> processorSpeedGenerationTask = null;

    @LoggedInvocation
    public String generateNewProcessorSpeed() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        processorSpeedGenerationTask = CompletableFuture.supplyAsync(() -> processorSpeedGenerator.generateProcessorSpeed());
        return "processorComputer.xhtml?faces-redirect=true&processorId=" + requestParameters.get("processorId");
    }

    public boolean isProcessorSpeedGenerationRunning() {
        return processorSpeedGenerationTask != null && !processorSpeedGenerationTask.isDone();
    }

    public String getProcessorSpeedGenerationStatus() throws ExecutionException, InterruptedException {
        if (processorSpeedGenerationTask == null) {
            return null;
        } else if (isProcessorSpeedGenerationRunning()) {
            return "Processor speed generation in progress";
        }
        return "Suggested processor speed: " + processorSpeedGenerationTask.get();
    }

}
