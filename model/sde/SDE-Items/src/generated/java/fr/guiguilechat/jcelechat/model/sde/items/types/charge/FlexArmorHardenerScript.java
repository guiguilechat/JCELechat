package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class FlexArmorHardenerScript
    extends Charge
{
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeSize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EmDamageResistanceBonusBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ExplosiveDamageResistanceBonusBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int KineticDamageResistanceBonusBonus;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ThermalDamageResistanceBonusBonus;
    public static final FlexArmorHardenerScript.MetaGroup METAGROUP = new FlexArmorHardenerScript.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  128 :
            {
                return ChargeSize;
            }
            case  2402 :
            {
                return EmDamageResistanceBonusBonus;
            }
            case  2403 :
            {
                return ExplosiveDamageResistanceBonusBonus;
            }
            case  2404 :
            {
                return KineticDamageResistanceBonusBonus;
            }
            case  137 :
            {
                return LauncherGroup;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  2405 :
            {
                return ThermalDamageResistanceBonusBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<FlexArmorHardenerScript> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<FlexArmorHardenerScript>
    {
        public static final String RESOURCE_PATH = "SDE/items/charge/FlexArmorHardenerScript.yaml";
        private Map<String, FlexArmorHardenerScript> cache = (null);

        @Override
        public IMetaCategory<? super FlexArmorHardenerScript> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1701;
        }

        @Override
        public String getName() {
            return "FlexArmorHardenerScript";
        }

        @Override
        public synchronized Map<String, FlexArmorHardenerScript> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(FlexArmorHardenerScript.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, FlexArmorHardenerScript> items;
        }
    }
}
