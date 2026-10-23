package fr.guiguilechat.jcelechat.libs.gameclient.meta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.GZIPInputStream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerInfo {

	public static final String APP_URL = "https://binaries.eveonline.com";

	public static final String RES_URL = "https://resources.eveonline.com";

	private static final String SUB_TEMPLATE = "%s/%s";

	public static final Charset CHARSET = Charset.forName("utf-8");

	public static URI subResource(String resourcePath, String serverType) {
		serverType = serverType == null ? "app" : serverType;
		String serverURL = switch (serverType) {
		case "app" -> APP_URL;
		case "res" -> RES_URL;
		default ->
				throw new IllegalArgumentException("Unexpected value: " + serverType);
		};
		URI ret = URI.create(String.format(SUB_TEMPLATE, serverURL, resourcePath));
		return ret;
	}

	@SuppressWarnings("resource")
	@SneakyThrows
	public static List<ResourceMetaData> loadIndex(String resourcePath) {
		URI resourceURI = subResource(resourcePath, null);
		try {
			Reader reader =
					Channels.newReader(
							Channels.newChannel(
									loadInputStream(resourceURI)),
							CHARSET);
			return new BufferedReader(reader)
					.lines()
					.map(ResourceMetaData::parse)
					.toList();
		} catch (Exception e) {
			throw new RuntimeException("while loading index for uri=" + resourceURI, e);
		}
	}

	/// load a potential gzip encoded uri, after decoding it if necessary
	public static InputStream loadInputStream(URI resourceURI) {
		try {
		HttpRequest request =
				HttpRequest.newBuilder(resourceURI)
						.build();
			HttpResponse<InputStream> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofInputStream());
			return decodeIfNeeded(response);
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static InputStream decodeIfNeeded(HttpResponse<InputStream> response) throws IOException {
		String encoding = response.headers().firstValue("Content-Encoding").orElse("");
		return switch (encoding) {
		case "" -> response.body();
		case "gzip" -> new GZIPInputStream(response.body());
		default ->
				throw new UnsupportedOperationException(
						"Unexpected Content-Encoding: " + encoding);
		};

	}

}
