package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryInstallations
    extends Infantry
{
    public static final InfantryInstallations.MetaGroup METAGROUP = new InfantryInstallations.MetaGroup();

    @Override
    public IMetaGroup<InfantryInstallations> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfantryInstallations>
    {
        public static final String RESOURCE_PATH = "SDE/items/infantry/InfantryInstallations.yaml";
        private Map<String, InfantryInstallations> cache = (null);

        @Override
        public IMetaCategory<? super InfantryInstallations> category() {
            return Infantry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  354753;
        }

        @Override
        public String getName() {
            return "InfantryInstallations";
        }

        @Override
        public synchronized Map<String, InfantryInstallations> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(InfantryInstallations.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InfantryInstallations> items;
        }
    }
}
