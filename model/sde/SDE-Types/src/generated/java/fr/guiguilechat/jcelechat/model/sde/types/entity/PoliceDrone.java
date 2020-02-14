package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class PoliceDrone
    extends Entity
{
    public static final PoliceDrone.MetaGroup METAGROUP = new PoliceDrone.MetaGroup();

    @Override
    public IMetaGroup<PoliceDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PoliceDrone>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/PoliceDrone.yaml";
        private Map<String, PoliceDrone> cache = (null);

        @Override
        public IMetaCategory<? super PoliceDrone> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  182;
        }

        @Override
        public String getName() {
            return "PoliceDrone";
        }

        @Override
        public synchronized Map<String, PoliceDrone> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PoliceDrone.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PoliceDrone> types;
        }
    }
}
