package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularFighter
    extends Entity
{
    public static final IrregularFighter.MetaGroup METAGROUP = new IrregularFighter.MetaGroup();

    @Override
    public IMetaGroup<IrregularFighter> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularFighter>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/IrregularFighter.yaml";
        private Map<String, IrregularFighter> cache = (null);

        @Override
        public IMetaCategory<? super IrregularFighter> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1454;
        }

        @Override
        public String getName() {
            return "IrregularFighter";
        }

        @Override
        public synchronized Map<String, IrregularFighter> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularFighter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularFighter> items;
        }
    }
}
