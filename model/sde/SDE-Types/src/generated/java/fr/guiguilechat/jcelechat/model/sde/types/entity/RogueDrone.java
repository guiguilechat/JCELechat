package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RogueDrone
    extends Entity
{
    public static final RogueDrone.MetaGroup METAGROUP = new RogueDrone.MetaGroup();

    @Override
    public IMetaGroup<RogueDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RogueDrone>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/RogueDrone.yaml";
        private Map<String, RogueDrone> cache = (null);

        @Override
        public IMetaCategory<? super RogueDrone> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  287;
        }

        @Override
        public String getName() {
            return "RogueDrone";
        }

        @Override
        public synchronized Map<String, RogueDrone> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RogueDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RogueDrone> items;
        }
    }
}
