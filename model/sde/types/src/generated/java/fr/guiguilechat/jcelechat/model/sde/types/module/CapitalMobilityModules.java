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
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup02;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType1;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowEarlyDeactivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInHighSec;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowRepeatingActivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowTethering;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MjdCapitalShipJumpCap;
import fr.guiguilechat.jcelechat.model.sde.attributes.MjdJumpRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MjdPostActivationScramDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.MjdShipJumpCap;
import fr.guiguilechat.jcelechat.model.sde.attributes.MjfgRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModuleReactivationDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadiusBonusPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class CapitalMobilityModules
    extends Module
{
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
    public int canfitshiptype1;
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
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinhighsec;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowrepeatingactivation;
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
     * The maximum number of capital ships that can be jumped per activation
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int mjdcapitalshipjumpcap;
    /**
     * distance jumped on mjd activation in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int mjdjumprange;
    /**
     * Attribute for MJD/MJFG modules which determines how long ships are warp scrambled for after having been jumped. If set to zero, no warp scrambling will occur.  Value is in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int mjdpostactivationscramduration;
    /**
     * The maximum number of ships that can be jumped per activation
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int mjdshipjumpcap;
    /**
     * range effected by mjfg scoop
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int mjfgradius;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int signatureradiusbonuspercent;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {CapacitorNeed.INSTANCE, MaxGroupFitted.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, DisallowEarlyDeactivation.INSTANCE, SignatureRadiusBonusPercent.INSTANCE, MjdShipJumpCap.INSTANCE, MjdJumpRange.INSTANCE, CanFitShipGroup01 .INSTANCE, MjfgRadius.INSTANCE, CanFitShipGroup02 .INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, CanFitShipType1 .INSTANCE, ModuleReactivationDelay.INSTANCE, Power.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, DisallowTethering.INSTANCE, Cpu.INSTANCE, DisallowInHighSec.INSTANCE, RequiredSkill1 .INSTANCE, DisallowRepeatingActivation.INSTANCE, MjdCapitalShipJumpCap.INSTANCE, RequiredSkill2 .INSTANCE, MjdPostActivationScramDuration.INSTANCE, MetaLevelOld.INSTANCE, MaxGroupActive.INSTANCE })));
    public static final CapitalMobilityModules.MetaGroup METAGROUP = new CapitalMobilityModules.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1299 :
            {
                return canfitshipgroup02;
            }
            case  1302 :
            {
                return canfitshiptype1;
            }
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
            case  1970 :
            {
                return disallowinhighsec;
            }
            case  1014 :
            {
                return disallowrepeatingactivation;
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
            case  5686 :
            {
                return mjdcapitalshipjumpcap;
            }
            case  2066 :
            {
                return mjdjumprange;
            }
            case  5687 :
            {
                return mjdpostactivationscramduration;
            }
            case  2832 :
            {
                return mjdshipjumpcap;
            }
            case  2067 :
            {
                return mjfgradius;
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
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  973 :
            {
                return signatureradiusbonuspercent;
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
    public IMetaGroup<CapitalMobilityModules> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CapitalMobilityModules>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/CapitalMobilityModules.yaml";
        private Map<Integer, CapitalMobilityModules> cache = (null);

        @Override
        public IMetaCategory<? super CapitalMobilityModules> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4769;
        }

        @Override
        public String getName() {
            return "CapitalMobilityModules";
        }

        @Override
        public synchronized Map<Integer, CapitalMobilityModules> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CapitalMobilityModules.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CapitalMobilityModules> types;
        }
    }
}
