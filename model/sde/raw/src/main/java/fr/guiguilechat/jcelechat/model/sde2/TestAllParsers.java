package fr.guiguilechat.jcelechat.model.sde2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.guiguilechat.jcelechat.model.sde2.parsers.*;
import fr.guiguilechat.jcelechat.model.sde2.yaml.YamlCache;

public class TestAllParsers {

	public static void main(String[] args)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		YamlCache.INSTANCE.donwloadSDE();
		for (Class<?> cl : new Class<?>[] {
				Eagents.class,
				EagentsInSpace.class,
				EagentTypes.class,
				Eancestries.class,
				Ebloodlines.class,
				Eblueprints.class,
				Ecategories.class,
				Ecertificates.class,
				EcharacterAttributes.class,
				EcontrabandTypes.class,
				EcontrolTowerResources.class,
				EcorporationActivities.class,
				EdbuffCollections.class,
				EdogmaAttributeCategory.class,
				EdogmaAttributes.class,
				EdogmaEffects.class
		}) {
			System.err.println(cl.getSimpleName());
			Method m = cl.getMethod("main", String[].class);
			m.invoke(null, new Object[] { null });
		}

	}

}
