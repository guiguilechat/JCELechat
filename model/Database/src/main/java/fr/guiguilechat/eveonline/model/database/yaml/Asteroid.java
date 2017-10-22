package fr.guiguilechat.eveonline.model.database.yaml;

public class Asteroid extends Type {

	public String compressedFrom = null;

	public String compressedTo = null;

	/** number of compressedFrom we need to have one element of this. */
	public double compressRatio = 0.0;

	/**
	 * maximum security we can mine this from. if <=-1 cannot be mined
	 */
	public double maxSecurity = 0.0;

}
