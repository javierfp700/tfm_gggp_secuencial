package robocode.battle;

import java.io.File;
import ec.evolution.Individual;
import gp.algorithm.GPConstants;
import net.sf.robocode.battle.Battle;
import robocode.BattleRules;
import robocode.RobocodeConstants;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;

public class BattleSimulator {

  public static final int WIDTH_BATTLEFIELD=800;
  public static final int HEIGHT_BATTLEFIELD=600;
  public static final int ROUNDS_NUM=10;

  /**
   * Simulate battle
   * @param individual individual participating in battle
   */
  public static void simulateBattle(Individual individual,String opponent){
    RobocodeEngine.setLogMessagesEnabled(false);
    RobocodeEngine engine = new RobocodeEngine(new File(RobocodeConstants.ROBOCODE_PATH));
    engine.addBattleListener(new BattleObserver(individual));
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
