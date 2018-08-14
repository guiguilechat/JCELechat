package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class PrecursorWeaponBlueprint
    extends Blueprint
{
    public final static PrecursorWeaponBlueprint.MetaGroup METAGROUP = new PrecursorWeaponBlueprint.MetaGroup();

    @Override
    public IMetaGroup<PrecursorWeaponBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PrecursorWeaponBlueprint>
    {
        public final static String RESOURCE_PATH = "SDE/items/blueprint/PrecursorWeaponBlueprint.yaml";
        private Map<String, PrecursorWeaponBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super PrecursorWeaponBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1990;
        }

        @Override
        public String getName() {
            return "PrecursorWeaponBlueprint";
        }

        @Override
        public synchronized Map<String, PrecursorWeaponBlueprint> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PrecursorWeaponBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PrecursorWeaponBlueprint> items;
        }
    }
}
