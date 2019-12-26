/**
 * 
 */
package com.thoughtworks.conferencemgmt.processor;

import com.thoughtworks.conferencemgmt.bo.Request;
import com.thoughtworks.conferencemgmt.exception.ConferenceException;
import com.thoughtworks.conferencemgmt.service.AbstractService;

/**
 * @author vaibhav.singh
 *
 */
public interface AbstractProcessor
{
	boolean validateInput(Request request);

	boolean execute(Request request) throws ConferenceException;

	void setService(AbstractService service);
}
