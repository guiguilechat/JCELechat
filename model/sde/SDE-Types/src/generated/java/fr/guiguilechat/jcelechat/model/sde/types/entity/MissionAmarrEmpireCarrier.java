package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireCarrier
    extends Entity
{
    public static final MissionAmarrEmpireCarrier.MetaGroup METAGROUP = new MissionAmarrEmpireCarrier.MetaGroup();

    @Override
    public IMetaGroup<MissionAmarrEmpireCarrier> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionAmarrEmpireCarrier>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionAmarrEmpireCarrier.yaml";
        private Map<String, MissionAmarrEmpireCarrier> cache = (null);

        @Override
        public IMetaCategory<? super MissionAmarrEmpireCarrier> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  865;
        }

        @Override
        public String getName() {
            return "MissionAmarrEmpireCarrier";
        }

        @Override
        public synchronized Map<String, MissionAmarrEmpireCarrier> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionAmarrEmpireCarrier.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionAmarrEmpireCarrier> types;
        }
    }
}
