package fr.guiguilechat.eveonline.database.elements;

public class Asteroid {

	public String compressedFrom = null;

	public String compressedTo = null;

	// number of compressedFrom we need to have one element of this.
	public double compressRatio = 0.0;

	public double volume;

	public int id;

	/**
	 * maximum security we can mine this from. if <=-1 cannot be mined
	 */
	public double maxSecurity = 0.0;

}
