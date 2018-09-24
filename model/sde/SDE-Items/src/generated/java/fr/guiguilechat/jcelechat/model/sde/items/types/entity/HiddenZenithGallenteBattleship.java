package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithGallenteBattleship
    extends Entity
{
    public final static HiddenZenithGallenteBattleship.MetaGroup METAGROUP = new HiddenZenithGallenteBattleship.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithGallenteBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithGallenteBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithGallenteBattleship.yaml";
        private Map<String, HiddenZenithGallenteBattleship> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithGallenteBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1795;
        }

        @Override
        public String getName() {
            return "HiddenZenithGallenteBattleship";
        }

        @Override
        public synchronized Map<String, HiddenZenithGallenteBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithGallenteBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithGallenteBattleship> items;
        }
    }
}
