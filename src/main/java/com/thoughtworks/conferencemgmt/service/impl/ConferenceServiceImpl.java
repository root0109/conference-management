/**
 * 
 */
package com.thoughtworks.conferencemgmt.service.impl;

import static com.thoughtworks.conferencemgmt.data.Konstants.AFTERNOON_HOUR_END_TIME;
import static com.thoughtworks.conferencemgmt.data.Konstants.AFTERNOON_HOUR_START_TIME;
import static com.thoughtworks.conferencemgmt.data.Konstants.MORNING_HOUR_END_TIME;
import static com.thoughtworks.conferencemgmt.data.Konstants.MORNING_HOUR_START_TIME;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.thoughtworks.conferencemgmt.bo.Conference;
import com.thoughtworks.conferencemgmt.bo.TalkEvent;
import com.thoughtworks.conferencemgmt.bo.Track;
import com.thoughtworks.conferencemgmt.exception.ConferenceException;
import com.thoughtworks.conferencemgmt.service.ConferenceService;

/**
 * @author vaibhav.singh
 *
 */
public class ConferenceServiceImpl implements ConferenceService
{
	private AtomicInteger trackId = new AtomicInteger(1);

	@Override
	public boolean addTalk(TalkEvent talkEvent) throws ConferenceException
	{
		final List<Track> availableTracks = getConference().getTracks().stream().filter(Track::hasAvailableSchedule).collect(Collectors.toList());
		boolean added = false;
		for (Track track : availableTracks)
		{
			if (track.addTalk(talkEvent))
			{
				added = true;
				talkEvent.setAllotted(true);
				break;
			}
		}
		if (!added)
		{
			int id = trackId.getAndIncrement();
			final Track newTrack = new Track(id, MORNING_HOUR_START_TIME, MORNING_HOUR_END_TIME, AFTERNOON_HOUR_START_TIME, AFTERNOON_HOUR_END_TIME);
			if (newTrack.addTalk(talkEvent))
			{
				talkEvent.setAllotted(true);
				getConference().add(id, newTrack);
			}
		}
		return true;
	}

	@Override
	public void addTalk(List<TalkEvent> talkEvents) throws ConferenceException
	{
		for (TalkEvent talkEvent : talkEvents)
		{
			addTalk(talkEvent);
		}
	}

	@Override
	public Conference getConference() throws ConferenceException
	{
		return Conference.getInstance();
	}
}
