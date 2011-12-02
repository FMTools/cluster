/**
 * 
 */
package org.feature.cluster.model.editor.editors;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.feature.cluster.model.cluster.CoreGroup;
import org.feature.cluster.model.cluster.Group;
import org.feature.cluster.model.cluster.GroupModel;
import org.featuremapper.models.feature.Feature;
import org.featuremapper.models.featuremapping.And;
import org.featuremapper.models.featuremapping.ElementMapping;
import org.featuremapper.models.featuremapping.FeatureRef;
import org.featuremapper.models.featuremapping.Mapping;
import org.featuremapper.models.featuremapping.Term;

/**
 * This class creates the views for every {@link Group} and the {@link CoreGroup} from the {@link GroupModel}.
 * @author Tim Winkelmann
 *
 */
public class ViewCreater {
	private CoreGroup coreGroup;
	private EList<Mapping> mappings;
	private List<View> views;
	private List<Feature> mappedFeatures = new LinkedList<Feature>();
	
	/**
	 * initializes the attributes
	 * @param mappings the mapping
	 * @param coreGroup the core Group
	 * @param featureModel the feature model
	 */
	public ViewCreater(EList<Feature> allFeatures,EList<Mapping> mappings,CoreGroup coreGroup) {
		this.coreGroup = coreGroup;
		this.mappings = mappings;
		//<Name of Group,  {@link Group}||{@link DefaultGroup}>
		List<EObject> listOfGroups = createMapOfGroups();
		views = createViews(listOfGroups);
		for (View view : views) {
			if (view.group.equals(coreGroup)) {
				allFeatures.removeAll(mappedFeatures);
				view.features.addAll(allFeatures);
			}
		}
	}
	
	/**
	 * create the views
	 * @param mapOfGroups
	 */
	private List<View> createViews(List<EObject> groups) {
		List<View> views = new LinkedList<View>();
		for (EObject group : groups) {
			views.add(new View(group, getFeatures(group)));
		}
		return views;
	}
	
	/**
	 * finds the features mapped to the group. If no mapping exists the list will be empty.
	 * @param group the {@link Group} or {@link CoreGroup}.
	 * @return a set of features or an empty list.
	 */
	private Set<Feature> getFeatures(EObject group) {
		Set<Feature> features = new HashSet<Feature>();
		for (Mapping mapping : mappings) {
			if (mapping instanceof ElementMapping) {
				ElementMapping elementMapping = (ElementMapping) mapping;
				EObject element = elementMapping.getElement();
				if (element.equals(group)) {
					Term term = elementMapping.getTerm();
					if (term instanceof FeatureRef) {
						FeatureRef featureRef = (FeatureRef) term;
						features.add(featureRef.getFeature());
						mappedFeatures.add(featureRef.getFeature());
					}else{
						if (term instanceof And) {
							And and = (And) term;
							EList<Term> terms = and.getTerms();
							for (Term andTerm : terms) {
								if (andTerm instanceof FeatureRef) {
									FeatureRef featureRef = (FeatureRef) andTerm;
									features.add(featureRef.getFeature());
									mappedFeatures.add(featureRef.getFeature());
								}
							}
						}
					}
				}
			}
		}
		return features;
	}

	/**
	 * creates a list of groups.
	 * @return a list of groups.
	 */
	private List<EObject> createMapOfGroups() {
		List<EObject> listOfGroups = new LinkedList<EObject>();
		listOfGroups.add(coreGroup);
		EList<Group> groups = coreGroup.getGroups();
		createMapOfGroups(groups,listOfGroups);
		return listOfGroups;
	}
	
	/**
	 * used to create the map of groups.
	 * @param groups the groups that will be added
	 * @param listOfGroups the map.
	 */
	private void createMapOfGroups(EList<Group> groups, List<EObject> listOfGroups) {
		for (Group group : groups) {
			listOfGroups.add(group);
			if (!group.getGroups().isEmpty()) {
				createMapOfGroups(group.getGroups(), listOfGroups);
			}
		}
	}

	/**
	 * @return the views
	 */
	public List<View> getViews() {
		return views;
	}
}
