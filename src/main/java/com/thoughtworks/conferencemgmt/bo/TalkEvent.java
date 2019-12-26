package com.thoughtworks.conferencemgmt.bo;

/**
 * 
 * @author vaibhav.singh
 *
 */
public class TalkEvent extends Event
{
	private boolean isAllotted;

	public TalkEvent(String title, int durationInMins)
	{
		super(title, durationInMins);
	}

	/**
	 * @return the isAllotted
	 */
	public boolean isAllotted()
	{
		return isAllotted;
	}

	/**
	 * @param isAllotted the isAllotted to set
	 */
	public void setAllotted(boolean isAllotted)
	{
		this.isAllotted = isAllotted;
	}
}
