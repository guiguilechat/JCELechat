package fr.guiguilechat.jcelechat.model.sde.types.starbase;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AnchoringDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowOffensiveModifiers;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonanceMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonanceMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiCapitalMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonanceMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.OnliningDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonanceMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.UnanchoringDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;
import org.yaml.snakeyaml.Yaml;

public class ShieldHardeningArray
    extends Starbase
{
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * Multiplier to the EM damage resonance of something.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double emdamageresonancemultiplier;
    /**
     * Multiplier to the explosive damage resistance of something.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double explosivedamageresonancemultiplier;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityanticapitalmissileresistance;
    /**
     * Multiplier to the kinetic damage resonance of something.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double kineticdamageresonancemultiplier;
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
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shielduniformity;
    /**
     * Multipler to adjust the thermal damage resonance of an object.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double thermaldamageresonancemultiplier;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double uniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ThermalDamageResonanceMultiplier.INSTANCE, KineticDamageResonanceMultiplier.INSTANCE, ExplosiveDamageResonanceMultiplier.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, EmDamageResonanceMultiplier.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RequiredSkill1Level.INSTANCE, Power.INSTANCE, ShieldRechargeRate.INSTANCE, Radius.INSTANCE, ShieldUniformity.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, DisallowOffensiveModifiers.INSTANCE, AnchoringDelay.INSTANCE, Cpu.INSTANCE, RequiredSkill1 .INSTANCE })));
    public static final ShieldHardeningArray.MetaGroup METAGROUP = new ShieldHardeningArray.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  50 :
            {
                return cpu;
            }
            case  133 :
            {
                return emdamageresonancemultiplier;
            }
            case  132 :
            {
                return explosivedamageresonancemultiplier;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  131 :
            {
                return kineticdamageresonancemultiplier;
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
            case  484 :
            {
                return shielduniformity;
            }
            case  130 :
            {
                return thermaldamageresonancemultiplier;
            }
            case  136 :
            {
                return uniformity;
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
    public IMetaGroup<ShieldHardeningArray> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ShieldHardeningArray>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/ShieldHardeningArray.yaml";
        private Map<String, ShieldHardeningArray> cache = (null);

        @Override
        public IMetaCategory<? super ShieldHardeningArray> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  444;
        }

        @Override
        public String getName() {
            return "ShieldHardeningArray";
        }

        @Override
        public synchronized Map<String, ShieldHardeningArray> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ShieldHardeningArray.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ShieldHardeningArray> types;
        }
    }
}
