import java.util.Arrays;
import java.util.List;
import robocode.RobocodeConstants;
import validate.ValidationBattle;

public class RunValidate {

  public static void main(String[] args) {
    String myRobot="/home/jfernandez/Desktop/micro_2024_04_23_16_57_51_grammar3_hit_bullet/bests/GPRobot_best.java";
    List<String> opponents= Arrays.asList(
        RobocodeConstants.WRAITH_ROBOCODE
    );
    ValidationBattle validationBattle = new ValidationBattle();
    validationBattle.execute(myRobot, opponents);
  }

}
