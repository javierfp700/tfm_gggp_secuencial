package ga.initialization;

import java.util.Random;
import ec.evolution.Individual;
import ec.initialization.PopulationGenerator;
import ga.algorithm.GAConstants;
import ga.evolution.IndividualGA;
import ec.evolution.Population;
import gp.structure.DerivationTree;

public class GAPopulationGeneratorImpl implements PopulationGenerator {

  private int realValuesCount;
  private DerivationTree derivationTree;

  public GAPopulationGeneratorImpl(int realValuesCount, DerivationTree derivationTree){
    this.realValuesCount=realValuesCount;
    this.derivationTree=derivationTree;
  }

  @Override
  public Population initializePopulation() {
    Population population=new Population();
    while(population.getIndividuals().size()< GAConstants.GA_POPULATION_SIZE){
      double[] values = generateRandomValues(realValuesCount);
      Individual individualGA=new IndividualGA(values,derivationTree);
      population.addIndividual(individualGA);
    }
    return population;
  }

  private double[] generateRandomValues(int size){
    Random r = new Random();
    double[] values=new double[size];
    for(int i=0;i<values.length;i++){
      values[i]=r.nextDouble();
    }
    return values;
  }
}
