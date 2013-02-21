package fr.neyrick.gamegrinder.entities;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class TimeFrame {

//	private static TreeSet<TimeFrame> timeFrames = new TreeSet<TimeFrame>(new TimeFrameComparator());
	
	public enum TimeFrameLocator { AFTERNOON, EVENING};
	
	@Enumerated(EnumType.ORDINAL)
	private TimeFrameLocator locator;
	
	private Date dayDate;
	
	public TimeFrame getInstance(Date dayDate, TimeFrameLocator locator) {
		TimeFrame result = new TimeFrame(dayDate, locator);
		return result;
	}
	
	public String getLocatorString() {
		return locator.toString();
	}
	
	public TimeFrameLocator getLocator() {
		return locator;
	}

	public void setLocator(TimeFrameLocator locator) {
		this.locator = locator;
	}

	public void setDayDate(Date dayDate) {
		this.dayDate = dayDate;
	}

	public Date getDayDate() {
		return dayDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayDate == null) ? 0 : dayDate.hashCode());
		result = prime * result + ((locator == null) ? 0 : locator.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeFrame other = (TimeFrame) obj;
		if (dayDate == null) {
			if (other.dayDate != null)
				return false;
		} else if (!dayDate.equals(other.dayDate))
			return false;
		if (locator != other.locator)
			return false;
		return true;
	}

/*
	private static class TimeFrameComparator implements Comparator<TimeFrame> {

		@Override
		public int compare(TimeFrame arg0, TimeFrame arg1) {
			if (arg0 == arg1) return 0;
			if (arg0.dayDate.equals(arg1.dayDate)) return arg0.locator.compareTo(arg1.locator);
			return arg0.dayDate.compareTo(arg1.dayDate);
		}
		
	}
*/	
	public TimeFrame(Date dayDate, TimeFrameLocator locator) {
		this.dayDate = dayDate;
		this.locator = locator;
	}
	
	public TimeFrame() {
		
	}
}
