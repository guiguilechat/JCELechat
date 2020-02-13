package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularDreadnought
    extends Entity
{
    public static final IrregularDreadnought.MetaGroup METAGROUP = new IrregularDreadnought.MetaGroup();

    @Override
    public IMetaGroup<IrregularDreadnought> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularDreadnought>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/IrregularDreadnought.yaml";
        private Map<String, IrregularDreadnought> cache = (null);

        @Override
        public IMetaCategory<? super IrregularDreadnought> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1724;
        }

        @Override
        public String getName() {
            return "IrregularDreadnought";
        }

        @Override
        public synchronized Map<String, IrregularDreadnought> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularDreadnought.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularDreadnought> items;
        }
    }
}
