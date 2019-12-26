/**
 * 
 */
package com.thoughtworks.conferencemgmt.service;

import java.util.List;

import com.thoughtworks.conferencemgmt.bo.Conference;
import com.thoughtworks.conferencemgmt.bo.TalkEvent;
import com.thoughtworks.conferencemgmt.exception.ConferenceException;

/**
 * @author vaibhav.singh
 *
 */
public interface ConferenceService extends AbstractService
{
	public boolean addTalk(TalkEvent talkEvent) throws ConferenceException;

	public void addTalk(List<TalkEvent> talkEvent) throws ConferenceException;

	public Conference getConference() throws ConferenceException;
}
