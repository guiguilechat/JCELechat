
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
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
    public final static String RESOURCE_PATH = "SDE/celestial/DisruptableStationServices.yaml";
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
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DisruptableStationServices> items;

    }

}
