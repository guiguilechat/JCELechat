package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSanshaSNationBattleship
    extends Entity
{
    public static final DeadspaceSanshaSNationBattleship.MetaGroup METAGROUP = new DeadspaceSanshaSNationBattleship.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceSanshaSNationBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceSanshaSNationBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceSanshaSNationBattleship.yaml";
        private Map<String, DeadspaceSanshaSNationBattleship> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceSanshaSNationBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  621;
        }

        @Override
        public String getName() {
            return "DeadspaceSanshaSNationBattleship";
        }

        @Override
        public synchronized Map<String, DeadspaceSanshaSNationBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceSanshaSNationBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceSanshaSNationBattleship> types;
        }
    }
}
