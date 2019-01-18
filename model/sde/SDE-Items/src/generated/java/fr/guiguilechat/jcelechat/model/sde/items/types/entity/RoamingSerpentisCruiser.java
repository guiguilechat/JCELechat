package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingSerpentisCruiser
    extends Entity
{
    public static final RoamingSerpentisCruiser.MetaGroup METAGROUP = new RoamingSerpentisCruiser.MetaGroup();

    @Override
    public IMetaGroup<RoamingSerpentisCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RoamingSerpentisCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/RoamingSerpentisCruiser.yaml";
        private Map<String, RoamingSerpentisCruiser> cache = (null);

        @Override
        public IMetaCategory<? super RoamingSerpentisCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1721;
        }

        @Override
        public String getName() {
            return "RoamingSerpentisCruiser";
        }

        @Override
        public synchronized Map<String, RoamingSerpentisCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RoamingSerpentisCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RoamingSerpentisCruiser> items;
        }
    }
}
