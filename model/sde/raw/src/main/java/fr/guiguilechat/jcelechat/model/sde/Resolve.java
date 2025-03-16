package fr.guiguilechat.jcelechat.model.sde;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Resolve {

	private static final String URL_STATIC = "https://eve-static-data-export.s3-eu-west-1.amazonaws.com/tranquility/sde.zip";
	private final static String URL_DYNAMIC = "https://developers.eveonline.com/docs/services/sde/";

	/**
	 * find the url to download the SDE from.
	 *
	 * @return
	 */
	public static String findURL() {
		try {
			org.jsoup.nodes.Document page = Jsoup.connect(URL_DYNAMIC).get();
			Elements a = page.select("a[href*=sde.zip]");
			String ret = a == null ? null : a.attr("href");
			if (ret == null || ret.length() == 0) {
				log.debug("can't find URL for SDE, fallback to static " + URL_STATIC);
			} else {
				log.debug("dynamic URL for SDE is " + ret);
				return ret;
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return URL_STATIC;
	}

}
