package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class LargeCollidableObject
    extends Celestial
{
    public final static LargeCollidableObject.MetaGroup METAGROUP = new LargeCollidableObject.MetaGroup();

    @Override
    public IMetaGroup<LargeCollidableObject> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LargeCollidableObject>
    {
        public final static String RESOURCE_PATH = "SDE/items/celestial/LargeCollidableObject.yaml";
        private Map<String, LargeCollidableObject> cache = (null);

        @Override
        public IMetaCategory<? super LargeCollidableObject> category() {
            return Celestial.METACAT;
        }

        @Override
        public int getGroupId() {
            return  226;
        }

        @Override
        public String getName() {
            return "LargeCollidableObject";
        }

        @Override
        public synchronized Map<String, LargeCollidableObject> load() {
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
    }
}
