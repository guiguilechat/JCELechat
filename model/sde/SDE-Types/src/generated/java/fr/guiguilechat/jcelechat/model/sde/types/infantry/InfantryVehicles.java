package fr.guiguilechat.jcelechat.model.sde.types.infantry;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Infantry;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class InfantryVehicles
    extends Infantry
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final InfantryVehicles.MetaGroup METAGROUP = new InfantryVehicles.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<InfantryVehicles> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InfantryVehicles>
    {
        public static final String RESOURCE_PATH = "SDE/types/infantry/InfantryVehicles.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(InfantryVehicles.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InfantryVehicles> types;
        }
    }
}
