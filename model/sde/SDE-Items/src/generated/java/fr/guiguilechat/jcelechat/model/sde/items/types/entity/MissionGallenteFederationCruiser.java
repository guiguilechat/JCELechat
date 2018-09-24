package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationCruiser
    extends Entity
{
    public final static MissionGallenteFederationCruiser.MetaGroup METAGROUP = new MissionGallenteFederationCruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionGallenteFederationCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionGallenteFederationCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionGallenteFederationCruiser.yaml";
        private Map<String, MissionGallenteFederationCruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionGallenteFederationCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  678;
        }

        @Override
        public String getName() {
            return "MissionGallenteFederationCruiser";
        }

        @Override
        public synchronized Map<String, MissionGallenteFederationCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionGallenteFederationCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGallenteFederationCruiser> items;
        }
    }
}
