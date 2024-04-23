package gp.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import gp.grammar.Symbol;

public class Node implements Serializable {

  private List<Node> children;
  private Node parent;
  private Symbol symbol;
  private int level;

  /**
   * Create a node
   * @param symbol symbol
   * @param parent parent node
   */
  public Node(Symbol symbol, Node parent){
    this.symbol=new Symbol(symbol.getValue(), symbol.isTerminal());
    this.children=new ArrayList<>();
    setParent(parent);
  }

  /**
   * Create an initial node
   * @param symbol symbol
   */
  public Node(Symbol symbol){
    this.symbol=symbol;
    this.parent=null;
    this.level=0;
    this.children=new ArrayList<>();
  }

  /**
   * Set parent
   * @param parent parent node
   */
  public void setParent(Node parent){
    this.parent=parent;
    this.level=parent.level+1;
    parent.children.add(this);
    //Propagate level
    List<Node> childrenPropagateLevel = new ArrayList<>();
    childrenPropagateLevel.addAll(children);
    while(childrenPropagateLevel.size()>0){
      Node child=childrenPropagateLevel.get(0);
      child.setLevel(child.getParent().getLevel()+1);
      childrenPropagateLevel.remove(child);
      childrenPropagateLevel.addAll(child.getChildren());
    }
  }

  public void replace(Node node){
    //Replace in parent
    node.parent=this.parent;
    node.level=this.parent.level+1;
    //Replace in child
    int index=this.parent.children.indexOf(this);
    this.parent.children.set(index,node);
    //Propagate level
    List<Node> childrenPropagateLevel = new ArrayList<>();
    childrenPropagateLevel.addAll(node.getChildren());
    while(childrenPropagateLevel.size()>0){
      Node child=childrenPropagateLevel.get(0);
      child.setLevel(child.getParent().getLevel()+1);
      childrenPropagateLevel.remove(child);
      childrenPropagateLevel.addAll(child.getChildren());
    }
  }

  public Symbol getSymbol(){
    return symbol;
  }

  public List<Node> getChildren(){
    return children;
  }

  public int getLevel(){
    return level;
  }

  public Node getParent(){
    return parent;
  }

  public void setLevel(int level){
    this.level=level;
  }

  @Override
  public String toString(){
    String tabs="";
    for(int i=0;i<level;i++){
      tabs=tabs+"|\t";
    }
    return tabs+symbol.getValue()+"\n";
  }

}
