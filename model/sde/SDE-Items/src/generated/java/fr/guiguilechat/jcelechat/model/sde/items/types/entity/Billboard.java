package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class Billboard
    extends Entity
{
    public final static Billboard.MetaGroup METAGROUP = new Billboard.MetaGroup();

    @Override
    public IMetaGroup<Billboard> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Billboard>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/Billboard.yaml";
        private Map<String, Billboard> cache = (null);

        @Override
        public IMetaCategory<? super Billboard> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  323;
        }

        @Override
        public String getName() {
            return "Billboard";
        }

        @Override
        public synchronized Map<String, Billboard> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Billboard.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Billboard> items;
        }
    }
}
