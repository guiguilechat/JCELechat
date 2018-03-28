package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class DisruptableStationServices
    extends Celestial
{
    /**
     * Whether a station type is player ownable.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IsPlayerOwnable;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    public final static String RESOURCE_PATH = "SDE/items/celestial/DisruptableStationServices.yaml";
    private static LinkedHashMap<String, DisruptableStationServices> cache = (null);

    @Override
    public int getGroupId() {
        return  874;
    }

    @Override
    public Class<?> getGroup() {
        return DisruptableStationServices.class;
    }

    public static synchronized LinkedHashMap<String, DisruptableStationServices> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DisruptableStationServices.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DisruptableStationServices> items;
    }
}
