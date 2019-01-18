package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationDestroyer
    extends Entity
{
    public static final MissionGallenteFederationDestroyer.MetaGroup METAGROUP = new MissionGallenteFederationDestroyer.MetaGroup();

    @Override
    public IMetaGroup<MissionGallenteFederationDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionGallenteFederationDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionGallenteFederationDestroyer.yaml";
        private Map<String, MissionGallenteFederationDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super MissionGallenteFederationDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  679;
        }

        @Override
        public String getName() {
            return "MissionGallenteFederationDestroyer";
        }

        @Override
        public synchronized Map<String, MissionGallenteFederationDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionGallenteFederationDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGallenteFederationDestroyer> items;
        }
    }
}
