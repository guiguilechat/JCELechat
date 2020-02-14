package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceBloodRaidersBattleship
    extends Entity
{
    public static final DeadspaceBloodRaidersBattleship.MetaGroup METAGROUP = new DeadspaceBloodRaidersBattleship.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceBloodRaidersBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceBloodRaidersBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceBloodRaidersBattleship.yaml";
        private Map<String, DeadspaceBloodRaidersBattleship> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceBloodRaidersBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  603;
        }

        @Override
        public String getName() {
            return "DeadspaceBloodRaidersBattleship";
        }

        @Override
        public synchronized Map<String, DeadspaceBloodRaidersBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceBloodRaidersBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceBloodRaidersBattleship> types;
        }
    }
}
