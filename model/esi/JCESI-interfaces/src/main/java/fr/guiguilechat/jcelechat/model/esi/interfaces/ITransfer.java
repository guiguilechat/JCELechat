package fr.guiguilechat.jcelechat.model.esi.interfaces;

import java.util.List;
import java.util.Map;


/**
 * interface for the basic transfer capacities.<br />
 * <ul>
 * <li>send GET|PUT|POST|DELETE requests</li>
 * <li>convert a string into a class</li>
 * <li>flatten object into url parameter</li>
 * </ul>
 */
public interface ITransfer {
	public String flatten(Object o);

	public String connectGet(String url, Map<String, List<String>> headerHandler);

	public String connectDel(String url, Map<String, List<String>> headerHandler);

	public String connectPost(String url, Map<String, Object> content, Map<String, List<String>> headerHandler);

	public String connectPut(String url, Map<String, Object> content, Map<String, List<String>> headerHandler);

	public<T> T convert(String line, Class<? extends T> clazz);

}
