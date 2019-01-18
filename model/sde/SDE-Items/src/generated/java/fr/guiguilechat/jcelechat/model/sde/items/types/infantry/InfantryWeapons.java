package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryWeapons
    extends Infantry
{
    public static final InfantryWeapons.MetaGroup METAGROUP = new InfantryWeapons.MetaGroup();

    @Override
    public IMetaGroup<InfantryWeapons> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfantryWeapons>
    {
        public static final String RESOURCE_PATH = "SDE/items/infantry/InfantryWeapons.yaml";
        private Map<String, InfantryWeapons> cache = (null);

        @Override
        public IMetaCategory<? super InfantryWeapons> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  350858;
        }

        @Override
        public String getName() {
            return "InfantryWeapons";
        }

        @Override
        public synchronized Map<String, InfantryWeapons> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(InfantryWeapons.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InfantryWeapons> items;
        }
    }
}
