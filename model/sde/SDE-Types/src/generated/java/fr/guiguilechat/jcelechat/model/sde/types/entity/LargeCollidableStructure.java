package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class LargeCollidableStructure
    extends Entity
{
    public static final LargeCollidableStructure.MetaGroup METAGROUP = new LargeCollidableStructure.MetaGroup();

    @Override
    public IMetaGroup<LargeCollidableStructure> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LargeCollidableStructure>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/LargeCollidableStructure.yaml";
        private Map<String, LargeCollidableStructure> cache = (null);

        @Override
        public IMetaCategory<? super LargeCollidableStructure> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  319;
        }

        @Override
        public String getName() {
            return "LargeCollidableStructure";
        }

        @Override
        public synchronized Map<String, LargeCollidableStructure> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(LargeCollidableStructure.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, LargeCollidableStructure> items;
        }
    }
}
