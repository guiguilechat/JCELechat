package fr.guiguilechat.jcelechat.model.sde.items.types.skins;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.SKINs;
import org.yaml.snakeyaml.Yaml;

public class Max1YearSKIN
    extends SKINs
{
    public final static Max1YearSKIN.MetaGroup METAGROUP = new Max1YearSKIN.MetaGroup();

    @Override
    public IMetaGroup<Max1YearSKIN> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Max1YearSKIN>
    {
        public final static String RESOURCE_PATH = "SDE/items/skins/Max1YearSKIN.yaml";
        private Map<String, Max1YearSKIN> cache = (null);

        @Override
        public IMetaCategory<? super Max1YearSKIN> category() {
            return SKINs.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1955;
        }

        @Override
        public String getName() {
            return "Max1YearSKIN";
        }

        @Override
        public synchronized Map<String, Max1YearSKIN> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Max1YearSKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Max1YearSKIN> items;
        }
    }
}
