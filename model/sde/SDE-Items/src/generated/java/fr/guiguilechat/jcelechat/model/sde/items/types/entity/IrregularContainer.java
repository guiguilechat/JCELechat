package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularContainer
    extends Entity
{
    public final static IrregularContainer.MetaGroup METAGROUP = new IrregularContainer.MetaGroup();

    @Override
    public IMetaGroup<IrregularContainer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularContainer>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/IrregularContainer.yaml";
        private Map<String, IrregularContainer> cache = (null);

        @Override
        public IMetaCategory<? super IrregularContainer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1928;
        }

        @Override
        public String getName() {
            return "IrregularContainer";
        }

        @Override
        public synchronized Map<String, IrregularContainer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularContainer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularContainer> items;
        }
    }
}
