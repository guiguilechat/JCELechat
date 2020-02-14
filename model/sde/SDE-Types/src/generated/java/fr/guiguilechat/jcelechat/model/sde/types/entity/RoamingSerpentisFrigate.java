package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingSerpentisFrigate
    extends Entity
{
    public static final RoamingSerpentisFrigate.MetaGroup METAGROUP = new RoamingSerpentisFrigate.MetaGroup();

    @Override
    public IMetaGroup<RoamingSerpentisFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RoamingSerpentisFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/RoamingSerpentisFrigate.yaml";
        private Map<String, RoamingSerpentisFrigate> cache = (null);

        @Override
        public IMetaCategory<? super RoamingSerpentisFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1722;
        }

        @Override
        public String getName() {
            return "RoamingSerpentisFrigate";
        }

        @Override
        public synchronized Map<String, RoamingSerpentisFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(RoamingSerpentisFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RoamingSerpentisFrigate> types;
        }
    }
}
