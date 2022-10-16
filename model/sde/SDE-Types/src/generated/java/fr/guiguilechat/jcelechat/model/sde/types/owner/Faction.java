package fr.guiguilechat.jcelechat.model.sde.types.owner;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Owner;
import org.yaml.snakeyaml.Yaml;

public class Faction
    extends Owner
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Faction.MetaGroup METAGROUP = new Faction.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Faction> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Faction>
    {
        public static final String RESOURCE_PATH = "SDE/types/owner/Faction.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(Faction.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Faction> types;
        }
    }
}
