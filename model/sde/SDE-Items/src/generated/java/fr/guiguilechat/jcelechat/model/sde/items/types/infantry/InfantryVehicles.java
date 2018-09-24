package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryVehicles
    extends Infantry
{
    public final static InfantryVehicles.MetaGroup METAGROUP = new InfantryVehicles.MetaGroup();

    @Override
    public IMetaGroup<InfantryVehicles> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfantryVehicles>
    {
        public final static String RESOURCE_PATH = "SDE/items/infantry/InfantryVehicles.yaml";
        private Map<String, InfantryVehicles> cache = (null);

        @Override
        public IMetaCategory<? super InfantryVehicles> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  351210;
        }

        @Override
        public String getName() {
            return "InfantryVehicles";
        }

        @Override
        public synchronized Map<String, InfantryVehicles> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(InfantryVehicles.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InfantryVehicles> items;
        }
    }
}
