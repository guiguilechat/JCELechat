package fr.guiguilechat.eveonline.model.esi.connect;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ESIConnection {

	private static final Logger logger = LoggerFactory.getLogger(ESIConnection.class);

	// acess flow to the sso
	public static void main(String[] args) {
		// 1 we need app id and app secret.
		String appID = null, appCode = null;
		// if args were specified we assume they are the app id and app secret.
		if (args.length > 1) {
			appID = args[0];
			appCode = args[1];
		} else {
			// request user to create api by directing him to the site.
			// the user should copy the appID, then copy the appCode
			openBrowserForDevAPI();
			appID = extractStringFromClipboard();
			System.out.println("api id is " + appID);
			appCode = extractStringFromClipboard();
			System.out.println("api code is " + appCode);
		}
		// the api + code is transformed into the basic code used in headers :
		String basicCode = encode(appID, appCode);

		// 2 request user to accept the connection of his app to his account
		// the user should copy the url of the error page
		String authCode = getCodeByClipboard(appID, "http://localhost/callback/", SCOPES.values());
		System.out.println("auth code is " + authCode);

		// 3 get a refresh token. The couple basicCode+refreshtoken allow us to
		// access the app later.
		String refreshToken = getRefreshToken(basicCode, authCode);
		System.out.println("refresh token is " + refreshToken);

		// 4 get an access token from the refreshToken. We need the access token to
		// actually ask the esi.
		String accessToken = getAccessToken(basicCode, refreshToken).token;
		System.out.println("acces token is " + accessToken);
	}

	public static enum SCOPES {
		corporationContactsRead, publicData, characterStatsRead, characterFittingsRead, characterFittingsWrite, characterContactsRead, characterContactsWrite, characterLocationRead, characterNavigationWrite, characterWalletRead, characterAssetsRead, characterCalendarRead, characterFactionalWarfareRead, characterIndustryJobsRead, characterKillsRead, characterMailRead, characterMarketOrdersRead, characterMedalsRead, characterNotificationsRead, characterResearchRead, characterSkillsRead, characterAccountRead, characterContractsRead, characterBookmarksRead, characterChatChannelsRead, characterClonesRead, characterOpportunitiesRead, characterLoyaltyPointsRead, corporationWalletRead, corporationAssetsRead, corporationMedalsRead, corporationFactionalWarfareRead, corporationIndustryJobsRead, corporationKillsRead, corporationMembersRead, corporationMarketOrdersRead, corporationStructuresRead, corporationShareholdersRead, corporationContractsRead, corporationBookmarksRead, fleetRead, fleetWrite, structureVulnUpdate, remoteClientUI;
	}

	public static boolean openBrowserForApp(String appID, String appCalllback, String... scopes) {
		String uri = "https://login.eveonline.com/oauth/authorize/?response_type=code&redirect_uri=" + appCalllback
				+ "&client_id=" + appID;
		if (scopes != null && scopes.length != 0) {
			uri = uri + "&scope=" + Stream.of(scopes).collect(Collectors.joining("%20"));
		}
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(uri));
				return true;
			}
		} catch (Exception e) {
			logger.debug("while open browser for " + uri, e);
		}
		return false;
	}

	public static void openBrowserForDevAPI() {
		String uri = "https://developers.eveonline.com/applications/create";
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(uri));
			}
		} catch (Exception e) {
			logger.debug("while open browser for " + uri, e);
		}

	}

	public static String extractStringFromClipboard() {
		StringSelection sel = new StringSelection("");
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		LinkedBlockingQueue<String> dataHolder = new LinkedBlockingQueue<>(1);
		clipboard.setContents(sel, (clip, data) -> {
			try {
				dataHolder.put((String) clipboard.getData(DataFlavor.stringFlavor));
			} catch (InterruptedException | UnsupportedFlavorException | IOException e) {
				logger.warn("while reading clipboard " + data, e);
				try {
					dataHolder.put(null);
				} catch (InterruptedException e1) {
					throw new UnsupportedOperationException("catch this", e1);
				}
			}
		});
		try {
			return dataHolder.take();
		} catch (InterruptedException e) {
			return null;
		}
	}

	public static String getCodeByClipboard(String appID, String calllback, SCOPES... scopes) {
		openBrowserForApp(appID, calllback, scopes == null || scopes.length == 0 ? new String[0]
				: Stream.of(scopes).map(SCOPES::name).toArray(String[]::new));
		String cpData = extractStringFromClipboard();
		if (cpData == null || !cpData.startsWith(calllback + "?code=")) {
			return null;
		}
		return cpData.substring(calllback.length() + "?code=".length());
	}

	public static String encode(String appID, String appCode) {
		return Base64.getEncoder().encodeToString((appID + ":" + appCode).getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * connect to the auth server with given appAuth, transmit data and return the
	 * resulting line.
	 *
	 * @param appAuth
	 *          the base64 value of APPID:APPSECRET
	 * @param transmitData
	 *          the data transmitted in the header of the application, as JSON
	 * @return the line returned as the result of connection, or null if any issue
	 *         appears.
	 */
	protected static String getAuthLine(String appAuth, String transmitData) {
		try {
			String url = "https://login.eveonline.com/oauth/token";
			URL target = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) target.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Basic " + appAuth);
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(transmitData.getBytes(StandardCharsets.UTF_8));
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				System.err.println("response is " + responseCode);
				System.err.println("properties are " + con.getRequestProperties());
				System.err.println("returned error :");
				new BufferedReader(new InputStreamReader(con.getErrorStream())).lines().forEach(System.err::println);
				return null;
			} else {
				return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
			}
		} catch (Exception e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public static String getRefreshToken(String appAuth, String authorizationCode) {
		String transmit = "{\"grant_type\":\"authorization_code\",\"code\":\"" + authorizationCode + "\"}";
		try {
			Map<String, String> map = new ObjectMapper().readValue(getAuthLine(appAuth, transmit),
					new TypeReference<Map<String, String>>() {
			});
			String refreshtoken = map.get("refresh_token");
			if (refreshtoken == null) {
				System.err.println("received " + map);
			}
			return refreshtoken;
		} catch (Exception e) {
			logger.debug("while getting refresh token", e);
			return null;
		}
	}

	public static class AccessToken {
		public long expire;
		public String token;
	}

	public static AccessToken getAccessToken(String appAuth, String refreshtoken) {
		String transmit = "{\"grant_type\":\"refresh_token\",\"refresh_token\":\"" + refreshtoken + "\"}";
		try {
			Map<String, String> map = new ObjectMapper().readValue(getAuthLine(appAuth, transmit),
					new TypeReference<Map<String, String>>() {
			});
			String accessToken = map.get("access_token");
			if (accessToken == null) {
				System.err.println("received " + map);
			}
			AccessToken ret = new AccessToken();
			ret.token = accessToken;
			ret.expire = System.currentTimeMillis() + (Integer.parseInt(map.get("expires_in")) - 1) * 1000;
			return ret;
		} catch (Exception e) {
			logger.debug("while getting access token", e);
			return null;
		}
	}

	private final String basicAuth, refreshToken;

	protected AccessToken accessToken = null;

	public ESIConnection(String basicAuth, String refreshToken) {
		this.basicAuth = basicAuth;
		this.refreshToken = refreshToken;
	}

	protected String getAccessToken() {
		if (accessToken == null || accessToken.expire < System.currentTimeMillis()) {
			logger.trace("fetching access token");
			accessToken = getAccessToken(basicAuth, refreshToken);
		}
		return accessToken.token;
	}

	public String getAuthorization() {
		return "Bearer " + getAccessToken();
	}


	/**
	 * connect to an url and retrieve the result.
	 *
	 * @param url
	 *          the url to fetch
	 * @param method
	 *          the method to connect. must be POST or GET
	 * @param properties
	 *          the properties to transmit in the header
	 * @param transmit
	 *          the data to transmit duing the query
	 * @return the line returned by the server as a response. null if there was an
	 *         issue
	 */
	public static String connect(String url, String method, Map<String, String> properties, String transmit) {
		try {
			URL target = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) target.openConnection();
			con.setRequestMethod(method);
			if (properties != null) {
				for (Entry<String, String> e : properties.entrySet()) {
					con.setRequestProperty(e.getKey(), e.getValue());
				}
			}
			con.setDoOutput(true);
			if (transmit != null) {
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(transmit.getBytes(StandardCharsets.UTF_8));
				wr.flush();
				wr.close();
			}
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				StringBuilder sb = new StringBuilder("get").append(url).append(" ").append(responseCode);
				if (responseCode != 500) {
					sb.append(" error : ");
					new BufferedReader(new InputStreamReader(con.getErrorStream())).lines().forEach(sb::append);
				}
				System.err.println(sb.toString());
				return null;
			} else {
				return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
			}
		} catch (Exception e) {
			logger.debug("while geting " + url, e);
			return null;
		}
	}

	/**
	 * get an url, using your authorization
	 *
	 * @param url
	 *          the url to fetch
	 * @return the line returned by the server as a response. null if there was an
	 *         issue
	 */
	public String connectGet(String url, boolean connected) {
		Map<String, String> props;
		if (connected) {
			props = new HashMap<>();
			props.put("Authorization", getAuthorization());
		} else {
			props = Collections.emptyMap();
		}
		return connect(url, "GET", props, null);
	}

	/**
	 * post an url, using your authorization
	 *
	 * @param url
	 *          the url to fetch
	 * @param contentType
	 *          the type of the data transmitted
	 * @param transmit
	 *          the data to transmit during the query
	 * @return the line returned by the server as a response. null if there was an
	 *         issue
	 */
	public String connectPost(String url, String contentType, String transmit, boolean connected) {
		HashMap<String, String> props = new HashMap<>();
		if (connected) {
			props.put("Authorization", getAuthorization());
		}
		props.put("Content-Type", contentType);
		return connect(url, "GET", props, transmit);
	}

	ObjectWriter ow = new ObjectMapper().writer();

	public String connectPost(String url, Map<String, String> transmit, boolean connected) {
		try {
			return connectPost(url, "application/json", ow.writeValueAsString(transmit), connected);
		} catch (JsonProcessingException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	public String verify() {
		String res = connectGet("https://login.eveonline.com/oauth/verify", true);
		try {
			Map<String, String> map = new ObjectMapper().readValue(res, new TypeReference<Map<String, String>>() {
			});
			return map.get("CharacterName");
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

}
