package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithGallenteFrigate
    extends Entity
{
    public static final HiddenZenithGallenteFrigate.MetaGroup METAGROUP = new HiddenZenithGallenteFrigate.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithGallenteFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithGallenteFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/HiddenZenithGallenteFrigate.yaml";
        private Map<String, HiddenZenithGallenteFrigate> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithGallenteFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1797;
        }

        @Override
        public String getName() {
            return "HiddenZenithGallenteFrigate";
        }

        @Override
        public synchronized Map<String, HiddenZenithGallenteFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithGallenteFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithGallenteFrigate> items;
        }
    }
}
