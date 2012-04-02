/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.feature.cluster.model.cluster;

import java.util.Map;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.feature.cluster.model.cluster.Group#getViewPointReference <em>View Point Reference</em>}</li>
 *   <li>{@link org.feature.cluster.model.cluster.Group#getParentGroup <em>Parent Group</em>}</li>
 *   <li>{@link org.feature.cluster.model.cluster.Group#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.feature.cluster.model.cluster.ClusterPackage#getGroup()
 * @model
 * @generated
 */
public interface Group extends Element {

	/**
    * Returns the value of the '<em><b>View Point Reference</b></em>' reference list.
    * The list contents are of type {@link org.feature.cluster.model.cluster.ViewPoint}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Point Reference</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>View Point Reference</em>' reference list.
    * @see org.feature.cluster.model.cluster.ClusterPackage#getGroup_ViewPointReference()
    * @model
    * @generated
    */
	EList<ViewPoint> getViewPointReference();

	/**
    * Returns the value of the '<em><b>Parent Group</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.feature.cluster.model.cluster.Group#getGroups <em>Groups</em>}'.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Group</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Parent Group</em>' container reference.
    * @see #setParentGroup(Group)
    * @see org.feature.cluster.model.cluster.ClusterPackage#getGroup_ParentGroup()
    * @see org.feature.cluster.model.cluster.Group#getGroups
    * @model opposite="groups" transient="false"
    * @generated
    */
	Group getParentGroup();

	/**
    * Sets the value of the '{@link org.feature.cluster.model.cluster.Group#getParentGroup <em>Parent Group</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Parent Group</em>' container reference.
    * @see #getParentGroup()
    * @generated
    */
   void setParentGroup(Group value);

   /**
    * Returns the value of the '<em><b>Groups</b></em>' containment reference list.
    * The list contents are of type {@link org.feature.cluster.model.cluster.Group}.
    * It is bidirectional and its opposite is '{@link org.feature.cluster.model.cluster.Group#getParentGroup <em>Parent Group</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Groups</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Groups</em>' containment reference list.
    * @see org.feature.cluster.model.cluster.ClusterPackage#getGroup_Groups()
    * @see org.feature.cluster.model.cluster.Group#getParentGroup
    * @model opposite="parentGroup" containment="true"
    * @generated
    */
   EList<Group> getGroups();

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @model required="true" eDiagnosticChainRequired="true" eMapRequired="true"
    * @generated
    */
	boolean checkForRedundantViewPoints(DiagnosticChain eDiagnosticChain, Map<?, ?> eMap);
} // Group
