package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MiningFrigate
    extends Entity
{
    public final static MiningFrigate.MetaGroup METAGROUP = new MiningFrigate.MetaGroup();

    @Override
    public IMetaGroup<MiningFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MiningFrigate>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MiningFrigate.yaml";
        private Map<String, MiningFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MiningFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1764;
        }

        @Override
        public String getName() {
            return "MiningFrigate";
        }

        @Override
        public synchronized Map<String, MiningFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MiningFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MiningFrigate> items;
        }
    }
}
