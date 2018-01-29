package fr.guiguilechat.eveonline.model.esi.connect;

import fr.guiguilechat.eveonline.model.esi.connect.modeled.Character;
import fr.guiguilechat.eveonline.model.esi.connect.modeled.Corporation;
import fr.guiguilechat.eveonline.model.esi.connect.modeled.Markets;
import fr.guiguilechat.eveonline.model.esi.connect.modeled.Names;
import fr.guiguilechat.eveonline.model.esi.connect.modeled.Verify;

/**
 * encapsulation of a raw connection to have better modeling
 *
 */
public class ESIConnection {

	public final ESIRawConnection raw;

	public ESIConnection(ESIRawConnection raw) {
		this.raw = raw;
		character = new Character(raw);
		verify = new Verify(raw);
		corporation = new Corporation(this);
		names = new Names(raw);
		markets = new Markets(this);
	}

	public ESIConnection(String refresh, String base) {
		this(new ESIRawConnection(refresh, base));
	}

	public ESIRawConnection getConnection() {
		return raw;
	}

	public final Character character;

	public final Verify verify;

	public final Names names;

	public final Markets markets;

	public long characterId() {
		return verify.characterID();
	}

	public String characterName() {
		return verify.characterName();
	}

	public final Corporation corporation;

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
