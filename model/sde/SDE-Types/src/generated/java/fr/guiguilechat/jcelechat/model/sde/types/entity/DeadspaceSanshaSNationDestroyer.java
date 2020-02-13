package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSanshaSNationDestroyer
    extends Entity
{
    public static final DeadspaceSanshaSNationDestroyer.MetaGroup METAGROUP = new DeadspaceSanshaSNationDestroyer.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSanshaSNationDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSanshaSNationDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSanshaSNationDestroyer.yaml";
        private Map<String, DeadspaceSanshaSNationDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSanshaSNationDestroyer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  623;
        }

        @Override
        public String getName() {
            return "DeadspaceSanshaSNationDestroyer";
        }

        @Override
        public synchronized Map<String, DeadspaceSanshaSNationDestroyer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSanshaSNationDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSanshaSNationDestroyer> items;
        }
    }
}
