package gp.grammar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import gp.algorithm.GPConstants;
import gp.structure.DerivationTree;
import gp.structure.Node;

public class Grammar {

  private static final String SEPARATOR_ANTECEDENTS_CONSEQUENTS=" ::= ";
  private static final String SEPARATOR_CLAUSE=" ";
  private static final String START_NO_TERMINAL_CLAUSE="<";
  private static final String END_NO_TERMINAL_CLAUSE=">";

  private Map<Symbol, List<Consequent>> grammarSymbolsMap;

  private Grammar(Map<Symbol, List<Consequent>> grammarSymbolsMap){
    this.grammarSymbolsMap=grammarSymbolsMap;
  }

  /**
   * Load grammar from file
   * @return grammar
   */
  public static Grammar loadGrammar(String grammarName){
    Map<Symbol, List<Consequent>> grammar=new HashMap<>();
    try{
      ClassLoader classLoader = Grammar.class.getClassLoader();
      InputStream inputStream = classLoader.getResourceAsStream(grammarName+".bnf");
      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while((line=br.readLine()) != null){
        if(line.trim().length()>0) {
          String[] lineComponents = line.split(SEPARATOR_ANTECEDENTS_CONSEQUENTS);
          String root = lineComponents[0].trim();
          Symbol rootSymbol = new Symbol(root, false);
          List<Symbol> consequentSymbols = getConsequentSymbols(lineComponents[1]);
          double probability = getConsequentProbability(lineComponents[1]);
          Consequent consequent=new Consequent(consequentSymbols,probability);
          addToGrammar(grammar,rootSymbol,consequent);
        }
      }
    } catch (Exception e){
      System.err.println("Impossible to load grammar file");
      e.printStackTrace();
    }
    return new Grammar(grammar);
  }

  private static void addToGrammar(Map<Symbol, List<Consequent>> grammar, Symbol antecedent,Consequent consequent){
    List<Consequent> consequents;
    if (grammar.containsKey(antecedent)){
      consequents = grammar.get(antecedent);
      Consequent lastConsequent=consequents.get(consequents.size()-1);
      consequent.setMinProbability(lastConsequent.getMaxProbability());
      consequent.setMaxProbability(consequent.getMinProbability()+consequent.getProbability());
      consequents.add(consequent);
    } else {
      consequent.setMinProbability(0);
      consequent.setMaxProbability(consequent.getProbability());
      consequents = new ArrayList<>();
      consequents.add(consequent);
    }
    grammar.put(antecedent,consequents);
  }

  /**
   * Get clauses (symbols) of consequent
   * @param consequent consequent of rule
   * @return list of clauses of consequent
   */
  private static List<Symbol> getConsequentSymbols(String consequent){
    List<String> consequentClauses=Arrays.asList(consequent.split(SEPARATOR_CLAUSE));
    consequentClauses=consequentClauses.subList(0,consequentClauses.size()-1);
    List<Symbol> consequentSymbols=new ArrayList<>();
    for(String consequentClause:consequentClauses){
      consequentClause=consequentClause.trim();
      boolean isTerminal=true;
      if(consequentClause.startsWith(START_NO_TERMINAL_CLAUSE)&&consequentClause.endsWith(END_NO_TERMINAL_CLAUSE)){
        isTerminal=false;
      }
      Symbol symbol=new Symbol(consequentClause,isTerminal);
      consequentSymbols.add(symbol);
    }
    return consequentSymbols;
  }

  private static double getConsequentProbability(String consequent){
    List<String> consequentClauses=Arrays.asList(consequent.split(SEPARATOR_CLAUSE));
    String probability=consequentClauses.get(consequentClauses.size()-1);
    probability=probability.substring(1,probability.length()-1);
    return Double.parseDouble(probability);
  }

  public DerivationTree generateDerivationTree(int depthLimit){
    return generateDerivationTree(depthLimit,GPConstants.AXIOM);
  }

  public DerivationTree generateDerivationTree(int depthLimit, String noTerminalRootValue){
    Symbol rootSymbol=new Symbol(noTerminalRootValue,false);
    Node parent=new Node(rootSymbol);
    List<Node> noTerminalNodes=new ArrayList<>();
    noTerminalNodes.add(parent);
    for (int i = 0; i < noTerminalNodes.size(); i++) {
      Node noTerminalNode = noTerminalNodes.get(i);
      Symbol noTerminalSymbol = noTerminalNode.getSymbol();
      List<Consequent> consequentsPossible = grammarSymbolsMap.get(noTerminalSymbol);
      Random random = new Random();
      double randomNumber = random.nextDouble();
      Optional<Consequent> opt = consequentsPossible.stream().filter(
          consequent -> consequent.getMinProbability() <= randomNumber
              && consequent.getMaxProbability() > randomNumber).findFirst();
      Consequent consequentChosen=null;
      if(!opt.isPresent()){
        System.out.println("Opti is not present");
      } else{
        consequentChosen=opt.get();
      }
      List<Symbol> symbolsConsequentChosen = consequentChosen.getSymbols();
      for (Symbol symbol : symbolsConsequentChosen) {
        Node node = new Node(symbol, noTerminalNode);
        if (node.getLevel() > depthLimit) {
          return null;
        }
        if (!symbol.isTerminal()) {
          noTerminalNodes.add(node);
        }
      }
    }
    return new DerivationTree(parent);
  }



}
