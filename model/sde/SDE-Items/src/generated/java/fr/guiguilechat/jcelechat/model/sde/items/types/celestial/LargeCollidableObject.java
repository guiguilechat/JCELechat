package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class LargeCollidableObject
    extends Celestial
{
    public final static LargeCollidableObject.MetaGroup METAGROUP = new LargeCollidableObject.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/celestial/LargeCollidableObject.yaml";
    private static Map<String, LargeCollidableObject> cache = (null);

    @Override
    public int getGroupId() {
        return  226;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<LargeCollidableObject> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, LargeCollidableObject> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(LargeCollidableObject.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, LargeCollidableObject> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<LargeCollidableObject>
    {

        @Override
        public MetaCategory<? super LargeCollidableObject> category() {
            return Celestial.METACAT;
        }

        @Override
        public String getName() {
            return "LargeCollidableObject";
        }

        @Override
        public Collection<LargeCollidableObject> items() {
            return (load().values());
        }
    }
}
