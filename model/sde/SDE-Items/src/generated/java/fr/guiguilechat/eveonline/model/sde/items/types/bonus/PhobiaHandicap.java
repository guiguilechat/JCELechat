package fr.guiguilechat.eveonline.model.sde.items.types.bonus;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class PhobiaHandicap
    extends Bonus
{
    public final static String RESOURCE_PATH = "SDE/items/bonus/PhobiaHandicap.yaml";
    private static LinkedHashMap<String, PhobiaHandicap> cache = (null);

    @Override
    public int getGroupId() {
        return  193;
    }

    @Override
    public Class<?> getGroup() {
        return PhobiaHandicap.class;
    }

    public static LinkedHashMap<String, PhobiaHandicap> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PhobiaHandicap.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, PhobiaHandicap> items;
    }
}
