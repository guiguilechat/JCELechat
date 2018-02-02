package fr.guiguilechat.eveonline.model.sde.items.types.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class Processors
    extends PlanetaryInteraction
{
    /**
     * This type can only be found/used/created on a planet matching this type ID.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PlanetRestriction;
    /**
     * CPU load of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuLoad;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerLoad;
    public final static String RESOURCE_PATH = "SDE/items/planetaryinteraction/Processors.yaml";
    private static LinkedHashMap<String, Processors> cache = (null);

    @Override
    public int getGroupId() {
        return  1028;
    }

    @Override
    public Class<?> getGroup() {
        return Processors.class;
    }

    public static synchronized LinkedHashMap<String, Processors> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Processors.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Processors> items;
    }
}
