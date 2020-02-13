package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithAmarrCruiser
    extends Entity
{
    public static final HiddenZenithAmarrCruiser.MetaGroup METAGROUP = new HiddenZenithAmarrCruiser.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithAmarrCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithAmarrCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/HiddenZenithAmarrCruiser.yaml";
        private Map<String, HiddenZenithAmarrCruiser> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithAmarrCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1790;
        }

        @Override
        public String getName() {
            return "HiddenZenithAmarrCruiser";
        }

        @Override
        public synchronized Map<String, HiddenZenithAmarrCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithAmarrCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithAmarrCruiser> items;
        }
    }
}
