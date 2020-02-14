package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class AdvancedExoticPlasmaChargeBlueprint
    extends Blueprint
{
    public static final AdvancedExoticPlasmaChargeBlueprint.MetaGroup METAGROUP = new AdvancedExoticPlasmaChargeBlueprint.MetaGroup();

    @Override
    public IMetaGroup<AdvancedExoticPlasmaChargeBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AdvancedExoticPlasmaChargeBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/AdvancedExoticPlasmaChargeBlueprint.yaml";
        private Map<String, AdvancedExoticPlasmaChargeBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super AdvancedExoticPlasmaChargeBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1994;
        }

        @Override
        public String getName() {
            return "AdvancedExoticPlasmaChargeBlueprint";
        }

        @Override
        public synchronized Map<String, AdvancedExoticPlasmaChargeBlueprint> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AdvancedExoticPlasmaChargeBlueprint.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AdvancedExoticPlasmaChargeBlueprint> types;
        }
    }
}
