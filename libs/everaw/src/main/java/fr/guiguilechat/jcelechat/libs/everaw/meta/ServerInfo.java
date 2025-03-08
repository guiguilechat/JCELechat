package fr.guiguilechat.jcelechat.libs.everaw.meta;

import java.io.BufferedReader;
import java.io.Reader;
import java.net.URI;
import java.nio.channels.Channels;
import java.nio.charset.Charset;
import java.util.List;

import lombok.SneakyThrows;

public class ServerInfo {

	public static final String SERVER_URL = "https://binaries.eveonline.com";

	private static final String SUB_TEMPLATE = "%s/%s";

	public static URI subResource(String resourcePath) {
		return URI.create(String.format(SUB_TEMPLATE, SERVER_URL, resourcePath));
	}

	@SuppressWarnings("resource")
	@SneakyThrows
	public static List<EveIndexLine> loadIndex(String resourcePath) {
		Reader reader = Channels.newReader(
				Channels.newChannel(subResource(resourcePath).toURL().openStream()), Charset.defaultCharset());
		return new BufferedReader(reader).lines().map(EveIndexLine::parse).toList();
	}

}
