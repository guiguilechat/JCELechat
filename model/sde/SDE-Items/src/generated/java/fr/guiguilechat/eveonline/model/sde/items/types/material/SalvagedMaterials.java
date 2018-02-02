package fr.guiguilechat.eveonline.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class SalvagedMaterials
    extends Material
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    public final static String RESOURCE_PATH = "SDE/items/material/SalvagedMaterials.yaml";
    private static LinkedHashMap<String, SalvagedMaterials> cache = (null);

    @Override
    public int getGroupId() {
        return  754;
    }

    @Override
    public Class<?> getGroup() {
        return SalvagedMaterials.class;
    }

    public static synchronized LinkedHashMap<String, SalvagedMaterials> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SalvagedMaterials.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SalvagedMaterials> items;
    }
}
