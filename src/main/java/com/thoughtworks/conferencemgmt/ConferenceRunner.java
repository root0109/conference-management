/**
 * 
 */
package com.thoughtworks.conferencemgmt;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.thoughtworks.conferencemgmt.bo.Conference;
import com.thoughtworks.conferencemgmt.bo.Event;
import com.thoughtworks.conferencemgmt.bo.FileRequest;
import com.thoughtworks.conferencemgmt.bo.Track;
import com.thoughtworks.conferencemgmt.data.Konstants;
import com.thoughtworks.conferencemgmt.exception.ConferenceException;
import com.thoughtworks.conferencemgmt.processor.AbstractProcessor;
import com.thoughtworks.conferencemgmt.processor.FileProcessor;
import com.thoughtworks.conferencemgmt.service.impl.ConferenceServiceImpl;

/**
 * @author vaibhav.singh
 *
 */
public class ConferenceRunner
{
	private static final Logger logger = Logger.getLogger(ConferenceRunner.class.getName());

	public static void main(String[] args)
	{
		File file = null;
		if (args.length > 1)
		{
			logger.log(Level.SEVERE, "Too Many arguments provided to the program");
			System.exit(1);
		}
		else if (args.length == 1)
		{
			file = new File(args[0]);
			if (!file.exists())
			{
				logger.log(Level.SEVERE, "Supplied Input file doesnot exist.");
				System.exit(1);
			}
		}
		else
		{
			String path = ConferenceRunner.class.getClassLoader().getResource(Konstants.TALKS_INPUT_FILE).getPath();
			file = new File(path);
			if (!file.exists())
			{
				logger.log(Level.SEVERE, "Supplied Input file doesnot exist in src/main/resources : " + path);
				System.exit(1);
			}
		}
		FileRequest fileRequest = new FileRequest(file, Konstants.IGNORE_INVALID_LINE_IN_FILE);
		try
		{
			boolean success = false;
			AbstractProcessor processor = new FileProcessor();
			processor.setService(new ConferenceServiceImpl());
			if (processor.validateInput(fileRequest))
				success = processor.execute(fileRequest);
			final Conference conference = Conference.getInstance();

			if (success)
			{
				for (Track track : conference.getTracks())
				{
					System.out.printf("Track %d:\n", track.getTrackId());
					for (Event event : track.getEvents())
					{
						System.out.printf("%s %s %s\n", event.getFormattedStartTime(), event.getTitle(), event.getFormattedDurationInMin());
					}
					System.out.println();
				}
			}
			else
			{
				System.out.println("System has encountered error due to faulty Input File, Please contact support");
			}
		}
		catch (ConferenceException e)
		{
			logger.log(Level.SEVERE, e.getMessage(), e);
			System.exit(0);
		}
	}
}
