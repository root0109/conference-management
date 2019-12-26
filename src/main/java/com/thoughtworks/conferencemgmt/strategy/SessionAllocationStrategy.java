/**
 * 
 */
package com.thoughtworks.conferencemgmt.strategy;

import java.util.List;

import com.thoughtworks.conferencemgmt.bo.Event;

/**
 * 
 * @author vaibhav.singh
 *
 * @param <T>
 */
public interface SessionAllocationStrategy<T>
{
	boolean addSession(T session);

	boolean hasAvailableSchedule();

	T getSession(String sessionId);

	List<T> getAllSessions();

	boolean addEvent(Event event);
}
