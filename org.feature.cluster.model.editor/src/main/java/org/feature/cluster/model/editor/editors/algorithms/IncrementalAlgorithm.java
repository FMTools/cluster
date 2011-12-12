/**
 * 
 */
package org.feature.cluster.model.editor.editors.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.feature.cluster.model.cluster.Group;
import org.feature.cluster.model.cluster.GroupModel;
import org.feature.cluster.model.cluster.IGroupContainer;
import org.feature.cluster.model.cluster.ViewPoint;
import org.feature.cluster.model.editor.editors.View;
import org.feature.cluster.model.editor.util.Util;
import org.featuremapper.models.feature.Feature;


/**
 * @author Tim Winkelmann
 *
 */
public class IncrementalAlgorithm {
	private static Logger log = Logger.getLogger(IncrementalAlgorithm.class);
	private HashMap<EObject, View> views;
	private GroupModel groupModel;
	private UsedGroup ugCG;
	
//	private int i = 0; //used for tests
	
	/**
	 * 
	 * @param views
	 * @param groupModel
	 */
	public IncrementalAlgorithm(List<View> views, GroupModel groupModel) {
		HashMap<EObject, View> views2 = new HashMap<EObject, View>();
		for (View view : views) {
			views2.put(view.getGroup(), view);
		}
		this.views = views2;
		this.groupModel = groupModel;
		run();
	}
	
	/**
	 * 
	 */
	private void run() {
		EList<ViewPoint> viewPoints = groupModel.getViewPoints().getViewPoints();
		log.info("Number of ViewPoints: " + viewPoints.size());
		Set<Group> groups = new HashSet<Group>();
		//get important groups
		for (ViewPoint viewPoint : viewPoints) {
			groups.addAll(viewPoint.getContainedInGroup());
		}
		Map<IGroupContainer,UsedGroup> usedGroups = createMSGM(groups);
		//
		for (Group g : ugCG.getGroup().getGroups()) {
			if (usedGroups.containsKey(g)) {
				UsedGroup msg = usedGroups.get(g);
				msg.setConsistent(Util.isConsistent(msg.getFeatures()));
				msg.setDone();
//				i++;//used for tests
				checkGroupModel(g,usedGroups);
			}
		}
		//Display results:
		for (ViewPoint vp : viewPoints) {
			log.info("Viewpoint: " + vp.getName());
			boolean isCon = true;
			EList<Group> groups2 = vp.getContainedInGroup();
			for (Group group : groups2) {
				UsedGroup usedGroup = usedGroups.get(group);
				boolean consistent = usedGroup.isConsistent();
				log.info("View: " + group.getName() + ":" + consistent);
				if (isCon && !consistent) {
					isCon = false;
				}
			}
			if (isCon) {
				log.info("Viewpoint: " + vp.getName() + " is consistent\n");
			}else{
				log.info("Viewpoint: " + vp.getName() + " is not consistent\n");
			}
		}
	}

	/**
	 * 
	 * @param group
	 * @param usedGroups
	 */
	private void checkGroupModel(Group group, Map<IGroupContainer,UsedGroup> usedGroups) {
		for(Group g : group.getGroups()){
			if (usedGroups.containsKey(g)) {
				UsedGroup ug = usedGroups.get(g);
				ug.setConsistent(Util.isConsistent(ug.getFeatures()));
				ug.setDone();
//				i++;//used for tests
				checkGroupModel(g,usedGroups);
			}
		}
	}

	/**
	 * uses the most specific groups to create a new group model
	 * @param groups most specific groups
	 * @return
	 */
	private Map<IGroupContainer,UsedGroup> createMSGM(Set<Group> groups) {
		Map<IGroupContainer,UsedGroup> usedGroups = new HashMap<IGroupContainer, UsedGroup>();
		ugCG = new UsedGroup(null, groupModel.getCoreGroup(),views.get(groupModel.getCoreGroup()).getFeatures());
		ugCG.setDone();
		ugCG.setConsistent(Util.isConsistent(ugCG.getFeatures()));
		usedGroups.put(groupModel.getCoreGroup(), ugCG);
		for (Group group : groups) {
			if (!usedGroups.containsKey(group)) {
				UsedGroup ugParent;
				if (group.getParentGroup().equals(groupModel.getCoreGroup())) {
					ugParent = ugCG;
				}else{
					ugParent = createMSG((Group)group.getParentGroup(),usedGroups);
				}
				
				Set<Feature> features = new HashSet<Feature>();
				features.addAll(ugParent.getFeatures());
				features.addAll(views.get(group).getFeatures());
				UsedGroup ug = new UsedGroup(ugParent, group, features);
				usedGroups.put(group, ug);
			}
		}
		return usedGroups;
	}
	
	/**
	 * create a {@link UsedGroup} and his parents (except the ugCG) from the {@link Group} and adds it and his parents to the list.
	 * @param group a most specific group.
	 * @param ugs a list of most specific group.
	 * @return the most specific group.
	 */
	private UsedGroup createMSG(Group group,Map<IGroupContainer,UsedGroup> ugs) {
		if (ugs.containsKey(group)) {
			return ugs.get(group);
		}
		UsedGroup ugParent;
		if (group.getParentGroup().equals(groupModel.getCoreGroup())) {
			ugParent = ugCG;
		}else{
			ugParent = createMSG((Group)group.getParentGroup(),ugs);
		}
		
		Set<Feature> features = new HashSet<Feature>();
		features.addAll(ugParent.getFeatures());
		features.addAll(views.get(group).getFeatures());
		UsedGroup ug = new UsedGroup(ugParent, group, features);
		ugs.put(group, ug);
		return ug;
	}
}
