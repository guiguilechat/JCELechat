package fr.guiguilechat.jcelechat.jcesi.request.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RequestedImpl<T> implements Requested<T> {

	private String URL;

	private int responseCode;

	private String error;

	private T OK;

	private Map<String, List<String>> headers;

	@Override
	public T getOK() {
		if (!isOk()) {
			throw new NullPointerException("received " + responseCode + " : " + error);
		}
		return OK;
	}

	@Override
	public T getOKOr(T ifnotok) {
		if (isOk()) {
			return OK;
		} else {
			return ifnotok;
		}
	}

	@Override
	public <U> RequestedImpl<U> mapBody(Function<T, U> mapper) {
		return new RequestedImpl<>(URL, responseCode, error, OK == null ? null : mapper.apply(OK), headers);
	}

	@Override
	public Requested<T> mapHeaders(Function<Map<String, List<String>>, Map<String, List<String>>> mapper) {
		return new RequestedImpl<>(URL, responseCode, error, OK, mapper.apply(headers));
	}
	
}
