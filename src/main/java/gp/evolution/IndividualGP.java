package gp.evolution;

import java.util.List;
import ec.evolution.Individual;
import gp.structure.DerivationTree;

public abstract class IndividualGP extends Individual {

  public IndividualGP(DerivationTree derivationTree){
    super(derivationTree);
  }

  @Override
  public List<String> getPhenotype() {
    return getDerivationTree().getWord();
  }

}

