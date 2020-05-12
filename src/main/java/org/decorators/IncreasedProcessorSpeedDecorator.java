package org.decorators;

import org.usecases.Processor;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.Serializable;

@Decorator
public class IncreasedProcessorSpeedDecorator implements Processor, Serializable {

    @Inject @Delegate @Any Processor processor;

    public String updateProcessorSpeed(double speed) {
        if (speed>4.5)
        {
            System.out.println("Speed was changed to higher than 4.5 GHz indicating an overclock");
        }
        return processor.updateProcessorSpeed(speed);
    }
}
