package org.services;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class ProcessorSpeedGenerator implements SpeedGenerator {
    public double generateProcessorSpeed() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        double generatedProcessorSpeed = 1.0 + new Random().nextDouble() * (6.0 - 1.0);
        return generatedProcessorSpeed;
    }
}
