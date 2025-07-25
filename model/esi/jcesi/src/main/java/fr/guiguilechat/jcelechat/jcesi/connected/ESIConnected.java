package fr.guiguilechat.jcelechat.jcesi.connected;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.connected.SsoFlow.AccessToken;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_ICOAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.SetHolder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * raw access to the esi services using an authentification.
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
@EqualsAndHashCode(callSuper = false)
public class ESIConnected extends ConnectedImpl implements G_ICOAccess {

	private final String refreshToken;

	private final String basicAuth;

	@Builder.Default
	@Getter(value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private SsoFlow version = SsoFlow.V2();

	@Builder.Default
	private transient AccessToken accessToken = null;

	@Getter
	@Accessors(fluent = true)
	private final transient CacheConnected cache = new CacheConnected(this);

	public boolean isNull() {
		return basicAuth == null || refreshToken == null;
	}

	protected String getAccessToken() {
		if (accessToken == null || accessToken.expire < System.currentTimeMillis()) {
			synchronized (this) {
				if (accessToken == null || accessToken.expire < System.currentTimeMillis()) {
					log.trace("fetching access token for connection {} refresh {} and basic auth {}, previous null={}",
					    Integer.toHexString(System.identityHashCode(this)), refreshToken,
					    basicAuth, accessToken == null);
					accessToken = version.getAccessToken(basicAuth, refreshToken);
					// log.trace(" fetched access token is null={}", accessToken == null);
				}
			}
		}
		return accessToken == null ? null : accessToken.token;
	}

	@Override
	protected void addConnection(Map<String, String> props) {
		props.put("Authorization", "Bearer " + getAccessToken());
	}

	public static record R_Verify(
			int CharacterID,
			String CharacterName,
			String ExpiresOn,
			String Scopes,
			String TokenType,
			String CharacterOwnerHash,
			String IntellectualProperty) {
	}

	public static final R_Verify NULLVERIFY = new R_Verify(
			0,
			"DISCONNECTED",
			"",
			"",
			"",
			"",
			"");

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final R_Verify verify = makeVerify();

	public R_Verify makeVerify() {
		if (isNull()) {
			return NULLVERIFY;
		} else {
			Requested<R_Verify> req = requestGet(version.verifyUrl(), null, R_Verify.class);
			while (req.isServerError() && req.getResponseCode() != 401) {
				log.warn("got error " + req.getError());
				req = requestGet(version.verifyUrl(), null, R_Verify.class);
			}
			R_Verify ret = req.isOk() ? req.getOK() : NULLVERIFY;
			log.debug("got verification " + ret + " for refresh " + refreshToken);
			return ret;
		}
	}

	// getting the roles

	@Getter(lazy = true)
	private final SetHolder<String> roles = makeRoles();

	protected SetHolder<String> makeRoles() {
		ObjHolder<R_get_characters_character_id_roles> rawroles = cache.characters.roles(verify().CharacterID);
		return rawroles.toSet(rr -> rr == null ? Collections.emptyList() : Arrays.asList(rr.roles))
		    .mapItems(r -> r.toString).distinct();
	}
}
