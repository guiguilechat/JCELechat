package fr.guiguilechat.jcelechat.jcesi.connected;

import java.util.Arrays;
import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.ESIAccountHelper;
import fr.guiguilechat.jcelechat.jcesi.ESIAccountHelper.AccessToken;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_ICOAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsSetHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * raw access to the esi services using a connection.
 *
 */
@RequiredArgsConstructor
@Slf4j
public class ESIConnected extends ConnectedImpl implements G_ICOAccess {

	private final String refreshToken;

	private final String basicAuth;

	private transient AccessToken accessToken = null;

	@Getter
	@Accessors(fluent = true)
	private final transient CacheConnected cache = new CacheConnected(this);

	public boolean isNull() {
		return basicAuth == null || refreshToken == null;
	}

	protected String getAccessToken() {
		if (accessToken == null || accessToken.expire < System.currentTimeMillis()) {
			log.trace("fetching access token");
			accessToken = ESIAccountHelper.getAccessToken(basicAuth, refreshToken);
		}
		return accessToken == null ? null : accessToken.token;
	}

	@Override
	protected void addConnection(Map<String, String> props) {
		props.put("Authorization", "Bearer " + getAccessToken());
	}


	public static class R_Verify {
		public int CharacterID;
		public String CharacterName;
		public String ExpiresOn;
		public String Scopes;
		public String TokenType;
		public String CharacterOwnerHash;
		public String IntellectualProperty;

		@Override
		public String toString() {
			return "id=" + CharacterID + " name=" + CharacterName + " expire=" + ExpiresOn;
		}
	}

	private R_Verify verify;

	public static final R_Verify NULLVERIFY = new R_Verify() {
		{
			CharacterID = 0;
			CharacterName = "DISCONNECTED";
			ExpiresOn = "";
			Scopes = "";
			TokenType = "";
			CharacterOwnerHash = "";
			IntellectualProperty = "";
		}
	};

	public R_Verify verify() {
		if (verify == null) {
			synchronized (this) {
				if (verify == null) {
					if (isNull()) {
						verify = NULLVERIFY;
					} else {
						Requested<R_Verify> req = requestGet("https://login.eveonline.com/oauth/verify", null, R_Verify.class);
						while (req.isServerError() || req.getResponseCode() == 401) {
							log.warn("got error " + req.getError());
							req = requestGet("https://login.eveonline.com/oauth/verify", null, R_Verify.class);
						}
						verify = req.getOKOr(null);
					}
					log.debug("got verification " + verify + " for refresh " + refreshToken);
				}
			}
		}
		return verify;
	}

	@Override
	public int hashCode() {
		return (basicAuth != null ? basicAuth.hashCode() : 0) + (refreshToken != null ? refreshToken.hashCode() : 0);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != ESIConnected.class) {
			return false;
		}
		ESIConnected o = (ESIConnected) obj;
		return (refreshToken == null && o.refreshToken == null
				|| refreshToken != null && refreshToken.equals(o.refreshToken))
				&& (basicAuth == null && o.basicAuth == null || basicAuth != null && basicAuth.equals(o.basicAuth));
	}

	// getting the roles

	@Getter(lazy = true)
	private final ObsSetHolder<String> roles = makeRoles();

	protected ObsSetHolder<String> makeRoles() {
		ObsObjHolder<R_get_characters_character_id_roles> rawroles = cache.characters.roles(verify().CharacterID);
		return rawroles.toSet(rr -> Arrays.asList(rr.roles)).mapItems(r -> r.toString).distinct();
	}
}
