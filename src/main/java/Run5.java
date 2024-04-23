public class Run5 {

  public static void main(String[] args) {
    /*
    //Parent 1
    Symbol symbol1=new Symbol("S",false);
    Symbol symbol2=new Symbol("E",false);
    Symbol symbol3=new Symbol("=",true);
    Symbol symbol4=new Symbol("N",false);
    Symbol symbol5=new Symbol("E",false);
    Symbol symbol6=new Symbol("+",true);
    Symbol symbol7=new Symbol("E",false);
    Symbol symbol8=new Symbol("8",true);
    Symbol symbol9=new Symbol("N",false);
    Symbol symbol10=new Symbol("N",false);
    Symbol symbol11=new Symbol("2",true);
    Symbol symbol12=new Symbol("3",true);
    Symbol symbol14=new Symbol("-",true);
    Symbol symbol15=new Symbol("F",false);

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
    IndividualGP individualGP =new IndividualGP(derivationTree);
    System.out.println("Individual");
    System.out.println(individualGP.getDerivationTree().toString());

    //Grammar
    List<Symbol> rule1=Arrays.asList(symbol2,symbol3,symbol4);
    List<Symbol> rule2=Arrays.asList(symbol5,symbol6,symbol7);
    List<Symbol> rule3=Arrays.asList(symbol5,symbol14,symbol7);
    List<Symbol> rule4=Arrays.asList(symbol15,symbol6,symbol7);
    List<Symbol> rule5=Arrays.asList(symbol15,symbol14,symbol7);
    List<Symbol> rule6=Arrays.asList(symbol9);
    List<Symbol> rule7=Arrays.asList(symbol9);
    List<Symbol> rule8=Arrays.asList(symbol11);
    List<Symbol> rule9=Arrays.asList(symbol12);

    List<List<Symbol>> setRules1=Arrays.asList(rule1);
    List<List<Symbol>> setRules2=Arrays.asList(rule2,rule3,rule4,rule5,rule6);
    List<List<Symbol>> setRules3=Arrays.asList(rule7);
    List<List<Symbol>> setRules4=Arrays.asList(rule8,rule9);

    Map<Symbol, List<List<Symbol>>> grammarMap= new HashMap<>();
    grammarMap.put(symbol1,setRules1);
    grammarMap.put(symbol2,setRules2);
    grammarMap.put(symbol15,setRules3);
    grammarMap.put(symbol4,setRules4);

    Grammar grammar=new Grammar(grammarMap);

    StandardMutation.mutate(Arrays.asList(individualGP),grammar);
    System.out.println("Mutated individual");
    System.out.println(individualGP.getDerivationTree().toString());
*/
  }

}
