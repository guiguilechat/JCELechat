package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AbyssalSpaceshipEntities
    extends Entity
{
    public static final AbyssalSpaceshipEntities.MetaGroup METAGROUP = new AbyssalSpaceshipEntities.MetaGroup();

    @Override
    public IMetaGroup<AbyssalSpaceshipEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AbyssalSpaceshipEntities>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AbyssalSpaceshipEntities.yaml";
        private Map<String, AbyssalSpaceshipEntities> cache = (null);

        @Override
        public IMetaCategory<? super AbyssalSpaceshipEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1982;
        }

        @Override
        public String getName() {
            return "AbyssalSpaceshipEntities";
        }

        @Override
        public synchronized Map<String, AbyssalSpaceshipEntities> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AbyssalSpaceshipEntities.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AbyssalSpaceshipEntities> types;
        }
    }
}
