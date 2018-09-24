package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularDestroyer
    extends Entity
{
    public final static IrregularDestroyer.MetaGroup METAGROUP = new IrregularDestroyer.MetaGroup();

    @Override
    public IMetaGroup<IrregularDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularDestroyer>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/IrregularDestroyer.yaml";
        private Map<String, IrregularDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super IrregularDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1664;
        }

        @Override
        public String getName() {
            return "IrregularDestroyer";
        }

        @Override
        public synchronized Map<String, IrregularDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularDestroyer> items;
        }
    }
}
