package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireCarrier
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionAmarrEmpireCarrier.yaml";
    private static LinkedHashMap<String, MissionAmarrEmpireCarrier> cache = (null);

    @Override
    public int getGroupId() {
        return  865;
    }

    @Override
    public Class<?> getGroup() {
        return MissionAmarrEmpireCarrier.class;
    }

    public static synchronized LinkedHashMap<String, MissionAmarrEmpireCarrier> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionAmarrEmpireCarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionAmarrEmpireCarrier> items;
    }
}
