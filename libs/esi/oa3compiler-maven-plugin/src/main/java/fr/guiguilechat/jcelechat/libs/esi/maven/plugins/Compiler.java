package fr.guiguilechat.jcelechat.libs.esi.maven.plugins;

import java.io.File;
import java.util.List;
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
		List<Server> possibleServers = schema.getServers();
		if (possibleServers.isEmpty()) {
			throw new IllegalArgumentException("the schema lacks a server description");
		}
		if (serverChoice != null) {
			possibleServers = schema.getServers().stream().filter(s -> s.getUrl().contains(serverChoice)).toList();
		}
		if (possibleServers.size() != 1) {
			throw new IllegalArgumentException(
					"can not choose one server from " + possibleServers + " , maybe set the serverChoice param ?");
		}
		Server server = possibleServers.get(0);
		String serverUrl = server.getUrl();
		log.info("server url : " + serverUrl);
		for (Entry<String, PathItem> p : schema.getPaths().entrySet()) {
//			System.err.println(p.getKey());
		}
	}

	private String serverChoice;

	public Compiler serverChoice(String serverChoice) {
		this.serverChoice = serverChoice;
		return this;
	}

}
