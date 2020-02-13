package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingBloodRaiderCruiser
    extends Entity
{
    public static final RoamingBloodRaiderCruiser.MetaGroup METAGROUP = new RoamingBloodRaiderCruiser.MetaGroup();

    @Override
    public IMetaGroup<RoamingBloodRaiderCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RoamingBloodRaiderCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/RoamingBloodRaiderCruiser.yaml";
        private Map<String, RoamingBloodRaiderCruiser> cache = (null);

        @Override
        public IMetaCategory<? super RoamingBloodRaiderCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1735;
        }

        @Override
        public String getName() {
            return "RoamingBloodRaiderCruiser";
        }

        @Override
        public synchronized Map<String, RoamingBloodRaiderCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RoamingBloodRaiderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RoamingBloodRaiderCruiser> items;
        }
    }
}
