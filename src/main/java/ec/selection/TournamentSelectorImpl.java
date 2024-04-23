package ec.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.SerializationUtils;
import ec.evolution.Individual;
import ec.evolution.Population;

public class TournamentSelectorImpl implements Selector {

  public Float replacedPopulationPercentage;
  public Integer tournamentSize;


  public TournamentSelectorImpl(Float replacedPopulationPercentage,Integer tournamentSize){
    this.replacedPopulationPercentage=replacedPopulationPercentage;
    this.tournamentSize=tournamentSize;
  }

  @Override
  public List<Individual> select(Population population){
    List<Individual> selectedIndividuals =new ArrayList<>();
    int neededParentsNumber=calculateNeededParentsNumber(population.getIndividuals().size());
    for(int i=0;i<neededParentsNumber;i++){
      Individual selectedIndividual=tournamentSelection(population);
      selectedIndividuals.add(selectedIndividual);
    }
    return selectedIndividuals;
  }

  private int calculateNeededParentsNumber(int populationSize){
    int neededDescendantsNumber=Math.round(populationSize*replacedPopulationPercentage);
    if(neededDescendantsNumber%2==0){
      return neededDescendantsNumber;
    } else {
      return neededDescendantsNumber+1;
    }
  }

  private Individual tournamentSelection(Population population){
    List<Individual> individuals =new ArrayList<>(population.getIndividuals());
    Individual selectedIndividual =null;
    for(int i=0;i<tournamentSize;i++){
      Random rand = new Random();
      Individual randomIndividual = individuals.get(rand.nextInt(individuals.size()));
      if(selectedIndividual == null || selectedIndividual.getFitness()< randomIndividual.getFitness()){
        selectedIndividual = SerializationUtils.clone(randomIndividual);
      }
      individuals.remove(randomIndividual);
    }
    return selectedIndividual;
  }
}
