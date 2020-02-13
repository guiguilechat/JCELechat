package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithGallenteCapital
    extends Entity
{
    public static final HiddenZenithGallenteCapital.MetaGroup METAGROUP = new HiddenZenithGallenteCapital.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithGallenteCapital> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithGallenteCapital>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/HiddenZenithGallenteCapital.yaml";
        private Map<String, HiddenZenithGallenteCapital> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithGallenteCapital> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1806;
        }

        @Override
        public String getName() {
            return "HiddenZenithGallenteCapital";
        }

        @Override
        public synchronized Map<String, HiddenZenithGallenteCapital> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithGallenteCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithGallenteCapital> items;
        }
    }
}
