package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithCaldariCruiser
    extends Entity
{
    public static final HiddenZenithCaldariCruiser.MetaGroup METAGROUP = new HiddenZenithCaldariCruiser.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithCaldariCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithCaldariCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/HiddenZenithCaldariCruiser.yaml";
        private Map<String, HiddenZenithCaldariCruiser> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithCaldariCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1793;
        }

        @Override
        public String getName() {
            return "HiddenZenithCaldariCruiser";
        }

        @Override
        public synchronized Map<String, HiddenZenithCaldariCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HiddenZenithCaldariCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithCaldariCruiser> types;
        }
    }
}
