package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType1;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType2;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageDelayDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DeadspaceUnsafe;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowEarlyDeactivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInHighSec;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowRepeatingActivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EffectDeactivationDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.LightningWeaponDamageLossTarget;
import fr.guiguilechat.jcelechat.model.sde.attributes.LightningWeaponTargetAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.LightningWeaponTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.OnlineMaxSecurityClass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.attributes.TargetFilterTypelistID;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamage;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class StructureDoomsdayWeapon
    extends StructureModule
{
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
     * The delay in ms until the damage is done to the target. (Allows some FX to be played)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int damagedelayduration;
    /**
     * Modules with this attribute set to 1 can not be used in deadspace. Modules with this attribute set to 2 can not be used in deadspace even where "disableModuleBlocking" is selected
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int deadspaceunsafe;
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
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int effectdeactivationdelay;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double emdamage;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double explosivedamage;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double kineticdamage;
    /**
     * Damage lost per target hit
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double lightningweapondamagelosstarget;
    /**
     * Number of targets affected by the structure doomsday beam.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int lightningweapontargetamount;
    /**
     * Maximum distance between two possible targets for the structure doomsday.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int lightningweapontargetrange;
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
     * Determines the maximum security class that a module can be onlined within. Used for structure modules.
     * 
     *  0=Nullsec
     *  1=Lowsec
     *  2=Highsec
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2)
    public int onlinemaxsecurityclass;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureitemvisualflag;
    /**
     * On a targeted module, module can only be activated against a target from this type list.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int targetfiltertypelistid;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double thermaldamage;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {CapacitorNeed.INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, Duration.INSTANCE, DisallowEarlyDeactivation.INSTANCE, OnlineMaxSecurityClass.INSTANCE, CanFitShipType1 .INSTANCE, CanFitShipType2 .INSTANCE, Power.INSTANCE, StructureItemVisualFlag.INSTANCE, DeadspaceUnsafe.INSTANCE, EffectDeactivationDelay.INSTANCE, DamageDelayDuration.INSTANCE, Cpu.INSTANCE, EmDamage.INSTANCE, DisallowInHighSec.INSTANCE, ExplosiveDamage.INSTANCE, KineticDamage.INSTANCE, ThermalDamage.INSTANCE, DisallowRepeatingActivation.INSTANCE, LightningWeaponTargetAmount.INSTANCE, LightningWeaponTargetRange.INSTANCE, LightningWeaponDamageLossTarget.INSTANCE, MaxGroupActive.INSTANCE, TargetFilterTypelistID.INSTANCE })));
    public static final StructureDoomsdayWeapon.MetaGroup METAGROUP = new StructureDoomsdayWeapon.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  1303 :
            {
                return canfitshiptype2;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  50 :
            {
                return cpu;
            }
            case  1839 :
            {
                return damagedelayduration;
            }
            case  801 :
            {
                return deadspaceunsafe;
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
            case  73 :
            {
                return duration;
            }
            case  1579 :
            {
                return effectdeactivationdelay;
            }
            case  114 :
            {
                return emdamage;
            }
            case  116 :
            {
                return explosivedamage;
            }
            case  9 :
            {
                return hp;
            }
            case  117 :
            {
                return kineticdamage;
            }
            case  2106 :
            {
                return lightningweapondamagelosstarget;
            }
            case  2104 :
            {
                return lightningweapontargetamount;
            }
            case  2105 :
            {
                return lightningweapontargetrange;
            }
            case  763 :
            {
                return maxgroupactive;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  2581 :
            {
                return onlinemaxsecurityclass;
            }
            case  30 :
            {
                return power;
            }
            case  2334 :
            {
                return structureitemvisualflag;
            }
            case  189 :
            {
                return targetfiltertypelistid;
            }
            case  118 :
            {
                return thermaldamage;
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
    public IMetaGroup<StructureDoomsdayWeapon> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureDoomsdayWeapon>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureDoomsdayWeapon.yaml";
        private Map<Integer, StructureDoomsdayWeapon> cache = (null);

        @Override
        public IMetaCategory<? super StructureDoomsdayWeapon> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1333;
        }

        @Override
        public String getName() {
            return "StructureDoomsdayWeapon";
        }

        @Override
        public synchronized Map<Integer, StructureDoomsdayWeapon> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureDoomsdayWeapon.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, StructureDoomsdayWeapon> types;
        }
    }
}
