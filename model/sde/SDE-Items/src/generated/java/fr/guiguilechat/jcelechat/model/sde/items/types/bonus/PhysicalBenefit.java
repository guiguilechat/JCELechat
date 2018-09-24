package fr.guiguilechat.jcelechat.model.sde.items.types.bonus;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class PhysicalBenefit
    extends Bonus
{
    /**
     * Scales the accuracy of some targeted weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double AccuracyMultiplier;
    /**
     * Multiplier to the agility of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double AgilityMultiplier;
    /**
     * Bonus or penalty to the percentage time it takes to manufacture from a blueprint.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BlueprintManufactureTimeMultiplierBonus;
    /**
     * Bonus or penalty to the percentage time it takes to research a blueprint.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BlueprintResearchTimeMultiplierBonus;
    /**
     * Factor to adjust module cpu need by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double CpuMultiplier;
    /**
     * The factor by which the amount mined by a mining laser is scaled.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double MiningAmountMultiplier;
    /**
     * Additional percentage to the characters missile damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MissileDamageMultiplierBonus;
    public final static PhysicalBenefit.MetaGroup METAGROUP = new PhysicalBenefit.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  205 :
            {
                return AccuracyMultiplier;
            }
            case  169 :
            {
                return AgilityMultiplier;
            }
            case  222 :
            {
                return BlueprintManufactureTimeMultiplierBonus;
            }
            case  220 :
            {
                return BlueprintResearchTimeMultiplierBonus;
            }
            case  202 :
            {
                return CpuMultiplier;
            }
            case  207 :
            {
                return MiningAmountMultiplier;
            }
            case  213 :
            {
                return MissileDamageMultiplierBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<PhysicalBenefit> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PhysicalBenefit>
    {
        public final static String RESOURCE_PATH = "SDE/items/bonus/PhysicalBenefit.yaml";
        private Map<String, PhysicalBenefit> cache = (null);

        @Override
        public IMetaCategory<? super PhysicalBenefit> category() {
            return Bonus.METACAT;
        }

        @Override
        public int getGroupId() {
            return  191;
        }

        @Override
        public String getName() {
            return "PhysicalBenefit";
        }

        @Override
        public synchronized Map<String, PhysicalBenefit> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(PhysicalBenefit.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PhysicalBenefit> items;
        }
    }
}
