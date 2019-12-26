/**
 * 
 */
package com.thoughtworks.conferencemgmt.bo;

import java.io.File;

/**
 * 
 * @author vaibhav.singh
 *
 */
public class FileRequest implements Request
{
	private File    file;
	private boolean ignoreInvalidLine;

	public FileRequest(File file, boolean ignoreInvalidLine)
	{
		this.file = file;
		this.ignoreInvalidLine = ignoreInvalidLine;
	}

	/**
	 * @return the file
	 */
	public File getFile()
	{
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file)
	{
		this.file = file;
	}

	/**
	 * @return the ignoreInvalidLine
	 */
	public boolean isIgnoreInvalidLine()
	{
		return ignoreInvalidLine;
	}

	/**
	 * @param ignoreInvalidLine the ignoreInvalidLine to set
	 */
	public void setIgnoreInvalidLine(boolean ignoreInvalidLine)
	{
		this.ignoreInvalidLine = ignoreInvalidLine;
	}
}
