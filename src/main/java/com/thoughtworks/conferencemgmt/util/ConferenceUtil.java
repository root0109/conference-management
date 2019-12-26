/**
 * 
 */
package com.thoughtworks.conferencemgmt.util;

import java.util.LinkedList;

import com.thoughtworks.conferencemgmt.bo.Event;
import com.thoughtworks.conferencemgmt.bo.InteractiveRequest;

/**
 * @author vaibhav.singh
 *
 */
public final class ConferenceUtil
{
	private ConferenceUtil()
	{
	}

	public static InteractiveRequest getInteractiveRequest(String input)
	{
		return new InteractiveRequest(input);
	}

	public static long sumTalksLength(LinkedList<Event> events)
	{
		return events.stream().mapToLong(Event::getDurationInMins).sum();
	}
}
