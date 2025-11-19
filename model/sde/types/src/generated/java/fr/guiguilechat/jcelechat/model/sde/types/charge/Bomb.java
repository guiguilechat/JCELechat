package fr.guiguilechat.jcelechat.model.sde.types.charge;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.AimedLaunch;
import fr.guiguilechat.jcelechat.model.sde.attributes.AllowInFullyCorruptedLowSec;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeCloudSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.DetonationRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInEmpireSpace;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmpFieldRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamage;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Bomb
    extends Charge
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double agility;
    /**
     * Determines wether a missile launches aligned with the ship (0) or directly at the target (1).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aimedlaunch;
    /**
     * if the module is disallowed in low sec (empire space), if it also have this attribute, it will allow that module to be used in low sec system if the systems is fully corrupted
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int allowinfullycorruptedlowsec;
    /**
     * Size of the damage cloud caused by impact.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int aoecloudsize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aoefalloff;
    /**
     * Multiplies EM damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armoremdamageresonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armorexplosivedamageresonance;
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armorhp;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armorkineticdamageresonance;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armorthermaldamageresonance;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armoruniformity;
    /**
     * the range in meters for an object to trigger detonation of missile. (own ship excluded)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int detonationrange;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinempirespace;
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
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int explosiondelay;
    /**
     * Range in meters of explosion effect area.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int explosionrange;
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
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
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
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(100.0)
    public double signatureradius;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {AimedLaunch.INSTANCE, Agility.INSTANCE, Hp.INSTANCE, LauncherGroup.INSTANCE, ArmorHP.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, AoeCloudSize.INSTANCE, ArmorThermalDamageResonance.INSTANCE, AoeFalloff.INSTANCE, RequiredSkill1Level.INSTANCE, ExplosionDelay.INSTANCE, AllowInFullyCorruptedLowSec.INSTANCE, EmpFieldRange.INSTANCE, MaxVelocity.INSTANCE, TechLevel.INSTANCE, SignatureRadius.INSTANCE, ExplosionRange.INSTANCE, DetonationRange.INSTANCE, EmDamage.INSTANCE, DisallowInEmpireSpace.INSTANCE, ExplosiveDamage.INSTANCE, KineticDamage.INSTANCE, ThermalDamage.INSTANCE, RequiredSkill1 .INSTANCE, MetaLevelOld.INSTANCE })));
    public static final Bomb.MetaGroup METAGROUP = new Bomb.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return agility;
            }
            case  644 :
            {
                return aimedlaunch;
            }
            case  5599 :
            {
                return allowinfullycorruptedlowsec;
            }
            case  654 :
            {
                return aoecloudsize;
            }
            case  655 :
            {
                return aoefalloff;
            }
            case  267 :
            {
                return armoremdamageresonance;
            }
            case  268 :
            {
                return armorexplosivedamageresonance;
            }
            case  265 :
            {
                return armorhp;
            }
            case  269 :
            {
                return armorkineticdamageresonance;
            }
            case  270 :
            {
                return armorthermaldamageresonance;
            }
            case  524 :
            {
                return armoruniformity;
            }
            case  108 :
            {
                return detonationrange;
            }
            case  1074 :
            {
                return disallowinempirespace;
            }
            case  114 :
            {
                return emdamage;
            }
            case  99 :
            {
                return empfieldrange;
            }
            case  281 :
            {
                return explosiondelay;
            }
            case  107 :
            {
                return explosionrange;
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
            case  137 :
            {
                return launchergroup;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  525 :
            {
                return structureuniformity;
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
    public IMetaGroup<Bomb> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Bomb>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/Bomb.yaml";
        private Map<Integer, Bomb> cache = (null);

        @Override
        public IMetaCategory<? super Bomb> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  90;
        }

        @Override
        public String getName() {
            return "Bomb";
        }

        @Override
        public synchronized Map<Integer, Bomb> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Bomb.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Bomb> types;
        }
    }
}
