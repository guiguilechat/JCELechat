
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class Biomass
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
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Hp;
    public final static String RESOURCE_PATH = "SDE/items/celestial/Biomass.yaml";
    private static LinkedHashMap<String, Biomass> cache = (null);

    @Override
    public int getGroupId() {
        return  14;
    }

    @Override
    public Class<?> getGroup() {
        return Biomass.class;
    }

    public static LinkedHashMap<String, Biomass> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Biomass.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Biomass> items;

    }

}
