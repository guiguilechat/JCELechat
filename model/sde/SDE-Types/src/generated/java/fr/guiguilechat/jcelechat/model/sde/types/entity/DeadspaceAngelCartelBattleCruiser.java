package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
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
        public static final String RESOURCE_PATH = "SDE/types/entity/DeadspaceAngelCartelBattleCruiser.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(DeadspaceAngelCartelBattleCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DeadspaceAngelCartelBattleCruiser> types;
        }
    }
}
