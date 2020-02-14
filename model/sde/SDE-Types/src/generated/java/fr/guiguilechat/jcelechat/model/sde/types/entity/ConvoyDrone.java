package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class ConvoyDrone
    extends Entity
{
    public static final ConvoyDrone.MetaGroup METAGROUP = new ConvoyDrone.MetaGroup();

    @Override
    public IMetaGroup<ConvoyDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ConvoyDrone>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/ConvoyDrone.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(ConvoyDrone.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ConvoyDrone> types;
        }
    }
}
