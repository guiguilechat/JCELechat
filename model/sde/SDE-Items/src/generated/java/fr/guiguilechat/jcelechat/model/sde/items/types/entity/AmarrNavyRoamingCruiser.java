package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrNavyRoamingCruiser
    extends Entity
{
    public final static AmarrNavyRoamingCruiser.MetaGroup METAGROUP = new AmarrNavyRoamingCruiser.MetaGroup();

    @Override
    public IMetaGroup<AmarrNavyRoamingCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AmarrNavyRoamingCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/AmarrNavyRoamingCruiser.yaml";
        private Map<String, AmarrNavyRoamingCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AmarrNavyRoamingCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1411;
        }

        @Override
        public String getName() {
            return "AmarrNavyRoamingCruiser";
        }

        @Override
        public synchronized Map<String, AmarrNavyRoamingCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AmarrNavyRoamingCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AmarrNavyRoamingCruiser> items;
        }
    }
}
