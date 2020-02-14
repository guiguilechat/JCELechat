package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingBloodRaiderFrigate
    extends Entity
{
    public static final RoamingBloodRaiderFrigate.MetaGroup METAGROUP = new RoamingBloodRaiderFrigate.MetaGroup();

    @Override
    public IMetaGroup<RoamingBloodRaiderFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RoamingBloodRaiderFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/RoamingBloodRaiderFrigate.yaml";
        private Map<String, RoamingBloodRaiderFrigate> cache = (null);

        @Override
        public IMetaCategory<? super RoamingBloodRaiderFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1734;
        }

        @Override
        public String getName() {
            return "RoamingBloodRaiderFrigate";
        }

        @Override
        public synchronized Map<String, RoamingBloodRaiderFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(RoamingBloodRaiderFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RoamingBloodRaiderFrigate> types;
        }
    }
}
