package fr.guiguilechat.jcelechat.model.sde.types.drone;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AccessDifficultyBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidthUsed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiFighterMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.OrbitRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.ProximityRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill4;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill4Level;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.types.Drone;
import org.yaml.snakeyaml.Yaml;

public class SalvageDrone
    extends Drone
{
    /**
     * Bonus to chance of opening a container.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double accessdifficultybonus;
    /**
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double damagemultiplier;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
    /**
     * The range at which this thing does it thing.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int orbitrange;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill4;
    /**
     * Required skill level for skill 4
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill4level;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {DamageMultiplier.INSTANCE, MaxLockedTargets.INSTANCE, Mass.INSTANCE, RequiredSkill4 .INSTANCE, AccessDifficultyBonus.INSTANCE, RequiredSkill4Level.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, ShieldCharge.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, FighterAbilityAntiFighterMissileResistance.INSTANCE, StructureUniformity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, Charge.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, ProximityRange.INSTANCE, OrbitRange.INSTANCE, ShieldRechargeRate.INSTANCE, Radius.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, MaxVelocity.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, MaxRange.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, RechargeRate.INSTANCE, RequiredSkill3 .INSTANCE, DroneBandwidthUsed.INSTANCE })));
    public static final SalvageDrone.MetaGroup METAGROUP = new SalvageDrone.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  902 :
            {
                return accessdifficultybonus;
            }
            case  64 :
            {
                return damagemultiplier;
            }
            case  73 :
            {
                return duration;
            }
            case  54 :
            {
                return maxrange;
            }
            case  157 :
            {
                return orbitrange;
            }
            case  1285 :
            {
                return requiredskill4;
            }
            case  1286 :
            {
                return requiredskill4level;
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
    public IMetaGroup<SalvageDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SalvageDrone>
    {
        public static final String RESOURCE_PATH = "SDE/types/drone/SalvageDrone.yaml";
        private Map<String, SalvageDrone> cache = (null);

        @Override
        public IMetaCategory<? super SalvageDrone> category() {
            return Drone.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1159;
        }

        @Override
        public String getName() {
            return "SalvageDrone";
        }

        @Override
        public synchronized Map<String, SalvageDrone> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SalvageDrone.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SalvageDrone> types;
        }
    }
}
