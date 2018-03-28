package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class Satellite
    extends Celestial
{
    /**
     * The distance at which to react when relevant objects come within range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ProximityRange;
    /**
     * Attribute to disallow targetting.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Untargetable;
    public final static String RESOURCE_PATH = "SDE/items/celestial/Satellite.yaml";
    private static LinkedHashMap<String, Satellite> cache = (null);

    @Override
    public int getGroupId() {
        return  1165;
    }

    @Override
    public Class<?> getGroup() {
        return Satellite.class;
    }

    public static synchronized LinkedHashMap<String, Satellite> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Satellite.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Satellite> items;
    }
}
