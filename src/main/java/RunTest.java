import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.SerializationUtils;
import ec.evolution.Individual;
import gp.algorithm.GPConstants;
import gp.evolution.IndividualGP;
import gp.evolution.IndividualGPFactory;
import gp.grammar.Symbol;
import gp.structure.DerivationTree;
import gp.structure.Node;

public class RunTest {

  public static void main(String[] args) {
    Symbol symbol11=new Symbol("<S>",false);
    Symbol symbol12=new Symbol("<A>",false);
    Symbol symbol13=new Symbol("<A>",false);
    Symbol symbol14=new Symbol("<A>",false);
    Symbol symbol15=new Symbol("<A>",false);
    Symbol symbol16=new Symbol("1",true);
    Symbol symbol17=new Symbol("2",true);
    Symbol symbol18=new Symbol("3",true);

    Node node11=new Node(symbol11);
    Node node12=new Node(symbol12,node11);
    Node node13=new Node(symbol13,node11);
    Node node14=new Node(symbol14,node11);
    Node node15=new Node(symbol15,node13);
    Node node16=new Node(symbol16,node12);
    Node node17=new Node(symbol17,node15);
    Node node18=new Node(symbol18,node14);

    DerivationTree derivationTree1=new DerivationTree(node11);
    IndividualGP parent1 = IndividualGPFactory.createIndividualGP(derivationTree1,GPConstants.GRAMMAR_RUN_FILE);

    Symbol symbol21=new Symbol("<S>",false);
    Symbol symbol22=new Symbol("<A>",false);
    Symbol symbol23=new Symbol("<A>",false);
    Symbol symbol24=new Symbol("<A>",false);
    Symbol symbol25=new Symbol("4",true);
    Symbol symbol26=new Symbol("5",true);
    Symbol symbol27=new Symbol("6",true);

    Node node21=new Node(symbol21);
    Node node22=new Node(symbol22,node21);
    Node node23=new Node(symbol23,node21);
    Node node24=new Node(symbol24,node21);
    Node node25=new Node(symbol25,node22);
    Node node26=new Node(symbol26,node23);
    Node node27=new Node(symbol27,node24);

    DerivationTree derivationTree2=new DerivationTree(node21);
    IndividualGP parent2 = IndividualGPFactory.createIndividualGP(derivationTree2, GPConstants.GRAMMAR_RUN_FILE);

    List<Individual> children=cross(parent1,parent2);
  }

  public static List<Individual> cross(Individual parent1, Individual parent2){
    List<Individual> crossedIndividuals =new ArrayList<>();
    DerivationTree derivationTreeParent1= SerializationUtils.clone(parent1.getDerivationTree());
    List<Node> nodesParent1=derivationTreeParent1.flatten();
    List<Node> noTerminalNodesParent1=nodesParent1.stream().filter(node->!node.getSymbol().isTerminal() && node.getLevel()>0).collect(
        Collectors.toList());
    Set<Symbol> noTerminalSymbolsParent1=noTerminalNodesParent1.stream().map(Node::getSymbol).collect(Collectors.toSet());
    DerivationTree derivationTreeParent2=SerializationUtils.clone(parent2.getDerivationTree());
    List<Node> nodesParent2=derivationTreeParent2.flatten();
    List<Node> noTerminalNodesParent2=nodesParent2.stream().filter(node->!node.getSymbol().isTerminal() && node.getLevel()>0).collect(Collectors.toList());
    Set<Symbol> noTerminalSymbolsParent2=noTerminalNodesParent2.stream().map(Node::getSymbol).collect(Collectors.toSet());
    noTerminalSymbolsParent1.retainAll(noTerminalSymbolsParent2);
    if(noTerminalSymbolsParent1.size()==0){
      System.err.println("Impossible to cross parents because no terminal nodes in common between parent 1-> "+derivationTreeParent1+" and parent 2-> "+derivationTreeParent2);
    } else {
      Random rand = new Random();
      System.out.println("Parent 1");
      System.out.println(derivationTreeParent1);
      System.out.println("Parent 2");
      System.out.println(derivationTreeParent2);
      Symbol crossoverSymbol = new ArrayList<>(noTerminalSymbolsParent1).get(rand.nextInt(noTerminalSymbolsParent1.size()));
      List<Node> possibleCrossoverNodesParent1 = noTerminalNodesParent1.stream().filter(node -> crossoverSymbol.equals(node.getSymbol())).collect(Collectors.toList());
      //Node crossoverNodeParent1 = possibleCrossoverNodesParent1.get(rand.nextInt(possibleCrossoverNodesParent1.size()));
      Node crossoverNodeParent1 = possibleCrossoverNodesParent1.get(3);
      List<Node> possibleCrossoverNodesParent2 = noTerminalNodesParent2.stream().filter(node -> crossoverSymbol.equals(node.getSymbol())).collect(Collectors.toList());
      //Node crossoverNodeParent2 = possibleCrossoverNodesParent2.get(rand.nextInt(possibleCrossoverNodesParent2.size()));
      Node crossoverNodeParent2 = possibleCrossoverNodesParent2.get(1);
      System.out.println("Crossover node parent 1");
      System.out.println(crossoverNodeParent1.getSymbol().getValue()+" ,parent-> "+crossoverNodeParent1.getParent().getSymbol().getValue());
      System.out.println("Crossover node parent 2");
      System.out.println(crossoverNodeParent2.getSymbol().getValue()+" ,parent-> "+crossoverNodeParent2.getParent().getSymbol().getValue());
      Node cloneCrossoverNodeParent1= SerializationUtils.clone(crossoverNodeParent1);
      Node cloneCrossoverNodeParent2= SerializationUtils.clone(crossoverNodeParent2);
      crossoverNodeParent1.replace(cloneCrossoverNodeParent2);
      crossoverNodeParent2.replace(cloneCrossoverNodeParent1);

     // Node parentCrossoverNodeParent1 = crossoverNodeParent1.getParent();
      //Node parentCrossoverNodeParent2 = crossoverNodeParent2.getParent();
      //crossoverNodeParent1.setParent(parentCrossoverNodeParent2);
      //crossoverNodeParent2.setParent(parentCrossoverNodeParent1);
     // System.out.println("Individual 1 setparent");
     // System.out.println(derivationTreeParent1);
     // System.out.println("Individual 2 setparent");
     // System.out.println(derivationTreeParent2);
     // parentCrossoverNodeParent1.getChildren().remove(crossoverNodeParent1);
     // parentCrossoverNodeParent2.getChildren().remove(crossoverNodeParent2);
      System.out.println("Parent 1 remove");
      System.out.println(derivationTreeParent1);
      System.out.println("Parent 2 remove");
      System.out.println(derivationTreeParent2);
    }
    Individual crossedIndividualGP1 =IndividualGPFactory.createIndividualGP(derivationTreeParent1, GPConstants.GRAMMAR_RUN_FILE);
    Individual crossedIndividualGP2 =IndividualGPFactory.createIndividualGP(derivationTreeParent2, GPConstants.GRAMMAR_RUN_FILE);
    crossedIndividuals.add(crossedIndividualGP1);
    crossedIndividuals.add(crossedIndividualGP2);
    return crossedIndividuals;

    //REVISANDO CROSSOVER!!!
  }

}
