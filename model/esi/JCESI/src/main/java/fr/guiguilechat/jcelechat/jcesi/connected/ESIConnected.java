package fr.guiguilechat.jcelechat.jcesi.connected;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.ESIAccountHelper;
import fr.guiguilechat.jcelechat.jcesi.ESIAccountHelper.AccessToken;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.G_ICOAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_characters_character_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * raw access to the esi services using a connection.
 *
 */
public class ESIConnected extends ConnectedImpl implements G_ICOAccess {

	private static final Logger logger = LoggerFactory.getLogger(ESIConnected.class);

	private final String basicAuth, refreshToken;

	protected AccessToken accessToken = null;

	public final CacheConnected cache = new CacheConnected(this);

	public ESIConnected(String refreshToken, String basicAuth) {
		this.basicAuth = basicAuth;
		this.refreshToken = refreshToken;
	}

	public boolean isNull() {
		return basicAuth == null || refreshToken == null;
	}

	protected String getAccessToken() {
		if (accessToken == null || accessToken.expire < System.currentTimeMillis()) {
			logger.trace("fetching access token");
			accessToken = ESIAccountHelper.getAccessToken(basicAuth, refreshToken);
		}
		return accessToken == null ? null : accessToken.token;
	}

	@Override
	protected void addConnection(HashMap<String, String> props) {
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
	}

	private R_Verify verify;

	private static final R_Verify NULLVERIFY = new R_Verify() {
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
					verify = isNull() ? NULLVERIFY
							: convert(connectGet("https://login.eveonline.com/oauth/verify", null), R_Verify.class);
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

	private ObservableSet<String> roles;

	@Override
	public ObservableSet<String> getRoles() {
		if (roles == null) {
			synchronized (this) {
				if (roles == null) {
					ObsObjHolder<R_get_characters_character_id_roles> r = cache.characters.roles(verify().CharacterID);
					roles = FXCollections.observableSet();
					r.follow((o, old, newroles) -> {
						synchronized (roles) {
							List<String> roleslist = Arrays.asList(newroles.roles);
							roles.retainAll(roleslist);
							roles.addAll(roleslist);
							logger.debug("new roles for " + verify().CharacterName + " are " + roles);
						}
					});
				}
			}
		}
		return roles;
	}
}