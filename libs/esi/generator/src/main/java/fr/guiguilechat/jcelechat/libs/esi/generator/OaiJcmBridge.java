package fr.guiguilechat.jcelechat.libs.esi.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.lang.model.SourceVersion;

import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JPackage;
import com.helger.jcodemodel.exceptions.JCodeModelException;

import fr.guiguilechat.jcelechat.libs.esi.commons.ISecurity;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem.HttpMethod;

/// bridge between an [OpenAPI] scpecificication and the corresponding classes
/// created in a [JCodeModel]
public class OaiJcmBridge {

	public static final String ANON_CLASS = "Anon";

	public final OpenAPI openapi;

	public final JCodeModel codemodel;

	public final JPackage rootPackage;

	public final String securityPrefix;

	public OaiJcmBridge(OpenAPI openapi, JCodeModel codemodel, JPackage rootPackage) {
		this.openapi = openapi;
		this.codemodel = codemodel;
		this.rootPackage = rootPackage;
		securityPrefix = extractSecurityPrefix(openapi.getServers().iterator().next().getUrl());
		System.out.println("security prefix is " + securityPrefix);
	}

	protected String extractSecurityPrefix(String baseUrl) {
		return upperFirstLowerRest(baseUrl
				.replaceFirst(".*://", "")
				.replaceFirst("^[^a-zA-Z]+", "")
				.replaceAll("[^a-zA-Z]+.*", ""));
	}

	protected String upperFirstLowerRest(String s) {
		return s.length() < 2
				? s.toUpperCase()
				: s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	class SecurityData {

		public final String securityName;

		public final JDefinedClass clazz;

		public SecurityData(String securityName) {
			this.securityName = securityName;
			clazz = createSecurityClass(securityName);
		}

		private final Map<String, JDefinedClass> pathClasses = new HashMap<>();

		public JDefinedClass pathClass(String path) {
			return pathClasses.computeIfAbsent(path, p -> createPathClass(clazz, p));
		}

	}

	private final Map<String, SecurityData> securities = new HashMap<>();

	public JDefinedClass path(String security, String operationPath) {
		SecurityData securityData = securities.computeIfAbsent(security == null ? ANON_CLASS : security, SecurityData::new);
		return securityData.pathClass(operationPath);
	}

	protected JDefinedClass createSecurityClass(String securityName) {
		String className = securityPrefix + upperFirstLowerRest(securityName);
		JDefinedClass ret;
		try {
			ret = rootPackage._class(className);
			ret._implements(codemodel.ref(ISecurity.class));
		} catch (JCodeModelException e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

	public static final String FIELD_SEC = "security";

	protected JDefinedClass createPathClass(JDefinedClass securityClass, String path) {
		List<String> pathItems =
				Stream.of(path.split("/"))
						.filter(s -> s != null && !s.isBlank())
						.map(s -> s.replaceAll("[^a-zA-Z]+", ""))
						.toList();
		JDefinedClass ret = securityClass;
		for (String pathItem : pathItems) {
			ret = fieldClass(ret, pathItem, securityClass);
		}
		return ret;
	}

	protected String correctFieldName(String s) {
		if (s == null || s.isBlank()) {
			return "error";
		}
		if (SourceVersion.isKeyword(s)) {
			return "_" + s;
		}
		return s;
	}

	protected JDefinedClass fieldClass(JDefinedClass baseClass, String pathItem, JDefinedClass securityClass) {
		String fieldName = correctFieldName(pathItem);
		JFieldVar fv = baseClass.fields().get(fieldName);
		if (fv != null) {
			return (JDefinedClass) fv.type();
		} else {
			try {
				JDefinedClass createdClass =
						baseClass._package()
								.subPackage(correctFieldName(baseClass.name().toLowerCase()))
								._class(upperFirstLowerRest(pathItem));
				JFieldVar createdSecurityField = createdClass.field(JMod.PUBLIC | JMod.FINAL, securityClass, FIELD_SEC);
				JMethod cratedConstr = createdClass.constructor(JMod.PUBLIC);
				cratedConstr.body().assign(JExpr.refthis(createdSecurityField), cratedConstr.param(securityClass, FIELD_SEC));
				JFieldVar baseField = baseClass.field(JMod.PUBLIC, createdClass, fieldName);
				if (!baseClass.constructors().hasNext()) {
					JMethod cons = baseClass.constructor(JMod.PUBLIC);
					if (baseClass != securityClass) {
						cons.param(securityClass, FIELD_SEC);
					}
				}
				JMethod baseConstructor = baseClass.constructors().next();
				baseConstructor.body().assign(baseField,
						createdClass._new().arg(baseClass == securityClass ? JExpr._this() : createdSecurityField));
				return createdClass;
			} catch (JCodeModelException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public JDefinedClass addOperation(JDefinedClass pathClass, HttpMethod meth, Operation operation) {
		JDefinedClass ret = null;
		try {
			ret = pathClass._class(meth.name());
			pathClass.field(JMod.PUBLIC | JMod.FINAL, ret, meth.name(), ret._new());
		} catch (JCodeModelException e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

}
