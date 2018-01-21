
package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class DisruptableStationServices
    extends Celestial
{

    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(100.0D)
    public double SignatureRadius;
    /**
     * Whether a station type is player ownable.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double IsPlayerOwnable;
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

    public static LinkedHashMap<String, DisruptableStationServices> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DisruptableStationServices.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DisruptableStationServices> items;

    }

}
