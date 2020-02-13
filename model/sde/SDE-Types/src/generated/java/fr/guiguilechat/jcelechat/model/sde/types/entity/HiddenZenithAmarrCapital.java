package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithAmarrCapital
    extends Entity
{
    public static final HiddenZenithAmarrCapital.MetaGroup METAGROUP = new HiddenZenithAmarrCapital.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithAmarrCapital> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithAmarrCapital>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/HiddenZenithAmarrCapital.yaml";
        private Map<String, HiddenZenithAmarrCapital> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithAmarrCapital> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1804;
        }

        @Override
        public String getName() {
            return "HiddenZenithAmarrCapital";
        }

        @Override
        public synchronized Map<String, HiddenZenithAmarrCapital> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithAmarrCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithAmarrCapital> items;
        }
    }
}
