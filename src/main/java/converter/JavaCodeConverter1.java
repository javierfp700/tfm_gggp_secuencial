package converter;

import java.util.List;

public class JavaCodeConverter1 {

  public static String[] convertToJavaCode(List<String> word){
    int block=0;
    String[] javaCode=new String[3];
    int functionArguments=0;
    int argumentsCounter=0;
    System.out.println("Word: "+word);
    for(int i=0;i<word.size();i++){
      if(javaCode[block]==null) {
        javaCode[block]="";
      }
      String terminal=word.get(i);
      switch (terminal) {
        case "if":
          if (!javaCode[block].isEmpty()) {
            javaCode[block] = javaCode[block] + System.lineSeparator() + "\t\t" + "}" + System.lineSeparator()
                + "\t\t"  + "else if (";;
          } else {
            javaCode[block] = javaCode[block] + "if (";
          }
          break;
        case "then":
          javaCode[block] = javaCode[block] + ") {" + System.lineSeparator() + "\t\t\t";
          break;
        case "not":
          javaCode[block] = javaCode[block] + "!";
          break;
        case "robot_x":
          javaCode[block] = javaCode[block] + "getX()";
          break;
        case "robot_y":
          javaCode[block] = javaCode[block] + "getY()";
          break;
        case "robot_heading":
          javaCode[block] = javaCode[block] + "getHeading()";
          break;
        case "robot_velocity":
          javaCode[block] = javaCode[block] + "getVelocity()";
          break;
        case "and":
          javaCode[block] = javaCode[block] + "&&";
          break;
        case "or":
          javaCode[block] = javaCode[block] + "||";
          break;
        case "ahead":
          javaCode[block] = javaCode[block] + "ahead(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "back":
          javaCode[block] = javaCode[block] + "back(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "turn_right":
          javaCode[block] = javaCode[block] + "turnRight(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "turn_left":
          javaCode[block] = javaCode[block] + "turnLeft(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "default":
          javaCode[block] = javaCode[block] + System.lineSeparator() + "\t\t" + "}" + System.lineSeparator()+ "\t\t" + "else{"+ System.lineSeparator()+ "\t\t\t";
          break;
        case "###":
          //Run method are only actions (no conditionals)
          if(block!=0) {
            javaCode[block] = javaCode[block] + System.lineSeparator() + "\t\t" + "}";
          }
          block=block+1;
          break;
        case "distance":
          javaCode[block] = javaCode[block] + "event.getDistance()";
          break;
        case "energy":
          javaCode[block] = javaCode[block] + "getEnergy()";
          break;
        case "fire":
          javaCode[block] = javaCode[block] + "fire(";
          functionArguments = 1;
          argumentsCounter = 0;
          break;
        case "do_nothing":
          javaCode[block] = javaCode[block] + "doNothing();";
          break;
        case "bearing":
          javaCode[block] = javaCode[block] + "event.getBearing()";
          break;
        default:
          javaCode[block] = javaCode[block] + terminal;
          break;
      }
      if(functionArguments>0){
        if(argumentsCounter==functionArguments){
          javaCode[block] = javaCode[block] +");";
          argumentsCounter=0;
          functionArguments=0;
        } else {
          argumentsCounter=argumentsCounter+1;
        }
      }
    }
    //Close last condition
    javaCode[block] = javaCode[block] + System.lineSeparator() + "\t\t" + "}";
    return javaCode;
  }



}
