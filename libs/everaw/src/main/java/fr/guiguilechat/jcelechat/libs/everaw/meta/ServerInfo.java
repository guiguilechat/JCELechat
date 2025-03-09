package fr.guiguilechat.jcelechat.libs.everaw.meta;

import java.io.BufferedReader;
import java.io.Reader;
import java.net.URI;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.util.List;

import lombok.SneakyThrows;

public class ServerInfo {

	public static final String APP_URL = "https://binaries.eveonline.com";

	public static final String RES_URL = "https://resources.eveonline.com";

	private static final String SUB_TEMPLATE = "%s/%s";

	public static URI subResource(String resourcePath, String serverType) {
		serverType = serverType == null ? "app" : serverType;
		String serverURL = switch (serverType) {
		case "app" -> APP_URL;
		case "res" -> RES_URL;
		default ->
			throw new IllegalArgumentException("Unexpected value: " + serverType);
		};
		return URI.create(String.format(SUB_TEMPLATE, serverURL, resourcePath));
	}

	@SuppressWarnings("resource")
	@SneakyThrows
	public static List<ResourceMetaData> loadIndex(String resourcePath) {
		Reader reader = Channels.newReader(
				Channels.newChannel(subResource(resourcePath, null).toURL().openStream()), Charset.defaultCharset());
		return new BufferedReader(reader).lines().map(ResourceMetaData::parse).toList();
	}

}
