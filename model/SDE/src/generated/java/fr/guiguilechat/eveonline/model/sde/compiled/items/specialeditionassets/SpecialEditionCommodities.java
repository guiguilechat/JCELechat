
package fr.guiguilechat.eveonline.model.sde.compiled.items.specialeditionassets;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class SpecialEditionCommodities
    extends SpecialEditionAssets
{

    /**
     * Factor to adjust module cpu need by.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double CpuMultiplier;
    public final static String RESOURCE_PATH = "SDE/items/specialeditionassets/SpecialEditionCommodities.yaml";
    private static LinkedHashMap<String, SpecialEditionCommodities> cache = (null);

    @Override
    public int getGroupId() {
        return  1194;
    }

    @Override
    public Class<?> getGroup() {
        return SpecialEditionCommodities.class;
    }

    public static LinkedHashMap<String, SpecialEditionCommodities> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SpecialEditionCommodities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SpecialEditionCommodities> items;

    }

}
