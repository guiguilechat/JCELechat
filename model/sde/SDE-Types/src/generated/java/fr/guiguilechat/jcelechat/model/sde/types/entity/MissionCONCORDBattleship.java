package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCONCORDBattleship
    extends Entity
{
    public static final MissionCONCORDBattleship.MetaGroup METAGROUP = new MissionCONCORDBattleship.MetaGroup();

    @Override
    public IMetaGroup<MissionCONCORDBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCONCORDBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionCONCORDBattleship.yaml";
        private Map<String, MissionCONCORDBattleship> cache = (null);

        @Override
        public IMetaCategory<? super MissionCONCORDBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  697;
        }

        @Override
        public String getName() {
            return "MissionCONCORDBattleship";
        }

        @Override
        public synchronized Map<String, MissionCONCORDBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionCONCORDBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCONCORDBattleship> types;
        }
    }
}
