package fr.guiguilechat.jcelechat.jcesi.transmit;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * basic interface to request urls
 */
public interface Requester {

	public enum Verb {
		DELETE,
		GET,
		POST,
		PUT
	}

	<T> CompletableFuture<RequestResult<T>> request(String url, Verb verb, Map<String, String> sendHeaders,
			Map<String, Object> transmitAsJson, Class<T> expectedClass);

	/**
	 * load a paginated resource
	 *
	 * @param pageFetcher
	 *                    function to load a response given a page and parameters
	 * @param parameters
	 *                    the parameters to give to each page
	 * @return the response for the first page, modified to hold the responses
	 *         from subsequent pages. Any error replaces the result.
	 */
	<T> CompletableFuture<RequestResult<List<T>>> requestPages(
			BiFunction<Integer, Map<String, String>, CompletableFuture<RequestResult<T[]>>> pageFetcher,
			Map<String, String> parameters);
}
