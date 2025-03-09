package fr.guiguilechat.jcelechat.libs.everaw.meta;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record ClientInfo(String build, String buildNumber, @JsonProperty("protected") boolean _protected,
		List<String> platforms) {


	public static final String RES_NAME = "eveclient_TQ.json";

	public static ClientInfo fetch() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(ServerInfo.subResource(RES_NAME, null).toURL(), ClientInfo.class);
		} catch (IOException e) {
			log.error("while fetching eve client data ", e);
			return null;
		}
	}

	private static final String INDEX_TEMPLATE = "eveonline_%s.txt";

	public String rootPath() {
		return String.format(INDEX_TEMPLATE, build);
	}

	public ResourceIndex rootIndex() {
		return new ResourceIndex(rootPath());
	}

}
