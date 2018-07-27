package fr.guiguilechat.eveonline.esi.connected.modeled;

import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.esi.connected.ESIConnected;

/**
 * encapsulation of a raw connection to have better modeling
 *
 */
public class ESIAccount {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ESIAccount.class);

	/**
	 * formatter for data provided. all calls must be synchronized !
	 */
	public static final DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

	public final ESIConnected raw;

	public ESIAccount(ESIConnected raw) {
		this.raw = raw;
		verify = new Verify(raw);
	}

	public ESIAccount(String refresh, String base) {
		this(new ESIConnected(refresh, base));
	}

	public ESIConnected getConnection() {
		return raw;
	}

	public final EveCharacter character = new EveCharacter(this);

	public final Verify verify;

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
		ESIConnected otherraw = ((ESIAccount) obj).raw;
		return raw == null || otherraw == null ? raw == otherraw : raw.equals(otherraw);
	}


}
