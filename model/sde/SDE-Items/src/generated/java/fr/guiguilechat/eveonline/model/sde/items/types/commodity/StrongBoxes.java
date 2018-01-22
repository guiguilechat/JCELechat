package fr.guiguilechat.eveonline.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class StrongBoxes
    extends Commodity
{
    /**
     * meta group of type
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    public final static String RESOURCE_PATH = "SDE/items/commodity/StrongBoxes.yaml";
    private static LinkedHashMap<String, StrongBoxes> cache = (null);

    @Override
    public int getGroupId() {
        return  1818;
    }

    @Override
    public Class<?> getGroup() {
        return StrongBoxes.class;
    }

    public static LinkedHashMap<String, StrongBoxes> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StrongBoxes.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StrongBoxes> items;
    }
}
