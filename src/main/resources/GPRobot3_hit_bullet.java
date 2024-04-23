package gp;
import robocode.*;
import java.awt.Color;
import java.io.*;

public class GPRobot extends AdvancedRobot
{

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

	public void onScannedRobot(ScannedRobotEvent event) {
		/* ON_SCANNED_ROBOT_CODE */

		// Call scan again, before we turn the gun
		scan();
	}

	public void onHitByBullet(HitByBulletEvent event) {
		/* ON_HIT_BY_BULLET_CODE */
	}


}
