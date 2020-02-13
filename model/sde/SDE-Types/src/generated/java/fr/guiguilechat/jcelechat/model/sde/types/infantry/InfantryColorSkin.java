package fr.guiguilechat.jcelechat.model.sde.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryColorSkin
    extends Infantry
{
    public static final InfantryColorSkin.MetaGroup METAGROUP = new InfantryColorSkin.MetaGroup();

    @Override
    public IMetaGroup<InfantryColorSkin> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfantryColorSkin>
    {
        public static final String RESOURCE_PATH = "SDE/items/infantry/InfantryColorSkin.yaml";
        private Map<String, InfantryColorSkin> cache = (null);

        @Override
        public IMetaCategory<? super InfantryColorSkin> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  368726;
        }

        @Override
        public String getName() {
            return "InfantryColorSkin";
        }

        @Override
        public synchronized Map<String, InfantryColorSkin> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(InfantryColorSkin.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InfantryColorSkin> items;
        }
    }
}
