package fr.guiguilechat.jcelechat.model.sde2;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RemoteMeta {

	public static final String URL = "https://developers.eveonline.com/static-data/tranquility/latest.jsonl";

	public static RemoteMeta fetch() {

		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new URI(URL).toURL().openStream(), RemoteMeta.class);
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public String _key;
	public long buildNumber;
	public String releaseDate;

	public String yamlURL() {
		return "https://developers.eveonline.com/static-data/tranquility/eve-online-static-data-" + buildNumber
				+ "-yaml.zip";
	}

	public String jsonURL() {
		return "https://developers.eveonline.com/static-data/tranquility/eve-online-static-data-" + buildNumber
				+ "-jsonl.zip";
	}

	public static void main(String[] args) {
		var meta = fetch();
		System.out.println("meta build=" + meta.buildNumber + " release=" + meta.releaseDate);
	}

}
