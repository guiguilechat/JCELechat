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
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowAssistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowEarlyDeactivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowOffensiveModifiers;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowRepeatingActivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnableOpenJumpPortal;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnablePerformConduitJump;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpConduitPassengerRequiredAttributeID;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpPortalConsumptionMassFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpPortalDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpPortalPassengerRequiredAttributeID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SiegeModeWarpStatus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class CovertJumpPortalGenerator
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
     * If this module is in use and this attribute is 1, then assistance modules cannot be used on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowassistance;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowearlydeactivation;
    /**
     * If this module is in use and this attribute is 1, then offensive modules cannot be used on the ship if they apply modifiers for the duration of their effect. If this is put on a ship or NPC with value of 1, then the ship or NPC are immune to offensive modifiers (target jamming, tracking disruption etc.)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowoffensivemodifiers;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowrepeatingactivation;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * Grants the ability to open Jump Portals
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int enableopenjumpportal;
    /**
     * Grants the ability to perform conduit jumps
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int enableperformconduitjump;
    /**
     * This attribute enables a ship to activate a Jump Conduit. Its value specifies a dogma attribute ID that a passenger ship must possess in order to be carried though that Jump Conduit.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int jumpconduitpassengerrequiredattributeid;
    /**
     * Multiplier used to calculate amount of quantity used for jumping via portals based on mass of ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double jumpportalconsumptionmassfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int jumpportalduration;
    /**
     * This attribute enables a ship to open a Jump Portal. Its value specifies a dogma attribute ID that a passenger ship must possess in order to travel through that Jump Portal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int jumpportalpassengerrequiredattributeid;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupactive;
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
    public int siegemodewarpstatus;
    /**
     * Amount to increase the maximum speed by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int speedbonus;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double speedfactor;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {CapacitorNeed.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, DisallowEarlyDeactivation.INSTANCE, SpeedBonus.INSTANCE, CanFitShipGroup01 .INSTANCE, SpeedFactor.INSTANCE, SiegeModeWarpStatus.INSTANCE, RequiredSkill1Level.INSTANCE, DisallowAssistance.INSTANCE, Power.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, DisallowOffensiveModifiers.INSTANCE, JumpPortalConsumptionMassFactor.INSTANCE, JumpPortalDuration.INSTANCE, Cpu.INSTANCE, EnableOpenJumpPortal.INSTANCE, EnablePerformConduitJump.INSTANCE, JumpPortalPassengerRequiredAttributeID.INSTANCE, RequiredSkill1 .INSTANCE, DisallowRepeatingActivation.INSTANCE, JumpConduitPassengerRequiredAttributeID.INSTANCE, MetaLevelOld.INSTANCE, MaxGroupActive.INSTANCE })));
    public static final CovertJumpPortalGenerator.MetaGroup METAGROUP = new CovertJumpPortalGenerator.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  50 :
            {
                return cpu;
            }
            case  854 :
            {
                return disallowassistance;
            }
            case  906 :
            {
                return disallowearlydeactivation;
            }
            case  872 :
            {
                return disallowoffensivemodifiers;
            }
            case  1014 :
            {
                return disallowrepeatingactivation;
            }
            case  73 :
            {
                return duration;
            }
            case  3125 :
            {
                return enableopenjumpportal;
            }
            case  3126 :
            {
                return enableperformconduitjump;
            }
            case  3321 :
            {
                return jumpconduitpassengerrequiredattributeid;
            }
            case  1001 :
            {
                return jumpportalconsumptionmassfactor;
            }
            case  1002 :
            {
                return jumpportalduration;
            }
            case  3318 :
            {
                return jumpportalpassengerrequiredattributeid;
            }
            case  763 :
            {
                return maxgroupactive;
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
            case  852 :
            {
                return siegemodewarpstatus;
            }
            case  80 :
            {
                return speedbonus;
            }
            case  20 :
            {
                return speedfactor;
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
    public IMetaGroup<CovertJumpPortalGenerator> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CovertJumpPortalGenerator>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/CovertJumpPortalGenerator.yaml";
        private Map<Integer, CovertJumpPortalGenerator> cache = (null);

        @Override
        public IMetaCategory<? super CovertJumpPortalGenerator> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4127;
        }

        @Override
        public String getName() {
            return "CovertJumpPortalGenerator";
        }

        @Override
        public synchronized Map<Integer, CovertJumpPortalGenerator> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CovertJumpPortalGenerator.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CovertJumpPortalGenerator> types;
        }
    }
}
