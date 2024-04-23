package ga.crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ec.crossover.Crossover;
import ec.evolution.Individual;
import ga.algorithm.GAConstants;
import ga.evolution.IndividualGA;

public class CombinedCrossoverImpl implements Crossover {

  @Override
  public List<Individual> cross(Individual parent1, Individual parent2) {
    List<Individual> crossedIndividuals =new ArrayList<>();
    IndividualGA parentGA1=(IndividualGA) parent1;
    IndividualGA parentGA2=(IndividualGA) parent2;
    double[] valuesParent1=parentGA1.getValues();
    double[] valuesParent2=parentGA2.getValues();
    double[] valuesDescendant1=new double[valuesParent1.length];
    double[] valuesDescendant2=new double[valuesParent2.length];
    for(int i=0;i<valuesParent1.length;i++){
      double[] genDescendants=cross(valuesParent1[i],valuesParent2[i]);
      valuesDescendant1[i]=genDescendants[0];
      valuesDescendant2[i]=genDescendants[1];
    }
    Individual crossedIndividual1=new IndividualGA(valuesDescendant1,parent1.getDerivationTree());
    Individual crossedIndividual2=new IndividualGA(valuesDescendant2,parent2.getDerivationTree());
    crossedIndividuals.add(crossedIndividual1);
    crossedIndividuals.add(crossedIndividual2);
    return crossedIndividuals;
  }

  private double[] cross(double valueParent1, double valueParent2){
    double minValueParents;
    double maxValueParents;
    if(valueParent1<=valueParent2){
      minValueParents=valueParent1;
      maxValueParents=valueParent2;
    } else {
      minValueParents=valueParent2;
      maxValueParents=valueParent1;
    }
    double difference=maxValueParents-minValueParents;
    double minCrossoverInterval=minValueParents-GAConstants.GA_THRESHOLD_CROSSOVER_INTERVAL*difference;
    double maxCrossoverInterval=maxValueParents+GAConstants.GA_THRESHOLD_CROSSOVER_INTERVAL*difference;
    Random r = new Random();
    double valuesDescendant1 = minCrossoverInterval + (maxCrossoverInterval - minCrossoverInterval) * r.nextDouble();
    double valuesDescendant2 = minCrossoverInterval + (maxCrossoverInterval - minCrossoverInterval) * r.nextDouble();
    double[] genDescendants=new double[]{valuesDescendant1,valuesDescendant2};
    return genDescendants;
  }

  @Override
  public Float getCrossoverProbability() {
    return GAConstants.GA_CROSSOVER_PROBABILITY;
  }
}
