/**
 * 
 */
package com.thoughtworks.conferencemgmt.strategy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.conferencemgmt.bo.AbstractSession;
import com.thoughtworks.conferencemgmt.bo.Event;
import com.thoughtworks.conferencemgmt.bo.TalkEvent;

/**
 * @author vaibhav.singh
 *
 */
public class FirstComeFirstServeSessionAllocationStrategy implements SessionAllocationStrategy<AbstractSession<Event>>
{
	private Map<String, AbstractSession<Event>> sessionMap = new LinkedHashMap<>();

	@Override
	public boolean addSession(AbstractSession<Event> session)
	{
		return sessionMap.put(session.getSessionId(), session) == null;
	}

	@Override
	public boolean hasAvailableSchedule()
	{
		boolean hasAvailableSchedule = true;
		for (AbstractSession<Event> abstractSession : sessionMap.values())
		{
			hasAvailableSchedule = hasAvailableSchedule || abstractSession.hasAvailableSchedule();
		}
		return hasAvailableSchedule;
	}

	@Override
	public AbstractSession<Event> getSession(String sessionId)
	{
		return sessionMap.get(sessionId);
	}

	@Override
	public List<AbstractSession<Event>> getAllSessions()
	{
		return new ArrayList<>(sessionMap.values());
	}

	@Override
	public boolean addEvent(Event event)
	{
		boolean added = false;
		for (AbstractSession<Event> abstractSession : sessionMap.values())
		{
			if (abstractSession.hasAvailableSchedule())
			{
				added = abstractSession.addEvent((TalkEvent) event);
				if (added)
					break;
			}
		}
		return added;
	}
}
