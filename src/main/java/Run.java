import java.util.Arrays;
import java.util.List;
import ec.evolution.Individual;
import gp.algorithm.GP;
import gp.algorithm.GPConstants;
import gp.results.GPResults;
import robocode.RobocodeConstants;

public class Run {

  public static void main(String[] args) {
    GPResults gpResults=GPResults.getInstance();
   List<String> opponents = Arrays
          .asList(RobocodeConstants.WALLS_OPPONENT_ROBOT, RobocodeConstants.KAWIGI_ROBOT,
              RobocodeConstants.WESCO_ROBOT,RobocodeConstants.GUPPY_ROBOT, RobocodeConstants.KOWARI_ROBOT,RobocodeConstants.WRAITH_ROBOCODE);
    for (String opponent : opponents) {
        GP gp1 = new GP(GPConstants.GRAMMAR_RUN_NAME);
        Individual bestIndividual1 = gp1.execute(opponent);
        gpResults.setBestRunIndividual(bestIndividual1);
        GP gp2 = new GP(GPConstants.GRAMMAR_SCANNED_NAME);
        Individual bestIndividual2 = gp2.execute(opponent);
        gpResults.setBestScannedIndividual(bestIndividual2);
        GP gp3 = new GP(GPConstants.GRAMMAR_HIT_BULLET_NAME);
        Individual bestIndividual3 = gp3.execute(opponent);
        gpResults.setBestHitBulletIndividual(bestIndividual3);
    }
  }

}
