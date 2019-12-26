/**
 * 
 */
package com.thoughtworks.conferencemgmt.exception;

/**
 * @author vaibhav.singh
 *
 */
public final class ErrorMessage
{
	private ErrorMessage()
	{
	}

	public static final String INVALID_VALUE         = "Invalid Value/s";
	public static final String INVALID_FILE          = "Invalid File";
	public static final String PROCESSING_ERROR      = "Processing Error. System Exiting ... ";
	public static final String INVALID_REQUEST       = "Invalid Talk Line, Talk doesnot contain duration after title : {0}";
	public static final String INVALID_TALK_DURATION = "Invalid talk duration length.";
	public static final String INVALID_TALK_TITLE    = "Invalid talk title. Talk title can't contains numbers.";
}
