package gp;
import robocode.*;
import java.awt.Color;
import java.io.*;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * JaviRobot - a robot by (your name here)
 */
public class GPRobot extends AdvancedRobot
{

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
			/* RUN_CODE */
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent event) {
		/* ON_SCANNED_ROBOT_CODE */

		// Call scan again, before we turn the gun
		scan();
	}

	public void onHitByBullet(HitByBulletEvent event) {
		/* ON_HIT_BY_BULLET_CODE */
	}


}
