package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithAmarrFrigate
    extends Entity
{
    public final static HiddenZenithAmarrFrigate.MetaGroup METAGROUP = new HiddenZenithAmarrFrigate.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithAmarrFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithAmarrFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithAmarrFrigate.yaml";
        private Map<String, HiddenZenithAmarrFrigate> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithAmarrFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1791;
        }

        @Override
        public String getName() {
            return "HiddenZenithAmarrFrigate";
        }

        @Override
        public synchronized Map<String, HiddenZenithAmarrFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithAmarrFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithAmarrFrigate> items;
        }
    }
}
