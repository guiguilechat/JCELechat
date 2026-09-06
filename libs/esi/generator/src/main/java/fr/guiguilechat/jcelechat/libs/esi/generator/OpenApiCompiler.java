package fr.guiguilechat.jcelechat.libs.esi.generator;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JPackage;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import io.swagger.v3.oas.models.security.SecurityRequirement;

public class OpenApiCompiler {

	public final JCodeModel jcm;

	public final OpenAPI openapi;

	public final JPackage rootPackage;

	public final String baseUrl;

	public final OaiJcmBridge bridge;

	public OpenApiCompiler(OpenAPI openapi, JCodeModel jcm, String rootPackage) {
		this.jcm = jcm;
		this.openapi = openapi;
		this.rootPackage = rootPackage == null || rootPackage.isBlank() ? jcm.rootPackage() : jcm._package(rootPackage);
		baseUrl = openapi.getServers().get(0).getUrl();
		bridge = new OaiJcmBridge(openapi, jcm, this.rootPackage);
	}

	public void compile() {
		System.out.println("compiling with root package" + rootPackage.name() + " and base URL " + baseUrl);
		openapi.getPaths().forEach(this::compilePath);
	}

	protected void compilePath(String pathName, PathItem pi) {
		System.out.println(
				"compiling path " + pathName);
		pi.readOperationsMap().entrySet().forEach(e->compileOperation(pathName, e.getKey(), e.getValue()));
	}

	protected void compileOperation(String pathName, HttpMethod meth, Operation operation) {
		if (operation.getSecurity() != null) {
			for (SecurityRequirement sr : operation.getSecurity()) {
				compileOperation(pathName, meth, operation, sr);
			}
		} else {
			compileOperation(pathName, meth, operation, null);
		}
	}

	protected void compileOperation(String pathName, HttpMethod meth, Operation operation, SecurityRequirement sr) {
		String secName = sr == null ? null : sr.keySet().iterator().next();
		System.out.println("  compiling security=" + secName + " path=" + pathName + " " + meth);
		JDefinedClass pathClass = bridge.path(secName, pathName);
		bridge.addOperation(pathClass, meth, operation);
	}

}
