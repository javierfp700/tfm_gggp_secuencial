package gp.algorithm;

import org.apache.commons.io.FilenameUtils;
import ec.algorithm.EC;
import ec.replacement.SteadyStateReplacementImpl;
import ec.selection.TournamentSelectorImpl;
import gp.crossover.WhighamCrossoverImpl;
import gp.initialization.StandardGPPopulationGeneratorImpl;
import gp.mutation.StandardGPMutationImpl;

public class GP extends EC {

  private String grammarName;

  public GP(String grammarName){
    super(new StandardGPPopulationGeneratorImpl(grammarName),
        new TournamentSelectorImpl(GPConstants.GP_REPLACED_POPULATION_PERCENTAGE,GPConstants.GP_TOURNAMENT_SIZE),
        new WhighamCrossoverImpl(grammarName),
        new StandardGPMutationImpl(grammarName),
        new SteadyStateReplacementImpl(GPConstants.GP_REPLACED_POPULATION_PERCENTAGE),
        "GP");
    this.grammarName=grammarName;
  }

  @Override
  public Integer getMaximumGenerations(){
    return GPConstants.GP_MAXIMUM_GENERATIONS;
  }

  @Override
  public String getAlgorithm() {
    return "GP";
  }

  public String getGrammarName(){
    return grammarName;
  }


}
