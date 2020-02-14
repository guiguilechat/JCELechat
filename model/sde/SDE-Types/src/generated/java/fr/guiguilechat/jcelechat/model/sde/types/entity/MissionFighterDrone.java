package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFighterDrone
    extends Entity
{
    public static final MissionFighterDrone.MetaGroup METAGROUP = new MissionFighterDrone.MetaGroup();

    @Override
    public IMetaGroup<MissionFighterDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionFighterDrone>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionFighterDrone.yaml";
        private Map<String, MissionFighterDrone> cache = (null);

        @Override
        public IMetaCategory<? super MissionFighterDrone> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  861;
        }

        @Override
        public String getName() {
            return "MissionFighterDrone";
        }

        @Override
        public synchronized Map<String, MissionFighterDrone> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionFighterDrone.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionFighterDrone> types;
        }
    }
}
