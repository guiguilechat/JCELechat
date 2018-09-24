package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class LCODrone
    extends Entity
{
    public final static LCODrone.MetaGroup METAGROUP = new LCODrone.MetaGroup();

    @Override
    public IMetaGroup<LCODrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LCODrone>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/LCODrone.yaml";
        private Map<String, LCODrone> cache = (null);

        @Override
        public IMetaCategory<? super LCODrone> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  279;
        }

        @Override
        public String getName() {
            return "LCODrone";
        }

        @Override
        public synchronized Map<String, LCODrone> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(LCODrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, LCODrone> items;
        }
    }
}
