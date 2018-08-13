package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class ExoticPlasmaChargeBlueprint
    extends Blueprint
{
    public final static ExoticPlasmaChargeBlueprint.MetaGroup METAGROUP = new ExoticPlasmaChargeBlueprint.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/ExoticPlasmaChargeBlueprint.yaml";
    private static Map<String, ExoticPlasmaChargeBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1993;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ExoticPlasmaChargeBlueprint> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, ExoticPlasmaChargeBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ExoticPlasmaChargeBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ExoticPlasmaChargeBlueprint> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ExoticPlasmaChargeBlueprint>
    {

        @Override
        public MetaCategory<? super ExoticPlasmaChargeBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "ExoticPlasmaChargeBlueprint";
        }

        @Override
        public Collection<ExoticPlasmaChargeBlueprint> items() {
            return (load().values());
        }
    }
}
