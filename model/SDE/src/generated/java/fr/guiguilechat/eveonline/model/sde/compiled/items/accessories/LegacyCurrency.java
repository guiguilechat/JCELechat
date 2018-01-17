
package fr.guiguilechat.eveonline.model.sde.compiled.items.accessories;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Accessories;
import org.yaml.snakeyaml.Yaml;

public class LegacyCurrency
    extends Accessories
{

    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double AurumConversionRate;
    /**
     * Number of days that this PLEX adds to your account
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double NumDays;
    public final static String RESOURCE_PATH = "SDE/items/accessories/LegacyCurrency.yaml";
    private static LinkedHashMap<String, LegacyCurrency> cache = (null);

    @Override
    public int getGroupId() {
        return  943;
    }

    @Override
    public Class<?> getGroup() {
        return LegacyCurrency.class;
    }

    public static LinkedHashMap<String, LegacyCurrency> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(LegacyCurrency.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, LegacyCurrency> items;

    }

}
