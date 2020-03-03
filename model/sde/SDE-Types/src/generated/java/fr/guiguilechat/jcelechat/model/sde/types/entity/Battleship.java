package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class Battleship
    extends Entity
{
    public static final Battleship.MetaGroup METAGROUP = new Battleship.MetaGroup();

    @Override
    public IMetaGroup<Battleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Battleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/Battleship.yaml";
        private Map<String, Battleship> cache = (null);

        @Override
        public IMetaCategory<? super Battleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1814;
        }

        @Override
        public String getName() {
            return "Battleship";
        }

        @Override
        public synchronized Map<String, Battleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Battleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Battleship> types;
        }
    }
}
