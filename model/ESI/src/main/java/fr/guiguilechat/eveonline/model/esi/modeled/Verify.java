package fr.guiguilechat.eveonline.model.esi.modeled;

import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;

/**
 * access to the verify method( not in swagger)
 *
 */
public class Verify {

	protected final ESIConnection raw;

	public Verify(ESIConnection raw) {
		this.raw = raw;
	}

	public int characterID() {
		return raw.verify().CharacterID;
	}

	public String characterName() {
		return raw.verify().CharacterName;
	}

	public String expiresOn() {
		return raw.verify().ExpiresOn;
	}

	public String scopes() {
		return raw.verify().Scopes;
	}

	public String tokenType() {
		return raw.verify().TokenType;
	}

	public String characterOwnerHash() {
		return raw.verify().CharacterOwnerHash;
	}

}
