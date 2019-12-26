/**
 * 
 */
package com.thoughtworks.conferencemgmt.processor;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.conferencemgmt.data.ProgramRunType;

/**
 * @author vaibhav.singh
 *
 */
public class ProcessorFactory
{
	private static final Map<ProgramRunType, AbstractProcessor> map = new HashMap<>();

	public static final AbstractProcessor createProcessor(ProgramRunType programRunType)
	{
		AbstractProcessor abstractProcessor = null;
		switch (programRunType)
		{
			default:
			case INTERACTIVE :
				abstractProcessor = map.getOrDefault(programRunType, getInteractiveProcessor());
				break;
			case FILE :
				abstractProcessor = map.getOrDefault(programRunType, getFileProcessor());
				break;
		}
		return abstractProcessor;
	}

	private static AbstractProcessor getFileProcessor()
	{
		return new FileProcessor();
	}

	private static AbstractProcessor getInteractiveProcessor()
	{
		return new InteractiveProcessor();
	}
}
