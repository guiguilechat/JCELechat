package fr.guiguilechat.jcelechat.model.sde.items.types.owner;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Owner;
import org.yaml.snakeyaml.Yaml;

public class Alliance
    extends Owner
{
    public static final Alliance.MetaGroup METAGROUP = new Alliance.MetaGroup();

    @Override
    public IMetaGroup<Alliance> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Alliance>
    {
        public static final String RESOURCE_PATH = "SDE/items/owner/Alliance.yaml";
        private Map<String, Alliance> cache = (null);

        @Override
        public IMetaCategory<? super Alliance> category() {
            return Owner.METACAT;
        }

        @Override
        public int getGroupId() {
            return  32;
        }

        @Override
        public String getName() {
            return "Alliance";
        }

        @Override
        public synchronized Map<String, Alliance> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Alliance.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Alliance> items;
        }
    }
}
