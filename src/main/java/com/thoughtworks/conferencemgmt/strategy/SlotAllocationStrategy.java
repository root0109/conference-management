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
public interface SlotAllocationStrategy<T extends Event>
{
	boolean addEvent(T event);

	boolean hasAvailableSchedule();

	List<T> getEvents();
}
