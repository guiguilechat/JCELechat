package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.time.format.DateTimeFormatter;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * encapsulation of a raw connection to have better modeling. The account can be
 * given a name at creation, if this name is null it will be retrieved from
 * connection instead. This allows to identify accounts which has issue for
 * connection.
 *
 */
@AllArgsConstructor
@Accessors(fluent = true)
public class ESIAccount {

	/**
	 * formatter for data provided. all calls must be synchronized !
	 */
	public static final DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

	@Getter
	private final ESIConnected connection;

	/**
	 * name given to the token. This may not be the name of the character, for
	 * example if the token is no more valid.
	 */
	private final String name;

	public ESIAccount(String refresh, String base, String name) {
		this(new ESIConnected(refresh, base), name);
	}

	public ESIAccount(String refresh, String base) {
		this(refresh, base, null);
	}

	/**
	 *
	 * @return the name set at creation if not null ; if null, fetch the name from
	 *         the connection.
	 */
	public String name() {
		return name == null ? verify().characterName() : name;
	}

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	private final Verify verify = new Verify(connection);

	public final EveCharacter character = new EveCharacter(this);

	public final Corporation corporation = new Corporation(this);

	public final Universe universe = new Universe(this);

	public int characterId() {
		return verify().characterID();
	}

	public boolean isValid() {
		return verify().check();
	}

	@Override
	public int hashCode() {
		return connection.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != ESIAccount.class) {
			return false;
		}
		ESIConnected otherraw = ((ESIAccount) obj).connection;
		return connection == null || otherraw == null ? connection == otherraw : connection.equals(otherraw);
	}

	@Override
	public String toString() {
		return "ESI:" + name();
	}

}
