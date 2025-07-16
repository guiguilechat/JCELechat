package fr.guiguilechat.jcelechat.jcesi.interfaces;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


/**
 * interface for the basic transfer capacities.<br />
 * <ul>
 * <li>send GET|PUT|POST|DELETE requests</li>
 * <li>convert a string into a class</li>
 * <li>flatten object into url parameter</li>
 * </ul>
 */
public interface ITransfer {

	default String flatten(Object o) {
		if (o == null) {
			return null;
		}
		if (o.getClass().isArray()) {
			Class<?> ct = o.getClass().getComponentType();
			if (ct.isPrimitive()) {
				if (ct == boolean.class) {
					boolean[] b = (boolean[]) o;
					return IntStream.range(0, b.length - 1).mapToObj(i -> Boolean.toString(b[i]))
							.collect(Collectors.joining(","));
				} else if (ct == char.class) {
					char[] c = (char[]) o;
					return IntStream.range(0, c.length - 1).mapToObj(i -> Character.toString(c[i]))
							.collect(Collectors.joining(","));
				} else if (ct == int.class || ct == short.class || ct == byte.class) {
					return IntStream.of((int[]) o).mapToObj(Integer::toString).collect(Collectors.joining(","));
				} else if (ct == long.class) {
					return LongStream.of((long[]) o).mapToObj(Long::toString).collect(Collectors.joining(","));
				} else if (ct == double.class || ct == float.class) {
					return DoubleStream.of((double[]) o).mapToObj(Double::toString).collect(Collectors.joining(","));
				}
			}
			return Stream.of((Object[]) o).map(Object::toString).collect(Collectors.joining(","));
		} else {
			return o.toString();
		}
	}

	<T> T convertJson(String line, Class<? extends T> clazz);

	Requested<Void> requestDel(String url, Map<String, String> properties);

	<T> Requested<T> requestGet(String url, Map<String, String> properties, Class<T> expectedClass);

	<T> Requested<T> requestPost(String url, Map<String, String> properties, Map<String, Object> transmit,
			Class<T> expectedClass);

	Requested<Void> requestPut(String url, Map<String, String> properties, Map<String, Object> transmit);

	/**
	 * load the pages for a given resource access.
	 *
	 * @param resourceAccess
	 *          function to load a page and parameters into a response
	 * @param parameters
	 *          the parameters to give to each page
	 * @return the response for the first page, modified to hold the responses
	 *         from subsequent pages
	 */
	<T> Requested<List<T>> requestGetPages(
			BiFunction<Integer, Map<String, String>, Requested<T[]>> resourceAccess,
			Map<String, String> parameters);

}
