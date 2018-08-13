package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class ScanningUpgradeTimeBlueprint
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
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static ScanningUpgradeTimeBlueprint.MetaGroup METAGROUP = new ScanningUpgradeTimeBlueprint.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/blueprint/ScanningUpgradeTimeBlueprint.yaml";
    private static Map<String, ScanningUpgradeTimeBlueprint> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1955 :
            {
                return IndustryBlueprintRank;
            }
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1239;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ScanningUpgradeTimeBlueprint> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, ScanningUpgradeTimeBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ScanningUpgradeTimeBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ScanningUpgradeTimeBlueprint> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ScanningUpgradeTimeBlueprint>
    {

        @Override
        public MetaCategory<? super ScanningUpgradeTimeBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public String getName() {
            return "ScanningUpgradeTimeBlueprint";
        }

        @Override
        public Collection<ScanningUpgradeTimeBlueprint> items() {
            return (load().values());
        }
    }
}
