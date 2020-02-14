package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithCaldariCapital
    extends Entity
{
    public static final HiddenZenithCaldariCapital.MetaGroup METAGROUP = new HiddenZenithCaldariCapital.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithCaldariCapital> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithCaldariCapital>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/HiddenZenithCaldariCapital.yaml";
        private Map<String, HiddenZenithCaldariCapital> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithCaldariCapital> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1805;
        }

        @Override
        public String getName() {
            return "HiddenZenithCaldariCapital";
        }

        @Override
        public synchronized Map<String, HiddenZenithCaldariCapital> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HiddenZenithCaldariCapital.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithCaldariCapital> types;
        }
    }
}
