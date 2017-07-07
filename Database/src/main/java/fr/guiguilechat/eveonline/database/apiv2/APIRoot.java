package fr.guiguilechat.eveonline.database.apiv2;

import org.jsoup.nodes.Element;

public class APIRoot {

	public final APIKey key;

	public static final String BASEURL = "https://api.eveonline.com/";

	public APIRoot(APIKey key) {
		this.key = key;
	}

	public APIRoot(int id, String code) {
		this(new APIKey(id, code));
	}

	public final Account account = new Account(this);

	public final Char chars = new Char(this);

	public static int getInt(Element el, String field, int defaultVal) {
		return el.hasAttr(field) ? Integer.parseInt(el.attr(field)) : defaultVal;
	}

	public static long getLong(Element el, String field, long defaultVal) {
		return el.hasAttr(field) ? Long.parseLong(el.attr(field)) : defaultVal;
	}

	public static boolean getBool(Element el, String field, boolean defaultVal) {
		return el.hasAttr(field) ? Integer.parseInt(el.attr(field)) != 0 : defaultVal;
	}

}
