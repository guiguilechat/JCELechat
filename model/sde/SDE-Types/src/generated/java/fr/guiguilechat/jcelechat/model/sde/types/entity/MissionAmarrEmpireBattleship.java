package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireBattleship
    extends Entity
{
    public static final MissionAmarrEmpireBattleship.MetaGroup METAGROUP = new MissionAmarrEmpireBattleship.MetaGroup();

    @Override
    public IMetaGroup<MissionAmarrEmpireBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionAmarrEmpireBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionAmarrEmpireBattleship.yaml";
        private Map<String, MissionAmarrEmpireBattleship> cache = (null);

        @Override
        public IMetaCategory<? super MissionAmarrEmpireBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  667;
        }

        @Override
        public String getName() {
            return "MissionAmarrEmpireBattleship";
        }

        @Override
        public synchronized Map<String, MissionAmarrEmpireBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionAmarrEmpireBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionAmarrEmpireBattleship> types;
        }
    }
}
