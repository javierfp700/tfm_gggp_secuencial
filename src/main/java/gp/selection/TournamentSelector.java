package gp.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ec.evolution.Individual;
import gp.algorithm.GPConstants;
import ec.evolution.Population;

public class TournamentSelector {

  public static List<Individual> selection(Population population){
    List<Individual> selectedIndividuals =new ArrayList<>();
    int neededParentsNumber=calculateNeededParentsNumber(population.getIndividuals().size());
    for(int i=0;i<neededParentsNumber;i++){
      Individual selectedIndividual=tournamentSelection(population);
      selectedIndividuals.add(selectedIndividual);
    }
    return selectedIndividuals;
  }

  private static int calculateNeededParentsNumber(int populationSize){
    int neededDescendantsNumber=Math.round(populationSize*GPConstants.GP_REPLACED_POPULATION_PERCENTAGE);
    if(neededDescendantsNumber%2==0){
      return neededDescendantsNumber;
    } else {
      return neededDescendantsNumber+1;
    }
  }

  private static Individual tournamentSelection(Population population){
    List<Individual> individuals =new ArrayList<>(population.getIndividuals());
    Individual selectedIndividual =null;
    for(int i=0;i<GPConstants.GP_TOURNAMENT_SIZE;i++){
      Random rand = new Random();
      Individual randomIndividual = individuals.get(rand.nextInt(individuals.size()));
      if(selectedIndividual == null || selectedIndividual.getFitness()< randomIndividual.getFitness()){
        selectedIndividual = randomIndividual;
      }
      individuals.remove(randomIndividual);
    }
    return selectedIndividual;
  }

}
