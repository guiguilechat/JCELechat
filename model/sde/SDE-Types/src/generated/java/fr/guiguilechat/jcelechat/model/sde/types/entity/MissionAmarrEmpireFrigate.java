package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireFrigate
    extends Entity
{
    public static final MissionAmarrEmpireFrigate.MetaGroup METAGROUP = new MissionAmarrEmpireFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionAmarrEmpireFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionAmarrEmpireFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionAmarrEmpireFrigate.yaml";
        private Map<String, MissionAmarrEmpireFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionAmarrEmpireFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  665;
        }

        @Override
        public String getName() {
            return "MissionAmarrEmpireFrigate";
        }

        @Override
        public synchronized Map<String, MissionAmarrEmpireFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionAmarrEmpireFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionAmarrEmpireFrigate> types;
        }
    }
}
