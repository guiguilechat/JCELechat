package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceAngelCartelBattleCruiser
    extends Entity
{
    public static final DeadspaceAngelCartelBattleCruiser.MetaGroup METAGROUP = new DeadspaceAngelCartelBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceAngelCartelBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceAngelCartelBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/DeadspaceAngelCartelBattleCruiser.yaml";
        private Map<String, DeadspaceAngelCartelBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceAngelCartelBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  593;
        }

        @Override
        public String getName() {
            return "DeadspaceAngelCartelBattleCruiser";
        }

        @Override
        public synchronized Map<String, DeadspaceAngelCartelBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceAngelCartelBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceAngelCartelBattleCruiser> items;
        }
    }
}
