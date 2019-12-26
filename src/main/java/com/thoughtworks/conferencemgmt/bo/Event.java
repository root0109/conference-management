/**
 * 
 */
package com.thoughtworks.conferencemgmt.bo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.thoughtworks.conferencemgmt.data.Konstants.DurationUnit;
import com.thoughtworks.conferencemgmt.exception.ConferenceException;

/**
 * @author singvai2
 *
 */
public abstract class Event
{
	private final String title;
	private final long   durationInMins;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public Event(String title, long durationInMins)
	{
		if (title == null || title.trim().isEmpty())
		{
			throw new ConferenceException("Talk name is mandatory, it can not be null, empty or whitespace.");
		}
		if (durationInMins <= 0)
		{
			throw new ConferenceException("Invalid talk length. It should be a valid positive integer (in minutes).");
		}
		this.title = title;
		this.durationInMins = durationInMins;
	}

	public long getDurationInMins()
	{
		// return Duration.between(startTime, endTime).toMinutes();
		return durationInMins;
	}

	public String getFormattedStartTime()
	{
		return getStartTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
	}

	public String getFormattedDurationInMin()
	{
		if (getDurationInMins() / DurationUnit.LIGHTENING.getValueInMins() == 1)
		{
			return (getDurationInMins() / DurationUnit.LIGHTENING.getValueInMins()) + " " + DurationUnit.LIGHTENING.toString();
		}
		else
			return String.format("%d min", getDurationInMins());
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return the startTime
	 */
	public LocalDateTime getStartTime()
	{
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalDateTime startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public LocalDateTime getEndTime()
	{
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalDateTime endTime)
	{
		this.endTime = endTime;
	}
}
