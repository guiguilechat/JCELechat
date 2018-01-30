package fr.guiguilechat.eveonline.model.esi.raw;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.connect.ESIRawConnection;
import is.ccp.tech.esi.Swagger;

@Deprecated
public class ESIRaw extends ESIConnection implements Swagger {

	public ESIRaw(ESIRawConnection connection) {
		super(connection);
	}

	public ESIRaw(String basicAuth, String refreshToken) {
		this(new ESIRawConnection(basicAuth, refreshToken));
	}

	ObjectMapper om = new ObjectMapper();

	@Override
	public String flatten(Object o) {
		return raw.flatten(o);
	}

	@Override
	public String connectGet(String url, boolean connected, Map<String, List<String>> headerHandler) {
		return raw.connectGet(url, connected, headerHandler);
	}

	@Override
	public String connectPost(String url, Map<String, String> content, boolean connected,
			Map<String, List<String>> headerHandler) {
		return raw.connectPost(url, content, connected, headerHandler);
	}

	@Override
	public <T> T convert(String line, Class<? extends T> clazz) {
		return raw.convert(line, clazz);
	}

}
