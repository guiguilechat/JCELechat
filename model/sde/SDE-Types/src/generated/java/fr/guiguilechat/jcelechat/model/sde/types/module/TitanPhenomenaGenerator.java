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
import fr.guiguilechat.jcelechat.model.sde.attributes.BuffDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType1;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType2;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType3;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConsumptionQuantity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConsumptionType;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowActivateInForcefield;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowDocking;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowTethering;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModuleReactivationDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff1ID;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff1Value;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff2ID;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff2Value;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff3ID;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff3Value;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff4ID;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff4Value;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class TitanPhenomenaGenerator
    extends Module
{
    /**
     * Applied modifier duration
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int buffduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype3;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
    /**
     * The amount of the given resource type needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int consumptionquantity;
    /**
     * The type of resource needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int consumptiontype;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * If set to True on a module, the module will not be allowed to activate whilst the ship is inside a starbase forcefield
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowactivateinforcefield;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from docking in stations or structures.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowdocking;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from activating the structure tethering.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowtethering;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
    /**
     * Amount of time that has to be waited after the deactivation of this module until it can be reactivated.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int modulereactivationdelay;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff1id;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double warfarebuff1value;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff2id;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double warfarebuff2value;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff3id;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double warfarebuff3value;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff4id;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double warfarebuff4value;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {DisallowActivateInForcefield.INSTANCE, CapacitorNeed.INSTANCE, MaxGroupFitted.INSTANCE, Duration.INSTANCE, ConsumptionType.INSTANCE, Hp.INSTANCE, ConsumptionQuantity.INSTANCE, RequiredSkill1Level.INSTANCE, CanFitShipType1 .INSTANCE, CanFitShipType2 .INSTANCE, CanFitShipType3 .INSTANCE, ModuleReactivationDelay.INSTANCE, Power.INSTANCE, Radius.INSTANCE, WarfareBuff1ID.INSTANCE, WarfareBuff1Value.INSTANCE, WarfareBuff2ID.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, DisallowTethering.INSTANCE, BuffDuration.INSTANCE, WarfareBuff2Value.INSTANCE, WarfareBuff3ID.INSTANCE, WarfareBuff4ID.INSTANCE, WarfareBuff3Value.INSTANCE, WarfareBuff4Value.INSTANCE, Cpu.INSTANCE, DisallowDocking.INSTANCE, RequiredSkill1 .INSTANCE, MaxRange.INSTANCE, MetaLevelOld.INSTANCE, MaxGroupActive.INSTANCE })));
    public static final TitanPhenomenaGenerator.MetaGroup METAGROUP = new TitanPhenomenaGenerator.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2535 :
            {
                return buffduration;
            }
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  1303 :
            {
                return canfitshiptype2;
            }
            case  1304 :
            {
                return canfitshiptype3;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  714 :
            {
                return consumptionquantity;
            }
            case  713 :
            {
                return consumptiontype;
            }
            case  50 :
            {
                return cpu;
            }
            case  1920 :
            {
                return disallowactivateinforcefield;
            }
            case  2354 :
            {
                return disallowdocking;
            }
            case  2343 :
            {
                return disallowtethering;
            }
            case  73 :
            {
                return duration;
            }
            case  763 :
            {
                return maxgroupactive;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  54 :
            {
                return maxrange;
            }
            case  669 :
            {
                return modulereactivationdelay;
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
            case  2468 :
            {
                return warfarebuff1id;
            }
            case  2469 :
            {
                return warfarebuff1value;
            }
            case  2470 :
            {
                return warfarebuff2id;
            }
            case  2471 :
            {
                return warfarebuff2value;
            }
            case  2472 :
            {
                return warfarebuff3id;
            }
            case  2473 :
            {
                return warfarebuff3value;
            }
            case  2536 :
            {
                return warfarebuff4id;
            }
            case  2537 :
            {
                return warfarebuff4value;
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
    public IMetaGroup<TitanPhenomenaGenerator> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TitanPhenomenaGenerator>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/TitanPhenomenaGenerator.yaml";
        private Map<String, TitanPhenomenaGenerator> cache = (null);

        @Override
        public IMetaCategory<? super TitanPhenomenaGenerator> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1815;
        }

        @Override
        public String getName() {
            return "TitanPhenomenaGenerator";
        }

        @Override
        public synchronized Map<String, TitanPhenomenaGenerator> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(TitanPhenomenaGenerator.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, TitanPhenomenaGenerator> types;
        }
    }
}
