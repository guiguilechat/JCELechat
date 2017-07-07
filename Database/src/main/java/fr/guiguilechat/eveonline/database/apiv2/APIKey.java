package fr.guiguilechat.eveonline.database.apiv2;

public class APIKey {

	public APIKey(int keyID, String code) {
		this.keyID = keyID;
		this.code = code;
	}

	public final int keyID;
	public final String code;

}
