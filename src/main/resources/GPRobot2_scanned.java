package gp;
import robocode.*;
import java.awt.Color;
import java.io.*;

public class GPRobot extends AdvancedRobot
{

	boolean isScanned=false;
	static int numScanned=0;
	static int numTurnStartScanned=0;

	public void run() {
		setBodyColor(Color.red);
		setGunColor(Color.black);
		setRadarColor(Color.yellow);
		setBulletColor(Color.green);
		setScanColor(Color.green);

		// Robot main loop
		while(true) {
			isScanned = false;
			/* RUN_CODE */
		}
	}

	public void onScannedRobot(ScannedRobotEvent event) {
		numTurnStartScanned=numTurnStartScanned+1;
		if(isScanned){
			numScanned = numScanned+1;
		}
		isScanned = true;

		/* ON_SCANNED_ROBOT_CODE */

		// Call scan again, before we turn the gun
		scan();
	}

	public void onBattleEnded(BattleEndedEvent event) {
		PrintStream w = null;
		try {
			w = new PrintStream(new RobocodeFileOutputStream("/home/jfernandez/robocode/robots/gp/GPRobot.data/battleData.dat",true));
			w.println("Num_scanned "+numScanned);
			w.println("Num_turn_start_scanned "+numTurnStartScanned);
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
