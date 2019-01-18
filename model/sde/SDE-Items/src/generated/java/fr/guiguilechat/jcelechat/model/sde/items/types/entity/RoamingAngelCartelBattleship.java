package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingAngelCartelBattleship
    extends Entity
{
    public static final RoamingAngelCartelBattleship.MetaGroup METAGROUP = new RoamingAngelCartelBattleship.MetaGroup();

    @Override
    public IMetaGroup<RoamingAngelCartelBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RoamingAngelCartelBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/RoamingAngelCartelBattleship.yaml";
        private Map<String, RoamingAngelCartelBattleship> cache = (null);

        @Override
        public IMetaCategory<? super RoamingAngelCartelBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1727;
        }

        @Override
        public String getName() {
            return "RoamingAngelCartelBattleship";
        }

        @Override
        public synchronized Map<String, RoamingAngelCartelBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RoamingAngelCartelBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RoamingAngelCartelBattleship> items;
        }
    }
}
