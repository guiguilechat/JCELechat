package fr.guiguilechat.jcelechat.jcesi.interfaces;

import java.util.List;
import java.util.Map;
import java.util.function.Function;


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

	public Response<Void> requestDel(String url, Map<String, String> properties);

	public <T> Response<T> requestGet(String url, Map<String, String> properties, Class<T> expectedClass);

	public Response<Void> requestPost(String url, Map<String, String> properties, Map<String, Object> transmit);

	public Response<Void> requestPut(String url, Map<String, String> properties, Map<String, Object> transmit);

	/**
	 * load the pages for a given resource access.
	 *
	 * @param resourceAccess
	 *          function to load a page into a response
	 * @return the response for the first page, modified to hold the responses
	 *         from subsequent pages
	 */
	public <T> Response<List<T>> requestGetPages(Function<Integer, Response<List<T>>> resourceAccess);

}
