package fr.guiguilechat.jcelechat.libs.gameclient.meta;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ClientInfo {

	private String build;
	private String buildNumber;
	private @JsonProperty("protected") boolean _protected;
	private List<String> platforms;
	private HttpHeaders headers;

	public static final String RES_NAME = "eveclient_TQ.json";

	public static ClientInfo fetch() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			URI uri = ServerInfo.subResource(RES_NAME, null);
			HttpRequest request = HttpRequest.newBuilder(uri)
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
			ClientInfo ret = mapper.readValue(response.body(), ClientInfo.class);
			ret.setHeaders(response.headers());
			return ret;
		} catch (IOException | InterruptedException e) {
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

	public String etag() {
		return headers.firstValue("etag").orElse(null);
	}

	public Instant lastModified() {
		String val = headers.firstValue("last-modified").orElse(null);
		if (val == null) {
			log.warn("missing last modified etag");
			return null;
		}
		return Instant.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(val));
	}

}
