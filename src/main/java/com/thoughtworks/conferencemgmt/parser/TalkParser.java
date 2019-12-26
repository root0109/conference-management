/**
 * 
 */
package com.thoughtworks.conferencemgmt.parser;

import com.thoughtworks.conferencemgmt.bo.TalkEvent;
import com.thoughtworks.conferencemgmt.data.Konstants;
import com.thoughtworks.conferencemgmt.exception.ErrorMessage;

/**
 * @author vaibhav.singh
 *
 */
public class TalkParser implements ConferenceParser<TalkEvent>
{
	@Override
	public TalkEvent parseInput(String text)
	{
		int lastSpace = text.lastIndexOf(" ");
		if (lastSpace != -1)
		{
			final String talkLength = text.substring(lastSpace);
			final int length = parseTalkLengthMinutes(talkLength);
			if (length <= 0)
			{
				throw new IllegalArgumentException(ErrorMessage.INVALID_TALK_DURATION);
			}
			final String talkName = text.substring(0, lastSpace);
			if (talkName.matches(".*\\d.*"))
			{
				throw new IllegalArgumentException(ErrorMessage.INVALID_TALK_TITLE);
			}
			return new TalkEvent(talkName, length);
		}
		return null;
	}

	private static int parseTalkLengthMinutes(String talkLength)
	{
		final StringBuilder builder = new StringBuilder();
		if (talkLength.contains(Konstants.DurationUnit.LIGHTENING.toString()))
			return Konstants.DurationUnit.LIGHTENING.getValueInMins();
		for (char c : talkLength.toCharArray())
		{
			if (Character.isDigit(c))
			{
				builder.append(Character.getNumericValue(c));
			}
		}
		return builder.length() > 0 ? Integer.parseInt(builder.toString()) : 0;
	}
}
