package validate;

import java.io.File;
import gp.algorithm.GPConstants;
import robocode.RobocodeConstants;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;

public class ValidateBattleSimulator {

  public static final int WIDTH_BATTLEFIELD=800;
  public static final int HEIGHT_BATTLEFIELD=600;
  public static final int ROUNDS_NUM=10;

  /**
   * Simulate battle
   */
  public static void simulateBattle(String opponent){
    RobocodeEngine.setLogMessagesEnabled(false);
    RobocodeEngine engine = new RobocodeEngine(new File(RobocodeConstants.ROBOCODE_PATH));
    engine.addBattleListener(new ValidateBattleObserver());
    engine.setVisible(false);
    BattlefieldSpecification battlefield = new BattlefieldSpecification(WIDTH_BATTLEFIELD, HEIGHT_BATTLEFIELD); // 800x600
    RobotSpecification[] selectedRobots = engine
        .getLocalRepository(GPConstants.GP_ROBOT +","+opponent);
    BattleSpecification battleSpec = new BattleSpecification(ROUNDS_NUM, battlefield,
        selectedRobots);
    System.out.println("Executing battle");
    engine.runBattle(battleSpec, true); // waits till the battle finishes
    System.out.println("Finished battle");
    engine.close();
  }

}
