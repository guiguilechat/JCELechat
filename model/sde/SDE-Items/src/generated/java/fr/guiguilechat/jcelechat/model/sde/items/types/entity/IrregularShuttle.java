package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularShuttle
    extends Entity
{
    public static final IrregularShuttle.MetaGroup METAGROUP = new IrregularShuttle.MetaGroup();

    @Override
    public IMetaGroup<IrregularShuttle> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularShuttle>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/IrregularShuttle.yaml";
        private Map<String, IrregularShuttle> cache = (null);

        @Override
        public IMetaCategory<? super IrregularShuttle> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1566;
        }

        @Override
        public String getName() {
            return "IrregularShuttle";
        }

        @Override
        public synchronized Map<String, IrregularShuttle> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularShuttle.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularShuttle> items;
        }
    }
}
