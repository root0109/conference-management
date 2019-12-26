/**
 * 
 */
package com.thoughtworks.conferencemgmt.bo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thoughtworks.conferencemgmt.strategy.FirstComeFirstServeSlotAllocationStrategy;
import com.thoughtworks.conferencemgmt.strategy.SlotAllocationStrategy;

/**
 * @author singvai2
 *
 */
public class MorningSession extends AbstractSession<Event>
{
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private SlotAllocationStrategy<Event> slotAllocationStrategy;

	public MorningSession(LocalDateTime startTime, LocalDateTime endTime)
	{
		super(UUID.randomUUID().toString(), startTime, endTime);
		this.startTime = startTime;
		this.endTime = endTime;
		slotAllocationStrategy = new FirstComeFirstServeSlotAllocationStrategy(this.startTime, this.endTime);
	}

	@Override
	public String getSessionName()
	{
		return MorningSession.class.getName();
	}

	@Override
	public SlotAllocationStrategy<Event> getSlotAllocationStrategy()
	{
		return slotAllocationStrategy;
	}
}
