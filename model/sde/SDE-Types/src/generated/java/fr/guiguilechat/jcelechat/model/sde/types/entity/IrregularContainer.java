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

public class IrregularContainer
    extends Entity
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final IrregularContainer.MetaGroup METAGROUP = new IrregularContainer.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<IrregularContainer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularContainer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/IrregularContainer.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(IrregularContainer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularContainer> types;
        }
    }
}
