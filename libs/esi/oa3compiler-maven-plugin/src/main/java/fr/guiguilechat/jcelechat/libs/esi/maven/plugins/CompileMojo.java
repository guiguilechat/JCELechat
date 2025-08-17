package fr.guiguilechat.jcelechat.libs.esi.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

@Mojo(name = "compile-url", threadSafe = true)
public class CompileMojo extends AbstractMojo {

	@Parameter(property = "oa3compiler-url")
	private String url;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("calling for url " + url);
		SwaggerParseResult result = new OpenAPIParser().readContents(url, null, null);

	}

}
