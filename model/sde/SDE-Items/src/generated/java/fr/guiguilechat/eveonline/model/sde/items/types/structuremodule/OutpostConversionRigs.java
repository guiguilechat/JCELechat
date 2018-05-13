package fr.guiguilechat.eveonline.model.sde.items.types.structuremodule;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class OutpostConversionRigs
    extends StructureModule
{
    public final static String RESOURCE_PATH = "SDE/items/structuremodule/OutpostConversionRigs.yaml";
    private static LinkedHashMap<String, OutpostConversionRigs> cache = (null);

    @Override
    public int getGroupId() {
        return  1984;
    }

    @Override
    public Class<?> getGroup() {
        return OutpostConversionRigs.class;
    }

    public static synchronized LinkedHashMap<String, OutpostConversionRigs> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OutpostConversionRigs.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, OutpostConversionRigs> items;
    }
}
