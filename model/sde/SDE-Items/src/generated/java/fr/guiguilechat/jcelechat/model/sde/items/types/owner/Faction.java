package fr.guiguilechat.jcelechat.model.sde.items.types.owner;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Owner;
import org.yaml.snakeyaml.Yaml;

public class Faction
    extends Owner
{
    public final static Faction.MetaGroup METAGROUP = new Faction.MetaGroup();

    @Override
    public IMetaGroup<Faction> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Faction>
    {
        public final static String RESOURCE_PATH = "SDE/items/owner/Faction.yaml";
        private Map<String, Faction> cache = (null);

        @Override
        public IMetaCategory<? super Faction> category() {
            return Owner.METACAT;
        }

        @Override
        public int getGroupId() {
            return  19;
        }

        @Override
        public String getName() {
            return "Faction";
        }

        @Override
        public synchronized Map<String, Faction> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Faction.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Faction> items;
        }
    }
}
