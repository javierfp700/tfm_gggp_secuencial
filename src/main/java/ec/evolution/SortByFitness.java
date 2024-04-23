package ec.evolution;

import java.util.Comparator;

public class SortByFitness implements Comparator<Individual> {


  @Override
  public int compare(Individual individual1, Individual individual2) {
    if(individual1.getFitness() < individual2.getFitness()){
      return -1;
    } else if(individual1.getFitness() > individual2.getFitness()){
      return 1;
    } else {
      return 0;
    }
  }
}
