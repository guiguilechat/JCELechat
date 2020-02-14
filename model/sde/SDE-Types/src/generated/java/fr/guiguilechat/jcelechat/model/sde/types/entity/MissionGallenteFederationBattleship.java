package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
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
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionGallenteFederationBattleship.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(MissionGallenteFederationBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGallenteFederationBattleship> types;
        }
    }
}
