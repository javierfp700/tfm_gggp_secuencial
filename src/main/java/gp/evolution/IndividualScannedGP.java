package gp.evolution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import converter.JavaCodeConverter;
import ec.evolution.Individual;
import gp.algorithm.GPConstants;
import gp.results.GPResults;
import gp.structure.DerivationTree;
import robocode.BattleResults;

public class IndividualScannedGP extends IndividualGP{

  private float numScanned=0;
  private float numTurnStartScanned=0;
  private float damage=0;
  private float damageOpponent=0;


  public IndividualScannedGP(DerivationTree derivationTree) {
    super(derivationTree);
  }

  @Override
  public String getRobotTemplate() {
    return GPConstants.ROBOT_SCANNED_TEMPLATE;
  }

  @Override
  public String geFitnessDetail() {
    return "fitness: " + getFitness() + " ;numScanned: " + numScanned + " ;numTurnStartScanned: "+numTurnStartScanned+ " ;damage: "+damage+ " ;damageOpponent: "+damageOpponent;
  }

  /**
   * Replace skeleton of robot code with code of genetic programming
   * @param javaTemplate content with skeleton of robot code
   * @return robot code
   */
  @Override
  public String replaceJavaTemplateWithCode(String javaTemplate){
    String code = JavaCodeConverter.convertToJavaCode(getPhenotype(), GPConstants.GRAMMAR_SCANNED_NAME);
    String templateWithCode=javaTemplate.replace(GPConstants.ON_SCANNED_ROBOT_CODE_TAG,code);


    GPResults gpResults=GPResults.getInstance();
    Individual bestRunIndividual=gpResults.getBestRunIndividual();
    String bestRunCode = JavaCodeConverter.convertToJavaCode(bestRunIndividual.getPhenotype(), GPConstants.GRAMMAR_RUN_NAME);
    templateWithCode = templateWithCode.replace(GPConstants.RUN_CODE_TAG,bestRunCode);

    return templateWithCode;
  }

  @Override
  public void setFitness(BattleResults[] battleResults){
    BufferedReader reader;
    try {
      File file = new File("/home/jfernandez/robocode/robots/gp/GPRobot.data/battleData.dat");
      reader = new BufferedReader(new FileReader(file));
      String line;
      while((line=reader.readLine()) != null){
        String[] lineElements=line.split(" ");
        if(lineElements[0].equals("Num_scanned")){
          numScanned=Float.parseFloat(lineElements[1]);
        }
        if(lineElements[0].equals("Num_turn_start_scanned")){
          numTurnStartScanned=Float.parseFloat(lineElements[1]);
        }
      }
      for (robocode.BattleResults result : battleResults) {
        if(result.getTeamLeaderName().equals(GPConstants.GP_ROBOT)){
          damage=result.getBulletDamage()+result.getBulletDamageBonus();
        } else {
          damageOpponent=result.getBulletDamage()+result.getBulletDamageBonus();
        }
      }
      float calculatedFitness = 0.35f*(numScanned/numTurnStartScanned)+0.65f*(damage/(damage+damageOpponent));
      setFitness(calculatedFitness);
      reader.close();
      file.delete();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
