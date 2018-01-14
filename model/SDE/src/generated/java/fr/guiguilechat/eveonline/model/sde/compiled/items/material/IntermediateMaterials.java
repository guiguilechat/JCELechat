
package fr.guiguilechat.eveonline.model.sde.compiled.items.material;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Material;
import org.yaml.snakeyaml.Yaml;

public class IntermediateMaterials
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
    public final static String RESOURCE_PATH = "SDE/material/IntermediateMaterials.yaml";
    private static LinkedHashMap<String, IntermediateMaterials> cache = (null);

    @Override
    public int getGroupId() {
        return  428;
    }

    @Override
    public Class<?> getGroup() {
        return IntermediateMaterials.class;
    }

    public static LinkedHashMap<String, IntermediateMaterials> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, IntermediateMaterials> items;

    }

}
