/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.feature.multi.perspective.classification.resource.clt.ui;

/**
 * An adapter from the Eclipse
 * <code>org.eclipse.jface.text.rules.ITokenScanner</code> interface to the
 * generated lexer.
 */
public class CltTokenScanner implements org.eclipse.jface.text.rules.ITokenScanner {
	
	private org.feature.multi.perspective.classification.resource.clt.ICltTextScanner lexer;
	private org.feature.multi.perspective.classification.resource.clt.ICltTextToken currentToken;
	private java.util.List<org.feature.multi.perspective.classification.resource.clt.ICltTextToken> nextTokens;
	private int offset;
	private String languageId;
	private org.eclipse.jface.preference.IPreferenceStore store;
	private org.feature.multi.perspective.classification.resource.clt.ui.CltColorManager colorManager;
	private org.feature.multi.perspective.classification.resource.clt.ICltTextResource resource;
	
	/**
	 * 
	 * @param colorManager A manager to obtain color objects
	 */
	public CltTokenScanner(org.feature.multi.perspective.classification.resource.clt.ICltTextResource resource, org.feature.multi.perspective.classification.resource.clt.ui.CltColorManager colorManager) {
		this.resource = resource;
		this.colorManager = colorManager;
		this.lexer = new org.feature.multi.perspective.classification.resource.clt.mopp.CltMetaInformation().createLexer();
		this.languageId = new org.feature.multi.perspective.classification.resource.clt.mopp.CltMetaInformation().getSyntaxName();
		org.feature.multi.perspective.classification.resource.clt.ui.CltUIPlugin plugin = org.feature.multi.perspective.classification.resource.clt.ui.CltUIPlugin.getDefault();
		if (plugin != null) {
			this.store = plugin.getPreferenceStore();
		}
		this.nextTokens = new java.util.ArrayList<org.feature.multi.perspective.classification.resource.clt.ICltTextToken>();
	}
	
	public int getTokenLength() {
		return currentToken.getLength();
	}
	
	public int getTokenOffset() {
		return offset + currentToken.getOffset();
	}
	
	public org.eclipse.jface.text.rules.IToken nextToken() {
		boolean isOriginalToken = true;
		if (!nextTokens.isEmpty()) {
			currentToken = nextTokens.remove(0);
			isOriginalToken = false;
		} else {
			currentToken = lexer.getNextToken();
		}
		if (currentToken == null || !currentToken.canBeUsedForSyntaxHighlighting()) {
			return org.eclipse.jface.text.rules.Token.EOF;
		}
		
		if (isOriginalToken) {
			splitCurrentToken();
		}
		
		org.eclipse.jface.text.TextAttribute textAttribute = null;
		String tokenName = currentToken.getName();
		if (tokenName != null) {
			org.feature.multi.perspective.classification.resource.clt.ICltTokenStyle staticStyle = getStaticTokenStyle();
			// now call dynamic token styler to allow to apply modifications to the static
			// style
			org.feature.multi.perspective.classification.resource.clt.ICltTokenStyle dynamicStyle = getDynamicTokenStyle(staticStyle);
			if (dynamicStyle != null) {
				textAttribute = getTextAttribute(dynamicStyle);
			}
		}
		
		return new org.eclipse.jface.text.rules.Token(textAttribute);
	}
	
	public void setRange(org.eclipse.jface.text.IDocument document, int offset, int length) {
		this.offset = offset;
		try {
			lexer.setText(document.get(offset, length));
		} catch (org.eclipse.jface.text.BadLocationException e) {
			// ignore this error. It might occur during editing when locations are outdated
			// quickly.
		}
	}
	
	public String getTokenText() {
		return currentToken.getText();
	}
	
	public int[] convertToIntArray(org.eclipse.swt.graphics.RGB rgb) {
		if (rgb == null) {
			return null;
		}
		return new int[] {rgb.red, rgb.green, rgb.blue};
	}
	
