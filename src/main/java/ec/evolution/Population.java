package ec.evolution;

import java.util.ArrayList;
import java.util.List;

public class Population {

  private List<Individual> individuals;

  public Population(){
    this.individuals =new ArrayList<>();
  }

  public List<Individual> getIndividuals(){
    return individuals;
  }

  public void addIndividual(Individual individual){
    individuals.add(individual);
  }

  /**
   * Evaluate individuals of population
   */
  public void evaluate(String opponent){
    for(Individual individual : individuals){
      if(individual.getFitness()==Float.NEGATIVE_INFINITY){
        individual.evaluate(opponent);
      }
    }
  }

  public Individual getBestIndividual(){
    Individual bestIndividual =null;
    for(Individual individual : individuals){
      if(bestIndividual ==null || individual.getFitness()> bestIndividual.getFitness()){
        bestIndividual = individual;
      }
    }
    return bestIndividual;
  }



}
