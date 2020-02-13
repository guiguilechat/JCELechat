package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AbyssalDroneEntities
    extends Entity
{
    public static final AbyssalDroneEntities.MetaGroup METAGROUP = new AbyssalDroneEntities.MetaGroup();

    @Override
    public IMetaGroup<AbyssalDroneEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AbyssalDroneEntities>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AbyssalDroneEntities.yaml";
        private Map<String, AbyssalDroneEntities> cache = (null);

        @Override
        public IMetaCategory<? super AbyssalDroneEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1997;
        }

        @Override
        public String getName() {
            return "AbyssalDroneEntities";
        }

        @Override
        public synchronized Map<String, AbyssalDroneEntities> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AbyssalDroneEntities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AbyssalDroneEntities> items;
        }
    }
}
