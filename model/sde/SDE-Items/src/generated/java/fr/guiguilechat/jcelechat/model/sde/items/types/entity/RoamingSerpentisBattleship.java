package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingSerpentisBattleship
    extends Entity
{
    public static final RoamingSerpentisBattleship.MetaGroup METAGROUP = new RoamingSerpentisBattleship.MetaGroup();

    @Override
    public IMetaGroup<RoamingSerpentisBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RoamingSerpentisBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/RoamingSerpentisBattleship.yaml";
        private Map<String, RoamingSerpentisBattleship> cache = (null);

        @Override
        public IMetaCategory<? super RoamingSerpentisBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1720;
        }

        @Override
        public String getName() {
            return "RoamingSerpentisBattleship";
        }

        @Override
        public synchronized Map<String, RoamingSerpentisBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RoamingSerpentisBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RoamingSerpentisBattleship> items;
        }
    }
}
