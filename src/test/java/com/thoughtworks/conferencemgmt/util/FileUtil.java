/**
 * 
 */
package com.thoughtworks.conferencemgmt.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * @author vaibhav.singh
 *
 */
public class FileUtil
{
	private FileUtil()
	{
	}

	public static BufferedReader getBufferedReaderForResourceFile(String resourceFile) throws IOException
	{
		return new BufferedReader(new InputStreamReader(new FileInputStream(resourceFile)));
	}

	public static boolean contentEquals(String resourceFile, String string) throws IOException
	{
		BufferedReader fileReader = getBufferedReaderForResourceFile(resourceFile);
		BufferedReader stringReader = new BufferedReader(new StringReader(string));
		while (true)
		{
			String fileLine = getNextLine(fileReader);
			String stringLine = getNextLine(stringReader);
			if (fileLine == null && stringLine == null)
			{
				break;
			}

			if (fileLine == null || stringLine == null)
			{
				return false;
			}

			if (fileLine.equals(stringLine) == false)
			{
				System.out.println(fileLine);
				System.out.println(stringLine);
				return false;
			}
		}
		return true;
	}

	private static String getNextLine(BufferedReader reader) throws IOException
	{
		while (true)
		{
			String line = reader.readLine();
			if (line == null)
			{
				return null;
			}

			if (line.trim().length() != 0)
			{
				return line;
			}
		}
	}
}
