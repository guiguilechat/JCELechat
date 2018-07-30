package fr.guiguilechat.jcelechat.model.sde.items.types.infrastructureupgrades;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.InfrastructureUpgrades;
import org.yaml.snakeyaml.Yaml;

public class StrategicUpgrades
    extends InfrastructureUpgrades
{
    /**
     * The minimum required sovereignty index level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DevIndexSovereignty;
    /**
     * The sum of this attribute on the claim markers, Infrastructure hub, and each upgrade is the systems base cost. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SovBillSystemCost;
    public final static String RESOURCE_PATH = "SDE/items/infrastructureupgrades/StrategicUpgrades.yaml";
    private static LinkedHashMap<String, StrategicUpgrades> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1615 :
            {
                return DevIndexSovereignty;
            }
            case  1603 :
            {
                return SovBillSystemCost;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1016;
    }

    @Override
    public Class<?> getGroup() {
        return StrategicUpgrades.class;
    }

    public static synchronized LinkedHashMap<String, StrategicUpgrades> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StrategicUpgrades.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StrategicUpgrades> items;
    }
}
