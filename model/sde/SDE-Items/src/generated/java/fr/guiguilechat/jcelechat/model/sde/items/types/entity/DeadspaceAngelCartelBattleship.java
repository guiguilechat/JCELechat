package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceAngelCartelBattleship
    extends Entity
{
    public final static DeadspaceAngelCartelBattleship.MetaGroup METAGROUP = new DeadspaceAngelCartelBattleship.MetaGroup();

    @Override
    public IMetaGroup<DeadspaceAngelCartelBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DeadspaceAngelCartelBattleship>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceAngelCartelBattleship.yaml";
        private Map<String, DeadspaceAngelCartelBattleship> cache = (null);

        @Override
        public IMetaCategory<? super DeadspaceAngelCartelBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  594;
        }

        @Override
        public String getName() {
            return "DeadspaceAngelCartelBattleship";
        }

        @Override
        public synchronized Map<String, DeadspaceAngelCartelBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DeadspaceAngelCartelBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceAngelCartelBattleship> items;
        }
    }
}
