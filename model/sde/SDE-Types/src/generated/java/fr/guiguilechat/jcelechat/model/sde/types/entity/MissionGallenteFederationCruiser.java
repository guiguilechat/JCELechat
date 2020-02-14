package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationCruiser
    extends Entity
{
    public static final MissionGallenteFederationCruiser.MetaGroup METAGROUP = new MissionGallenteFederationCruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionGallenteFederationCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionGallenteFederationCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionGallenteFederationCruiser.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(MissionGallenteFederationCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGallenteFederationCruiser> types;
        }
    }
}
