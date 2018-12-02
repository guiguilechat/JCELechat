package fr.guiguilechat.jcelechat.jcesi.impl;

import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Response;

public class ResponseImpl<T> implements Response<T> {

	public ResponseImpl(int responseCode, String message, T item, Map<String, List<String>> headers) {
		this.responseCode = responseCode;
		this.error = message;
		this.ok = item;
		this.headers = headers;
	}

	private int responseCode;

	@Override
	public int getResponseCode() {
		return responseCode;
	}

	private T ok;

	@Override
	public T getOK() {
		return ok;
	}

	private String error;

	@Override
	public String getError() {
		return error;
	}

	private Map<String, List<String>> headers;

	@Override
	public Map<String, List<String>> getHeaders() {
		return headers;
	}

}
