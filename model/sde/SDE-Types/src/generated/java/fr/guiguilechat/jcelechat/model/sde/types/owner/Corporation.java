package fr.guiguilechat.jcelechat.model.sde.types.owner;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Owner;
import org.yaml.snakeyaml.Yaml;

public class Corporation
    extends Owner
{
    public static final Corporation.MetaGroup METAGROUP = new Corporation.MetaGroup();

    @Override
    public IMetaGroup<Corporation> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Corporation>
    {
        public static final String RESOURCE_PATH = "SDE/items/owner/Corporation.yaml";
        private Map<String, Corporation> cache = (null);

        @Override
        public IMetaCategory<? super Corporation> category() {
            return Owner.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2;
        }

        @Override
        public String getName() {
            return "Corporation";
        }

        @Override
        public synchronized Map<String, Corporation> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Corporation.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Corporation> items;
        }
    }
}
