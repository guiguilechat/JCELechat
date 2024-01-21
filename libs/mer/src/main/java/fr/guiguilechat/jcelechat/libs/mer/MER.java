package fr.guiguilechat.jcelechat.libs.mer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import fr.guiguilechat.jcelechat.libs.mer.MERFetcher.MERFetch;
import fr.guiguilechat.jcelechat.libs.mer.files.EconomyIndicesDetailsEntry;
import fr.guiguilechat.jcelechat.libs.mer.files.IndexBasketsEntry;
import fr.guiguilechat.jcelechat.libs.mer.files.IskVolumeEntry;
import fr.guiguilechat.jcelechat.libs.mer.files.KillDumpEntry;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MER {

	private final MERFetch source;

	private final List<EconomyIndicesDetailsEntry> economyIndicesDetailsEntries = new ArrayList<>();
	private final List<IndexBasketsEntry> indexBasketsEntries = new ArrayList<>();
	private final List<KillDumpEntry> killDumpEntries = new ArrayList<>();
	private final List<IskVolumeEntry> iskVolumeEntries = new ArrayList<>();

	private final List<String> images = new ArrayList<>();
	private final List<String> htmls = new ArrayList<>();
	private final List<String> skipped = new ArrayList<>();

	static final Pattern EconomyIndicesDetails = Pattern.compile(".*[eE]conomy_?[iI]ndices_?[dD]etails\\.csv$");

	static final Pattern IndexBaskets = Pattern.compile(".*[iI]ndex_?[bB]askets\\.csv$");

	static final Pattern KillDump = Pattern.compile(".*[kK]ill_?dump\\.csv$");

	static final Pattern IskVolume = Pattern.compile(".*IskVolume\\.csv$");

	static final Pattern Image = Pattern.compile(".*\\.png");

	static final Pattern Html = Pattern.compile(".*\\.html");

	public MER load() {
		try (ZipInputStream zis = source.data()) {
			ZipEntry ze;
			while ((ze = zis.getNextEntry()) != null) {
				if (ze.isDirectory()) {
					continue;
				}
				String name = ze.getName();
				Matcher m;
				m = Image.matcher(name);
				if (m.matches()) {
					processImage(name, zis);
					continue;
				}
				m = Html.matcher(name);
				if (m.matches()) {
					processHtml(name, zis);
					continue;
				}
				m = EconomyIndicesDetails.matcher(name);
				if (m.matches()) {
					processEconomyIndicesDetails(zis);
					continue;
				}
				m = IndexBaskets.matcher(name);
				if (m.matches()) {
					processIndexBaskets(zis);
					continue;
				}
				m = KillDump.matcher(name);
				if (m.matches()) {
					processKillDump(zis);
					continue;
				}
				m = IskVolume.matcher(name);
				if (m.matches()) {
					processIskVolume(zis);
					continue;
				}
				processUknown(name, zis);

			}
		} catch (IOException e1) {
			throw new RuntimeException("while reading entries for " + source.url(), e1);
		}
		return this;
	}

	private void processIskVolume(ZipInputStream zis) {
		iskVolumeEntries.addAll(IskVolumeEntry.parse(zis));
	}

	private void processKillDump(ZipInputStream zis) {
		killDumpEntries.addAll(KillDumpEntry.parse(zis));
	}

	void processEconomyIndicesDetails(ZipInputStream zis) {
		economyIndicesDetailsEntries.addAll(EconomyIndicesDetailsEntry.parse(zis));
	}

	private void processIndexBaskets(ZipInputStream zis) {
		indexBasketsEntries.addAll(IndexBasketsEntry.parse(zis));
	}

	private void processImage(String name, ZipInputStream zis) {
		images.add(name);
	}

	private void processHtml(String name, ZipInputStream zis) {
		htmls.add(name);
	}

	private void processUknown(String name, ZipInputStream zis) {
		skipped.add(name);
	}

	public String printStats() {
		return "loaded : "
				+ economyIndicesDetailsEntries.size() + "EconomyIndicesDetails, "
				+ indexBasketsEntries.size() + "IndexBaskets, "
				+ killDumpEntries.size() + "KillDump, "
				+ iskVolumeEntries.size() + "IskVolume, "
				+ images.size() + " images, " + htmls.size() + " htmls" + ", " + skipped + " unknown";
	}

}
