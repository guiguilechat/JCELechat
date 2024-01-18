package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class RestControllerHelper {

	/**
	 * presents data as a response depending on the accept value.
	 *
	 * @return a new responsentity with data provided, and content Type matching the
	 *           accept if provided. Default is json.
	 */
	public static <T> ResponseEntity<T> makeResponse(T data, Optional<String> accept) {
		HttpHeaders responseHeaders = new HttpHeaders();
		switch (accept.orElse("json")) {
			case "xml":
				responseHeaders.setContentType(MediaType.APPLICATION_XML);
			break;
			case "json":
			default:
				responseHeaders.setContentType(MediaType.APPLICATION_JSON);
			break;
		}
		return new ResponseEntity<>(data, responseHeaders, HttpStatus.OK);
	}

}
