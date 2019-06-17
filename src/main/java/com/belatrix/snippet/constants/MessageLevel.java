package com.belatrix.snippet.constants;

/**
 * Hierarchy from most to least severity : ERROR, WARNING, MESSAGE
 * 
 * @author beto
 *
 */
public enum MessageLevel {
	MESSAGE("Message", 0), WARNING("Warning", 1), ERROR("Error", 2);

	private String message;
	private int severity;

	private MessageLevel(String msg, int s) {
		message = msg;
		severity = s;
	}

	@Override
	public String toString() {
		return message;
	}

	public int getSeverity() {
		return severity;
	}
	
	
}
