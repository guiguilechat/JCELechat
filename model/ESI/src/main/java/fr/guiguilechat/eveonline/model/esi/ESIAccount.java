package fr.guiguilechat.eveonline.model.esi;

import java.time.format.DateTimeFormatter;

import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.modeled.Character;
import fr.guiguilechat.eveonline.model.esi.modeled.Corporation;
import fr.guiguilechat.eveonline.model.esi.modeled.Industry;
import fr.guiguilechat.eveonline.model.esi.modeled.Markets;
import fr.guiguilechat.eveonline.model.esi.modeled.Names;
import fr.guiguilechat.eveonline.model.esi.modeled.Verify;

/**
 * encapsulation of a raw connection to have better modeling
 *
 */
public class ESIAccount {

	public final ESIConnection raw;

	public static final DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

	public ESIAccount(ESIConnection raw) {
		this.raw = raw;
		character = new Character(this);
		verify = new Verify(raw);
		corporation = new Corporation(this);
		names = new Names(raw);
		markets = new Markets(this);
		industry = new Industry(this);
	}

	public ESIAccount(String refresh, String base) {
		this(new ESIConnection(refresh, base));
	}

	public static final ESIAccount DISCONNECTED = new ESIAccount(null, null);

	public ESIConnection getConnection() {
		return raw;
	}

	public final Character character;

	public final Verify verify;

	public final Names names;

	public final Markets markets;

	public final Industry industry;

	public int characterId() {
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
		if (obj == null || obj.getClass() != ESIAccount.class) {
			return false;
		}
		return raw.equals(((ESIAccount) obj).raw);
	}

}
