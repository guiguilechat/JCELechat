package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationBattleship
    extends Entity
{
    public static final MissionGallenteFederationBattleship.MetaGroup METAGROUP = new MissionGallenteFederationBattleship.MetaGroup();

    @Override
    public IMetaGroup<MissionGallenteFederationBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionGallenteFederationBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/MissionGallenteFederationBattleship.yaml";
        private Map<String, MissionGallenteFederationBattleship> cache = (null);

        @Override
        public IMetaCategory<? super MissionGallenteFederationBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  680;
        }

        @Override
        public String getName() {
            return "MissionGallenteFederationBattleship";
        }

        @Override
        public synchronized Map<String, MissionGallenteFederationBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionGallenteFederationBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGallenteFederationBattleship> items;
        }
    }
}
