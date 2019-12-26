/**
 * 
 */
package com.thoughtworks.conferencemgmt.parser;

/**
 * @author vaibhav.singh
 *
 */
@FunctionalInterface
public interface ConferenceParser<T>
{
	public T parseInput(String text);
}
