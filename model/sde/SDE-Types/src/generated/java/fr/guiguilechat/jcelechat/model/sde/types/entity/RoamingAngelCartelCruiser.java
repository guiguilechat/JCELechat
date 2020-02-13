package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingAngelCartelCruiser
    extends Entity
{
    public static final RoamingAngelCartelCruiser.MetaGroup METAGROUP = new RoamingAngelCartelCruiser.MetaGroup();

    @Override
    public IMetaGroup<RoamingAngelCartelCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RoamingAngelCartelCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/RoamingAngelCartelCruiser.yaml";
        private Map<String, RoamingAngelCartelCruiser> cache = (null);

        @Override
        public IMetaCategory<? super RoamingAngelCartelCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1728;
        }

        @Override
        public String getName() {
            return "RoamingAngelCartelCruiser";
        }

        @Override
        public synchronized Map<String, RoamingAngelCartelCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RoamingAngelCartelCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RoamingAngelCartelCruiser> items;
        }
    }
}
