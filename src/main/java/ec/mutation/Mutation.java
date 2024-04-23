package ec.mutation;

import java.util.List;
import java.util.Random;
import ec.evolution.Individual;

public interface Mutation {

  default void mutate(List<Individual> crossedIndividuals){
    for(Individual crossedIndividual : crossedIndividuals){
      Random rand = new Random();
      float randFloat = rand.nextFloat();
      if(randFloat<getMutationProbability()){
        mutate(crossedIndividual);
      }
    }
  }

  void mutate(Individual individual);

  Float getMutationProbability();
}
