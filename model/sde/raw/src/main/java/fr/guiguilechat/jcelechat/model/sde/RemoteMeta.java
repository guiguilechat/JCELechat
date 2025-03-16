package fr.guiguilechat.jcelechat.model.sde;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Getter
@Slf4j
public class RemoteMeta {
	private final HttpHeaders headers;

	public static RemoteMeta forUrl(String url) {
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				url))
				.method("HEAD", HttpRequest.BodyPublishers.noBody())
				.build();
		try {
			return new RemoteMeta(HttpClient.newHttpClient().send(request, BodyHandlers.discarding()).headers());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public String etag() {
		return headers.firstValue("ETag").orElse(null);
	}

	public Instant lastModified() {
		String val = headers.firstValue("Last-Modified").orElse(null);
		if (val == null) {
			log.warn("missing last modified etag");
			return null;
		}
		return Instant.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(val));
	}
}