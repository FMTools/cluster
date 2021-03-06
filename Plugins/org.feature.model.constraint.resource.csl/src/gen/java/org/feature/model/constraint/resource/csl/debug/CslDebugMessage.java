/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.feature.model.constraint.resource.csl.debug;

/**
 * DebugMessages are exchanged between the debug server (the Eclipse debug
 * framework) and the debug client (a running process or interpreter). To exchange
 * messages they are serialized and sent over sockets.
 */
public class CslDebugMessage {
	
	private static final char DELIMITER = ':';
	private org.feature.model.constraint.resource.csl.debug.ECslDebugMessageTypes messageType;
	private String[] arguments;
	
	public CslDebugMessage(org.feature.model.constraint.resource.csl.debug.ECslDebugMessageTypes messageType, String[] arguments) {
		super();
		this.messageType = messageType;
		this.arguments = arguments;
	}
	
	public CslDebugMessage(org.feature.model.constraint.resource.csl.debug.ECslDebugMessageTypes messageType, java.util.List<String> arguments) {
		super();
		this.messageType = messageType;
		this.arguments = new String[arguments.size()];
		for (int i = 0; i < arguments.size(); i++) {
			this.arguments[i] = arguments.get(i);
		}
	}
	
	public org.feature.model.constraint.resource.csl.debug.ECslDebugMessageTypes getMessageType() {
		return messageType;
	}
	
	public String[] getArguments() {
		return arguments;
	}
	
	public String serialize() {
		java.util.List<String> parts = new java.util.ArrayList<String>();
		parts.add(messageType.name());
		for (String argument : arguments) {
			parts.add(argument);
		}
		return org.feature.model.constraint.resource.csl.util.CslStringUtil.encode(DELIMITER, parts);
	}
	
	public static CslDebugMessage deserialize(String response) {
		java.util.List<String> parts = org.feature.model.constraint.resource.csl.util.CslStringUtil.decode(response, DELIMITER);
		String messageType = parts.get(0);
		String[] arguments = new String[parts.size() - 1];
		for (int i = 1; i < parts.size(); i++) {
			arguments[i - 1] = parts.get(i);
		}
		org.feature.model.constraint.resource.csl.debug.ECslDebugMessageTypes type = org.feature.model.constraint.resource.csl.debug.ECslDebugMessageTypes.valueOf(messageType);
		CslDebugMessage message = new CslDebugMessage(type, arguments);
		return message;
	}
	
	public boolean hasType(org.feature.model.constraint.resource.csl.debug.ECslDebugMessageTypes type) {
		return this.messageType == type;
	}
	
	public String getArgument(int index) {
		return getArguments()[index];
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + "[" + messageType.name() + ": " + org.feature.model.constraint.resource.csl.util.CslStringUtil.explode(arguments, ", ") + "]";
	}
	
}
