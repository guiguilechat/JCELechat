package fr.guiguilechat.eveonline.model.database.yaml;

public class HullAttributes {

	public int velocity;
	public double warpSpeed;
	public double agility;
	public long mass;
	public long volume;

	public int targetRange;
	public int scanRes;
	public int scanStr;
	/** RADAR | LADAR | Magnetometric | Gravimetric */
	public String scanType;
	public int maxTargets;

	public int highSlots, mediumSlots, lowSlots, launcherHardPoints, turretHardPoints, cpu, powergrid;
	public double capacitor, capacitorTime;

	public int rigSlots, rigCalibration;
	/** small | medium | large | capital */
	public String rigSize = null;

	public int droneCapa, droneBandwidth;

}