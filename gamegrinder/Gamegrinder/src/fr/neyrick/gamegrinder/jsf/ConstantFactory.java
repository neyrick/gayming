package fr.neyrick.gamegrinder.jsf;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import fr.neyrick.gamegrinder.entities.TimeFrame.TimeFrameLocator;

@ApplicationScoped
@Named
public class ConstantFactory {

	public TimeFrameLocator getAfternoon() {
		return TimeFrameLocator.AFTERNOON;
	}
	
	public TimeFrameLocator getEvening() {
		return TimeFrameLocator.EVENING;
	}
	
	public ConstantFactory() {
	}

}
