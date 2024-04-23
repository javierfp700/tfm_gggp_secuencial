package ga.evolution;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.SerializationUtils;
import ec.evolution.Individual;
import gp.algorithm.GPConstants;
import gp.structure.DerivationTree;
import gp.structure.Node;
import robocode.BattleResults;

public class IndividualGA extends Individual {

  private double[] values;

  public IndividualGA(double[] values,DerivationTree derivationTree){
    super(derivationTree);
    this.values=values;
  }

  public double[] getValues(){
    return values;
  }

  @Override
  public List<String> getPhenotype() {
    DerivationTree derivationTree=replacePhenotypeWithValues();
    return derivationTree.getWord();
  }

  @Override
  public String getRobotTemplate() {
    return null;
  }

  @Override
  public String geFitnessDetail() {
    return null;
  }

  @Override
  public void setFitness(BattleResults[] battleResults) {

  }

  @Override
  public String replaceJavaTemplateWithCode(String javaTemplate) {
    return null;
  }

  public DerivationTree replacePhenotypeWithValues(){
    DerivationTree derivationTree= SerializationUtils.clone(getDerivationTree());
    List<Node> nodes=derivationTree.flatten();
    List<Node> realValueNodes=nodes.stream().filter(node-> GPConstants.REAL_VALUE_TAG.equals(node.getSymbol().getValue())).collect((Collectors.toList()));
    for(int i=0;i<realValueNodes.size();i++){
      realValueNodes.get(i).getSymbol().setValue(Double.toString(values[i]));
    }
    return derivationTree;
  }

}
