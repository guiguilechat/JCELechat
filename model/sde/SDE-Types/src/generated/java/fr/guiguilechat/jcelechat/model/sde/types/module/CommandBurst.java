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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.BuffDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup02;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup03;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup04;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup05;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup06;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup07;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup08;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup09;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup10;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowActivateInForcefield;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowDocking;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowTethering;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupOnline;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReloadTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff1Value;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff2Value;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff3Value;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff4Value;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareLinkCPUAdd;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class CommandBurst
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
    public int canfitshipgroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup02;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup03;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup04;
    /**
     * Can be fitted to
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup05;
    /**
     * Can be fitted to
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup06;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup07;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup08;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup09;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup10;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacitorneed;
    /**
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargegroup1;
    /**
     * Number of charges consumed per activation
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int chargerate;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
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
    @DefaultDoubleValue(0.0)
    public double duration;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupactive;
    /**
     * Maximum modules of same group that can be onlined at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgrouponline;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double maxrange;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * reload time (ms)
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(10000.0)
    public double reloadtime;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3level;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double warfarebuff1value;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double warfarebuff2value;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double warfarebuff3value;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double warfarebuff4value;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarelinkcpuadd;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {DisallowActivateInForcefield.INSTANCE, ReloadTime.INSTANCE, Mass.INSTANCE, CapacitorNeed.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, CanFitShipGroup05 .INSTANCE, CanFitShipGroup09 .INSTANCE, CanFitShipGroup01 .INSTANCE, MaxGroupOnline.INSTANCE, CanFitShipGroup02 .INSTANCE, CanFitShipGroup03 .INSTANCE, RequiredSkill1Level.INSTANCE, CanFitShipGroup04 .INSTANCE, RequiredSkill2Level.INSTANCE, CanFitShipGroup06 .INSTANCE, RequiredSkill3Level.INSTANCE, CanFitShipGroup07 .INSTANCE, CanFitShipGroup08 .INSTANCE, WarfareLinkCPUAdd.INSTANCE, CanFitShipGroup10 .INSTANCE, ChargeGroup1 .INSTANCE, Power.INSTANCE, Radius.INSTANCE, WarfareBuff1Value.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, DisallowTethering.INSTANCE, BuffDuration.INSTANCE, WarfareBuff2Value.INSTANCE, WarfareBuff4Value.INSTANCE, WarfareBuff3Value.INSTANCE, DisallowDocking.INSTANCE, Cpu.INSTANCE, RequiredSkill1 .INSTANCE, MaxRange.INSTANCE, RequiredSkill2 .INSTANCE, ChargeRate.INSTANCE, RequiredSkill3 .INSTANCE, MetaLevelOld.INSTANCE, MaxGroupActive.INSTANCE })));
    public static final CommandBurst.MetaGroup METAGROUP = new CommandBurst.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2535 :
            {
                return buffduration;
            }
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1299 :
            {
                return canfitshipgroup02;
            }
            case  1300 :
            {
                return canfitshipgroup03;
            }
            case  1301 :
            {
                return canfitshipgroup04;
            }
            case  1872 :
            {
                return canfitshipgroup05;
            }
            case  1879 :
            {
                return canfitshipgroup06;
            }
            case  1880 :
            {
                return canfitshipgroup07;
            }
            case  1881 :
            {
                return canfitshipgroup08;
            }
            case  2065 :
            {
                return canfitshipgroup09;
            }
            case  2396 :
            {
                return canfitshipgroup10;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  604 :
            {
                return chargegroup1;
            }
            case  56 :
            {
                return chargerate;
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
            case  978 :
            {
                return maxgrouponline;
            }
            case  54 :
            {
                return maxrange;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  30 :
            {
                return power;
            }
            case  1795 :
            {
                return reloadtime;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  184 :
            {
                return requiredskill3;
            }
            case  279 :
            {
                return requiredskill3level;
            }
            case  422 :
            {
                return techlevel;
            }
            case  2469 :
            {
                return warfarebuff1value;
            }
            case  2471 :
            {
                return warfarebuff2value;
            }
            case  2473 :
            {
                return warfarebuff3value;
            }
            case  2537 :
            {
                return warfarebuff4value;
            }
            case  1882 :
            {
                return warfarelinkcpuadd;
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
    public IMetaGroup<CommandBurst> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CommandBurst>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/CommandBurst.yaml";
        private Map<String, CommandBurst> cache = (null);

        @Override
        public IMetaCategory<? super CommandBurst> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1770;
        }

        @Override
        public String getName() {
            return "CommandBurst";
        }

        @Override
        public synchronized Map<String, CommandBurst> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CommandBurst.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CommandBurst> types;
        }
    }
}
