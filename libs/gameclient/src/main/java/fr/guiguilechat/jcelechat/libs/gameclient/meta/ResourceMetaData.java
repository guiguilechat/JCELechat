package fr.guiguilechat.jcelechat.libs.gameclient.meta;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import lombok.extern.slf4j.Slf4j;

/**
 * a line loaded from an index resource
 */
@Slf4j
public record ResourceMetaData(String resName, String serverType, String relPath, String md5, int size, int compressed,
		int permissions,
		String fullLine) {

	public static ResourceMetaData parse(String line) {
		String[] split = line.split(",");
		return new ResourceMetaData(
				removePrefix(split[0]),
				serverType(split[0]),
				split[1],
				split[2],
				Integer.parseInt(split[3]),
				Integer.parseInt(split[4]),
				split.length < 6 ? 0 : Integer.parseInt(split[5]),
				line);
	}

	public static String removePrefix(String resName) {
		return resName.replaceAll("^.*:/", "");
	}

	public static String serverType(String resName) {
		return resName.replaceAll(":/.*", "");
	}

	public URI uri() {
		return ServerInfo.subResource(relPath, serverType);
	}

	public File dump(File destDir) throws MalformedURLException, IOException {
		File targetFile = new File(destDir, resName);
		if(targetFile.exists()) {
			log.debug(" " + targetFile.getPath() + " already exists");
			return targetFile;
		}
		targetFile.getParentFile().mkdirs();
		try (
				BufferedInputStream in = new BufferedInputStream(uri().toURL().openStream());
				FileOutputStream out = new FileOutputStream(targetFile)) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				out.write(dataBuffer, 0, bytesRead);
			}
		}
		return targetFile;
	}

}
