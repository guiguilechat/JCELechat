package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;

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

	/**
	 * name given to the token. This may or may not be the name of the character,
	 * for example if the token is no more valid.
	 */
	public final String name;

	public ESIAccount(ESIConnected raw, String name) {
		this.raw = raw;
		verify = new Verify(raw);
		this.name = name;
	}

	public ESIAccount(String refresh, String base, String name) {
		this(new ESIConnected(refresh, base), name);
	}

	public ESIAccount(String refresh, String base) {
		this(refresh, base, null);
	}

	public ESIConnected getConnection() {
		return raw;
	}

	public final EveCharacter character = new EveCharacter(this);

	public final Verify verify;

	public final Corporation corporation = new Corporation(this);

	public final Universe universe = new Universe(this);

	public int characterId() {
		return verify.characterID();
	}

	public String characterName() {
		return verify.characterName();
	}

	public boolean isValid() {
		return verify.check();
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

	@Override
	public String toString() {
		return "ESI:" + characterName();
	}

}
