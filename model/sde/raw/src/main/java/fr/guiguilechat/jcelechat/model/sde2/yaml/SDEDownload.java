package fr.guiguilechat.jcelechat.model.sde2.yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.ZipInputStream;

import fr.guiguilechat.jcelechat.model.sde2.RemoteMeta;

public sealed interface SDEDownload {
	RemoteMeta meta();

	public record Success(String url, RemoteMeta meta, ReadableByteChannel channel) implements SDEDownload {

		public void copyTo(File targetDir)
				throws FileNotFoundException, IOException {
			try (FileOutputStream fileOutputStream = new FileOutputStream(targetDir)) {
				fileOutputStream.getChannel().transferFrom(channel(), 0, Long.MAX_VALUE);
			}
		}

		public File toTempFile() throws IOException {
			File created = File.createTempFile("SDE", null);
			copyTo(created);
			return created;
		}

		/**
		 * load the internal channel into an inputstream.
		 */
		public InputStream inputStream() {
			return Channels.newInputStream(channel());
		}

		public ZipInputStream zipputSteam() {
			return new ZipInputStream(inputStream());
		}
	}

	public record Cached(RemoteMeta meta) implements SDEDownload {
	}

	public record Errored(String url, RemoteMeta meta, Exception error) implements SDEDownload {
	}
}