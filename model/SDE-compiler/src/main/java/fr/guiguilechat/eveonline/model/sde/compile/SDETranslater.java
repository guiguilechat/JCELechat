package fr.guiguilechat.eveonline.model.sde.compile;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.model.sde.compile.SDEClassesCompiler.CompiledClassesData;
import fr.guiguilechat.eveonline.model.sde.compile.inmemory.DynamicClassLoader;
import fr.guiguilechat.eveonline.model.sde.load.fsd.EtypeIDs;

public class SDETranslater {

	public void translate(CompiledClassesData classes) {
		DynamicClassLoader cl = new DynamicClassLoader(getClass().getClassLoader()).withCode(classes.model);
		HashMap<Integer, Object> builtItems = new HashMap<>();
		LinkedHashMap<Integer, EtypeIDs> typeids = EtypeIDs.load();
		for (Entry<Integer, EtypeIDs> e : typeids.entrySet()) {
			int id = e.getKey();
			EtypeIDs type = e.getValue();
			String className = classes.groupID2ClassName.get(type.groupID);
			Object item = makeObjectDefault(className, cl);
			builtItems.put(id, item);
		}
	}

	protected Object makeObjectDefault(String string, DynamicClassLoader cl) {
		try {
			Class<?> clazz = cl.loadClass(string);
			Object ret = clazz.newInstance();
			// TODO use annotations to get the default value
			return ret;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

}
