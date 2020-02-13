package fr.guiguilechat.jcelechat.model.sde.types.skins;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.SKINs;
import org.yaml.snakeyaml.Yaml;

public class PermanentSKIN
    extends SKINs
{
    public static final PermanentSKIN.MetaGroup METAGROUP = new PermanentSKIN.MetaGroup();

    @Override
    public IMetaGroup<PermanentSKIN> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PermanentSKIN>
    {
        public static final String RESOURCE_PATH = "SDE/items/skins/PermanentSKIN.yaml";
        private Map<String, PermanentSKIN> cache = (null);

        @Override
        public IMetaCategory<? super PermanentSKIN> category() {
            return SKINs.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1950;
        }

        @Override
        public String getName() {
            return "PermanentSKIN";
        }

        @Override
        public synchronized Map<String, PermanentSKIN> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PermanentSKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PermanentSKIN> items;
        }
    }
}
