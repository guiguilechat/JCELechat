package fr.guiguilechat.eveonline.model.esi;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public interface RequestHandler {

	/**
	 *
	 * @param url
	 *          the url we want
	 * @return the line returned by the server.
	 */
	public String connectGet(String url, boolean connected);

	/**
	 *
	 *
	 * @param url
	 *          the url we want to connect
	 * @param contentType
	 *          the content type of the data transmited
	 * @param transmit
	 *          additionnal data transmitted
	 * @return the line returned by the server.
	 */
	public String connectPost(String url, Map<String, String> content, boolean connected);

	/**
	 * convert a Line returned by a server into a structure
	 *
	 * @param line
	 *          the server line
	 * @param clazz
	 *          the class to convert the line to
	 * @return a new Structure
	 */
	public <T> T convert(String line, Class<? extends T> clazz);

	/**
	 * flatten all
	 * 
	 * @param o
	 * @return
	 */
	public default String flatten(Object o) {
		if(o==null) {
			return null;
		}
		if(o.getClass().isArray()) {
			Class<?> ct = o.getClass().getComponentType();
			if(ct.isPrimitive()) {
				if (ct == int.class || ct == short.class || ct == byte.class || ct == char.class || ct == boolean.class) {
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
}