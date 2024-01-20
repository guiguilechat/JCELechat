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
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MER {

	private final MERFetch source;

	private final List<EconomyIndicesDetailsEntry> economyIndicesDetailsEntries = new ArrayList<>();
	private final List<IndexBasketsEntry> indexBasketsEntries = new ArrayList<>();

	private final List<String> images = new ArrayList<>();
	private final List<String> htmls = new ArrayList<>();
	private final List<String> skipped = new ArrayList<>();

	static final Pattern EconomyIndicesDetails = Pattern.compile(".*EconomyIndicesDetails\\.csv$");

	static final Pattern IndexBaskets = Pattern.compile(".*IndexBaskets\\.csv$");

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
				processUknown(name, zis);

			}
		} catch (IOException e1) {
			throw new RuntimeException("while reading entries for " + source.url(), e1);
		}
		System.out.println(source.url() + " loaded : " + economyIndicesDetailsEntries.size() + "EconomyIndicesDetails, "
				+ indexBasketsEntries.size() + "IndexBaskets, "
				+ images.size() + " images, " + htmls.size() + " htmls" + ", " + skipped + " unknown");
		return this;
	}

	void processEconomyIndicesDetails(ZipInputStream zis) {
		economyIndicesDetailsEntries.addAll(EconomyIndicesDetailsEntry.parseDump(zis));
	}

	private void processIndexBaskets(ZipInputStream zis) {
		indexBasketsEntries.addAll(IndexBasketsEntry.parseDump(zis));
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

}
