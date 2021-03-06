/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.term.propositional.expression.resource.expression.grammar;

/**
 * The abstract super class for all elements of a grammar. This class provides
 * methods to traverse the grammar rules.
 */
public abstract class ExpressionSyntaxElement {
	
	private ExpressionSyntaxElement[] children;
	private ExpressionSyntaxElement parent;
	private org.emftext.term.propositional.expression.resource.expression.grammar.ExpressionCardinality cardinality;
	
	public ExpressionSyntaxElement(org.emftext.term.propositional.expression.resource.expression.grammar.ExpressionCardinality cardinality, ExpressionSyntaxElement[] children) {
		this.cardinality = cardinality;
		this.children = children;
		if (this.children != null) {
			for (ExpressionSyntaxElement child : this.children) {
				child.setParent(this);
			}
		}
	}
	
	public void setParent(ExpressionSyntaxElement parent) {
		assert this.parent == null;
		this.parent = parent;
	}
	
	public ExpressionSyntaxElement[] getChildren() {
		if (children == null) {
			return new ExpressionSyntaxElement[0];
		}
		return children;
	}
	
	public org.eclipse.emf.ecore.EClass getMetaclass() {
		return parent.getMetaclass();
	}
	
	public org.emftext.term.propositional.expression.resource.expression.grammar.ExpressionCardinality getCardinality() {
		return cardinality;
	}
	
}
