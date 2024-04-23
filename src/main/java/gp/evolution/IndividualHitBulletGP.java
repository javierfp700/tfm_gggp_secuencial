package gp.evolution;

import converter.JavaCodeConverter;
import ec.evolution.Individual;
import gp.algorithm.GPConstants;
import gp.results.GPResults;
import gp.structure.DerivationTree;
import robocode.BattleResults;

public class IndividualHitBulletGP extends IndividualGP{

  private int gpRobotScore=0;
  private int opponentScore=0;

  public IndividualHitBulletGP(DerivationTree derivationTree) {
    super(derivationTree);
  }

  @Override
  public String getRobotTemplate() {
    return GPConstants.ROBOT_HIT_BULLET_TEMPLATE;
  }

  @Override
  public String geFitnessDetail() {
    return "fitness: " + getFitness() + " ;gpRobotScore: " + gpRobotScore + " ;opponentScore: "+opponentScore;
  }

  /**
   * Replace skeleton of robot code with code of genetic programming
   * @param javaTemplate content with skeleton of robot code
   * @return robot code
   */
  @Override
  public String replaceJavaTemplateWithCode(String javaTemplate){
    /*String[] codeBlocks=getPhenotype().split("###");
    String templateWithCode=javaTemplate.replace(GPConstants.RUN_CODE_TAG,codeBlocks[0]);
    /*templateWithCode=templateWithCode.replace(GPConstants.ON_BULLET_HIT_CODE_TAG,codeBlocks[1]);
    templateWithCode=templateWithCode.replace(GPConstants.ON_BULLET_HIT_BULLET_CODE_TAG,codeBlocks[2]);
    templateWithCode=templateWithCode.replace(GPConstants.ON_BULLET_MISSED_CODE_TAG,codeBlocks[3]);
    templateWithCode=templateWithCode.replace(GPConstants.ON_HIT_BY_BULLET_CODE_TAG,codeBlocks[4]);
    templateWithCode=templateWithCode.replace(GPConstants.ON_HIT_ROBOT_CODE_TAG,codeBlocks[5]);
    templateWithCode=templateWithCode.replace(GPConstants.ON_HIT_WALL_CODE_TAG,codeBlocks[6]);
    templateWithCode=templateWithCode.replace(GPConstants.ON_SCANNED_ROBOT_CODE_TAG,codeBlocks[7]);
    templateWithCode=templateWithCode.replace(GPConstants.EMPTY_TAG,GPConstants.EMPTY_VALUE);
    return templateWithCode;*/
    String code = JavaCodeConverter.convertToJavaCode(getPhenotype(), GPConstants.GRAMMAR_HIT_BULLET_NAME);
    String templateWithCode=javaTemplate.replace(GPConstants.ON_HIT_BY_BULLET_CODE_TAG,code);

    GPResults gpResults=GPResults.getInstance();
    Individual bestRunIndividual=gpResults.getBestRunIndividual();
    Individual bestScannedIndividual=gpResults.getBestScannedIndividual();
    String bestRunCode = JavaCodeConverter.convertToJavaCode(bestRunIndividual.getPhenotype(), GPConstants.GRAMMAR_RUN_NAME);
    String bestScannedCode = JavaCodeConverter.convertToJavaCode(bestScannedIndividual.getPhenotype(), GPConstants.GRAMMAR_SCANNED_NAME);
    templateWithCode = templateWithCode.replace(GPConstants.RUN_CODE_TAG,bestRunCode);
    templateWithCode = templateWithCode.replace(GPConstants.ON_SCANNED_ROBOT_CODE_TAG,bestScannedCode);

    return templateWithCode;
  }

  /**
   * Set fitness
   * @param battleResults battle results
   */
  @Override
  public void setFitness(BattleResults[] battleResults){
    for (robocode.BattleResults result : battleResults) {
      if(result.getTeamLeaderName().equals(GPConstants.GP_ROBOT)){
        gpRobotScore=result.getScore();
      } else {
        opponentScore=result.getScore();
      }
    }
    float calculatedFitness=gpRobotScore-opponentScore;
    setFitness(calculatedFitness);
  }


}
