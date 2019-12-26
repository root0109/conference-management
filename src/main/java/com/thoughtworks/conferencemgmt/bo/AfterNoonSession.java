/**
 * 
 */
package com.thoughtworks.conferencemgmt.bo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thoughtworks.conferencemgmt.strategy.FirstComeFirstServeSlotAllocationStrategy;
import com.thoughtworks.conferencemgmt.strategy.SlotAllocationStrategy;

/**
 * @author vaibhav.singh
 *
 */
public class AfterNoonSession extends AbstractSession<Event>
{
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private SlotAllocationStrategy<Event> slotAllocationStrategy;

	public AfterNoonSession(LocalDateTime startTime, LocalDateTime endTime)
	{
		super(UUID.randomUUID().toString(), startTime, endTime);
		this.startTime = startTime;
		this.endTime = endTime;
		slotAllocationStrategy = new FirstComeFirstServeSlotAllocationStrategy(this.startTime, this.endTime);
	}

	@Override
	public String getSessionName()
	{
		return AfterNoonSession.class.getName();
	}

	@Override
	public SlotAllocationStrategy<Event> getSlotAllocationStrategy()
	{
		return slotAllocationStrategy;
	}

}
