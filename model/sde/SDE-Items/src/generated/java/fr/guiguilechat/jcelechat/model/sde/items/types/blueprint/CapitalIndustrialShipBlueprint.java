package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CapitalIndustrialShipBlueprint
    extends Blueprint
{
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IndustryBlueprintRank;
    /**
     * Multiplies the job cost for this blueprint type by the specified value
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int IndustryJobCostMultiplier;
    public final static CapitalIndustrialShipBlueprint.MetaGroup METAGROUP = new CapitalIndustrialShipBlueprint.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1955 :
            {
                return IndustryBlueprintRank;
            }
            case  1954 :
            {
                return IndustryJobCostMultiplier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<CapitalIndustrialShipBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CapitalIndustrialShipBlueprint>
    {
        public final static String RESOURCE_PATH = "SDE/items/blueprint/CapitalIndustrialShipBlueprint.yaml";
        private Map<String, CapitalIndustrialShipBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super CapitalIndustrialShipBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  944;
        }

        @Override
        public String getName() {
            return "CapitalIndustrialShipBlueprint";
        }

        @Override
        public synchronized Map<String, CapitalIndustrialShipBlueprint> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CapitalIndustrialShipBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CapitalIndustrialShipBlueprint> items;
        }
    }
}