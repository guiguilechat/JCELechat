package fr.guiguilechat.jcelechat.model.jcesi.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.helger.jcodemodel.AbstractJType;
import com.helger.jcodemodel.IJExpression;
import com.helger.jcodemodel.JArray;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JCodeModel;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JInvocation;
import com.helger.jcodemodel.JLambda;
import com.helger.jcodemodel.JLambdaParam;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JSynchronizedBlock;
import com.helger.jcodemodel.JTryBlock;
import com.helger.jcodemodel.JVar;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.FetchTranslator.RETURNTYPE;
import fr.lelouet.collectionholders.impl.ObsObjHolderSimple;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import io.swagger.models.Operation;
import io.swagger.models.parameters.Parameter;

/**
 * create a cache information over the result of a {@link FetchTranslator}.
 * Basically holds all the informations used when calling the {@link #apply()}
 * methods, because this methods can make a lot of things and this avoids
 * guessing data several times.
 *
 */
public class CacheTranslator {

	final FetchTranslator parent;
	final JCodeModel cm;
	final Operation operation;
	final ClassBridge bridge;

	public CacheTranslator(FetchTranslator parent) {
		this.parent = parent;
		cm = parent.cm;
		operation = parent.operation;
		bridge = parent.bridge;
	}

	protected String cacheFieldName;
	protected JDefinedClass cacheGroup;

	protected List<JVar> cacheParams = new ArrayList<>();

	/** type returned by the cache method */
	protected AbstractJType cacheRetItf;
	/**
	 * type constructed in the cache method, must be a subtype of
	 * {@link #cacheRetItf}
	 */
	protected AbstractJType cacheRetType;
	/**
	 * type of the object we transmit to the scheduled fetcher to hold the data
	 */
	// protected AbstractJType cacheHolderType;

	protected JMethod cacheMeth;

	protected AbstractJType cacheKeyType;
	protected JVar cacheContainer;
	protected JVar cacheParam;

