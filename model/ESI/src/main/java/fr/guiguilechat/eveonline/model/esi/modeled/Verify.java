package fr.guiguilechat.eveonline.model.esi.modeled;

import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection.R_Verify;

/**
 * access to the verify method( not in swagger)
 *
 */
public class Verify {

	protected final ESIConnection raw;

	public Verify(ESIConnection raw) {
		this.raw = raw;
	}

	private R_Verify verify = null;

	protected synchronized R_Verify verify() {
		if (verify == null) {
			verify = raw.verify();
		}
		if (verify == null) {
			verify = new R_Verify();
			verify.CharacterName = null;
		}
		return verify;
	}

	public int characterID() {
		return verify().CharacterID;
	}

	public String characterName() {
		return verify().CharacterName;
	}

	public String expiresOn() {
		return verify().ExpiresOn;
	}

	public String scopes() {
		return verify().Scopes;
	}

	public String tokenType() {
		return verify().TokenType;
	}

	public String characterOwnerHash() {
		return verify().CharacterOwnerHash;
	}

}
