package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithGallenteCruiser
    extends Entity
{
    public final static HiddenZenithGallenteCruiser.MetaGroup METAGROUP = new HiddenZenithGallenteCruiser.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithGallenteCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithGallenteCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithGallenteCruiser.yaml";
        private Map<String, HiddenZenithGallenteCruiser> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithGallenteCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1796;
        }

        @Override
        public String getName() {
            return "HiddenZenithGallenteCruiser";
        }

        @Override
        public synchronized Map<String, HiddenZenithGallenteCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithGallenteCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithGallenteCruiser> items;
        }
    }
}
