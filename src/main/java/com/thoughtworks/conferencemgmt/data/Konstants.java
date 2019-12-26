/**
 * 
 */
package com.thoughtworks.conferencemgmt.data;

/**
 * @author vaibhav.singh
 *
 */
public class Konstants
{
	private Konstants()
	{
	}

	public enum DurationUnit
	{
		MINUTES(1, "min"),
		LIGHTENING(5, "lightning");

		private int    valueInMins;
		private String stringRepresentation;

		private DurationUnit(int valueInMins, String stringRepresentation)
		{
			this.valueInMins = valueInMins;
			this.stringRepresentation = stringRepresentation;
		}

		public int getValueInMins()
		{
			return valueInMins;
		}

		@Override
		public String toString()
		{
			return stringRepresentation;
		}
	}

	public static final boolean IGNORE_INVALID_LINE_IN_FILE = true;

	public static final int    MORNING_HOUR_START_TIME    = 9;
	public static final int    MORNING_HOUR_END_TIME      = 12;
	public static final int    AFTERNOON_HOUR_START_TIME  = 13;
	public static final int    AFTERNOON_HOUR_END_TIME    = 17;
	public static final String LUNCH_TITLE                = "Lunch";
	public static final int    LUNCH_HOUR_START_TIME      = 12;
	public static final int    LUNCH_HOUR_END_TIME        = 13;
	public static final String NETWORKING_EVENT_TITLE     = "Networking Event";
	public static final int    NETWORKING_HOUR_START_TIME = 17;
	public static final int    NETWORKING_HOUR_END_TIME   = 18;
	public static final String TALKS_INPUT_FILE           = "talks.txt";
}
