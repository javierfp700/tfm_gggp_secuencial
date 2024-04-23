package gp.grammar;

import java.util.List;

public class Consequent {

  private List<Symbol> symbols;
  private double probability;
  private double minProbability;
  private double maxProbability;

  public Consequent(List<Symbol> symbols, double probability){
    this.symbols=symbols;
    this.probability=probability;
  }

  public double getMinProbability() {
    return minProbability;
  }

  public void setMinProbability(double minProbability) {
    this.minProbability = minProbability;
  }

  public double getMaxProbability() {
    return maxProbability;
  }

  public void setMaxProbability(double maxProbability) {
    this.maxProbability = maxProbability;
  }

  public double getProbability(){
    return probability;
  }

  public List<Symbol> getSymbols() {
    return symbols;
  }
}
