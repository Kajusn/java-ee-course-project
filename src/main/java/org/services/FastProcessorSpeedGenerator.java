package org.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;

@Alternative
@ApplicationScoped
public class FastProcessorSpeedGenerator implements SpeedGenerator {

    public double generateProcessorSpeed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        double generatedProcessorSpeed = 1.0 + new Random().nextDouble() * (6.0 - 1.0);
        return generatedProcessorSpeed;
    }
}
