package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularDestroyer
    extends Entity
{
    public static final IrregularDestroyer.MetaGroup METAGROUP = new IrregularDestroyer.MetaGroup();

    @Override
    public IMetaGroup<IrregularDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/IrregularDestroyer.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(IrregularDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularDestroyer> types;
        }
    }
}
