/**
 * 
 */
package com.thoughtworks.conferencemgmt.bo;

import static com.thoughtworks.conferencemgmt.data.Konstants.LUNCH_HOUR_END_TIME;
import static com.thoughtworks.conferencemgmt.data.Konstants.LUNCH_HOUR_START_TIME;
import static com.thoughtworks.conferencemgmt.data.Konstants.NETWORKING_HOUR_END_TIME;
import static com.thoughtworks.conferencemgmt.data.Konstants.NETWORKING_HOUR_START_TIME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.conferencemgmt.strategy.FirstComeFirstServeSessionAllocationStrategy;
import com.thoughtworks.conferencemgmt.strategy.SessionAllocationStrategy;

/**
 * @author vaibhav.singh
 *
 */
public class Track
{
	private final int                                         trackId;
	private final String                                      morningSessionId;
	private final String                                      afternoonSessionId;
	private SessionAllocationStrategy<AbstractSession<Event>> sessionAllocationStrategy;

	public Track(int trackId, int morningSessionStartHour, int morningSessionEndHour, int afternoonSessionStartHour, int afterNoonSessionEndHour)
	{
		sessionAllocationStrategy = new FirstComeFirstServeSessionAllocationStrategy();
		this.trackId = trackId;

		LocalDateTime morningStartDateTime = LocalDateTime.now().withHour(morningSessionStartHour).withMinute(0);
		LocalDateTime morningEndDateTime = LocalDateTime.now().withHour(morningSessionEndHour).withMinute(0);
		AbstractSession<Event> morningSession = new MorningSession(morningStartDateTime, morningEndDateTime);

		morningSessionId = morningSession.getSessionId();
		sessionAllocationStrategy.addSession(morningSession);

		LocalDateTime afterNoonStartDateTime = LocalDateTime.now().withHour(afternoonSessionStartHour).withMinute(0);
		LocalDateTime afterNoonEndDateTime = LocalDateTime.now().withHour(afterNoonSessionEndHour).withMinute(0);
		AbstractSession<Event> afterNoonSession = new AfterNoonSession(afterNoonStartDateTime, afterNoonEndDateTime);

		afternoonSessionId = afterNoonSession.getSessionId();
		sessionAllocationStrategy.addSession(afterNoonSession);
	}

	public int getTrackId()
	{
		return trackId;
	}

	public boolean hasAvailableSchedule()
	{
		return sessionAllocationStrategy.hasAvailableSchedule();
	}

	public boolean addTalk(Event talkEvent)
	{
		return sessionAllocationStrategy.addEvent(talkEvent);
	}

	public List<Event> getEvents()
	{
		LocalDateTime lastSession = null;
		final List<Event> result = new ArrayList<>();
		LinkedList<Event> morningEvents = sessionAllocationStrategy.getSession(morningSessionId).getEvents();
		result.addAll(morningEvents);
		result.add(new LunchEvent(LocalDate.now().atTime(LUNCH_HOUR_START_TIME, 0), LocalDate.now().atTime(LUNCH_HOUR_END_TIME, 0)));

		LinkedList<Event> afternoonEvents = sessionAllocationStrategy.getSession(afternoonSessionId).getEvents();
		if (!afternoonEvents.isEmpty())
		{
			result.addAll(afternoonEvents);
			lastSession = afternoonEvents.getLast().getEndTime();
		}
		else
		{
			lastSession = morningEvents.getLast().getEndTime();
		}
		if (lastSession.isBefore(LocalDate.now().atTime(16, 0)))
		{
			result.add(new NetworkingEvent(LocalDate.now().atTime(NETWORKING_HOUR_START_TIME - 1, 0),
			                        LocalDate.now().atTime(NETWORKING_HOUR_END_TIME - 1, 0)));
		}
		else if (lastSession.isBefore(LocalDate.now().atTime(NETWORKING_HOUR_START_TIME, 0)))
		{
			LocalDateTime newNetworkingTime = LocalDate.now().atTime(lastSession.getHour(), lastSession.getMinute());
			result.add(new NetworkingEvent(newNetworkingTime, newNetworkingTime.plusHours(NETWORKING_HOUR_END_TIME - NETWORKING_HOUR_START_TIME)));
		}
		else
		{
			result.add(new NetworkingEvent(LocalDate.now().atTime(NETWORKING_HOUR_START_TIME, 0),
			                        LocalDate.now().atTime(NETWORKING_HOUR_END_TIME, 0)));
		}
		return Collections.unmodifiableList(result);
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (Event event : getEvents())
		{
			str.append(event);
		}
		return str.toString();
	}
}
