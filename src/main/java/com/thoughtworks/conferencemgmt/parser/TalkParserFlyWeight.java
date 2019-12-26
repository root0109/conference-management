/**
 * 
 */
package com.thoughtworks.conferencemgmt.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vaibhav.singh
 *
 */
public final class TalkParserFlyWeight
{
	private TalkParserFlyWeight()
	{
	}

	private static final Map<Class<?>, TalkParser> map = new HashMap<>();

	public static TalkParser getTalkParser(Class<?> className)
	{
		return map.getOrDefault(className, createTalkParser());
	}

	private static TalkParser createTalkParser()
	{
		return new TalkParser();
	}
}
