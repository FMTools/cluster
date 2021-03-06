/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.feature.multi.perspective.model.viewmodel.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.feature.multi.perspective.model.viewmodel.util.ViewmodelAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ViewmodelItemProviderAdapterFactory extends ViewmodelAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
   /**
    * This keeps track of the root adapter factory that delegates to this adapter factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ComposedAdapterFactory parentAdapterFactory;

   /**
    * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected IChangeNotifier changeNotifier = new ChangeNotifier();

   /**
    * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected Collection<Object> supportedTypes = new ArrayList<Object>();

   /**
    * This constructs an instance.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ViewmodelItemProviderAdapterFactory() {
      supportedTypes.add(IEditingDomainItemProvider.class);
      supportedTypes.add(IStructuredItemContentProvider.class);
      supportedTypes.add(ITreeItemContentProvider.class);
      supportedTypes.add(IItemLabelProvider.class);
      supportedTypes.add(IItemPropertySource.class);
      supportedTypes.add(IItemFontProvider.class);
   }

   /**
    * This keeps track of the one adapter used for all {@link org.feature.multi.perspective.model.viewmodel.CoreGroup} instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected CoreGroupItemProvider coreGroupItemProvider;

   /**
    * This creates an adapter for a {@link org.feature.multi.perspective.model.viewmodel.CoreGroup}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Adapter createCoreGroupAdapter() {
      if (coreGroupItemProvider == null) {
         coreGroupItemProvider = new CoreGroupItemProvider(this);
      }

      return coreGroupItemProvider;
   }

   /**
    * This keeps track of the one adapter used for all {@link org.feature.multi.perspective.model.viewmodel.AbstractGroup} instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected AbstractGroupItemProvider abstractGroupItemProvider;

   /**
    * This creates an adapter for a {@link org.feature.multi.perspective.model.viewmodel.AbstractGroup}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Adapter createAbstractGroupAdapter() {
      if (abstractGroupItemProvider == null) {
         abstractGroupItemProvider = new AbstractGroupItemProvider(this);
      }

      return abstractGroupItemProvider;
   }

   /**
    * This keeps track of the one adapter used for all {@link org.feature.multi.perspective.model.viewmodel.Group} instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected GroupItemProvider groupItemProvider;

   /**
    * This creates an adapter for a {@link org.feature.multi.perspective.model.viewmodel.Group}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Adapter createGroupAdapter() {
      if (groupItemProvider == null) {
         groupItemProvider = new GroupItemProvider(this);
      }

      return groupItemProvider;
   }

   /**
    * This keeps track of the one adapter used for all {@link org.feature.multi.perspective.model.viewmodel.ViewPoint} instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ViewPointItemProvider viewPointItemProvider;

   /**
    * This creates an adapter for a {@link org.feature.multi.perspective.model.viewmodel.ViewPoint}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Adapter createViewPointAdapter() {
      if (viewPointItemProvider == null) {
         viewPointItemProvider = new ViewPointItemProvider(this);
      }

      return viewPointItemProvider;
   }

   /**
    * This keeps track of the one adapter used for all {@link org.feature.multi.perspective.model.viewmodel.GroupModel} instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected GroupModelItemProvider groupModelItemProvider;

   /**
    * This creates an adapter for a {@link org.feature.multi.perspective.model.viewmodel.GroupModel}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Adapter createGroupModelAdapter() {
      if (groupModelItemProvider == null) {
         groupModelItemProvider = new GroupModelItemProvider(this);
      }

      return groupModelItemProvider;
   }

   /**
    * This keeps track of the one adapter used for all {@link org.feature.multi.perspective.model.viewmodel.ViewPointContainer} instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ViewPointContainerItemProvider viewPointContainerItemProvider;

   /**
    * This creates an adapter for a {@link org.feature.multi.perspective.model.viewmodel.ViewPointContainer}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Adapter createViewPointContainerAdapter() {
      if (viewPointContainerItemProvider == null) {
         viewPointContainerItemProvider = new ViewPointContainerItemProvider(this);
      }

      return viewPointContainerItemProvider;
   }

   /**
    * This returns the root adapter factory that contains this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ComposeableAdapterFactory getRootAdapterFactory() {
      return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
   }

   /**
    * This sets the composed adapter factory that contains this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
      this.parentAdapterFactory = parentAdapterFactory;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean isFactoryForType(Object type) {
      return supportedTypes.contains(type) || super.isFactoryForType(type);
   }

   /**
    * This implementation substitutes the factory itself as the key for the adapter.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Adapter adapt(Notifier notifier, Object type) {
      return super.adapt(notifier, this);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object adapt(Object object, Object type) {
      if (isFactoryForType(type)) {
         Object adapter = super.adapt(object, type);
         if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
            return adapter;
         }
      }

      return null;
   }

   /**
    * This adds a listener.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void addListener(INotifyChangedListener notifyChangedListener) {
      changeNotifier.addListener(notifyChangedListener);
   }

   /**
    * This removes a listener.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void removeListener(INotifyChangedListener notifyChangedListener) {
      changeNotifier.removeListener(notifyChangedListener);
   }

   /**
    * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void fireNotifyChanged(Notification notification) {
      changeNotifier.fireNotifyChanged(notification);

      if (parentAdapterFactory != null) {
         parentAdapterFactory.fireNotifyChanged(notification);
      }
   }

   /**
    * This disposes all of the item providers created by this factory. 
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void dispose() {
      if (coreGroupItemProvider != null) coreGroupItemProvider.dispose();
      if (abstractGroupItemProvider != null) abstractGroupItemProvider.dispose();
      if (viewPointItemProvider != null) viewPointItemProvider.dispose();
      if (groupModelItemProvider != null) groupModelItemProvider.dispose();
      if (viewPointContainerItemProvider != null) viewPointContainerItemProvider.dispose();
      if (groupItemProvider != null) groupItemProvider.dispose();
   }

}
