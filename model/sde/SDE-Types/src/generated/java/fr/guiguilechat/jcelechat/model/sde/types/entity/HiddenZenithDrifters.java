package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithDrifters
    extends Entity
{
    public static final HiddenZenithDrifters.MetaGroup METAGROUP = new HiddenZenithDrifters.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithDrifters> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithDrifters>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/HiddenZenithDrifters.yaml";
        private Map<String, HiddenZenithDrifters> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithDrifters> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1788;
        }

        @Override
        public String getName() {
            return "HiddenZenithDrifters";
        }

        @Override
        public synchronized Map<String, HiddenZenithDrifters> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HiddenZenithDrifters.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithDrifters> types;
        }
    }
}
