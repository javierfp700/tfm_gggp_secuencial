package gp.evolution;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import ec.evolution.Individual;
import ga.algorithm.GA;
import ga.evolution.IndividualGA;
import gp.algorithm.GPConstants;
import gp.structure.DerivationTree;
import gp.structure.Node;

public abstract class IndividualGP extends Individual {

  public IndividualGP(DerivationTree derivationTree){
    super(derivationTree);
  }

  @Override
  public List<String> getPhenotype() {
    return getDerivationTree().getWord();
  }

  /**
   * Evaluate individual
   */
  /*
  public void evaluate(){
    List<String> word= getDerivationTree().getWord();
    if (!word.contains(GPConstants.REAL_VALUE_TAG)) {
      super.evaluate(null, null);
    } else {
      int realValuesCount= Collections.frequency(word, GPConstants.REAL_VALUE_TAG);
      System.out.println("Executing GA with "+realValuesCount+" values");
      GA ga=new GA(realValuesCount,getDerivationTree());
      IndividualGA bestIndividualGA=(IndividualGA) ga.execute(null);
      DerivationTree derivationTree=bestIndividualGA.replacePhenotypeWithValues();
      setFitness(bestIndividualGA.getFitness());
      setDerivationTree(derivationTree);
      super.evaluate(null, null);
    }
  }*/




}

