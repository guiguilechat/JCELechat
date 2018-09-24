package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class StructureEntities
    extends Entity
{
    public final static StructureEntities.MetaGroup METAGROUP = new StructureEntities.MetaGroup();

    @Override
    public IMetaGroup<StructureEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureEntities>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/StructureEntities.yaml";
        private Map<String, StructureEntities> cache = (null);

        @Override
        public IMetaCategory<? super StructureEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1872;
        }

        @Override
        public String getName() {
            return "StructureEntities";
        }

        @Override
        public synchronized Map<String, StructureEntities> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(StructureEntities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureEntities> items;
        }
    }
}
