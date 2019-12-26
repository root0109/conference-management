/**
 * 
 */
package com.thoughtworks.conferencemgmt.processor;

import com.thoughtworks.conferencemgmt.bo.InteractiveRequest;
import com.thoughtworks.conferencemgmt.bo.Request;
import com.thoughtworks.conferencemgmt.bo.TalkEvent;
import com.thoughtworks.conferencemgmt.exception.ConferenceException;
import com.thoughtworks.conferencemgmt.parser.TalkParser;
import com.thoughtworks.conferencemgmt.parser.TalkParserFlyWeight;
import com.thoughtworks.conferencemgmt.service.AbstractService;
import com.thoughtworks.conferencemgmt.service.ConferenceService;
import com.thoughtworks.conferencemgmt.util.InputValidationUtil;

/**
 * @author vaibhav.singh
 *
 */
public class InteractiveProcessor implements AbstractProcessor
{
	private ConferenceService conferenceService;

	@Override
	public boolean validateInput(Request request)
	{
		boolean valid = false;
		if (request instanceof InteractiveRequest)
		{
			InteractiveRequest interactiveRequest = (InteractiveRequest) request;
			valid = InputValidationUtil.validate(interactiveRequest.getInputString());
		}
		return valid;
	}

	@Override
	public boolean execute(Request request) throws ConferenceException
	{
		InteractiveRequest interactiveRequest = (InteractiveRequest) request;
		TalkParser talkParser = TalkParserFlyWeight.getTalkParser(TalkParser.class);
		TalkEvent talkEvent = talkParser.parseInput(interactiveRequest.getInputString());
		return conferenceService.addTalk(talkEvent);
	}

	@Override
	public void setService(AbstractService service)
	{
		conferenceService = (ConferenceService) service;
	}
}
