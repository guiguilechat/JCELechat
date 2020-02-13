package fr.guiguilechat.jcelechat.model.sde.types.skins;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.SKINs;
import org.yaml.snakeyaml.Yaml;

public class Max90DaySKIN
    extends SKINs
{
    public static final Max90DaySKIN.MetaGroup METAGROUP = new Max90DaySKIN.MetaGroup();

    @Override
    public IMetaGroup<Max90DaySKIN> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Max90DaySKIN>
    {
        public static final String RESOURCE_PATH = "SDE/items/skins/Max90DaySKIN.yaml";
        private Map<String, Max90DaySKIN> cache = (null);

        @Override
        public IMetaCategory<? super Max90DaySKIN> category() {
            return SKINs.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1954;
        }

        @Override
        public String getName() {
            return "Max90DaySKIN";
        }

        @Override
        public synchronized Map<String, Max90DaySKIN> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Max90DaySKIN.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Max90DaySKIN> items;
        }
    }
}
