package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSanshaSNationCruiser
    extends Entity
{
    public final static DeadspaceSanshaSNationCruiser.MetaGroup METAGROUP = new DeadspaceSanshaSNationCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSanshaSNationCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSanshaSNationCruiser>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSanshaSNationCruiser.yaml";
        private Map<String, DeadspaceSanshaSNationCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSanshaSNationCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  622;
        }

        @Override
        public String getName() {
            return "DeadspaceSanshaSNationCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceSanshaSNationCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSanshaSNationCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSanshaSNationCruiser> items;
        }
    }
}
