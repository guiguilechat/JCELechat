package fr.guiguilechat.jcelechat.libs.esi.maven.plugins;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import fr.guiguilechat.jcelechat.libs.esi.maven.plugins.params.ParamVal;
import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

@Mojo(name = "compile-url", threadSafe = true)
public class CompileMojo extends AbstractMojo {

	@Component
	private MavenProject project;

	@Parameter(property = "oa3compiler-output-java-folder")
	private String outputJavaFolder;

	public static class FetchParams {

		public String url;

		public Map<String, ParamVal> params = new HashMap<>();

		public String makeUrl() {
			return Stream.of(url,
					params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue().evaluate())
							.collect(Collectors.joining("&")))
					.collect(Collectors.joining("?"));
		}

	}

	@Parameter(property = "oa3compiler-fetch")
	private FetchParams fetch;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		File javaOutputFolder = javaOutputFolder();
		String url = fetch.makeUrl();
		getLog().info("compiling url " + url + " into folder " + javaOutputFolder.getAbsolutePath());
		javaOutputFolder.mkdirs();
		SwaggerParseResult result = new OpenAPIParser().readLocation(url, null, null);
		OpenAPI schema = result.getOpenAPI();
		new Compiler(getLog(), schema, javaOutputFolder).compile();

	}

	protected File javaOutputFolder() {
		if (outputJavaFolder == null) {
			return new File(project.getBasedir(), "src/generated/java");
		} else if (outputJavaFolder.startsWith("/")) {
			return new File(outputJavaFolder);
		} else {
			return new File(project.getBasedir(), outputJavaFolder);
		}
	}

}
