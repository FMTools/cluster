/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.emftext.term.propositional.expression.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.emftext.term.propositional.expression.BinaryOperator;
import org.emftext.term.propositional.expression.TermFactory;
import org.emftext.term.propositional.expression.TermPackage;

/**
 * This is the item provider adapter for a {@link org.emftext.term.propositional.expression.BinaryOperator} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class BinaryOperatorItemProvider
   extends TermItemProvider
   implements
      IEditingDomainItemProvider,
      IStructuredItemContentProvider,
      ITreeItemContentProvider,
      IItemLabelProvider,
      IItemPropertySource {
   /**
    * This constructs an instance from a factory and a notifier.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BinaryOperatorItemProvider(AdapterFactory adapterFactory) {
      super(adapterFactory);
   }

   /**
    * This returns the property descriptors for the adapted class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
      if (itemPropertyDescriptors == null) {
         super.getPropertyDescriptors(object);

      }
      return itemPropertyDescriptors;
   }

   /**
    * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
    * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
    * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
      if (childrenFeatures == null) {
         super.getChildrenFeatures(object);
         childrenFeatures.add(TermPackage.Literals.BINARY_OPERATOR__OPERAND1);
         childrenFeatures.add(TermPackage.Literals.BINARY_OPERATOR__OPERAND2);
      }
      return childrenFeatures;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EStructuralFeature getChildFeature(Object object, Object child) {
      // Check the type of the specified child object and return the proper feature to use for
      // adding (see {@link AddCommand}) it as a child.

      return super.getChildFeature(object, child);
   }

   /**
    * This returns the label text for the adapted class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String getText(Object object) {
      return getString("_UI_BinaryOperator_type");
   }

   /**
    * This handles model notifications by calling {@link #updateChildren} to update any cached
    * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void notifyChanged(Notification notification) {
      updateChildren(notification);

      switch (notification.getFeatureID(BinaryOperator.class)) {
         case TermPackage.BINARY_OPERATOR__OPERAND1:
         case TermPackage.BINARY_OPERATOR__OPERAND2:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
      }
      super.notifyChanged(notification);
   }

   /**
    * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
    * that can be created under this object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
      super.collectNewChildDescriptors(newChildDescriptors, object);

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND1,
             TermFactory.eINSTANCE.createAnd()));

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND1,
             TermFactory.eINSTANCE.createOr()));

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND1,
             TermFactory.eINSTANCE.createNot()));

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND1,
             TermFactory.eINSTANCE.createFeatureRef()));

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND1,
             TermFactory.eINSTANCE.createNested()));

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND2,
             TermFactory.eINSTANCE.createAnd()));

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND2,
             TermFactory.eINSTANCE.createOr()));

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND2,
             TermFactory.eINSTANCE.createNot()));

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND2,
             TermFactory.eINSTANCE.createFeatureRef()));

      newChildDescriptors.add
         (createChildParameter
            (TermPackage.Literals.BINARY_OPERATOR__OPERAND2,
             TermFactory.eINSTANCE.createNested()));
   }

   /**
    * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
      Object childFeature = feature;
      Object childObject = child;

      boolean qualify =
         childFeature == TermPackage.Literals.BINARY_OPERATOR__OPERAND1 ||
         childFeature == TermPackage.Literals.BINARY_OPERATOR__OPERAND2;

      if (qualify) {
         return getString
            ("_UI_CreateChild_text2",
             new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
      }
      return super.getCreateChildText(owner, feature, child, selection);
   }

}
