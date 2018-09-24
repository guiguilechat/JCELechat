package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarCapital
    extends Entity
{
    public final static HiddenZenithMinmatarCapital.MetaGroup METAGROUP = new HiddenZenithMinmatarCapital.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithMinmatarCapital> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithMinmatarCapital>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithMinmatarCapital.yaml";
        private Map<String, HiddenZenithMinmatarCapital> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithMinmatarCapital> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1807;
        }

        @Override
        public String getName() {
            return "HiddenZenithMinmatarCapital";
        }

        @Override
        public synchronized Map<String, HiddenZenithMinmatarCapital> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithMinmatarCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithMinmatarCapital> items;
        }
    }
}
