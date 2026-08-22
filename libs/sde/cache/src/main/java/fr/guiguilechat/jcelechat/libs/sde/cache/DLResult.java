package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/// result of fetching a locally cached resource. The success stores the
/// readablechannel to allow working with in in memory, and has a
public sealed interface DLResult {

	RemoteMeta meta();

	public record Success(String url, RemoteMeta meta, ReadableByteChannel channel) implements DLResult {

		/// @param targetDir
		///          directory into which to dump the zipped files
		public void copyTo(File targetDir)
				throws FileNotFoundException,
				IOException {
			try (FileOutputStream fileOutputStream = new FileOutputStream(targetDir)) {
				fileOutputStream.getChannel().transferFrom(channel(), 0, Long.MAX_VALUE);
			}
		}

		public File toTempFile() throws IOException {
			File created = File.createTempFile("SDE", null);
			copyTo(created);
			return created;
		}

		public void extract(File cacheDir) {
			try (ZipInputStream zis = zipputSteam()) {
				ZipEntry e;
				while ((e = zis.getNextEntry()) != null) {
					File out = new File(cacheDir, e.getName());
					out.getParentFile().mkdirs();
					FileWriter fw = new FileWriter(out);
					byte[] b = new byte[1000];
					while (zis.available() > 0) {
						int r = zis.read(b, 0, b.length);
						if (r > -1) {
							fw.write(new String(b, 0, r));
						}
					}
					fw.close();
				}
			} catch (IOException e1) {
				throw new RuntimeException(e1);
			}

		}

		public ZipInputStream zipputSteam() {
			return new ZipInputStream(Channels.newInputStream(channel()));
		}
	}

	public record Cached(RemoteMeta meta) implements DLResult {
	}

	public record Errored(String url, RemoteMeta meta, Exception error) implements DLResult {
	}
}