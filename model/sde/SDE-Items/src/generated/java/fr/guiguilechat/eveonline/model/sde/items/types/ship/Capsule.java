package fr.guiguilechat.eveonline.model.sde.items.types.ship;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class Capsule
    extends Ship
{
    /**
     * meta group of type
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    public final static String RESOURCE_PATH = "SDE/items/ship/Capsule.yaml";
    private static LinkedHashMap<String, Capsule> cache = (null);

    @Override
    public int getGroupId() {
        return  29;
    }

    @Override
    public Class<?> getGroup() {
        return Capsule.class;
    }

    public static synchronized LinkedHashMap<String, Capsule> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Capsule.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Capsule> items;
    }
}
