package fr.guiguilechat.jcelechat.model.sde.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Module;

public class CapitalSensorArray
    extends Module
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int cancloak;
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
     * Stops the module from being activated if the ship is aligning to warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowactivateonwarp;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from docking in stations or structures.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowdocking;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowearlydeactivation;
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
     * Bonus attribute for capacitor need of EW and propulsion jamming.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ewcapacitorneedbonus;
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
     * Scales the max target range of a ships electronics.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double maxtargetrangemultiplier;
    /**
     * The maximum possible target range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int maximumrangecap;
    /**
     * For use with the Networked Sensor Array dogma effect, moduleBonusNetworkedSensorArray [6567]. If this value is set to 1, prevent activation of a Capital Micro Jump Drive or Capital Micro Jump Field Generator.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int networkedsensorarraydisallowcapitalmicrojump;
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
    public int scangravimetricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanladarstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanmagnetometricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanradarstrengthpercent;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolutionbonus;
    /**
     * Armor Repairer / Shield Booster Capacitor Need Bonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegelocallogisticscapacitorneedbonus;
    /**
     * Armor Repairer / Shield Booster Duration Bonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegelocallogisticsdurationbonus;
    /**
     * Amount to modify ships warp scramble status by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblestrength;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ScanGravimetricStrengthPercent.INSTANCE, ScanLadarStrengthPercent.INSTANCE, NetworkedSensorArrayDisallowCapitalMicroJump.INSTANCE, ScanMagnetometricStrengthPercent.INSTANCE, CapacitorNeed.INSTANCE, ScanRadarStrengthPercent.INSTANCE, MaxGroupFitted.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, DisallowEarlyDeactivation.INSTANCE, CanCloak.INSTANCE, CanFitShipGroup01 .INSTANCE, CanFitShipGroup02 .INSTANCE, RequiredSkill1Level.INSTANCE, DisallowActivateOnWarp.INSTANCE, MaximumRangeCap.INSTANCE, Power.INSTANCE, SiegeLocalLogisticsCapacitorNeedBonus.INSTANCE, EwCapacitorNeedBonus.INSTANCE, TechLevel.INSTANCE, DisallowTethering.INSTANCE, WarpScrambleStrength.INSTANCE, SiegeLocalLogisticsDurationBonus.INSTANCE, MaxTargetRangeMultiplier.INSTANCE, Cpu.INSTANCE, DisallowDocking.INSTANCE, RequiredSkill1 .INSTANCE, ScanResolutionBonus.INSTANCE, MetaLevelOld.INSTANCE, MaxGroupActive.INSTANCE })));
    public static final CapitalSensorArray.MetaGroup METAGROUP = new CapitalSensorArray.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1163 :
            {
                return cancloak;
            }
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1299 :
            {
                return canfitshipgroup02;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  50 :
            {
                return cpu;
            }
            case  1245 :
            {
                return disallowactivateonwarp;
            }
            case  2354 :
            {
                return disallowdocking;
            }
            case  906 :
            {
                return disallowearlydeactivation;
            }
            case  2343 :
            {
                return disallowtethering;
            }
            case  73 :
            {
                return duration;
            }
            case  1190 :
            {
                return ewcapacitorneedbonus;
            }
            case  763 :
            {
                return maxgroupactive;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  237 :
            {
                return maxtargetrangemultiplier;
            }
            case  797 :
            {
                return maximumrangecap;
            }
            case  5700 :
            {
                return networkedsensorarraydisallowcapitalmicrojump;
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
            case  1027 :
            {
                return scangravimetricstrengthpercent;
            }
            case  1028 :
            {
                return scanladarstrengthpercent;
            }
            case  1029 :
            {
                return scanmagnetometricstrengthpercent;
            }
            case  1030 :
            {
                return scanradarstrengthpercent;
            }
            case  566 :
            {
                return scanresolutionbonus;
            }
            case  5988 :
            {
                return siegelocallogisticscapacitorneedbonus;
            }
            case  2346 :
            {
                return siegelocallogisticsdurationbonus;
            }
            case  105 :
            {
                return warpscramblestrength;
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
    public IMetaGroup<CapitalSensorArray> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CapitalSensorArray>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/CapitalSensorArray.yaml";
        private Map<Integer, CapitalSensorArray> cache = (null);

        @Override
        public IMetaCategory<? super CapitalSensorArray> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1706;
        }

        @Override
        public String getName() {
            return "CapitalSensorArray";
        }

        @Override
        public synchronized Map<Integer, CapitalSensorArray> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CapitalSensorArray.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CapitalSensorArray> types;
        }
    }
}
