package gp.structure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DerivationTree implements Serializable {

  private Node root;

  public DerivationTree(Node root){
    this.root=root;
  }

  public Node getRoot(){
    return root;
  }

  @Override
  public String toString(){
    String derivationTree="";
    List<Node> children=new ArrayList<>();
    children.add(root);
    for(int i=0;i<children.size();i++){
      Node child=children.get(i);
      if(child.getChildren().size()>0){
        children.addAll(i+1,child.getChildren());
      }
    }
    for(Node child:children){
      derivationTree=derivationTree+child.toString();
    }
    return derivationTree;
  }

  public List<Node> flatten(){
    List<Node> flattenedNodes=new ArrayList<>();
    List<Node> noFlattenedNodes= new ArrayList<>(Arrays.asList(root));
    while(noFlattenedNodes.size()>0){
      Node node=noFlattenedNodes.get(0);
      flattenedNodes.add(node);
      noFlattenedNodes.addAll(node.getChildren());
      noFlattenedNodes.remove(0);
    }
    return flattenedNodes;
  }

  public List<String> getWord(){
    List<Node> children=new ArrayList<>();
    children.add(root);
    for(int i=0;i<children.size();i++){
      Node child=children.get(i);
      if(child.getChildren().size()>0){
        children.addAll(i+1,child.getChildren());
      }
    }
    List<Node> terminalNodes=children.stream().filter(n->n.getSymbol().isTerminal()).collect(
        Collectors.toList());
    List<String> terminalValues=new ArrayList<>();
    for(Node terminalNode: terminalNodes){
      terminalValues.add(terminalNode.getSymbol().getValue());
    }
    return terminalValues;
  }



}
