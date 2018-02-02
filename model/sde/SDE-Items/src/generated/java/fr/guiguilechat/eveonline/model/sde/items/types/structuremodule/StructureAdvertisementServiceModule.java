package fr.guiguilechat.eveonline.model.sde.items.types.structuremodule;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureAdvertisementServiceModule
    extends StructureModule
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup01;
    public final static String RESOURCE_PATH = "SDE/items/structuremodule/StructureAdvertisementServiceModule.yaml";
    private static LinkedHashMap<String, StructureAdvertisementServiceModule> cache = (null);

    @Override
    public int getGroupId() {
        return  1326;
    }

    @Override
    public Class<?> getGroup() {
        return StructureAdvertisementServiceModule.class;
    }

    public static synchronized LinkedHashMap<String, StructureAdvertisementServiceModule> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureAdvertisementServiceModule.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StructureAdvertisementServiceModule> items;
    }
}
