package fr.guiguilechat.jcelechat.model.sde.types.module;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowEarlyDeactivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningWasteProbability;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningWastedVolumeMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.Slots;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class GasCloudScoops
    extends Module
{
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowearlydeactivation;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupactive;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
    /**
     * How much ore gets mined
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningamount;
    /**
     * The probability of volume getting wasted every cycle
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int miningwasteprobability;
    /**
     * This multiplier is applied to the Mining Volume of the actor (module, drone etc.) to calculate the potential wasted volume per cycle
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(1)
    public int miningwastedvolumemultiplier;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * The number of slots this module requires.  Only used for launchers, bays and turrets.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int slots;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {CapacitorNeed.INSTANCE, TechLevel.INSTANCE, TypeColorScheme.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, DisallowEarlyDeactivation.INSTANCE, MiningAmount.INSTANCE, Slots.INSTANCE, MiningWastedVolumeMultiplier.INSTANCE, Cpu.INSTANCE, MiningWasteProbability.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, MaxRange.INSTANCE, MetaLevelOld.INSTANCE, MaxGroupActive.INSTANCE, MetaGroupID.INSTANCE, Power.INSTANCE })));
    public static final GasCloudScoops.MetaGroup METAGROUP = new GasCloudScoops.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  6 :
            {
                return capacitorneed;
            }
            case  50 :
            {
                return cpu;
            }
            case  906 :
            {
                return disallowearlydeactivation;
            }
            case  73 :
            {
                return duration;
            }
            case  763 :
            {
                return maxgroupactive;
            }
            case  54 :
            {
                return maxrange;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  77 :
            {
                return miningamount;
            }
            case  3154 :
            {
                return miningwasteprobability;
            }
            case  3153 :
            {
                return miningwastedvolumemultiplier;
            }
            case  30 :
            {
                return power;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  47 :
            {
                return slots;
            }
            case  1768 :
            {
                return typecolorscheme;
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
    public IMetaGroup<GasCloudScoops> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<GasCloudScoops>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/GasCloudScoops.yaml";
        private Map<Integer, GasCloudScoops> cache = (null);

        @Override
        public IMetaCategory<? super GasCloudScoops> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  737;
        }

        @Override
        public String getName() {
            return "GasCloudScoops";
        }

        @Override
        public synchronized Map<Integer, GasCloudScoops> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(GasCloudScoops.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, GasCloudScoops> types;
        }
    }
}
