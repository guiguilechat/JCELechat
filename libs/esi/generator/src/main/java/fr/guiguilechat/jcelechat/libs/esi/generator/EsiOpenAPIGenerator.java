package fr.guiguilechat.jcelechat.libs.esi.generator;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.exceptions.JCodeModelException;
import com.helger.jcodemodel.plugin.maven.ICodeModelBuilder;
import com.helger.jcodemodel.plugin.maven.ISourcedInputStream;
import com.helger.jcodemodel.plugin.maven.generators.JCMGen;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.core.models.SwaggerParseResult;

@JCMGen
public class EsiOpenAPIGenerator implements ICodeModelBuilder {

	private String rootPackage = "";


	@Override
	public void build(JCodeModel model, @NonNull ISourcedInputStream source) throws JCodeModelException {
		String dataText;
		try {
			dataText = new String(source.inputStream().readAllBytes(), Charset.defaultCharset());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		SwaggerParseResult result = new OpenAPIParser().readContents(dataText, null, null);
		OpenAPI oai = result.getOpenAPI();
		if (oai == null) {
			System.err.println("received null openapi");
			throw new NullPointerException("received null openapi schema from " + source);
		}
		System.err.println("received openapi");
		new OpenApiCompiler(oai, model, rootPackage).compile();
	}

	@Override
	public @Nullable String getRootPackage() {
		return rootPackage;
	}

	@Override
	public void setRootPackage(@Nullable String rootPackage) {
		this.rootPackage = rootPackage;
	}

}
