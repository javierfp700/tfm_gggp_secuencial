package ec.crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ec.evolution.Individual;

public interface Crossover {

  default List<Individual> cross(List<Individual> selectedIndividuals) {
    List<Individual> crossedIndividuals =new ArrayList<>();
    for(int i=0;i< selectedIndividuals.size();i=i+2){
      Individual parent1= selectedIndividuals.get(i);
      Individual parent2= selectedIndividuals.get(i+1);
      Random rand = new Random();
      float randFloat = rand.nextFloat();
      if(randFloat<getCrossoverProbability()){
        List<Individual> descendants=cross(parent1,parent2);
        crossedIndividuals.addAll(descendants);
      } else {
        crossedIndividuals.add(parent1);
        crossedIndividuals.add(parent2);
      }
    }
    return crossedIndividuals;
  }

  List<Individual> cross(Individual parent1, Individual parent2);

  Float getCrossoverProbability();

}
