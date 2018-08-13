package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CapacitorBoosterChargeBlueprint
    extends Blueprint
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    public final static CapacitorBoosterChargeBlueprint.MetaGroup METAGROUP = new CapacitorBoosterChargeBlueprint.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/CapacitorBoosterChargeBlueprint.yaml";
    private static Map<String, CapacitorBoosterChargeBlueprint> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1955 :
            {
                return IndustryBlueprintRank;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  169;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CapacitorBoosterChargeBlueprint> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, CapacitorBoosterChargeBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CapacitorBoosterChargeBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, CapacitorBoosterChargeBlueprint> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<CapacitorBoosterChargeBlueprint>
    {

        @Override
        public MetaCategory<? super CapacitorBoosterChargeBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "CapacitorBoosterChargeBlueprint";
        }

        @Override
        public Collection<CapacitorBoosterChargeBlueprint> items() {
            return (load().values());
        }
    }
}
