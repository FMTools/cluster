/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.emftext.term.propositional.expression.resource.expression.ui;

public class ExpressionOutlinePageTypeSortingAction extends org.emftext.term.propositional.expression.resource.expression.ui.AbstractExpressionOutlinePageAction {
	
	public ExpressionOutlinePageTypeSortingAction(org.emftext.term.propositional.expression.resource.expression.ui.ExpressionOutlinePageTreeViewer treeViewer) {
		super(treeViewer, "Group types", org.eclipse.jface.action.IAction.AS_CHECK_BOX);
		initialize("icons/group_types_icon.gif");
	}
	
	public void runInternal(boolean on) {
		getTreeViewerComparator().setGroupTypes(on);
		getTreeViewer().refresh();
	}
	
}
