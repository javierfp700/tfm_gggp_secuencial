package converter;

import java.util.List;

public class JavaCodeConvertercOPY {

  public static String convertToJavaCode(List<String> word){
    String javaCode="";
    int functionArguments=0;
    int argumentsCounter=0;
    for(int i=0;i<word.size();i++){
      String terminal=word.get(i);
      switch (terminal) {
        case "if":
          if (javaCode.length() != 0) {
            javaCode = javaCode + System.lineSeparator() + "\t\t\t" + "}" + System.lineSeparator()
                + "\t\t\t"  + "else if (";;
          } else {
            javaCode = "if (";
          }
          break;
        case "then":
          javaCode = javaCode + ") {" + System.lineSeparator() + "\t\t\t\t";
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
          javaCode = javaCode + "ahead(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "back":
          javaCode = javaCode + "back(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "turn_right":
          javaCode = javaCode + "turnRight(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "turn_left":
          javaCode = javaCode + "turnLeft(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "default":
          javaCode = javaCode + System.lineSeparator() + "\t\t\t" + "}" + System.lineSeparator()+ "\t\t\t" + "else{"+ System.lineSeparator()+ "\t\t\t\t";
          break;
        default:
          javaCode = javaCode + terminal;
          break;
      }
      if(functionArguments>0){
        if(argumentsCounter==functionArguments){
          javaCode = javaCode +");";
          argumentsCounter=0;
          functionArguments=0;
        } else {
          argumentsCounter=argumentsCounter+1;
        }
      }
    }
    //Close last condition
    javaCode = javaCode + System.lineSeparator() + "\t\t\t" + "}";
    return javaCode;
  }



}
