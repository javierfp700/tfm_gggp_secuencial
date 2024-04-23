package ec.replacement;

import java.util.Collections;
import java.util.List;
import ec.evolution.Individual;
import ec.evolution.Population;
import ec.evolution.SortByFitness;

public class SteadyStateReplacementImpl implements Replacement {

  public Float replacedPopulationPercentage;

  public SteadyStateReplacementImpl(Float replacedPopulationPercentage){
    this.replacedPopulationPercentage=replacedPopulationPercentage;
  }

  @Override
  public void replace(Population population, List<Individual> mutatedIndividuals){
    int individualsNumberToReplace=Math.round(population.getIndividuals().size() * replacedPopulationPercentage);
    removeWorstIndividuals(population,individualsNumberToReplace);
    addMutatedDescendants(population, mutatedIndividuals,individualsNumberToReplace);
  }

  private void removeWorstIndividuals(Population population, int individualsNumberToRemove){
    Collections.sort(population.getIndividuals(),new SortByFitness());
    population.getIndividuals().subList(0,individualsNumberToRemove).clear();
  }

  private void addMutatedDescendants(Population population,List<Individual> mutatedIndividualGPS, int individualsNumberToReplace){
    for (int i=0;i<individualsNumberToReplace;i++){
      population.addIndividual(mutatedIndividualGPS.get(i));
    }
  }

}
