package fr.guiguilechat.jcelechat.jcesi;

/**
 * app information transmitted to ESI as the user-agent header.
 */
public record AppUserAgent(String name, String version, String mail, String codeUrl) {

	/**
	 * append the informations to a stringbuilder
	 *
	 * @return the stringbuilder transmitted, or a new one if it was null
	 * @throws NullPointerException if this name is null
	 */
	public StringBuilder toHeader(StringBuilder ret) {
		if (name == null) {
			throw new NullPointerException("no app name provided");
		}
		if (ret == null) {
			ret = new StringBuilder();
		}
		if (!ret.isEmpty()) {
			ret.append(" ");
		}
		ret.append(name);
		if (version != null) {
			ret.append("/").append(version);
		}
		if (mail != null || codeUrl != null) {
			ret.append(" (");
			if (mail != null) {
				ret.append(mail);
			}
			if (codeUrl != null) {
				if (mail != null) {
					ret.append(" ");
				}
				ret.append("+").append(codeUrl);
			}
			ret.append(")");
		}
		return ret;
	}

	public StringBuilder toHeader() {
		return toHeader(null);
	}

}
