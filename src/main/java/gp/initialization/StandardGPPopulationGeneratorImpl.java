package gp.initialization;

import ec.evolution.Individual;
import ec.initialization.PopulationGenerator;
import gp.algorithm.GPConstants;
import gp.evolution.IndividualGP;
import ec.evolution.Population;
import gp.evolution.IndividualGPFactory;
import gp.evolution.IndividualHitBulletGP;
import gp.evolution.IndividualRunGP;
import gp.evolution.IndividualScannedGP;
import gp.grammar.Grammar;
import gp.structure.DerivationTree;

public class StandardGPPopulationGeneratorImpl implements PopulationGenerator {

  private String grammarName;

  public StandardGPPopulationGeneratorImpl(String grammarName){
    this.grammarName=grammarName;
  }

  @Override
  public Population initializePopulation() {
    Grammar grammar= Grammar.loadGrammar(grammarName);
    Population population=new Population();
    while(population.getIndividuals().size()< GPConstants.GP_POPULATION_SIZE){
      DerivationTree derivationTree=grammar.generateDerivationTree(GPConstants.MAXIMUM_DEPTH_INDIVIDUAL_INITIALIZATION);
      if(derivationTree!=null){
        Individual individualGP = IndividualGPFactory.createIndividualGP(derivationTree,grammarName);
        population.addIndividual(individualGP);
      }
    }
    return population;
  }


}
