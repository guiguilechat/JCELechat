package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class ConvoyDrone
    extends Entity
{
    public final static ConvoyDrone.MetaGroup METAGROUP = new ConvoyDrone.MetaGroup();

    @Override
    public IMetaGroup<ConvoyDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ConvoyDrone>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/ConvoyDrone.yaml";
        private Map<String, ConvoyDrone> cache = (null);

        @Override
        public IMetaCategory<? super ConvoyDrone> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  298;
        }

        @Override
        public String getName() {
            return "ConvoyDrone";
        }

        @Override
        public synchronized Map<String, ConvoyDrone> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(ConvoyDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ConvoyDrone> items;
        }
    }
}
