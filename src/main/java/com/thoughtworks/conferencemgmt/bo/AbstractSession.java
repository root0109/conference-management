/**
 * 
 */
package com.thoughtworks.conferencemgmt.bo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

import com.thoughtworks.conferencemgmt.strategy.SlotAllocationStrategy;

/**
 * @author vaibhav.singh
 *
 * @param <T>
 */
public abstract class AbstractSession<T extends Event>
{
	private final String        sessionId;
	private final LocalDateTime start;
	private final LocalDateTime end;
	private final long          durationInMinutes;

	public AbstractSession(String sessionId, LocalDateTime startTime, LocalDateTime endTime)
	{
		this.sessionId = sessionId;
		this.start = startTime;
		this.end = startTime;
		durationInMinutes = ChronoUnit.MINUTES.between(startTime, endTime);
	}

	public abstract String getSessionName();

	public abstract SlotAllocationStrategy<T> getSlotAllocationStrategy();

	public boolean addEvent(T event)
	{
		return getSlotAllocationStrategy().addEvent(event);
	}

	public boolean hasAvailableSchedule()
	{
		long sum = getEvents().stream().mapToLong(T::getDurationInMins).sum();
		return sum < durationInMinutes;
	}

	public LinkedList<T> getEvents()
	{
		return new LinkedList<>(getSlotAllocationStrategy().getEvents());
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId()
	{
		return sessionId;
	}

	/**
	 * @return the start
	 */
	public LocalDateTime getStart()
	{
		return start;
	}

	/**
	 * @return the end
	 */
	public LocalDateTime getEnd()
	{
		return end;
	}

	/**
	 * @return the durationInMinutes
	 */
	public long getDurationInMinutes()
	{
		return durationInMinutes;
	}
}
