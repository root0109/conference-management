/**
 * 
 */
package com.thoughtworks.conferencemgmt.exception;

/**
 * @author vaibhav.singh
 *
 */
public class ConferenceException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3103685963956412617L;

	public ConferenceException()
	{
		super();
	}

	public ConferenceException(String message)
	{
		super(message);
	}

	public ConferenceException(Throwable cause)
	{
		super(cause);
	}

	public ConferenceException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
