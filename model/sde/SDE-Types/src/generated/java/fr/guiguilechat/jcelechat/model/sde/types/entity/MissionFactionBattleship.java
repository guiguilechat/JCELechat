package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionBattleship
    extends Entity
{
    public static final MissionFactionBattleship.MetaGroup METAGROUP = new MissionFactionBattleship.MetaGroup();

    @Override
    public IMetaGroup<MissionFactionBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionFactionBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionFactionBattleship.yaml";
        private Map<String, MissionFactionBattleship> cache = (null);

        @Override
        public IMetaCategory<? super MissionFactionBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  924;
        }

        @Override
        public String getName() {
            return "MissionFactionBattleship";
        }

        @Override
        public synchronized Map<String, MissionFactionBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionFactionBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionFactionBattleship> types;
        }
    }
}
