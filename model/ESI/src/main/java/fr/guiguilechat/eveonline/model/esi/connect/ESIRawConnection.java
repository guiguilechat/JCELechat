package fr.guiguilechat.eveonline.model.esi.connect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.guiguilechat.eveonline.model.esi.connect.MakeKey.AccessToken;
import is.ccp.tech.esi.Swagger;

/**
 * raw access to the esi services using a connection.
 *
 */
public class ESIRawConnection implements Swagger {

	private static final Logger logger = LoggerFactory.getLogger(ESIRawConnection.class);

	private final String basicAuth, refreshToken;

	protected AccessToken accessToken = null;

	public ESIRawConnection(String refreshToken, String basicAuth) {
		this.basicAuth = basicAuth;
		this.refreshToken = refreshToken;
	}

	protected String getAccessToken() {
		if (accessToken == null || accessToken.expire < System.currentTimeMillis()) {
			logger.trace("fetching access token");
			accessToken = MakeKey.getAccessToken(basicAuth, refreshToken);
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
				StringBuilder sb = new StringBuilder(method).append(url).append(" ").append(responseCode);
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
	@Override
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
		return connect(url, "POST", props, transmit);
	}

	ObjectWriter ow = new ObjectMapper().writer();

	@Override
	public String connectPost(String url, Map<String, String> transmit, boolean connected) {
		try {
			return connectPost(url, "application/json", ow.writeValueAsString(transmit), connected);
		} catch (JsonProcessingException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
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

	public R_Verify verify() {
		return convert(connectGet("https://login.eveonline.com/oauth/verify", true), R_Verify.class);
	}

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T> T convert(String line, Class<? extends T> clazz) {
		if (line == null || line.length() == 0) {
			return null;
		}
		try {
			return mapper.readerFor(clazz).readValue(line);
		} catch (Exception e) {
			throw new UnsupportedOperationException("while converting line " + line + "to class" + clazz.getName(), e);
		}
	}

	@Override
	public int hashCode() {
		return (basicAuth != null ? basicAuth.hashCode() : 0) + (refreshToken != null ? refreshToken.hashCode() : 0);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != ESIRawConnection.class) {
			return false;
		}
		ESIRawConnection o = (ESIRawConnection) obj;
		return (refreshToken == null && o.refreshToken == null
				|| refreshToken != null && refreshToken.equals(o.refreshToken))
				&& (basicAuth == null && o.basicAuth == null || basicAuth != null && basicAuth.equals(o.basicAuth));
	}

}
