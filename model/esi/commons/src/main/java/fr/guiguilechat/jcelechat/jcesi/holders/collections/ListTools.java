package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListTools {

	public static <T> T at(List<T> list, int position, T absent) {
		if (position < 0) {
			position = list.size() + position;
		}
		if (position < 0 || position >= list.size()) {
			return absent;
		}
		return Objects.requireNonNullElse(list.get(position), absent);
	}

	public static <T> List<T> subListCopy(List<T> list, int from, int to) {
		if (from < 0) {
			from = list.size() + from;
		}
		if (to < 0) {
			to = list.size() + 1 + to;
		}
		if (from >= list.size() || from > to || to <= 0) {
			return new ArrayList<>();
		}
		return new ArrayList<>(list.subList(Math.max(from, 0), Math.min(to, list.size())));
	}

	public static <T> List<T> subListCopyFrom(List<T> list, int from) {
		if (from < 0) {
			from = list.size() + from;
		}
		if (from >= list.size()) {
			return new ArrayList<>();
		}
		return new ArrayList<>(list.subList(Math.max(from, 0), list.size()));
	}

	public static <T> List<T> subListCopyTo(List<T> list, int to) {
		if (to < 0) {
			to = list.size() + 1 + to;
		}
		if (to <= 0) {
			return new ArrayList<>();
		}
		return new ArrayList<>(list.subList(0, Math.min(to, list.size())));
	}

}
