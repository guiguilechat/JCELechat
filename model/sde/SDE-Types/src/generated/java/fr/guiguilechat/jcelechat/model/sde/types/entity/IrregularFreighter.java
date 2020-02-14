package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularFreighter
    extends Entity
{
    public static final IrregularFreighter.MetaGroup METAGROUP = new IrregularFreighter.MetaGroup();

    @Override
    public IMetaGroup<IrregularFreighter> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularFreighter>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/IrregularFreighter.yaml";
        private Map<String, IrregularFreighter> cache = (null);

        @Override
        public IMetaCategory<? super IrregularFreighter> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1926;
        }

        @Override
        public String getName() {
            return "IrregularFreighter";
        }

        @Override
        public synchronized Map<String, IrregularFreighter> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(IrregularFreighter.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularFreighter> types;
        }
    }
}
