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
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType10;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType11;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType2;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType3;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType4;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType5;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType6;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType7;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType8;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType9;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.DeadspaceUnsafe;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInHighSec;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmpFieldRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupOnline;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.OnlineMaxSecurityClass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReloadTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamage;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureAreaDenialModule
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
    public int canfitshiptype10;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype11;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype4;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype5;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype6;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype7;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype8;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype9;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
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
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargesize;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double damagemultiplier;
    /**
     * Modules with this attribute set to 1 can not be used in deadspace. Modules with this attribute set to 2 can not be used in deadspace even where "disableModuleBlocking" is selected
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int deadspaceunsafe;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinhighsec;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double emdamage;
    /**
     * Range of broadcasted EMP field.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int empfieldrange;
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
     * Maximum modules of same group that can be onlined at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgrouponline;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
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
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    /**
     * reload time (ms)
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(10000.0)
    public double reloadtime;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureitemvisualflag;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double thermaldamage;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, DamageMultiplier.INSTANCE, ReloadTime.INSTANCE, CapacitorNeed.INSTANCE, CanFitShipType11 .INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, Duration.INSTANCE, MaxGroupOnline.INSTANCE, OnlineMaxSecurityClass.INSTANCE, CanFitShipType1 .INSTANCE, CanFitShipType2 .INSTANCE, CanFitShipType5 .INSTANCE, CanFitShipType3 .INSTANCE, CanFitShipType4 .INSTANCE, ChargeGroup1 .INSTANCE, Power.INSTANCE, StructureItemVisualFlag.INSTANCE, CanFitShipType7 .INSTANCE, DeadspaceUnsafe.INSTANCE, Radius.INSTANCE, EmpFieldRange.INSTANCE, Capacity.INSTANCE, TechLevel.INSTANCE, DisallowInHighSec.INSTANCE, Cpu.INSTANCE, EmDamage.INSTANCE, ExplosiveDamage.INSTANCE, KineticDamage.INSTANCE, CanFitShipType8 .INSTANCE, ThermalDamage.INSTANCE, CanFitShipType6 .INSTANCE, CanFitShipType9 .INSTANCE, ChargeRate.INSTANCE, CanFitShipType10 .INSTANCE, MetaLevelOld.INSTANCE, MaxGroupActive.INSTANCE })));
    public static final StructureAreaDenialModule.MetaGroup METAGROUP = new StructureAreaDenialModule.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  2488 :
            {
                return canfitshiptype10;
            }
            case  2758 :
            {
                return canfitshiptype11;
            }
            case  1303 :
            {
                return canfitshiptype2;
            }
            case  1304 :
            {
                return canfitshiptype3;
            }
            case  1305 :
            {
                return canfitshiptype4;
            }
            case  1944 :
            {
                return canfitshiptype5;
            }
            case  2103 :
            {
                return canfitshiptype6;
            }
            case  2463 :
            {
                return canfitshiptype7;
            }
            case  2486 :
            {
                return canfitshiptype8;
            }
            case  2487 :
            {
                return canfitshiptype9;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  38 :
            {
                return capacity;
            }
            case  604 :
            {
                return chargegroup1;
            }
            case  56 :
            {
                return chargerate;
            }
            case  128 :
            {
                return chargesize;
            }
            case  50 :
            {
                return cpu;
            }
            case  64 :
            {
                return damagemultiplier;
            }
            case  801 :
            {
                return deadspaceunsafe;
            }
            case  1970 :
            {
                return disallowinhighsec;
            }
            case  73 :
            {
                return duration;
            }
            case  114 :
            {
                return emdamage;
            }
            case  99 :
            {
                return empfieldrange;
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
            case  763 :
            {
                return maxgroupactive;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  978 :
            {
                return maxgrouponline;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  2581 :
            {
                return onlinemaxsecurityclass;
            }
            case  30 :
            {
                return power;
            }
            case  162 :
            {
                return radius;
            }
            case  1795 :
            {
                return reloadtime;
            }
            case  2334 :
            {
                return structureitemvisualflag;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaGroup<StructureAreaDenialModule> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureAreaDenialModule>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureAreaDenialModule.yaml";
        private Map<String, StructureAreaDenialModule> cache = (null);

        @Override
        public IMetaCategory<? super StructureAreaDenialModule> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1330;
        }

        @Override
        public String getName() {
            return "StructureAreaDenialModule";
        }

        @Override
        public synchronized Map<String, StructureAreaDenialModule> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureAreaDenialModule.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureAreaDenialModule> types;
        }
    }
}
