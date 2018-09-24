package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class PirateDrone
    extends Entity
{
    public final static PirateDrone.MetaGroup METAGROUP = new PirateDrone.MetaGroup();

    @Override
    public IMetaGroup<PirateDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PirateDrone>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/PirateDrone.yaml";
        private Map<String, PirateDrone> cache = (null);

        @Override
        public IMetaCategory<? super PirateDrone> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  185;
        }

        @Override
        public String getName() {
            return "PirateDrone";
        }

        @Override
        public synchronized Map<String, PirateDrone> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PirateDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PirateDrone> items;
        }
    }
}
