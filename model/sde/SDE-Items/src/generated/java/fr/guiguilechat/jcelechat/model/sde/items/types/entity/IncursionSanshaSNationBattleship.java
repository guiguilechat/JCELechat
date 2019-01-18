package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IncursionSanshaSNationBattleship
    extends Entity
{
    public static final IncursionSanshaSNationBattleship.MetaGroup METAGROUP = new IncursionSanshaSNationBattleship.MetaGroup();

    @Override
    public IMetaGroup<IncursionSanshaSNationBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IncursionSanshaSNationBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/IncursionSanshaSNationBattleship.yaml";
        private Map<String, IncursionSanshaSNationBattleship> cache = (null);

        @Override
        public IMetaCategory<? super IncursionSanshaSNationBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1056;
        }

        @Override
        public String getName() {
            return "IncursionSanshaSNationBattleship";
        }

        @Override
        public synchronized Map<String, IncursionSanshaSNationBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(IncursionSanshaSNationBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IncursionSanshaSNationBattleship> items;
        }
    }
}
