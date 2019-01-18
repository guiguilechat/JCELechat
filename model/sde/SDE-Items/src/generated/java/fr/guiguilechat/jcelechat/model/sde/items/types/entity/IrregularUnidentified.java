package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularUnidentified
    extends Entity
{
    public static final IrregularUnidentified.MetaGroup METAGROUP = new IrregularUnidentified.MetaGroup();

    @Override
    public IMetaGroup<IrregularUnidentified> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularUnidentified>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/IrregularUnidentified.yaml";
        private Map<String, IrregularUnidentified> cache = (null);

        @Override
        public IMetaCategory<? super IrregularUnidentified> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1929;
        }

        @Override
        public String getName() {
            return "IrregularUnidentified";
        }

        @Override
        public synchronized Map<String, IrregularUnidentified> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularUnidentified.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularUnidentified> items;
        }
    }
}
