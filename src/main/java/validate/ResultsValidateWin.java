package validate;

public class ResultsValidateWin {

  private static ResultsValidateWin instance;
  public boolean winRobot;

  private ResultsValidateWin(){
    this.winRobot=false;
  }

  public static ResultsValidateWin getInstance(){
    if(instance==null){
      instance = new ResultsValidateWin();
    }
    return instance;
  }

  public boolean isWinRobot() {
    return winRobot;
  }

  public void setWinRobot(boolean winRobot) {
    this.winRobot = winRobot;
  }
}
