/**
 * 
 */
package com.thoughtworks.conferencemgmt.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.BitSet;

import com.thoughtworks.conferencemgmt.bo.FileRequest;
import com.thoughtworks.conferencemgmt.bo.Request;
import com.thoughtworks.conferencemgmt.exception.ConferenceException;
import com.thoughtworks.conferencemgmt.exception.ErrorMessage;
import com.thoughtworks.conferencemgmt.util.ConferenceUtil;
import com.thoughtworks.conferencemgmt.util.InputValidationUtil;

/**
 * @author vaibhav.singh
 *
 */
public class FileProcessor extends InteractiveProcessor
{
	private BitSet validSet = new BitSet();

	@Override
	public boolean validateInput(Request request)
	{
		boolean valid = true;
		if (request instanceof FileRequest)
		{
			FileRequest fileRequest = ((FileRequest) request);
			File inputFile = fileRequest.getFile();
			valid = inputFile.length() != 0;
			int line = 0;
			try (BufferedReader bufferReader = new BufferedReader(new FileReader(inputFile)))
			{
				String input = null;
				while ((input = bufferReader.readLine()) != null)
				{
					valid = true;
					input = input.trim();
					if (!InputValidationUtil.validate(input))
					{
						valid = false;
						if (!fileRequest.isIgnoreInvalidLine())
							break;
					}
					validSet.set(line, valid);
					line++;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				valid = false;
			}
		}
		return valid;
	}

	@Override
	public boolean execute(Request request) throws ConferenceException
	{
		File inputFile = ((FileRequest) request).getFile();
		try (BufferedReader bufferReader = new BufferedReader(new FileReader(inputFile)))
		{
			String input = null;
			int line = 0;
			while ((input = bufferReader.readLine()) != null && validSet.get(line++))
			{
				input = input.trim();
				// System.out.println(line + "----" + input);
				Request interactiveRequest = ConferenceUtil.getInteractiveRequest(input);
				super.execute(interactiveRequest);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ConferenceException(ErrorMessage.INVALID_FILE, e);
		}
		return true;
	}
}
