import java.util.List;
import ec.evolution.Individual;
import ec.evolution.Population;
import ga.algorithm.GA;
import gp.grammar.Symbol;
import gp.structure.DerivationTree;
import gp.structure.Node;

public class RunGA1 {

  public static void main(String[] args) {
    int realValuesCount=3;

    Symbol symbol1=new Symbol("S",false);
    Symbol symbol2=new Symbol("E",false);
    Symbol symbol3=new Symbol("=",true);
    Symbol symbol4=new Symbol("N",false);
    Symbol symbol5=new Symbol("E",false);
    Symbol symbol6=new Symbol("+",true);
    Symbol symbol7=new Symbol("E",false);
    Symbol symbol8=new Symbol("7",true);
    Symbol symbol9=new Symbol("N",false);
    Symbol symbol10=new Symbol("N",false);
    Symbol symbol11=new Symbol("6",true);
    Symbol symbol12=new Symbol("4",true);

    Node node1=new Node(symbol1);
    Node node2=new Node(symbol2,node1);
    Node node3=new Node(symbol3,node1);
    Node node4=new Node(symbol4,node1);
    Node node5=new Node(symbol5,node2);
    Node node6=new Node(symbol6,node2);
    Node node7=new Node(symbol7,node2);
    Node node8=new Node(symbol8,node4);
    Node node9=new Node(symbol9,node5);
    Node node10=new Node(symbol10,node7);
    Node node11=new Node(symbol11,node9);
    Node node12=new Node(symbol12,node10);

    DerivationTree derivationTree=new DerivationTree(node1);
    GA ga =new GA(realValuesCount,derivationTree);
    Population population=ga.getPopulationGenerator().initializePopulation();
    //population.evaluate();
    for(int i=0;i<population.getIndividuals().size();i++){
      population.getIndividuals().get(i).setFitness(i);
    }
    List<Individual> selectedIndividuals = ga.getSelector().select(population);
    List<Individual> crossedIndividuals = ga.getCrossover().cross(selectedIndividuals);
    ga.getMutation().mutate(crossedIndividuals);
    ga.getReplacement().replace(population,crossedIndividuals);
  }

}
