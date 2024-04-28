package fr.guiguilechat.jcelechat.jcesi.interfaces;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;


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

	public<T> T convertJson(String line, Class<? extends T> clazz);

	public Requested<Void> requestDel(String url, Map<String, String> properties);

	public <T> Requested<T> requestGet(String url, Map<String, String> properties, Class<T> expectedClass);

	public <T> Requested<T> requestPost(String url, Map<String, String> properties, Map<String, Object> transmit,
			Class<T> expectedClass);

	public Requested<Void> requestPut(String url, Map<String, String> properties, Map<String, Object> transmit);

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
	public <T> Requested<List<T>> requestGetPages(
			BiFunction<Integer, Map<String, String>, Requested<T[]>> resourceAccess,
			Map<String, String> parameters);

}