	protected void apply() {
		if (operation == null) {
			return;
		}

		// create the correct names, method, the group to hold the cache class in,
		// etc.

		cacheFieldName = operation.getOperationId().split("_")[1];
		cacheGroup = bridge.getCacheGroupClass(cacheFieldName, parent.connected);

		String cacheMethName = operation.getOperationId().replaceAll("^get_", "").replaceAll("^" + cacheFieldName + "_",
				"");
		for (JVar v : parent.allParams) {
			cacheMethName = cacheMethName.replaceAll(v.name(), "");
		}
		cacheMethName = cacheMethName.replaceAll("__", "_").replaceAll("^_", "").replaceAll("_$", "");
		if (cacheMethName.length() < 2) {
			cacheMethName = "get";
		}

		if (keywords.contains(cacheMethName)) {
			cacheMethName = "get" + cacheMethName;
		}

		// create the cache init according to the resource structure.

		switch (parent.resourceStructure) {
		case NONE:
			return;
		case OBJECT:
			cacheRetItf = cm.ref(ObsObjHolder.class).narrow(parent.resourceFlatType.boxify());
			cacheRetType = cm.ref(ObsObjHolderSimple.class).narrow(parent.resourceFlatType.boxify());
			break;
		case LIST:
			cacheRetItf = cm.ref(ObsListHolder.class).narrow(parent.resourceFlatType.boxify());
			cacheRetType = cm.ref(ObsListHolderImpl.class).narrow(parent.resourceFlatType.boxify());
			break;
		case MAP:
			cacheRetItf = cm.ref(ObsMapHolder.class).narrow(parent.resourceFlatType.boxify());
			cacheRetType = cm.ref(ObsMapHolderImpl.class).narrow(parent.resourceFlatType.boxify());
			break;
		default:
			throw new UnsupportedOperationException("can't handle case " + parent.resourceStructure);
		}

		cacheMeth = cacheGroup.method(JMod.PUBLIC, cacheRetItf, cacheMethName);
		cacheMeth.javadoc().add(operation.getDescription().split("---")[0]);
		cacheMeth.javadoc().add("cache over {@link Swagger#" + parent.fetchMeth.name() + "}<br />");

		// after that we need to know the parameters for the method
		// some params are not kept in the cache.

		for (JVar v : parent.allParams) {
			if (!v.name().equals(parent.propsParamName) && !v.name().equals("page")) {
				cacheParams.add(cacheMeth.param(v.type(), v.name()));
				Parameter vp = operation.getParameters().stream().filter(p -> p.getName().equals(v.name())).findFirst()
						.orElse(null);
				if (vp != null) {
					cacheMeth.javadoc().addParam(v).add(vp.getDescription());
				}
			}
		}

		// according to the number of params, we either have one entry that is
		// cached, a map of entry to the cached value, or a complex type that holds
		// all the cache params.

		switch (cacheParams.size()) {
		case 0:
			cacheKeyType = null;
			switch (parent.resourceStructure) {
			case OBJECT:
				createCache_NoParam_Container();
				break;
			case LIST:
				createCache_NoParam_List();
				break;
			case MAP:
				createCache_NoParam_Map();
				break;
			default:
				throw new UnsupportedOperationException("handle case " + parent.resourceStructure);
			}
			break;
		case 1:
			cacheKeyType = cacheParams.get(0).type().boxify();
			cacheContainer = cacheGroup.field(JMod.PRIVATE | JMod.FINAL,
					cm.ref(Map.class).narrow(cacheKeyType).narrow(cacheRetType), operation.getOperationId() + "_holder")
					.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));
			cacheParam = cacheParams.get(0);
			switch (parent.resourceStructure) {
			case OBJECT:
				createCache_Param_Container();
				break;
			case LIST:
				createCache_Param_List();
				break;
			case MAP:
				createCache_Param_Map();
				break;
			default:
				throw new UnsupportedOperationException("handle case " + parent.resourceStructure);
			}
			break;
		default:
			cacheKeyType = makeKeyParam(cacheParams);
			cacheContainer = cacheGroup.field(JMod.PRIVATE | JMod.FINAL,
					cm.ref(Map.class).narrow(cacheKeyType).narrow(cacheRetType), operation.getOperationId() + "_holder")
					.init(JExpr._new(cm.ref(HashMap.class).narrowEmpty()));
			cacheParam = cacheMeth.body().decl(cacheKeyType, "param");
			JMethod cons = ((JDefinedClass) cacheKeyType).constructors().next();
			JInvocation callNew = JExpr._new(cacheKeyType);
			for (JVar b : cons.params()) {
				callNew = callNew.arg(b);
			}
			cacheParam.init(callNew);
			switch (parent.resourceStructure) {
			case OBJECT:
				createCache_Param_Container();
				break;
			case LIST:
				createCache_Param_List();
				break;
			case MAP:
				createCache_Param_Map();
				break;
			default:
				throw new UnsupportedOperationException("handle case " + parent.resourceStructure);
			}
		}
	}

	/** java keywords we can't use as a name */
	public static final Set<String> keywords = Collections.unmodifiableSet(new HashSet<>(
			Arrays.asList("abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
					"continue", "default", "do", "double", "else", "extends", "false", "final", "finally", "float", "for", "goto",
					"if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package",
					"private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized",
					"this", "throw", "throws", "transient", "true", "try", "void", "volatile", "while")));

	protected JDefinedClass makeKeyParam(List<JVar> cacheParams2) {
		JDefinedClass ret = parent.bridge
				.getCacheKeyClass(cacheParams2.stream().collect(Collectors.toMap(JVar::name, JVar::type)));
		ret.javadoc().append("@see " + parent.path + "\n");
		return ret;
	}

	/**
	 * create the lambda method to call the fetch inside the cache method.<br />
	 * The result is transformed into an array of Integer if the fetch returns
	 * integer[]. The lambda takes an additional page parameter iff the
	 * transformation is not into a container (list or map)
	 *
	 * @return
	 */
	protected JLambda lambdaFetch() {
		JLambda lambdaFetch = new JLambda();
		Map<String, IJExpression> paramsByName = new HashMap<>();
		if (parent.resourceStructure != RETURNTYPE.OBJECT) {
			JLambdaParam page = lambdaFetch.addParam("page");
			paramsByName.put(page.name(), page);
		}
		JLambdaParam head = lambdaFetch.addParam(parent.propsParamName);
		paramsByName.put(head.name(), head);
		for (JVar p : cacheParams) {
			paramsByName.put(p.name(), p);
		}
		JInvocation callmeth = JExpr.direct("cache.swagger").invoke(parent.fetchMeth);
		for (JVar v : parent.fetchMeth.params()) {
			IJExpression v2 = paramsByName.get(v.name());
			if (v2 == null) {
				System.err.println("getting arg " + v.name() + " from " + paramsByName);
			}
			callmeth.arg(v2);
		}
		lambdaFetch.body().add(callmeth);
		return lambdaFetch;
	}

	/**
	 * create the following block : ret=getter; if(ret==null) synchronized(lock)
	 * {ret=getter; if(ret==null){BLOCK}}
	 *
	 * @return the block to assign value. in this block ret is null and we need to
	 *         create it.
	 */
	protected JBlock createTestNullCase(JBlock outBlock, JVar ret, IJExpression getter, JVar lock) {
		ret.init(getter);
		return sync(outBlock._if(ret.eqNull())._then(), lock).add(JExpr.assign(ret, getter))._if(ret.eqNull())._then();
	}

	/**
	 * create a synchronized(expr) surounded by a call to barker.tak(expr) and a
	 * call to barker.rel(expr)
	 *
	 * @param parent
	 * @param expr
	 * @return
	 */
	protected JBlock sync(JBlock parent, IJExpression expr) {
		parent.add(JExpr.invoke(cm.ref(LockWatchDog.class).staticRef("BARKER"), "tak").arg(expr));
		JTryBlock tryblock = parent._try();
		JSynchronizedBlock syncblock = tryblock.body().synchronizedBlock(expr);
		syncblock.body().add(JExpr.invoke(cm.ref(LockWatchDog.class).staticRef("BARKER"), "hld").arg(expr));
		JBlock ret = syncblock.body().block();
		JInvocation releaseexpr = JExpr.invoke(cm.ref(LockWatchDog.class).staticRef("BARKER"), "rel").arg(expr);
		syncblock.body().add(releaseexpr);
		tryblock._finally().add(releaseexpr);
		return ret;
	}

	////
	// creation of cache method based on fetch method
	////

	/**
	 * create the cache body when cache has no parameter and the fetch method
	 * returns an object
	 */
	protected void createCache_NoParam_Container() {
		cacheContainer = cacheGroup.field(JMod.PRIVATE, cacheRetType, operation.getOperationId() + "_holder");
		JBlock instanceBlock = sync(cacheMeth.body()._if(cacheContainer.eqNull())._then(), JExpr._this())
				._if(cacheContainer.eqNull())._then();
		instanceBlock.assign(cacheContainer, JExpr._new(cacheRetType));
		JInvocation invoke = JExpr.invoke(JExpr.direct("cache"), bridge.methFetchCacheObject())
				.arg(operation.getOperationId());
		invoke.arg(lambdaFetch());
		instanceBlock.add(invoke);
		JLambda lambdaset = new JLambda();
		JLambdaParam item = lambdaset.addParam("item");
		lambdaset.body().add(JExpr.invoke(cacheContainer, "set").arg(item));
		invoke.arg(lambdaset);
		if (!parent.requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : parent.requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		cacheMeth.body()._return(cacheContainer);
	}

	/**
	 * create the cache body when no parameter and the fetch method returns a list
	 * of items with no unique field.
	 */
	protected void createCache_NoParam_List() {
		cacheContainer = cacheGroup.field(JMod.PRIVATE, cacheRetType, operation.getOperationId() + "_holder");
		JBlock instanceBlock = sync(cacheMeth.body()._if(cacheContainer.eqNull())._then(), JExpr._this())
				._if(cacheContainer.eqNull())._then();
		instanceBlock.assign(cacheContainer, JExpr._new(cacheRetType));

		JInvocation invoke = JExpr.invoke(JExpr.direct("cache"), bridge.methFetchCacheArray())
				.arg(operation.getOperationId());
		invoke.arg(lambdaFetch());

		JLambda lambdaSet = new JLambda();
		JLambdaParam arr = lambdaSet.addParam("arr");
		lambdaSet.body().add(JExpr.invoke(cacheContainer, "set").arg(arr));
		invoke.arg(lambdaSet);
		if (!parent.requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : parent.requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		instanceBlock.add(invoke);
		cacheMeth.body()._return(cacheContainer);
	}

	/**
	 * create the cache body when one or more parameter.
	 */
	protected void createCache_Param_List() {
		JVar ret = cacheMeth.body().decl(cacheRetType, "ret");
		JBlock instanceBlock = createTestNullCase(cacheMeth.body(), ret, cacheContainer.invoke("get").arg(cacheParam),
				cacheContainer);
		instanceBlock.assign(ret, JExpr._new(cacheRetType));
		instanceBlock.add(JExpr.invoke(cacheContainer, "put").arg(cacheParam).arg(ret));
		JVar finalRet = instanceBlock.decl(cacheRetType, "finalRet").init(ret);
		JInvocation invoke = JExpr.invoke(JExpr.direct("cache"), bridge.methFetchCacheArray())
				.arg(operation.getOperationId());
		invoke.arg(lambdaFetch());
		instanceBlock.add(invoke);

		JLambda lambdaSet = new JLambda();
		JLambdaParam arr = lambdaSet.addParam("arr");
		lambdaSet.body().add(JExpr.invoke(finalRet, "set").arg(arr));
		invoke.arg(lambdaSet);
		if (!parent.requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : parent.requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		cacheMeth.body()._return(ret);
	}

	/**
	 * create the cache body when only one parameter and returns an map of String
	 * to resource
	 */
	protected void createCache_Param_Map() {
		JVar ret = cacheMeth.body().decl(cacheRetType, "ret");
		JBlock instanceBlock = createTestNullCase(cacheMeth.body(), ret, cacheContainer.invoke("get").arg(cacheParam),
				cacheContainer);
		instanceBlock.assign(ret, JExpr._new(cacheRetType));
		instanceBlock.add(JExpr.invoke(cacheContainer, "put").arg(cacheParam).arg(ret));
		JVar finalRet = instanceBlock.decl(cacheRetType, "finalRet").init(ret);
		JInvocation invoke = JExpr.invoke(JExpr.direct("cache"), bridge.methFetchCacheMap())
				.arg(operation.getOperationId());

		invoke.arg(lambdaFetch());
		instanceBlock.add(invoke);

		JLambda lambdaSet = new JLambda();
		JLambdaParam newmap = lambdaSet.addParam("newmap");
		JBlock ifnotnull = lambdaSet.body()._if(newmap.neNull())._then();
		ifnotnull.add(JExpr.invoke(finalRet, "set").arg(newmap));
		invoke.arg(lambdaSet);
		if (!parent.requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : parent.requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}
		cacheMeth.body()._return(ret);
	}

	/** method is fetch()-> map (param, rettype) */
	protected void createCache_NoParam_Map() {
		cacheContainer = cacheGroup.field(JMod.PRIVATE, cacheRetType, operation.getOperationId() + "_holder");
		JBlock instanceBlock = sync(cacheMeth.body()._if(cacheContainer.eqNull())._then(), JExpr._this())
				._if(cacheContainer.eqNull())._then();
		instanceBlock.assign(cacheContainer, JExpr._new(cacheRetType));
		JVar finalRet = instanceBlock.decl(cacheRetType, "finalRet").init(cacheContainer);
		JInvocation invoke = JExpr.invoke(JExpr.direct("cache"), bridge.methFetchCacheMap())
				.arg(operation.getOperationId());

		invoke.arg(lambdaFetch());
		instanceBlock.add(invoke);

		JLambda lambdaSet = new JLambda();
		JLambdaParam newmap = lambdaSet.addParam("newmap");
		JBlock ifnotnull = lambdaSet.body()._if(newmap.neNull())._then();
		ifnotnull.add(JExpr.invoke(finalRet, "set").arg(newmap));
		invoke.arg(lambdaSet);

		if (!parent.requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : parent.requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}

		cacheMeth.body()._return(cacheContainer);
	}

	/**
	 * Create the cache body when only one parameter and return a container of
	 * resourcetype.<br />
	 * Typically produces a map<cachekey, cacheret>
	 */
	protected void createCache_Param_Container() {
		JVar ret = cacheMeth.body().decl(cacheRetType, "ret");
		JBlock instanceBlock = createTestNullCase(cacheMeth.body(), ret, cacheContainer.invoke("get").arg(cacheParam),
				cacheContainer);
		instanceBlock.assign(ret, JExpr._new(cacheRetType));
		JVar finalRet = instanceBlock.decl(cacheRetType, "finalRet").init(ret);
		instanceBlock.add(JExpr.invoke(cacheContainer, "put").arg(cacheParam).arg(ret));
		JInvocation invoke = JExpr.invoke(JExpr.direct("cache"), bridge.methFetchCacheObject())
				.arg(operation.getOperationId());

		invoke.arg(lambdaFetch());
		instanceBlock.add(invoke);

		JLambda lambdaset = new JLambda();
		JLambdaParam item = lambdaset.addParam("item");
		lambdaset.body().add(JExpr.invoke(finalRet, "set").arg(item));
		invoke.arg(lambdaset);
		if (!parent.requiredRoles.isEmpty()) {
			JArray array = JExpr.newArray(cm.ref(String.class));
			for (String s : parent.requiredRoles) {
				array.add(JExpr.lit(s));
			}
			invoke.arg(array);
		}

		cacheMeth.body()._return(ret);
	}

}
