package fr.guiguilechat.jcelechat.libs.esi.maven.plugins;

import java.io.File;
import java.util.Map.Entry;

import org.apache.maven.plugin.logging.Log;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Compiler {

	private final Log log;

	@Getter
	private final OpenAPI schema;

	@Getter
	private final File javaOutputFolder;

	public void compile() {
		if (schema.getServers().size() != 1) {
			throw new RuntimeException("can not choose one server from " + schema.getServers());
		}
		Server server = schema.getServers().get(0);
		String serverUrl = server.getUrl();
		log.info("server url : " + serverUrl);
		for (Entry<String, PathItem> p : schema.getPaths().entrySet()) {

		}
	}

}
