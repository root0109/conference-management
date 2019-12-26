/**
 * 
 */
package com.thoughtworks.conferencemgmt.strategy;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.conferencemgmt.bo.Event;
import com.thoughtworks.conferencemgmt.util.ConferenceUtil;

/**
 * @author vaibhav.singh
 *
 */
public class FirstComeFirstServeSlotAllocationStrategy implements SlotAllocationStrategy<Event>
{
	private LocalDateTime           startTime;
	private LocalDateTime           endTime;
	private long                    lengthInMinutes;
	private final LinkedList<Event> events = new LinkedList<>();

	public FirstComeFirstServeSlotAllocationStrategy(LocalDateTime startTime, LocalDateTime endTime)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		lengthInMinutes = ChronoUnit.MINUTES.between(startTime, endTime);
	}

	@Override
	public boolean hasAvailableSchedule()
	{
		return ConferenceUtil.sumTalksLength(events) < lengthInMinutes;
	}

	@Override
	public List<Event> getEvents()
	{
		return events;
	}

	@Override
	public boolean addEvent(Event event)
	{
		if (!hasAvailableSchedule())
		{
			return false;
		}
		final long addedLength = ConferenceUtil.sumTalksLength(events);
		if ((addedLength + event.getDurationInMins()) <= lengthInMinutes)
		{
			if (events.isEmpty())
			{
				event.setStartTime(startTime);
				event.setEndTime(event.getStartTime().plusMinutes(event.getDurationInMins()));
			}
			else
			{
				final Event lastEvent = events.getLast();
				event.setStartTime(lastEvent.getEndTime());
				event.setEndTime(lastEvent.getEndTime().plusMinutes(event.getDurationInMins()));
			}
			events.addLast(event);
			return true;
		}
		return false;
	}
}
