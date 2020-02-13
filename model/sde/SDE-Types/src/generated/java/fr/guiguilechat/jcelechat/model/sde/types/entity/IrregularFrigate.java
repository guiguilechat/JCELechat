package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularFrigate
    extends Entity
{
    public static final IrregularFrigate.MetaGroup METAGROUP = new IrregularFrigate.MetaGroup();

    @Override
    public IMetaGroup<IrregularFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/IrregularFrigate.yaml";
        private Map<String, IrregularFrigate> cache = (null);

        @Override
        public IMetaCategory<? super IrregularFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1568;
        }

        @Override
        public String getName() {
            return "IrregularFrigate";
        }

        @Override
        public synchronized Map<String, IrregularFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IrregularFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularFrigate> items;
        }
    }
}
