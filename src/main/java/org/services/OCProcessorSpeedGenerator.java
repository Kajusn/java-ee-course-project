package org.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.Random;

@Specializes
@ApplicationScoped
public class OCProcessorSpeedGenerator extends ProcessorSpeedGenerator{
    public double generateProcessorSpeed() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        double generatedProcessorSpeed = 4.5 + new Random().nextDouble() * (6.0 - 4.5);
        return generatedProcessorSpeed;
    }
}
