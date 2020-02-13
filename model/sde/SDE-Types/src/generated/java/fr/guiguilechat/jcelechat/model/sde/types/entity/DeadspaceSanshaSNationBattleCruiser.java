package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSanshaSNationBattleCruiser
    extends Entity
{
    public static final DeadspaceSanshaSNationBattleCruiser.MetaGroup METAGROUP = new DeadspaceSanshaSNationBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSanshaSNationBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSanshaSNationBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceSanshaSNationBattleCruiser.yaml";
        private Map<String, DeadspaceSanshaSNationBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSanshaSNationBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  620;
        }

        @Override
        public String getName() {
            return "DeadspaceSanshaSNationBattleCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceSanshaSNationBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSanshaSNationBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSanshaSNationBattleCruiser> items;
        }
    }
}
