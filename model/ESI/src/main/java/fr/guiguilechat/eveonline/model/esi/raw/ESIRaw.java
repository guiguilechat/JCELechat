package fr.guiguilechat.eveonline.model.esi.raw;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.eveonline.model.esi.connect.ConnectedCall;
import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;
import is.ccp.tech.esi.Swagger;

public class ESIRaw extends ConnectedCall implements Swagger {

	public ESIRaw(ESIConnection connection) {
		super(connection);
	}

	public ESIRaw(String basicAuth, String refreshToken) {
		this(new ESIConnection(basicAuth, refreshToken));
	}

	@Override
	public String connectGet(String url, boolean connected) {
		return connection.connectGet(url, connected);
	}

	@Override
	public String connectPost(String url, Map<String, String> content, boolean connected) {
		return connection.connectPost(url, content, connected);
	}

	ObjectMapper om = new ObjectMapper();

	@Override
	public <T> T convert(String line, Class<? extends T> clazz) {
		if (line == null) {
			return null;
		}
		try {
			return om.readerFor(clazz).readValue(line);
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

}
