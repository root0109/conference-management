/**
 * 
 */
package com.thoughtworks.conferencemgmt.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.thoughtworks.conferencemgmt.data.Konstants.DurationUnit;
import com.thoughtworks.conferencemgmt.exception.ErrorMessage;

/**
 * @author vaibhav.singh
 *
 */
public final class InputValidationUtil
{
	private static final Logger logger = Logger.getLogger(InputValidationUtil.class.getName());

	private InputValidationUtil()
	{
	}

	public static boolean validate(String input)
	{
		boolean valid = false;
		try
		{
			String[] inputs = input.trim().split(" ");
			// get last token and check whether it contains min e.g 60min or lightning
			if (inputs[inputs.length - 1].contains(DurationUnit.MINUTES.toString())
			                        || inputs[inputs.length - 1].contains(DurationUnit.LIGHTENING.toString()))
			{
				valid = true;
			}
			else
			{
				logger.log(Level.SEVERE, ErrorMessage.INVALID_REQUEST, input);
			}
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, ErrorMessage.INVALID_REQUEST, e);
		}
		return valid;
	}
}
