package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationFrigate
    extends Entity
{
    public final static MissionGallenteFederationFrigate.MetaGroup METAGROUP = new MissionGallenteFederationFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionGallenteFederationFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionGallenteFederationFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionGallenteFederationFrigate.yaml";
        private Map<String, MissionGallenteFederationFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionGallenteFederationFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  677;
        }

        @Override
        public String getName() {
            return "MissionGallenteFederationFrigate";
        }

        @Override
        public synchronized Map<String, MissionGallenteFederationFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionGallenteFederationFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGallenteFederationFrigate> items;
        }
    }
}