package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Material;

public class AbyssalMaterials
    extends Material
{
    public final static String RESOURCE_PATH = "SDE/items/material/AbyssalMaterials.yaml";
    private static LinkedHashMap<String, AbyssalMaterials> cache = (null);

    @Override
    public int getGroupId() {
        return  1996;
    }

    @Override
    public Class<?> getGroup() {
        return AbyssalMaterials.class;
    }

    public static synchronized LinkedHashMap<String, AbyssalMaterials> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AbyssalMaterials.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AbyssalMaterials> items;
    }
}
