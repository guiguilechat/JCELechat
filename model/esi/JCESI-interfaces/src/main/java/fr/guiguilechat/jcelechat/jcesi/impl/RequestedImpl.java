package fr.guiguilechat.jcelechat.jcesi.impl;

import java.util.List;
import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
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


}