	public org.feature.multi.perspective.classification.resource.clt.ICltTokenStyle getStaticTokenStyle() {
		org.feature.multi.perspective.classification.resource.clt.ICltTokenStyle staticStyle = null;
		String tokenName = currentToken.getName();
		String enableKey = org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.StyleProperty.ENABLE);
		boolean enabled = store.getBoolean(enableKey);
		if (enabled) {
			String colorKey = org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.StyleProperty.COLOR);
			org.eclipse.swt.graphics.RGB foregroundRGB = org.eclipse.jface.preference.PreferenceConverter.getColor(store, colorKey);
			org.eclipse.swt.graphics.RGB backgroundRGB = null;
			boolean bold = store.getBoolean(org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.StyleProperty.BOLD));
			boolean italic = store.getBoolean(org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.StyleProperty.ITALIC));
			boolean strikethrough = store.getBoolean(org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.StyleProperty.STRIKETHROUGH));
			boolean underline = store.getBoolean(org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.getPreferenceKey(languageId, tokenName, org.feature.multi.perspective.classification.resource.clt.ui.CltSyntaxColoringHelper.StyleProperty.UNDERLINE));
			staticStyle = new org.feature.multi.perspective.classification.resource.clt.mopp.CltTokenStyle(convertToIntArray(foregroundRGB), convertToIntArray(backgroundRGB), bold, italic, strikethrough, underline);
		}
		return staticStyle;
	}
	
	public org.feature.multi.perspective.classification.resource.clt.ICltTokenStyle getDynamicTokenStyle(org.feature.multi.perspective.classification.resource.clt.ICltTokenStyle staticStyle) {
		org.feature.multi.perspective.classification.resource.clt.mopp.CltDynamicTokenStyler dynamicTokenStyler = new org.feature.multi.perspective.classification.resource.clt.mopp.CltDynamicTokenStyler();
		org.feature.multi.perspective.classification.resource.clt.ICltTokenStyle dynamicStyle = dynamicTokenStyler.getDynamicTokenStyle(resource, currentToken, staticStyle);
		return dynamicStyle;
	}
	
	public org.eclipse.jface.text.TextAttribute getTextAttribute(org.feature.multi.perspective.classification.resource.clt.ICltTokenStyle tokeStyle) {
		int[] foregroundColorArray = tokeStyle.getColorAsRGB();
		org.eclipse.swt.graphics.Color foregroundColor = null;
		if (colorManager != null) {
			foregroundColor = colorManager.getColor(new org.eclipse.swt.graphics.RGB(foregroundColorArray[0], foregroundColorArray[1], foregroundColorArray[2]));
		}
		int[] backgroundColorArray = tokeStyle.getBackgroundColorAsRGB();
		org.eclipse.swt.graphics.Color backgroundColor = null;
		if (backgroundColorArray != null) {
			org.eclipse.swt.graphics.RGB backgroundRGB = new org.eclipse.swt.graphics.RGB(backgroundColorArray[0], backgroundColorArray[1], backgroundColorArray[2]);
			if (colorManager != null) {
				backgroundColor = colorManager.getColor(backgroundRGB);
			}
		}
		int style = org.eclipse.swt.SWT.NORMAL;
		if (tokeStyle.isBold()) {
			style = style | org.eclipse.swt.SWT.BOLD;
		}
		if (tokeStyle.isItalic()) {
			style = style | org.eclipse.swt.SWT.ITALIC;
		}
		if (tokeStyle.isStrikethrough()) {
			style = style | org.eclipse.jface.text.TextAttribute.STRIKETHROUGH;
		}
		if (tokeStyle.isUnderline()) {
			style = style | org.eclipse.jface.text.TextAttribute.UNDERLINE;
		}
		return new org.eclipse.jface.text.TextAttribute(foregroundColor, backgroundColor, style);
	}
	
	/**
	 * Tries to split the current token if it contains task items.
	 */
	public void splitCurrentToken() {
		final String text = currentToken.getText();
		final String name = currentToken.getName();
		final int line = currentToken.getLine();
		final int charStart = currentToken.getOffset();
		final int column = currentToken.getColumn();
		
		java.util.List<org.feature.multi.perspective.classification.resource.clt.mopp.CltTaskItem> taskItems = new org.feature.multi.perspective.classification.resource.clt.mopp.CltTaskItemDetector().findTaskItems(text, line, charStart);
		
		// this is the offset for the next token to be added
		int offset = charStart;
		int itemBeginRelative;
		java.util.List<org.feature.multi.perspective.classification.resource.clt.ICltTextToken> newItems = new java.util.ArrayList<org.feature.multi.perspective.classification.resource.clt.ICltTextToken>();
		for (org.feature.multi.perspective.classification.resource.clt.mopp.CltTaskItem taskItem : taskItems) {
			int itemBegin = taskItem.getCharStart();
			int itemLine = taskItem.getLine();
			int itemColumn = 0;
			
			itemBeginRelative = itemBegin - charStart;
			// create token before task item (TODO if required)
			String textBefore = text.substring(offset - charStart, itemBeginRelative);
			int textBeforeLength = textBefore.length();
			newItems.add(new org.feature.multi.perspective.classification.resource.clt.mopp.CltTextToken(name, textBefore, offset, textBeforeLength, line, column, true));
			
			// create token for the task item itself
			offset = offset + textBeforeLength;
			String itemText = taskItem.getKeyword();
			int itemTextLength = itemText.length();
			newItems.add(new org.feature.multi.perspective.classification.resource.clt.mopp.CltTextToken(org.feature.multi.perspective.classification.resource.clt.mopp.CltTokenStyleInformationProvider.TASK_ITEM_TOKEN_NAME, itemText, offset, itemTextLength, itemLine, itemColumn, true));
			
			offset = offset + itemTextLength;
		}
		
		if (!taskItems.isEmpty()) {
			// create token after last task item (TODO if required)
			String textAfter = text.substring(offset - charStart);
			newItems.add(new org.feature.multi.perspective.classification.resource.clt.mopp.CltTextToken(name, textAfter, offset, textAfter.length(), line, column, true));
		}
		
		if (!newItems.isEmpty()) {
			// replace tokens
			currentToken = newItems.remove(0);
			nextTokens = newItems;
		}
		
	}
}
