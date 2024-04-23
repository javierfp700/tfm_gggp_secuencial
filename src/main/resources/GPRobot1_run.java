package gp;
import robocode.*;
import java.awt.Color;
import java.io.*;
import java.util.Arrays;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * JaviRobot - a robot by (your name here)
 */
public class GPRobot extends AdvancedRobot
{

	boolean isScanned=false;
	static int numScanned=0;
	static int numRun=0;

	/**
	 * run: JaviRobot's default behavior
	 */
	public void run() {
		setBodyColor(Color.red);
		setGunColor(Color.black);
		setRadarColor(Color.yellow);
		setBulletColor(Color.green);
		setScanColor(Color.green);

		// Robot main loop
		while(true) {
			isScanned=false;
			numRun=numRun+1;

			/* RUN_CODE */
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent event) {
		if(!isScanned){
			numScanned=numScanned+1;
		}
		isScanned=true;

		// Call scan again, before we turn the gun
		scan();
	}

	/**
	 * onBattleEnded
	 */
	public void onBattleEnded(BattleEndedEvent event) {
		PrintStream w = null;
		try {
			w = new PrintStream(new RobocodeFileOutputStream("/home/jfernandez/robocode/robots/gp/GPRobot.data/battleData.dat",true));
			w.println("Num_scanned "+numScanned);
			w.println("Num_run "+numRun);
			w.flush();
			w.close();
		} catch (IOException exception) {
			out.println("Impossible to write battle data");
		} finally {
			if (w != null) {
				w.close();
			}
		}
	}

}
