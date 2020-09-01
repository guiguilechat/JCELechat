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
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DeadspaceUnsafe;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInEmpireSpace;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowRepeatingActivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MinVelocityActivationLimit;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModuleReactivationDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReloadTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadiusAdd;
import fr.guiguilechat.jcelechat.model.sde.attributes.Slots;
import fr.guiguilechat.jcelechat.model.sde.attributes.Speed;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class MissileLauncherBomb
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
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargegroup1;
    /**
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargegroup2;
    /**
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargegroup3;
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
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * Modules with this attribute set to 1 can not be used in deadspace. Modules with this attribute set to 2 can not be used in deadspace even where "disableModuleBlocking" is selected
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int deadspaceunsafe;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinempirespace;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowrepeatingactivation;
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
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int minvelocityactivationlimit;
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
     * reload time (ms)
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(10000.0)
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
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int signatureradiusadd;
    /**
     * The number of slots this module requires.  Only used for launchers, bays and turrets.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int slots;
    /**
     * Time in milliseconds between possible activations
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double speed;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ReloadTime.INSTANCE, Mass.INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, CanFitShipGroup01 .INSTANCE, RequiredSkill1Level.INSTANCE, SignatureRadiusAdd.INSTANCE, ChargeGroup1 .INSTANCE, ModuleReactivationDelay.INSTANCE, ChargeGroup2 .INSTANCE, Power.INSTANCE, ChargeGroup3 .INSTANCE, DeadspaceUnsafe.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, TypeColorScheme.INSTANCE, Slots.INSTANCE, MinVelocityActivationLimit.INSTANCE, Cpu.INSTANCE, DisallowInEmpireSpace.INSTANCE, Speed.INSTANCE, RequiredSkill1 .INSTANCE, DisallowRepeatingActivation.INSTANCE, ChargeRate.INSTANCE, MetaLevelOld.INSTANCE, MaxGroupActive.INSTANCE })));
    public static final MissileLauncherBomb.MetaGroup METAGROUP = new MissileLauncherBomb.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  604 :
            {
                return chargegroup1;
            }
            case  605 :
            {
                return chargegroup2;
            }
            case  606 :
            {
                return chargegroup3;
            }
            case  56 :
            {
                return chargerate;
            }
            case  50 :
            {
                return cpu;
            }
            case  801 :
            {
                return deadspaceunsafe;
            }
            case  1074 :
            {
                return disallowinempirespace;
            }
            case  1014 :
            {
                return disallowrepeatingactivation;
            }
            case  763 :
            {
                return maxgroupactive;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  2608 :
            {
                return minvelocityactivationlimit;
            }
            case  669 :
            {
                return modulereactivationdelay;
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
            case  983 :
            {
                return signatureradiusadd;
            }
            case  47 :
            {
                return slots;
            }
            case  51 :
            {
                return speed;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaGroup<MissileLauncherBomb> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissileLauncherBomb>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/MissileLauncherBomb.yaml";
        private Map<String, MissileLauncherBomb> cache = (null);

        @Override
        public IMetaCategory<? super MissileLauncherBomb> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  862;
        }

        @Override
        public String getName() {
            return "MissileLauncherBomb";
        }

        @Override
        public synchronized Map<String, MissileLauncherBomb> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissileLauncherBomb.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissileLauncherBomb> types;
        }
    }
}
