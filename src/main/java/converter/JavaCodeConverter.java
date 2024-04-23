package converter;

import java.util.List;
import gp.algorithm.GPConstants;

public class JavaCodeConverter {

  public static String convertToJavaCode(List<String> word, String grammarName){
    String javaCode="";
    int functionArguments=0;
    int argumentsCounter=0;
    for (String terminal : word) {
      switch (terminal) {
        case "if":
          if (!javaCode.isEmpty()) {
            javaCode =
                javaCode + newLineAndTabulate(grammarName, false) + "}" + newLineAndTabulate(grammarName, false) + "else if (";
          } else {
            javaCode = javaCode + "if (";
          }
          break;
        case "then":
          javaCode =
              javaCode + ") {";
          break;
        case "not":
          javaCode = javaCode + "!";
          break;
        case "robot_x":
          javaCode = javaCode + "getX()";
          break;
        case "robot_y":
          javaCode = javaCode + "getY()";
          break;
        case "robot_heading":
          javaCode = javaCode + "getHeading()";
          break;
        case "robot_velocity":
          javaCode = javaCode + "getVelocity()";
          break;
        case "and":
          javaCode = javaCode + "&&";
          break;
        case "or":
          javaCode = javaCode + "||";
          break;
        case "ahead":
          javaCode = javaCode + newLineAndTabulate(grammarName, true) + "ahead(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "back":
          javaCode = javaCode + newLineAndTabulate(grammarName, true) + "back(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "turn_right":
          javaCode = javaCode + newLineAndTabulate(grammarName, true) + "turnRight(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "turn_left":
          javaCode = javaCode + newLineAndTabulate(grammarName, true) + "turnLeft(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "default":
          javaCode =
              javaCode + newLineAndTabulate(grammarName, false) + "}" + newLineAndTabulate(grammarName, false) + "else{";
          break;
        case "distance":
          javaCode = javaCode + "event.getDistance()";
          break;
        case "energy":
          javaCode = javaCode + "getEnergy()";
          break;
        case "fire":
          javaCode = javaCode + newLineAndTabulate(grammarName, true) + "fire(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "do_nothing":
          javaCode = javaCode + newLineAndTabulate(grammarName, true) + "doNothing();";
          break;
        case "bearing":
          javaCode = javaCode + "event.getBearing()";
          break;
        default:
          javaCode = javaCode + terminal;
          break;
      }
      if (functionArguments > 0) {
        if (argumentsCounter == functionArguments) {
          javaCode = javaCode + ");";
          argumentsCounter = 0;
          functionArguments = 0;
        } else {
          argumentsCounter = argumentsCounter + 1;
        }
      }
    }
    //Close last condition
    if(!grammarName.equals(GPConstants.GRAMMAR_RUN_NAME)) {
      javaCode = javaCode + System.lineSeparator() + "\t\t" + "}";
    }
    return javaCode;
  }

  private static String newLineAndTabulate(String grammarName, boolean insideIf){
    if(grammarName.equals(GPConstants.GRAMMAR_RUN_NAME)){
      return newLineAndTabulate(3);
    }
    if(!insideIf){
      return newLineAndTabulate(2);
    } else {
      return newLineAndTabulate(3);
    }
  }

  private static String newLineAndTabulate(int times){
    String tab=System.lineSeparator();
    for(int i=0;i<times;i++){
      tab=tab+"\t";
    }
    return tab;
  }





}
