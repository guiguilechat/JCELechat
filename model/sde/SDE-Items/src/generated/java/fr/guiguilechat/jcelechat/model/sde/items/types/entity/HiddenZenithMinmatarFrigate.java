package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarFrigate
    extends Entity
{
    public final static HiddenZenithMinmatarFrigate.MetaGroup METAGROUP = new HiddenZenithMinmatarFrigate.MetaGroup();

    @Override
    public IMetaGroup<HiddenZenithMinmatarFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HiddenZenithMinmatarFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithMinmatarFrigate.yaml";
        private Map<String, HiddenZenithMinmatarFrigate> cache = (null);

        @Override
        public IMetaCategory<? super HiddenZenithMinmatarFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1800;
        }

        @Override
        public String getName() {
            return "HiddenZenithMinmatarFrigate";
        }

        @Override
        public synchronized Map<String, HiddenZenithMinmatarFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithMinmatarFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HiddenZenithMinmatarFrigate> items;
        }
    }
}
