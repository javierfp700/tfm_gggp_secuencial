public class Run6 {

  public static void main(String[] args) {
    /*
    Symbol symbol1=new Symbol("S",false);
    Symbol symbol2=new Symbol("E",false);
    Symbol symbol3=new Symbol("=",true);
    Symbol symbol4=new Symbol("N",false);
    Symbol symbol5=new Symbol("+",true);
    Symbol symbol6=new Symbol("realvalue",true);
    Symbol symbol7=new Symbol("4",true);
    Symbol symbol8=new Symbol("7",true);

    Node node1=new Node(symbol1);
    Node node2=new Node(symbol2,node1);
    Node node3=new Node(symbol3,node1);
    Node node4=new Node(symbol4,node1);
    Node node5=new Node(symbol2,node2);
    Node node6=new Node(symbol5,node2);
    Node node7=new Node(symbol2,node2);
    Node node8=new Node(symbol8,node4);
    Node node9=new Node(symbol4,node5);
    Node node10=new Node(symbol4,node7);
    Node node11=new Node(symbol6,node9);
    Node node12=new Node(symbol7,node10);

    DerivationTree derivationTree=new DerivationTree(node1);

    IndividualGP individualGP = new IndividualGP(derivationTree);

    List<Node> nodes=derivationTree.flatten();
    List<Node> filter_nodes=nodes.stream().filter(node-> GPConstants.REAL_VALUE_TAG.equals(node.getSymbol().getValue())).collect((Collectors.toList()));
    for(Node node: filter_nodes){
      node.getSymbol().setValue("abc");
    }

    System.out.println(individualGP.getDerivationTree().toString());

    //COMPROBANDO SI SE SETEA UN VALOR DE NODO EN FLATTEN SE CAMBIA (PARA HACER EVALUACON INDIVIDUO)*/
  }

}
