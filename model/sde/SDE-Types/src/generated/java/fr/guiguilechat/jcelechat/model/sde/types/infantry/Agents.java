package fr.guiguilechat.jcelechat.model.sde.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class Agents
    extends Infantry
{
    public static final Agents.MetaGroup METAGROUP = new Agents.MetaGroup();

    @Override
    public IMetaGroup<Agents> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Agents>
    {
        public static final String RESOURCE_PATH = "SDE/items/infantry/Agents.yaml";
        private Map<String, Agents> cache = (null);

        @Override
        public IMetaCategory<? super Agents> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  367580;
        }

        @Override
        public String getName() {
            return "Agents";
        }

        @Override
        public synchronized Map<String, Agents> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Agents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Agents> items;
        }
    }
}
