package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSanshaSNationFrigate
    extends Entity
{
    public static final DeadspaceSanshaSNationFrigate.MetaGroup METAGROUP = new DeadspaceSanshaSNationFrigate.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSanshaSNationFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSanshaSNationFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSanshaSNationFrigate.yaml";
        private Map<String, DeadspaceSanshaSNationFrigate> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSanshaSNationFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  624;
        }

        @Override
        public String getName() {
            return "DeadspaceSanshaSNationFrigate";
        }

        @Override
        public synchronized Map<String, DeadspaceSanshaSNationFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSanshaSNationFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSanshaSNationFrigate> items;
        }
    }
}
