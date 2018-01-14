
package fr.guiguilechat.eveonline.model.sde.compiled.items.material;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Material;
import org.yaml.snakeyaml.Yaml;

public class AncientSalvage
    extends Material
{

    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Hp;
    public final static String RESOURCE_PATH = "SDE/material/AncientSalvage.yaml";
    private static LinkedHashMap<String, AncientSalvage> cache = (null);

    @Override
    public int getGroupId() {
        return  966;
    }

    @Override
    public Class<?> getGroup() {
        return AncientSalvage.class;
    }

    public static LinkedHashMap<String, AncientSalvage> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AncientSalvage> items;

    }

}
