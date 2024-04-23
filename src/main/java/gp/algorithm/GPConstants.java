package gp.algorithm;

public class GPConstants {

  public static final String AXIOM="<S>";
  public static final String ROBOT_TEMPLATE ="GPRobot.java";
  public static final String ROBOT_RUN_TEMPLATE ="GPRobot1_run.java";
  public static final String ROBOT_SCANNED_TEMPLATE ="GPRobot2_scanned.java";
  public static final String ROBOT_HIT_BULLET_TEMPLATE ="GPRobot3_hit_bullet.java";
  public static final String RUN_CODE_TAG="/* RUN_CODE */";
  public static final String ON_BULLET_HIT_CODE_TAG="/* ON_BULLET_HIT_CODE */";
  public static final String ON_BULLET_HIT_BULLET_CODE_TAG="/* ON_BULLET_HIT_BULLET_CODE */";
  public static final String ON_BULLET_MISSED_CODE_TAG="/* ON_BULLET_MISSED_CODE */";
  public static final String ON_HIT_BY_BULLET_CODE_TAG="/* ON_HIT_BY_BULLET_CODE */";
  public static final String ON_HIT_ROBOT_CODE_TAG="/* ON_HIT_ROBOT_CODE */";
  public static final String ON_HIT_WALL_CODE_TAG="/* ON_HIT_WALL_CODE */";
  public static final String ON_SCANNED_ROBOT_CODE_TAG="/* ON_SCANNED_ROBOT_CODE */";
  public static final String REAL_VALUE_TAG="realvalue";
  public static final String EMPTY_TAG="empty";
  public static final String EMPTY_VALUE="";
  public static final String GP_ROBOT ="gp.GPRobot*";
  public static final Integer GP_TOURNAMENT_SIZE=5;
  public static final Integer GP_POPULATION_SIZE=50;
  public static final Integer MAXIMUM_DEPTH_INDIVIDUAL_INITIALIZATION=50;
  public static final Float GP_REPLACED_POPULATION_PERCENTAGE =0.2F;
  public static final Float GP_CROSSOVER_PROBABILITY =1F;
  public static final Float GP_MUTATION_PROBABILITY =0.15F;
  public static final Integer MAXIMUM_DEPTH_MUTATION_SUBTREE=20;
  public static final Integer GP_MAXIMUM_GENERATIONS=200; //200
  //public static final Integer GP_MAXIMUM_GENERATIONS =5;

  public static final String GRAMMAR_RUN_NAME = "grammar1_run";
  public static final String GRAMMAR_RUN_FILE = GRAMMAR_RUN_NAME +".bnf";
  public static final String GRAMMAR_SCANNED_NAME = "grammar2_scanned";
  public static final String GRAMMAR_SCANNED_FILE = GRAMMAR_SCANNED_NAME +".bnf";
  public static final String GRAMMAR_HIT_BULLET_NAME = "grammar3_hit_bullet";
  public static final String GRAMMAR_HIT_BULLET_FILE = GRAMMAR_HIT_BULLET_NAME +".bnf";
}
