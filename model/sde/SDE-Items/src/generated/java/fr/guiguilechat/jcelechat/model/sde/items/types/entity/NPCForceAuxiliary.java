package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class NPCForceAuxiliary
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/NPCForceAuxiliary.yaml";
    private static LinkedHashMap<String, NPCForceAuxiliary> cache = (null);

    @Override
    public int getGroupId() {
        return  1879;
    }

    @Override
    public Class<?> getGroup() {
        return NPCForceAuxiliary.class;
    }

    public static synchronized LinkedHashMap<String, NPCForceAuxiliary> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NPCForceAuxiliary.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, NPCForceAuxiliary> items;
    }
}
