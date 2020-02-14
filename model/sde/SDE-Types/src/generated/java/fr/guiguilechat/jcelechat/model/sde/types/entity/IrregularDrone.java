package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularDrone
    extends Entity
{
    public static final IrregularDrone.MetaGroup METAGROUP = new IrregularDrone.MetaGroup();

    @Override
    public IMetaGroup<IrregularDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularDrone>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/IrregularDrone.yaml";
        private Map<String, IrregularDrone> cache = (null);

        @Override
        public IMetaCategory<? super IrregularDrone> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1452;
        }

        @Override
        public String getName() {
            return "IrregularDrone";
        }

        @Override
        public synchronized Map<String, IrregularDrone> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(IrregularDrone.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularDrone> types;
        }
    }
}
