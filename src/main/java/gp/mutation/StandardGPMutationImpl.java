package gp.mutation;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import ec.evolution.Individual;
import ec.mutation.Mutation;
import gp.algorithm.GPConstants;
import gp.grammar.Grammar;
import gp.structure.DerivationTree;
import gp.structure.Node;

public class StandardGPMutationImpl implements Mutation {

  private String grammarName;

  public StandardGPMutationImpl(String grammarName){
    this.grammarName=grammarName;
  }

  @Override
  public void mutate(Individual individual){
    Grammar grammar= Grammar.loadGrammar(grammarName);
    DerivationTree derivationTree= individual.getDerivationTree();
    List<Node> nodes=derivationTree.flatten();
    List<Node> noTerminalNodes=nodes.stream().filter(node->!node.getSymbol().isTerminal() && node.getLevel()>0).collect(Collectors.toList());
    Random rand = new Random();
    Node mutationNode=noTerminalNodes.get(rand.nextInt(noTerminalNodes.size()));
    DerivationTree mutationDerivationSubtree=null;
    while(mutationDerivationSubtree==null) {
      mutationDerivationSubtree = grammar.generateDerivationTree(GPConstants.MAXIMUM_DEPTH_MUTATION_SUBTREE,mutationNode.getSymbol().getValue());
    }
    mutationNode.getChildren().clear();
    for(Node nodeMutationSubtree: mutationDerivationSubtree.getRoot().getChildren()){
      nodeMutationSubtree.setParent(mutationNode);
    }
  }

  @Override
  public Float getMutationProbability() {
    return GPConstants.GP_MUTATION_PROBABILITY;
  }

}
