/**
 * 
 */
package com.thoughtworks.conferencemgmt;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import com.thoughtworks.conferencemgmt.bo.Conference;
import com.thoughtworks.conferencemgmt.bo.FileRequest;
import com.thoughtworks.conferencemgmt.data.Konstants;
import com.thoughtworks.conferencemgmt.processor.AbstractProcessor;
import com.thoughtworks.conferencemgmt.processor.FileProcessor;
import com.thoughtworks.conferencemgmt.service.impl.ConferenceServiceImpl;
import com.thoughtworks.conferencemgmt.util.FileUtil;

/**
 * @author vaibhav.singh
 *
 */
public class ConferenceTrackManagementTest
{
	@After
	public void cleanup()
	{
		Conference.getInstance().doCleanUp();
	}

	@Test
	public void testConferenceTrackManagementMultipleFullDayEvents() throws IOException
	{
		testConferenceTrackManagement(getClass().getClassLoader().getResource("input_file").getPath());
	}

	@Test
	public void testConferenceTrackManagementMultipleDayLessEvents() throws IOException
	{
		testConferenceTrackManagement(getClass().getClassLoader().getResource("input_file_less_events").getPath());
	}

	@Test
	public void testConferenceTrackManagementSingleDayEvents() throws IOException
	{
		testConferenceTrackManagement(getClass().getClassLoader().getResource("input_file_single_day_events").getPath());
	}

	private void testConferenceTrackManagement(String inputFilePathString) throws IOException
	{
		File inputFile = new File(inputFilePathString);
		FileRequest fileRequest = new FileRequest(inputFile, Konstants.IGNORE_INVALID_LINE_IN_FILE);
		AbstractProcessor processor = new FileProcessor();
		processor.setService(new ConferenceServiceImpl());
		if (processor.validateInput(fileRequest))
			processor.execute(fileRequest);
		final Conference conference = Conference.getInstance();
		assertTrue(FileUtil.contentEquals(getExpectedOutputFile(inputFile.getAbsolutePath()), conference.toString()));
	}

	private String getExpectedOutputFile(String inputFile)
	{
		return inputFile + "_expected";
	}
}
