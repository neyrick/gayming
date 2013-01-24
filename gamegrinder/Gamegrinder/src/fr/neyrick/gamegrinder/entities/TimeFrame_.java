package fr.neyrick.gamegrinder.entities;

import fr.neyrick.gamegrinder.entities.TimeFrame.TimeFrameLocator;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-01-24T14:49:44.153+0100")
@StaticMetamodel(TimeFrame.class)
public class TimeFrame_ {
	public static volatile SingularAttribute<TimeFrame, TimeFrameLocator> locator;
	public static volatile SingularAttribute<TimeFrame, Date> dayDate;
}
