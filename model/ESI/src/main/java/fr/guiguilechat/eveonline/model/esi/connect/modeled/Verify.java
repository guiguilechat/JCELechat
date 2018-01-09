package fr.guiguilechat.eveonline.model.esi.connect.modeled;

import fr.guiguilechat.eveonline.model.esi.connect.ESIRawConnection;
import fr.guiguilechat.eveonline.model.esi.connect.ESIRawConnection.R_Verify;

/**
 * access to the verify method( not in swagger)
 * 
 */
public class Verify {

	protected final ESIRawConnection raw;

	public Verify(ESIRawConnection raw) {
		this.raw = raw;
	}

	private R_Verify verify = null;

	protected synchronized R_Verify verify() {
		if (verify == null) {
			verify = raw.verify();
		}
		return verify;
	}

	public long characterID() {
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
