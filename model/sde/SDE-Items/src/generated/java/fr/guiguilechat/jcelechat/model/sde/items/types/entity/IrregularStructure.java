package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularStructure
    extends Entity
{
    public final static IrregularStructure.MetaGroup METAGROUP = new IrregularStructure.MetaGroup();

    @Override
    public IMetaGroup<IrregularStructure> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularStructure>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/IrregularStructure.yaml";
        private Map<String, IrregularStructure> cache = (null);

        @Override
        public IMetaCategory<? super IrregularStructure> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1927;
        }

        @Override
        public String getName() {
            return "IrregularStructure";
        }

        @Override
        public synchronized Map<String, IrregularStructure> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularStructure.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularStructure> items;
        }
    }
}
