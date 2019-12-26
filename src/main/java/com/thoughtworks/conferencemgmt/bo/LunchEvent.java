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
public class LunchEvent extends Event
{
	public LunchEvent(LocalDateTime startTime, LocalDateTime endTime)
	{
		super(Konstants.LUNCH_TITLE, 60);
		setStartTime(startTime);
		setEndTime(endTime);
	}
}