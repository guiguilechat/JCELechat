package fr.guiguilechat.eveonline.model.esi;

public interface RequestHandler {

	/**
	 *
	 * @param url
	 *          the url we want
	 * @return the line returned by the server.
	 */
	public String connectGet(String url);

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
	public String connectPost(String url, String contentType, String transmit);

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

}
