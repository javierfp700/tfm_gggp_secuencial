package gp.grammar;

import java.io.Serializable;
import java.util.Objects;

public class Symbol implements Serializable {

  private String value;

  private boolean isTerminal;

  public Symbol(String value,boolean isTerminal){
    this.value=value;
    this.isTerminal=isTerminal;
  }

  public boolean isTerminal(){
    return isTerminal;
  }

  public String getValue(){
    return value;
  }

  public void setValue(String value){
    this.value=value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Symbol symbol = (Symbol) o;
    return isTerminal == symbol.isTerminal && Objects.equals(value, symbol.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, isTerminal);
  }
}
