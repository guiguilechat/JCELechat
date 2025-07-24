package fr.guiguilechat.jcelechat.jcesi.holders.common;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.holders.Notification.Listener;

public class StrongRefStorage {

	private static final List<Listener<?>> STRONG_REFERENCES = new ArrayList<>();

	/**
	 * maintain a listener strongly referenced, to avoid it being garbage collected.
	 */
	public static <T> void maintain(Listener<T> o) {
		STRONG_REFERENCES.add(o);
	}

}
