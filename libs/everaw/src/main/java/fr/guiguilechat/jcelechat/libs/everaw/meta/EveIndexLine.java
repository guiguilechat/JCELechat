package fr.guiguilechat.jcelechat.libs.everaw.meta;

import java.net.URI;

/**
 * a line loaded from an index resource
 */
public record EveIndexLine(String resName, String relPath, String md5, int size, int compressed, int permissions) {

	public static EveIndexLine parse(String line) {
		String[] split = line.split(",");
		return new EveIndexLine(
				split[0],
				split[1],
				split[2],
				Integer.parseInt(split[3]),
				Integer.parseInt(split[4]),
				Integer.parseInt(split[5]));
	}

	public URI uri() {
		return ServerInfo.subResource(relPath);
	}

}
