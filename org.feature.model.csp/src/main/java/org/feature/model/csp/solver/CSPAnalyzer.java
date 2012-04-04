package org.feature.model.csp.solver;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.feature.model.csp.analyze.FeatureModelAnalyzer;
import org.feature.model.utilities.ResourceUtil;
import org.featuremapper.models.feature.FeatureModel;

public class CSPAnalyzer {

   private static Logger log = Logger.getLogger(CSPAnalyzer.class);

   /**
    * analyzes multiple files and creates statistics for them.
    * 
    * @param files
    */
   public static void analyze(List<IFile> files) {
      for (IFile file : files) {
      analyze(file);
         for (int j = 0; j < 10; j++) {
            satPerformanceMeasure(file);
         }
      }
   }

   public static void satPerformanceMeasure(IFile file){
      FeatureModel featureModel = ResourceUtil.getFeatureModel(file, new ResourceSetImpl());
      if (featureModel != null) {
         FeatureModelAnalyzer analyzer = new FeatureModelAnalyzer(featureModel);
         analyzer.isSatisfiable();
      }
   }
   
   /**
    * analyze method to create a statistic for the given featuremodel.
    * 
    * @param file
    */
   public static void analyze(IFile file) {
      Assert.isNotNull(file);
      log.info("--------------------------------------");
      log.info("--------------------------------------");
      log.info("Analyzing file '" + file.getName() + "'.");
      FeatureModel featureModel = ResourceUtil.getFeatureModel(file, new ResourceSetImpl());
      if (featureModel != null) {
         log.info("--------------------------------------");
         log.info("Featuremodel " + featureModel.getName());
         log.info("--------------------------------------");
         FeatureModelAnalyzer analyzer = new FeatureModelAnalyzer(featureModel);
         int numberOfAllFeatures = analyzer.getNumberOfAllFeatures();
         log.info("Number of total features         : " + numberOfAllFeatures);

//         int numberOfMandatoryFeatures = analyzer.getNumberOfMandatoryFeatures();
//         log.info("Number of mandatory features     : " + numberOfMandatoryFeatures);

         
//         int numberOfCoreFeature = analyzer.getNumberOfCoreFeatures();
//         String core = analyzer.getCoreFeaturesAsString();
//         log.info("Number of core features          : " + numberOfCoreFeature + " " + core);
//
//         int numberOfVariableFeature = analyzer.getNumberOfVariableFeatures();
//         log.info("Number of variable features      : " + numberOfVariableFeature);

         int numberOfAllCSPConstraints = analyzer.getNumberOfAllCSPConstraints();
         log.info("Number of cross-tree constraints : " + numberOfAllCSPConstraints);

         int constraintCoverage = analyzer.getConstraintCoverage();
         log.info("Constraint Coverage              : " + constraintCoverage+"%");

         boolean isConsistent = analyzer.isSatisfiable();
         log.info("The feature model is satisfiable : " + isConsistent);

//         int derivableVariants = analyzer.getNumberOfDerivableVariants();
//         log.info("Number of derivable variants     : " + derivableVariants);

//         log.info("--------------------------------------");
//         log.info("--------------------------------------");
//         log.info("--------------------------------------");
      } else {
         log.info("The file" + file.getName() + " does not represent a feature model");
      }
   }
}