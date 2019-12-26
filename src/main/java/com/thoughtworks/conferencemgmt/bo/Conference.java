/**
 * 
 */
package com.thoughtworks.conferencemgmt.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vaibhav.singh
 *
 */
public class Conference
{
	private static Conference INSTANCE = new Conference();

	private final Map<Integer, Track> trackMap;

	private Conference()
	{
		trackMap = new LinkedHashMap<>();
	}

	public static Conference getInstance()
	{
		return INSTANCE;
	}

	public List<Track> getTracks()
	{
		return new ArrayList<>(trackMap.values());
	}

	public boolean add(int trackId, Track newTrack)
	{
		return trackMap.put(trackId, newTrack) == null;
	}

	@Override
	public String toString()
	{
		String NEW_LINE = System.getProperty("line.separator");
		StringBuilder str = new StringBuilder();
		str.append("Conference Schedule:" + NEW_LINE + NEW_LINE);
		for (Track track : this.getTracks())
		{
			str.append("Track ").append(track.getTrackId()).append(":").append(NEW_LINE);
			for (Event event : track.getEvents())
			{
				str.append(event.getFormattedStartTime()).append(" ").append(event.getTitle()).append(" - ").append(event.getFormattedDurationInMin())
				                        .append(NEW_LINE);
			}
			str.append(NEW_LINE);
		}
		return str.toString();
	}

	public synchronized void doCleanUp()
	{
		trackMap.clear();
	}
}
