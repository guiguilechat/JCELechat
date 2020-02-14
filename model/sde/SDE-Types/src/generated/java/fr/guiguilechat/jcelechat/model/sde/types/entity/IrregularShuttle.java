package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
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
        public static final String RESOURCE_PATH = "SDE/types/entity/IrregularShuttle.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(IrregularShuttle.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularShuttle> types;
        }
    }
}
