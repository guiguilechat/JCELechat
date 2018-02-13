package fr.guiguilechat.eveonline.model.esi;

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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * code to make a sso key
 *
 */
public class ESITools {
	private static final Logger logger = LoggerFactory.getLogger(ESITools.class);

	public static final String LOCAL_CALLBACK = "http://localhost/callback/";

	// acess flow to the sso
	public static void main(String[] args) {
		// 1 we need app id and app secret.
		String appID = null, appSecret = null;
		// if args were specified we assume they are the app id and app secret.
		if (args.length > 1) {
			appID = args[0];
			appSecret = args[1];
		} else {
			// request user to create api by directing him to the site.
			// the user should copy the appID, then copy the appCode
			openBrowserForDevCreate();
			appID = extractStringFromClipboard();
			System.out.println("api id is " + appID);
			appSecret = extractStringFromClipboard();
			System.out.println("api code is " + appSecret);
		}

		if (!checkAppId(appID)) {
			System.out.println("incorrect app ID " + appID);
			return;
		}
		if (!checkAppSecret(appSecret)) {
			System.out.println("incorect app secret " + appSecret);
			return;
		}
		// the api + code is transformed into the basic code used in headers :
		String basicCode = encode(appID, appSecret);

		// 2 request user to accept the connection of his app to his account
		// the user should copy the url of the error page
		String authCode = getCodeByClipboard(appID, LOCAL_CALLBACK, SCOPES.values());
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
		String urif = uri;
		openBrowser(urif);
		return false;
	}

	/** open a browser to given url */
	public static void openBrowser(String url) {
		new Thread(() -> {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException | URISyntaxException e) {
					logger.debug("while open browser for " + url, e);
				}
			}
		}).start();
	}

	public static void openBrowserForDevCreate() {
		openBrowser("https://developers.eveonline.com/applications/create");
	}

	public static void openBrowserForDevRetrieve() {
		openBrowser("https://developers.eveonline.com/applications");
	}

	public static final Pattern appIdPat = Pattern.compile("^[0-9a-fA-F]{32}$");

	public static boolean checkAppId(String value) {
		return appIdPat.matcher(value).matches();
	}

	public static final Pattern appKeyPat = Pattern.compile("^[0-9a-zA-Z]{40}$");

	public static boolean checkAppSecret(String value) {
		return appKeyPat.matcher(value).matches();
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

	/**
	 * @param redirectURL
	 *          the url the client was redirected to
	 * @param callback
	 *          the app's callback
	 * @return the auth code from the returned url
	 */
	public static String callbackURLToAuthCode(String redirectURL, String callback) {
		if (redirectURL == null || !redirectURL.startsWith(callback + "?code=")) {
			return null;
		}
		return redirectURL.substring(callback.length() + "?code=".length());
	}

	public static String getCodeByClipboard(String appID, String callback, SCOPES... scopes) {
		if (callback == null) {
			callback=LOCAL_CALLBACK;
		}
		openBrowserForApp(appID, callback, scopes == null || scopes.length == 0 ? new String[0]
				: Stream.of(scopes).map(SCOPES::name).toArray(String[]::new));
		return callbackURLToAuthCode(extractStringFromClipboard(), callback);
	}

	public static String encode(String appID, String appSecret) {
		return Base64.getEncoder().encodeToString((appID + ":" + appSecret).getBytes(StandardCharsets.UTF_8));
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

	/**
	 *
	 * @param appAuth
	 *          the base64 id:secret of the app
	 * @param authorizationCode
	 *          the token returned by ccp server when login the client.
	 * @return the new refresh token that allows to create esiconnection.
	 */
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
}
