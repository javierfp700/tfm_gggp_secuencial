package gp.crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.SerializationUtils;
import ec.crossover.Crossover;
import ec.evolution.Individual;
import gp.algorithm.GPConstants;
import gp.evolution.IndividualGP;
import gp.evolution.IndividualGPFactory;
import gp.grammar.Symbol;
import gp.structure.DerivationTree;
import gp.structure.Node;

public class WhighamCrossoverImpl implements Crossover {

  private String grammarName;

  public WhighamCrossoverImpl(String grammarName){
    this.grammarName=grammarName;
  }

  @Override
  public List<Individual> cross(Individual parent1, Individual parent2){
    List<Individual> crossedIndividuals =new ArrayList<>();
    DerivationTree derivationTreeParent1= parent1.getDerivationTree();
    List<Node> nodesParent1=derivationTreeParent1.flatten();
    List<Node> noTerminalNodesParent1=nodesParent1.stream().filter(node->!node.getSymbol().isTerminal() && node.getLevel()>0).collect(
        Collectors.toList());
    Set<Symbol> noTerminalSymbolsParent1=noTerminalNodesParent1.stream().map(Node::getSymbol).collect(Collectors.toSet());
    DerivationTree derivationTreeParent2=parent2.getDerivationTree();
    List<Node> nodesParent2=derivationTreeParent2.flatten();
    List<Node> noTerminalNodesParent2=nodesParent2.stream().filter(node->!node.getSymbol().isTerminal() && node.getLevel()>0).collect(Collectors.toList());
    Set<Symbol> noTerminalSymbolsParent2=noTerminalNodesParent2.stream().map(Node::getSymbol).collect(Collectors.toSet());
    noTerminalSymbolsParent1.retainAll(noTerminalSymbolsParent2);
    if(noTerminalSymbolsParent1.size()==0){
      System.err.println("Impossible to cross parents because no terminal nodes in common between parent 1-> "+derivationTreeParent1+" and parent 2-> "+derivationTreeParent2);
    } else {
      Random rand = new Random();
      //System.out.println("Parent 1");
      //System.out.println(derivationTreeParent1);
      //System.out.println("Parent 2");
      //System.out.println(derivationTreeParent2);
      Symbol crossoverSymbol = new ArrayList<>(noTerminalSymbolsParent1).get(rand.nextInt(noTerminalSymbolsParent1.size()));
      List<Node> possibleCrossoverNodesParent1 = noTerminalNodesParent1.stream().filter(node -> crossoverSymbol.equals(node.getSymbol())).collect(Collectors.toList());
      Node crossoverNodeParent1 = possibleCrossoverNodesParent1.get(rand.nextInt(possibleCrossoverNodesParent1.size()));
      List<Node> possibleCrossoverNodesParent2 = noTerminalNodesParent2.stream().filter(node -> crossoverSymbol.equals(node.getSymbol())).collect(Collectors.toList());
      Node crossoverNodeParent2 = possibleCrossoverNodesParent2.get(rand.nextInt(possibleCrossoverNodesParent2.size()));
      //System.out.println("Crossover node parent 1");
      //System.out.println(crossoverNodeParent1.getSymbol().getValue()+" ,parent-> "+crossoverNodeParent1.getParent().getSymbol().getValue());
      //System.out.println("Crossover node parent 2");
      //System.out.println(crossoverNodeParent2.getSymbol().getValue()+" ,parent-> "+crossoverNodeParent2.getParent().getSymbol().getValue());
      Node cloneCrossoverNodeParent1= SerializationUtils.clone(crossoverNodeParent1);
      Node cloneCrossoverNodeParent2= SerializationUtils.clone(crossoverNodeParent2);
      crossoverNodeParent1.replace(cloneCrossoverNodeParent2);
      crossoverNodeParent2.replace(cloneCrossoverNodeParent1);
      //System.out.println("Parent 1 remove");
      // System.out.println(derivationTreeParent1);
      //System.out.println("Parent 2 remove");
      //System.out.println(derivationTreeParent2);
    }
    Individual crossedIndividualGP1 = IndividualGPFactory.createIndividualGP(derivationTreeParent1,grammarName);
    Individual crossedIndividualGP2 = IndividualGPFactory.createIndividualGP(derivationTreeParent2,grammarName);
    crossedIndividuals.add(crossedIndividualGP1);
    crossedIndividuals.add(crossedIndividualGP2);
    return crossedIndividuals;
  }

  @Override
  public Float getCrossoverProbability() {
    return GPConstants.GP_CROSSOVER_PROBABILITY;
  }


}
