package fr.guiguilechat.jcelechat.model.sde.items.types.skins;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.SKINs;
import org.yaml.snakeyaml.Yaml;

public class Max7DaySKIN
    extends SKINs
{
    public final static Max7DaySKIN.MetaGroup METAGROUP = new Max7DaySKIN.MetaGroup();

    @Override
    public IMetaGroup<Max7DaySKIN> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Max7DaySKIN>
    {
        public final static String RESOURCE_PATH = "SDE/items/skins/Max7DaySKIN.yaml";
        private Map<String, Max7DaySKIN> cache = (null);

        @Override
        public IMetaCategory<? super Max7DaySKIN> category() {
            return SKINs.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1952;
        }

        @Override
        public String getName() {
            return "Max7DaySKIN";
        }

        @Override
        public synchronized Map<String, Max7DaySKIN> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Max7DaySKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Max7DaySKIN> items;
        }
    }
}
