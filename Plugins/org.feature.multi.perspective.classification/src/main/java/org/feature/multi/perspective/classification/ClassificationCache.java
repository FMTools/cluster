package org.feature.multi.perspective.classification;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.feature.multi.perspective.mapping.viewmapping.MappingModel;
import org.feature.multi.perspective.model.viewmodel.AbstractGroup;
import org.feature.multi.perspective.view.View;
import org.feature.multi.perspective.view.provider.ViewBuilder;
import org.featuremapper.models.feature.Feature;

public class ClassificationCache {

   private static ClassificationCache cache;

   private Map<String, ViewBuilder> viewBuilders = new HashMap<String, ViewBuilder>();

   public static ClassificationCache getInstance() {
      if (cache == null) {
         cache = new ClassificationCache();
      }
      return cache;
   }

   private ViewBuilder getViewBuilder(EObject classificationObject) {
      ViewBuilder result = null;
      EObject rootContainer = EcoreUtil.getRootContainer(classificationObject);
      if (rootContainer instanceof ClassificationModel) {
         ClassificationModel model = (ClassificationModel) rootContainer;
         MappingModel viewMapping = model.getViewMapping();
         String resourceUri = viewMapping.eResource().getURI().toString();
         if (!viewBuilders.containsKey(resourceUri)) {
            initViewBuilder(model, resourceUri);
         }
         result = viewBuilders.get(resourceUri);
      }
      return result;
   }

   private void initViewBuilder(ClassificationModel model, String key) {
      MappingModel viewMapping = model.getViewMapping();
      ViewBuilder viewBuilder = new ViewBuilder(viewMapping, false);
      this.viewBuilders.put(key, viewBuilder);
   }

   /**
    * get the corresponding view for a classification.
    * 
    * @param classification
    * @return
    */
   private View getView(Classification classification) {
      ViewBuilder viewBuilder = getViewBuilder(classification);
      AbstractGroup viewgroup = classification.getViewgroup();
      return viewBuilder.getView(viewgroup);
   }

   /**
    * determine if a feature is contained in a view.
    * 
    * @param classification
    * @param feature
    * @return
    */
   public boolean isFeatureContainedInView(Classification classification, Feature feature) {
      AbstractGroup viewgroup = classification.getViewgroup();
      ViewBuilder viewBuilder = getViewBuilder(classification);
      return viewBuilder.isFeatureContained(feature, viewgroup);
   }

}