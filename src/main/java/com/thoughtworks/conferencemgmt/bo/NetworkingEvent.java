/**
 * 
 */
package com.thoughtworks.conferencemgmt.bo;

import java.time.LocalDateTime;

import com.thoughtworks.conferencemgmt.data.Konstants;

/**
 * @author vaibhav.singh
 *
 */
public class NetworkingEvent extends TalkEvent
{
	public NetworkingEvent(LocalDateTime startTime, LocalDateTime endTime)
	{
		super(Konstants.NETWORKING_EVENT_TITLE, 60);
		setStartTime(startTime);
		setEndTime(endTime);
	}
}