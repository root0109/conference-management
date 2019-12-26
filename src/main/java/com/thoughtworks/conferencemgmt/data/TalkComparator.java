/**
 * 
 */
package com.thoughtworks.conferencemgmt.data;

import java.util.Comparator;

import com.thoughtworks.conferencemgmt.bo.TalkEvent;

/**
 * @author vaibhav.singh
 *
 */
public class TalkComparator implements Comparator<TalkEvent>
{
	public int compare(TalkEvent talk1, TalkEvent talk2)
	{
		if (talk1.getDurationInMins() < talk2.getDurationInMins())
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
}
