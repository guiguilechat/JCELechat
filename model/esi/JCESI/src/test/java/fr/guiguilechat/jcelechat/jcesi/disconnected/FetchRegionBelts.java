package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIModel;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_regions_region_id;

public class FetchRegionBelts {

	public static void main(String[] args) {
		List<RegionData> regiondatas = ESIModel.INSTANCE.universe.cache.regions().get().parallelStream()
				.map(rid -> regionData(rid))
				.filter(rd -> rd != null).sorted((rd1, rd2) -> rd1.region.compareTo(rd2.region))
				.collect(Collectors.toList());
		System.out.println("| region | constellations | icebelts |\n| --- | --- | --- |");
		int totalRegions = 0;
		int totalConstels = 0;
		int totalBelts = 0;
		for (RegionData r : regiondatas) {
			totalRegions++;
			totalConstels += r.constellations;
			totalBelts += r.icebelts;
			System.out.println("| " + r.region + " | " + r.constellations + " | " + r.icebelts + " |");
		}
		System.out.println("total : regions=" + totalRegions + " constels=" + totalConstels + " belts=" + totalBelts);
	}

	public static class RegionData {
		String region;
		int icebelts = 0;
		int constellations = 0;
	}

	public static RegionData regionData(int regionId) {
		RegionData ret = new RegionData();
		R_get_universe_regions_region_id region = ESIModel.INSTANCE.universe.cache.regions(regionId).get();
		if (region.name.matches("[A-Z]-R.*")) {
			// System.err.println("skip wh region " + region.name);
			return null;
		}
		if (region.name.matches("ADR[0-9]+")) {
			// System.err.println("skip abyss region " + region.name);
			return null;
		}
		if (region.name.matches(".*-.*")) {
			// System.err.println("skip Jove region " + region.name);
			return null;
		}
		ret.region = region.name;
		ret.constellations = region.constellations.length;
		try {
			Document doc = Jsoup.connect("http://evemaps.dotlan.net/region/" + region.name).get();
			Elements links = doc.select("span[style=\"font-weight: bold; color: blue;\"]");
			for (Element e : links) {
				ret.icebelts += Integer.parseInt(e.text());
			}
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
		return ret;
	}

}
