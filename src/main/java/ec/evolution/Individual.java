package ec.evolution;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.IOUtils;
import gp.algorithm.GPConstants;
import gp.structure.DerivationTree;
import robocode.BattleResults;
import robocode.RobocodeConstants;
import robocode.battle.BattleSimulator;

public abstract class Individual implements Serializable {

  //Si es GA es el derivation tree proveniente de gp (sin sustituciones)
  private DerivationTree derivationTree;

  private float fitness;

  private List<Float> hitWallsList=new ArrayList<>();
  private List<Float> spacesAchieved=new ArrayList<>();
  private List<Float> totalTurnsList=new ArrayList<>();
  private List<Float> bulletHitAnotherRobotList=new ArrayList<>();
  private List<Float> scannedRobotList=new ArrayList<>();
  private List<Float> bulletHitMissedList=new ArrayList<>();

  public Individual(DerivationTree derivationTree){
    this.derivationTree=derivationTree;
    this.fitness=Float.NEGATIVE_INFINITY;
  }

  public float getFitness(){
    return fitness;
  }

  public DerivationTree getDerivationTree(){
    return derivationTree;
  }

  public void setFitness(float fitness){
    this.fitness=fitness;
  }

  public void setDerivationTree(DerivationTree derivationTree){
    this.derivationTree=derivationTree;
  }

  public abstract List<String> getPhenotype();

  public abstract String getRobotTemplate();

  public abstract String geFitnessDetail();

  public abstract void setFitness(BattleResults[] battleResults);

  public abstract String replaceJavaTemplateWithCode(String javaTemplate);

  public List<Float> getHitWallsList() {
    return hitWallsList;
  }

  public List<Float> getSpacesAchieved() {
    return spacesAchieved;
  }

  public List<Float> getTotalTurnsList() {
    return totalTurnsList;
  }

  public List<Float> getBulletHitAnotherRobotList() {
    return bulletHitAnotherRobotList;
  }

  public List<Float> getScannedRobotList() {
    return scannedRobotList;
  }

  public List<Float> getBulletHitMissedList() {
    return bulletHitMissedList;
  }

  /**
   * Evaluate individual
   */
  public void evaluate(String opponent){
    String javaTemplate = getJavaTemplate();
    String templateWithCode = replaceJavaTemplateWithCode(javaTemplate);
    writeInJavaFile(templateWithCode,RobocodeConstants.GP_ROBOT_PACKAGE+GPConstants.ROBOT_TEMPLATE);
    compileIndividual();
    simulateBattle(opponent);
  }
  /**
   * Get java template with skeleton of robot code
   * @return content with skeleton of robot code
   */
  private String getJavaTemplate(){
    try {
      ClassLoader classLoader = Individual.class.getClassLoader();
      InputStream inputStream = classLoader.getResourceAsStream(getRobotTemplate());
      return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    } catch (IOException e){
      System.err.println("Impossible to get individual java template");
      e.printStackTrace();
      return null;
    }
  }


  /**
   * Write robot code in java file
   * @param content content with robot code
   */
  private void writeInJavaFile(String content,String javaCodePath){
    try {
      FileWriter myWriter = new FileWriter(javaCodePath);
      myWriter.write(content);
      myWriter.close();
      System.out.println("Written code "+javaCodePath);
    } catch (IOException e) {
      System.err.println("Impossible to write individual code in "+javaCodePath);
      e.printStackTrace();
    }
  }

  /**
   * Compile robot code
   */
  private void compileIndividual(){
    try {
      String line = "javac " + RobocodeConstants.GP_ROBOT_PACKAGE+GPConstants.ROBOT_TEMPLATE + " -cp " + RobocodeConstants.ROBOCODE_JAR;
      CommandLine cmdLine = CommandLine.parse(line);
      DefaultExecutor executor = new DefaultExecutor();
      System.out.println("Executing "+line);
      executor.execute(cmdLine);
      System.out.println("Executed "+line);
    } catch(IOException e) {
      System.err.println("Impossible to compile individual in java");
      e.printStackTrace();
    }
  }

  /**
   * Simulate battle with individual
   */
  private void simulateBattle(String opponent){
    BattleSimulator.simulateBattle(this,opponent);
  }


  public void save(String path) {
    String javaTemplate = getJavaTemplate();
    String templateWithCode = replaceJavaTemplateWithCode(javaTemplate);
    writeInJavaFile(templateWithCode,path);
  }
}
