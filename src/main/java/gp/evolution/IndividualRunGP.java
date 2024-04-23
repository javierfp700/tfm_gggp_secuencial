package gp.evolution;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import converter.JavaCodeConverter;
import gp.algorithm.GPConstants;
import gp.structure.DerivationTree;
import robocode.BattleResults;

public class IndividualRunGP extends IndividualGP{

  private float numScanned=0;
  private float numRun=0;

  public IndividualRunGP(DerivationTree derivationTree) {
    super(derivationTree);
  }

  @Override
  public String getRobotTemplate() {
    return GPConstants.ROBOT_RUN_TEMPLATE;
  }

  @Override
  public String geFitnessDetail() {
    return "fitness: " + getFitness() + " ;numScanned: " + numScanned + " ;numRun: "+numRun;
  }

  /**
   * Replace skeleton of robot code with code of genetic programming
   * @param javaTemplate content with skeleton of robot code
   * @return robot code
   */
  @Override
  public String replaceJavaTemplateWithCode(String javaTemplate){
    String code= JavaCodeConverter.convertToJavaCode(getPhenotype(), GPConstants.GRAMMAR_RUN_NAME);
    String templateWithCode=javaTemplate.replace(GPConstants.RUN_CODE_TAG,code);

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
        if(lineElements[0].equals("Num_run")){
          numRun=Float.parseFloat(lineElements[1]);
        }
      }
      float calculatedFitness=numScanned/numRun;
      setFitness(calculatedFitness);
      reader.close();
      file.delete();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
