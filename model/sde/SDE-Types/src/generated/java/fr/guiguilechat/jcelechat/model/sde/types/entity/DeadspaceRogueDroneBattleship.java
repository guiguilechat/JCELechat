package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceRogueDroneBattleship
    extends Entity
{
    public static final DeadspaceRogueDroneBattleship.MetaGroup METAGROUP = new DeadspaceRogueDroneBattleship.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceRogueDroneBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceRogueDroneBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceRogueDroneBattleship.yaml";
        private Map<String, DeadspaceRogueDroneBattleship> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceRogueDroneBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  802;
        }

        @Override
        public String getName() {
            return "DeadspaceRogueDroneBattleship";
        }

        @Override
        public synchronized Map<String, DeadspaceRogueDroneBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceRogueDroneBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceRogueDroneBattleship> types;
        }
    }
}
