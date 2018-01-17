
package fr.guiguilechat.eveonline.model.sde.compiled.items.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Material;
import org.yaml.snakeyaml.Yaml;

public class MoonMaterials
    extends Material
{

    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double MoonMiningAmount;
    public final static String RESOURCE_PATH = "SDE/items/material/MoonMaterials.yaml";
    private static LinkedHashMap<String, MoonMaterials> cache = (null);

    @Override
    public int getGroupId() {
        return  427;
    }

    @Override
    public Class<?> getGroup() {
        return MoonMaterials.class;
    }

    public static LinkedHashMap<String, MoonMaterials> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MoonMaterials.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MoonMaterials> items;

    }

}
