package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingSerpentisFrigate
    extends Entity
{
    public static final RoamingSerpentisFrigate.MetaGroup METAGROUP = new RoamingSerpentisFrigate.MetaGroup();

    @Override
    public IMetaGroup<RoamingSerpentisFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RoamingSerpentisFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/RoamingSerpentisFrigate.yaml";
        private Map<String, RoamingSerpentisFrigate> cache = (null);

        @Override
        public IMetaCategory<? super RoamingSerpentisFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1722;
        }

        @Override
        public String getName() {
            return "RoamingSerpentisFrigate";
        }

        @Override
        public synchronized Map<String, RoamingSerpentisFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RoamingSerpentisFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RoamingSerpentisFrigate> items;
        }
    }
}
