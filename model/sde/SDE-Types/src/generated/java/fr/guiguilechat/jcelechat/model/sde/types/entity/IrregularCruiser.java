package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularCruiser
    extends Entity
{
    public static final IrregularCruiser.MetaGroup METAGROUP = new IrregularCruiser.MetaGroup();

    @Override
    public IMetaGroup<IrregularCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/IrregularCruiser.yaml";
        private Map<String, IrregularCruiser> cache = (null);

        @Override
        public IMetaCategory<? super IrregularCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1665;
        }

        @Override
        public String getName() {
            return "IrregularCruiser";
        }

        @Override
        public synchronized Map<String, IrregularCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularCruiser> items;
        }
    }
}
