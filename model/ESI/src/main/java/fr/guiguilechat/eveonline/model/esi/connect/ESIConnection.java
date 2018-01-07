package fr.guiguilechat.eveonline.model.esi.connect;

import fr.guiguilechat.eveonline.model.esi.connect.modeled.Character;
import fr.guiguilechat.eveonline.model.esi.connect.modeled.Verify;

/**
 * encapsulation of a raw connection to have better modeling
 *
 */
public class ESIConnection {

	public final ESIRawConnection raw;

	public ESIConnection(ESIRawConnection connection) {
		raw = connection;
		chars = new Character(raw);
		verify = new Verify(raw);
	}

	public ESIConnection(String refresh, String base) {
		this(new ESIRawConnection(refresh, base));
	}

	public ESIRawConnection getConnection() {
		return raw;
	}

	public final Character chars;

	public final Verify verify;

	public long characterId() {
		return verify.characterID();
	}

	public String characterName() {
		return verify.characterName();
	}

	@Override
	public int hashCode() {
		return raw.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != ESIConnection.class) {
			return false;
		}
		return raw.equals(((ESIConnection) obj).raw);
	}

}
