package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingBloodRaiderBattleship
    extends Entity
{
    public final static RoamingBloodRaiderBattleship.MetaGroup METAGROUP = new RoamingBloodRaiderBattleship.MetaGroup();

    @Override
    public IMetaGroup<RoamingBloodRaiderBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RoamingBloodRaiderBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/RoamingBloodRaiderBattleship.yaml";
        private Map<String, RoamingBloodRaiderBattleship> cache = (null);

        @Override
        public IMetaCategory<? super RoamingBloodRaiderBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1737;
        }

        @Override
        public String getName() {
            return "RoamingBloodRaiderBattleship";
        }

        @Override
        public synchronized Map<String, RoamingBloodRaiderBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RoamingBloodRaiderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RoamingBloodRaiderBattleship> items;
        }
    }
}
