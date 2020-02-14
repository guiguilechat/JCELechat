package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SeekerScouts
    extends Entity
{
    public static final SeekerScouts.MetaGroup METAGROUP = new SeekerScouts.MetaGroup();

    @Override
    public IMetaGroup<SeekerScouts> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SeekerScouts>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/SeekerScouts.yaml";
        private Map<String, SeekerScouts> cache = (null);

        @Override
        public IMetaCategory<? super SeekerScouts> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1768;
        }

        @Override
        public String getName() {
            return "SeekerScouts";
        }

        @Override
        public synchronized Map<String, SeekerScouts> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SeekerScouts.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SeekerScouts> types;
        }
    }
}
