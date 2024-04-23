package ga.algorithm;

import ec.algorithm.EC;
import ec.replacement.SteadyStateReplacementImpl;
import ec.selection.TournamentSelectorImpl;
import ga.crossover.CombinedCrossoverImpl;
import ga.initialization.GAPopulationGeneratorImpl;
import ga.mutation.GAMutationImpl;
import gp.structure.DerivationTree;

public class GA extends EC {

  public GA(int realValuesCount, DerivationTree derivationTree){
    super(new GAPopulationGeneratorImpl(realValuesCount,derivationTree),
        new TournamentSelectorImpl(GAConstants.GA_REPLACED_POPULATION_PERCENTAGE,GAConstants.GA_TOURNAMENT_SIZE),
        new CombinedCrossoverImpl(),
        new GAMutationImpl(),
        new SteadyStateReplacementImpl(GAConstants.GA_REPLACED_POPULATION_PERCENTAGE),
        "GA");
  }

  @Override
  public Integer getMaximumGenerations() {
    return GAConstants.GA_MAXIMUM_GENERATIONS;
  }

  @Override
  public String getAlgorithm() {
    return "GA";
  }

  @Override
  public String getGrammarName() {
    return "";
  }

}
