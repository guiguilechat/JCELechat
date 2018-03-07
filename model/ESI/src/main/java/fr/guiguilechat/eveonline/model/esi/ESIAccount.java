package fr.guiguilechat.eveonline.model.esi;

import java.time.format.DateTimeFormatter;

import fr.guiguilechat.eveonline.model.esi.direct.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.modeled.Corporation;
import fr.guiguilechat.eveonline.model.esi.modeled.EveCharacter;
import fr.guiguilechat.eveonline.model.esi.modeled.Industry;
import fr.guiguilechat.eveonline.model.esi.modeled.Markets;
import fr.guiguilechat.eveonline.model.esi.modeled.Names;
import fr.guiguilechat.eveonline.model.esi.modeled.PI;
import fr.guiguilechat.eveonline.model.esi.modeled.Route;
import fr.guiguilechat.eveonline.model.esi.modeled.Universe;
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
		verify = new Verify(raw);
		names = new Names(raw);
	}

	public ESIAccount(String refresh, String base) {
		this(new ESIConnection(refresh, base));
	}

	public static final ESIAccount DISCONNECTED = new ESIAccount(null, null);

	public ESIConnection getConnection() {
		return raw;
	}

	public final EveCharacter character = new EveCharacter(this);

	public final Verify verify;

	public final Names names;

	public final Markets markets = new Markets(this);

	public final Industry industry = new Industry(this);

	public final PI pi = new PI(this);

	public final Route route = new Route(this);

	public final Corporation corporation = new Corporation(this);

	public final Universe universe = new Universe(this);

	public int characterId() {
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
		if (obj == null || obj.getClass() != ESIAccount.class) {
			return false;
		}
		ESIConnection otherraw = ((ESIAccount) obj).raw;
		return raw == null || otherraw == null ? raw == otherraw : raw.equals(otherraw);
	}

}
