package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
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
        public static final String RESOURCE_PATH = "SDE/types/entity/IrregularFighter.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(IrregularFighter.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularFighter> types;
        }
    }
}
