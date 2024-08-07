package fr.guiguilechat.jcelechat.jcesi.connected;

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
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * code to make a sso key
 *
 */
public abstract class SsoFlow {
	private static final Logger logger = LoggerFactory.getLogger(SsoFlow.class);

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

	public abstract String verifyUrl();

	public abstract String baseAuthUrl();

	public abstract boolean addState();

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
	public void openBrowserForApp(String appID, String appCalllback, String... scopes) {
		String uri = baseAuthUrl() + "authorize?" + (addState() ? "state=aa&" : "") + "response_type=code&redirect_uri="
		    + appCalllback
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

	static final Pattern appIdPat = Pattern.compile("^[0-9a-fA-F]{32}$");

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

	static final Pattern appSecretPat = Pattern.compile("^[0-9a-zA-Z]{40}$");

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
		return redirectURL.substring(callback.length() + "?code=".length()).replaceAll("&state=.*", "");
	}


	public String getCodeByClipboard(String appID, String callback, String... scopes) {
		Objects.requireNonNull(callback);
		openBrowserForApp(appID, callback, scopes == null || scopes.length == 0 ? new String[0]
				: scopes);
		return callbackURLToAuthCode(extractStringFromClipboard(), callback);
	}

	public static String encode(String appID, String appSecret) {
		return Base64.getEncoder().encodeToString((appID + ":" + appSecret).getBytes(StandardCharsets.UTF_8));
	}

	public static enum CONTENT_TYPE {
		JSON {

			@Override
			public String getContentType() {
				return "application/json;charset=UTF-8";
			}

			@Override
			public String encode(Map<String, String> params) {
				try {
					return new ObjectMapper().writeValueAsString(params);
				} catch (JsonProcessingException e) {
					throw new UnsupportedOperationException("catch this", e);
				}
			}
		},

		FORM {

			@Override
			public String getContentType() {
				return "application/x-www-form-urlencoded;charset=UTF-8";
			}

			@Override
			public String encode(Map<String, String> params) {
				StringBuilder sb = null;
				for (Entry<String, String> entry : params.entrySet()) {
					if (sb == null) {
						sb = new StringBuilder();
					} else {
						sb.append("&");
					}
					try {
						sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
						sb.append("=");
						sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						throw new UnsupportedOperationException("catch this", e);
					}
				}
				return sb == null ? "" : sb.toString();
			}
		};

		public abstract String getContentType();

		public abstract String encode(Map<String, String> params);
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
	protected String getAuthLine(String appAuth, Map<String, String> transmitMap, CONTENT_TYPE type) {
		try {
			if (appAuth == null) {
				throw new UnsupportedOperationException("can't auth with null appAuth");
			}
			if (transmitMap == null) {
				throw new UnsupportedOperationException("can't auth with null transmitData");
			}
			String transmitData = type.encode(transmitMap);
			// System.err.println("sending auth line with base64=" + appAuth + " and body="
			// + transmitData);
			String url = baseAuthUrl() + "token";
			URL target = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) target.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Basic " + appAuth);
			con.setRequestProperty("Content-Type", type.getContentType());
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(transmitData.getBytes(StandardCharsets.UTF_8));
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				logger.error("while fetching url=" + url + " appAuth=" + appAuth
						+ " transmit=" + transmitData + " : responseCode="
				    + responseCode, new Exception());
				System.err.println("transmit is " + transmitData);
				System.err.println("response is " + responseCode);
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

	static CONTENT_TYPE content_type = CONTENT_TYPE.FORM;

	public Map<String, String> getFromCode(String appAuth, String authorizationCode) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("grant_type", "authorization_code");
			params.put("code", authorizationCode);
			return new ObjectMapper().readValue(
			    getAuthLine(appAuth, params, content_type),
					new TypeReference<Map<String, String>>() {
					});
		} catch (Exception e) {
			logger.error("while getting refresh token", e);
			return null;
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
	public String getRefreshToken(String appAuth, String authorizationCode) {
		try {
			Map<String, String> map = getFromCode(appAuth, authorizationCode);
			// System.err.println("auth response is " + map);
			String refreshtoken = map.get("refresh_token");
			if (refreshtoken == null) {
				System.err.println("received " + map);
			}
			return refreshtoken;
		} catch (Exception e) {
			logger.error("while getting refresh token", e);
			return null;
		}
	}

	public static class AccessToken {
		public long expire;
		public String token;
	}

	/**
	 * get an access token from a refresh token
	 * 
	 * @param appAuth
	 *                       the base64 value of APPID:APPSECRET
	 * @param refreshtoken retrieved refresh token
	 * @return access token, or null if error
	 */
	public AccessToken getAccessToken(String appAuth, String refreshtoken) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("grant_type", "refresh_token");
			params.put("refresh_token", refreshtoken);
			Map<String, String> map = new ObjectMapper().readValue(getAuthLine(appAuth, params, content_type),
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
			logger.error("while getting access token", e);
			return null;
		}
	}

	// actual implementations

	public static SsoFlow V1 = new SsoFlow() {

		@Override
		public String verifyUrl() {
			return "https://login.eveonline.com/oauth/verify";
		}

		@Override
		public String baseAuthUrl() {
			return "https://login.eveonline.com/oauth/";
		}

		@Override
		public boolean addState() {
			return false;
		}

	};

	public static SsoFlow V2 = new SsoFlow() {

		@Override
		public String verifyUrl() {
			return "https://login.eveonline.com/oauth/verify";
		}

		@Override
		public String baseAuthUrl() {
			return "https://login.eveonline.com/v2/oauth/";
		}

		@Override
		public boolean addState() {
			return true;
		}

	};

	/** extract version from refresh token. Defaults to V2 */
	public static SsoFlow extract(String refresh) {
		if (refresh == null) {
			return V2;
		}
		if (refresh.length() != 24) {
			return V1;
		}
		return V2;
	}

}
