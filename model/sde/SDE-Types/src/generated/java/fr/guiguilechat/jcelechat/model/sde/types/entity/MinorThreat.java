package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MinorThreat
    extends Entity
{
    public static final MinorThreat.MetaGroup METAGROUP = new MinorThreat.MetaGroup();

    @Override
    public IMetaGroup<MinorThreat> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MinorThreat>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MinorThreat.yaml";
        private Map<String, MinorThreat> cache = (null);

        @Override
        public IMetaCategory<? super MinorThreat> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  286;
        }

        @Override
        public String getName() {
            return "MinorThreat";
        }

        @Override
        public synchronized Map<String, MinorThreat> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MinorThreat.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MinorThreat> types;
        }
    }
}
