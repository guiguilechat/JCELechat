package fr.guiguilechat.jcelechat.model.sde.types.bonus;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AccuracyMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.AgilityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BlueprintManufactureTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.BlueprintResearchTimeMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningAmountMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileDamageMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Bonus;

public class PhysicalBenefit
    extends Bonus
{
    /**
     * Scales the accuracy of some targeted weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double accuracymultiplier;
    /**
     * Multiplier to the agility of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double agilitymultiplier;
    /**
     * Bonus or penalty to the percentage time it takes to manufacture from a blueprint.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double blueprintmanufacturetimemultiplierbonus;
    /**
     * Bonus or penalty to the percentage time it takes to research a blueprint.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double blueprintresearchtimemultiplierbonus;
    /**
     * Factor to adjust module cpu need by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double cpumultiplier;
    /**
     * The factor by which the amount mined by a mining laser is scaled.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double miningamountmultiplier;
    /**
     * Additional percentage to the characters missile damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double missiledamagemultiplierbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {MissileDamageMultiplierBonus.INSTANCE, AgilityMultiplier.INSTANCE, CpuMultiplier.INSTANCE, BlueprintResearchTimeMultiplierBonus.INSTANCE, AccuracyMultiplier.INSTANCE, BlueprintManufactureTimeMultiplierBonus.INSTANCE, MiningAmountMultiplier.INSTANCE })));
    public static final PhysicalBenefit.MetaGroup METAGROUP = new PhysicalBenefit.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  205 :
            {
                return accuracymultiplier;
            }
            case  169 :
            {
                return agilitymultiplier;
            }
            case  222 :
            {
                return blueprintmanufacturetimemultiplierbonus;
            }
            case  220 :
            {
                return blueprintresearchtimemultiplierbonus;
            }
            case  202 :
            {
                return cpumultiplier;
            }
            case  207 :
            {
                return miningamountmultiplier;
            }
            case  213 :
            {
                return missiledamagemultiplierbonus;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PhysicalBenefit> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PhysicalBenefit>
    {
        public static final String RESOURCE_PATH = "SDE/types/bonus/PhysicalBenefit.yaml";
        private Map<Integer, PhysicalBenefit> cache = (null);

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
        public synchronized Map<Integer, PhysicalBenefit> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PhysicalBenefit.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, PhysicalBenefit> types;
        }
    }
}
