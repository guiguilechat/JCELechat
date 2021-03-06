package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularUnidentified
    extends Entity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final IrregularUnidentified.MetaGroup METAGROUP = new IrregularUnidentified.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<IrregularUnidentified> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularUnidentified>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/IrregularUnidentified.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(IrregularUnidentified.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularUnidentified> types;
        }
    }
}
