package fr.guiguilechat.eveonline.model.esi;

import java.time.format.DateTimeFormatter;

import fr.guiguilechat.eveonline.model.esi.direct.ESIRawConnection;
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
public class ESIConnection {

	public final ESIRawConnection raw;

	public static final DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

	public ESIConnection(ESIRawConnection raw) {
		this.raw = raw;
		character = new Character(this);
		verify = new Verify(raw);
		corporation = new Corporation(this);
		names = new Names(raw);
		markets = new Markets(this);
		industry = new Industry(this);
	}

	public ESIConnection(String refresh, String base) {
		this(new ESIRawConnection(refresh, base));
	}

	public static final ESIConnection DISCONNECTED = new ESIConnection(null, null);

	public ESIRawConnection getConnection() {
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
		if (obj == null || obj.getClass() != ESIConnection.class) {
			return false;
		}
		return raw.equals(((ESIConnection) obj).raw);
	}

}
