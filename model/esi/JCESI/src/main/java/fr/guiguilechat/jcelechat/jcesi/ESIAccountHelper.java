package fr.guiguilechat.jcelechat.jcesi;

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

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.G_ICOAccess;

/**
 * code to make a sso key
 *
 */
public class ESIAccountHelper {
	private static final Logger logger = LoggerFactory.getLogger(ESIAccountHelper.class);

	public static final String LOCAL_CALLBACK = "http://localhost/callback/";

	// access flow to the sso
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
		String authCode = getCodeByClipboard(appID, LOCAL_CALLBACK, G_ICOAccess.SCOPES);
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

	/**
	 * Open a browser to given URL. This is done in another thread otherwise it
	 * can crash. Don't ask.
	 */
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

	/**
	 * open browser for the client to log in his account for an app.
	 *
	 * @param appID
	 *          the public application id to connect to.
	 * @param appCalllback
	 *          the callback, must match the app's callback.
	 * @param scopes
	 *          the optional scopes to request for the app.
	 */
	public static void openBrowserForApp(String appID, String appCalllback, String... scopes) {
		String uri = "https://login.eveonline.com/oauth/authorize/?response_type=code&redirect_uri=" + appCalllback
				+ "&client_id=" + appID;
		if (scopes != null && scopes.length != 0) {
			uri = uri + "&scope=" + Stream.of(scopes).collect(Collectors.joining("%20"));
		}
		String urif = uri;
		openBrowser(urif);
	}

	/**
	 * open a new web page to create an application.
	 */
	public static void openBrowserForDevCreate() {
		openBrowser("https://developers.eveonline.com/applications/create");
	}

	/**
	 * open a web page to list the applications.
	 */
	public static void openBrowserForDevRetrieve() {
		openBrowser("https://developers.eveonline.com/applications");
	}

	public static final Pattern appIdPat = Pattern.compile("^[0-9a-fA-F]{32}$");

	/**
	 * check if an application ID string matches the requested format.
	 *
	 * @param appId
	 *          the application ID to check
	 * @return true if the appId is in correct format
	 */
	public static boolean checkAppId(String appId) {
		return appIdPat.matcher(appId).matches();
	}

	public static final Pattern appSecretPat = Pattern.compile("^[0-9a-zA-Z]{40}$");

	/**
	 * check if an application secret string matches the requested format.
	 *
	 * @param appSecret
	 *          the application secret to check
	 * @return true if the appSecret is in correct format
	 */
	public static boolean checkAppSecret(String appSecret) {
		return appSecretPat.matcher(appSecret).matches();
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
	 * convert a url that was sent as redirect from the eve auth server, to the
	 * auth code.
	 *
	 * @param redirectURL
	 *          the url the server redirected the client to.
	 * @param callback
	 *          the application's callback. It must be removed from the url.
	 * @return the auth code from the returned url
	 */
	public static String callbackURLToAuthCode(String redirectURL, String callback) {
		if (redirectURL == null || !redirectURL.startsWith(callback + "?code=")) {
			return null;
		}
		return redirectURL.substring(callback.length() + "?code=".length());
	}


	public static String getCodeByClipboard(String appID, String callback, String... scopes) {
		if (callback == null) {
			callback = LOCAL_CALLBACK;
		}
		openBrowserForApp(appID, callback, scopes == null || scopes.length == 0 ? new String[0]
				: G_ICOAccess.SCOPES);
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
			if (appAuth == null) {
				throw new UnsupportedOperationException("can't auth with null appAuth");
			}
			if (transmitData == null) {
				throw new UnsupportedOperationException("can't auth with null transmitData");
			}
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
				logger.error("while fetching url=" + url + " properties=" + con.getRequestProperties() + " appAuth=" + appAuth
						+ " transmit=" + transmitData + " : response="
						+ responseCode + " ");
				System.err.println("response is " + responseCode);
				System.err.println("properties are " + con.getRequestProperties());
				if (con.getErrorStream() != null) {
					System.err.println("returned error :");
					new BufferedReader(new InputStreamReader(con.getErrorStream())).lines().forEach(System.err::println);
				}
				return null;
			} else {
				try (InputStreamReader reader = new InputStreamReader(con.getInputStream())) {
					return new BufferedReader(reader).readLine();
				}
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
