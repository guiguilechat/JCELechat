package fr.guiguilechat.jcelechat.model.sde.types.drone;

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
import fr.guiguilechat.jcelechat.model.sde.types.Drone;

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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {DamageMultiplier.INSTANCE, MaxLockedTargets.INSTANCE, RequiredSkill4 .INSTANCE, AccessDifficultyBonus.INSTANCE, RequiredSkill4Level.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, FighterAbilityAntiFighterMissileResistance.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, ProximityRange.INSTANCE, OrbitRange.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, MaxVelocity.INSTANCE, TechLevel.INSTANCE, SignatureRadius.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, MaxRange.INSTANCE, RequiredSkill1 .INSTANCE, RechargeRate.INSTANCE, RequiredSkill2 .INSTANCE, RequiredSkill3 .INSTANCE, DroneBandwidthUsed.INSTANCE })));
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
        private Map<Integer, SalvageDrone> cache = (null);

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
        public synchronized Map<Integer, SalvageDrone> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SalvageDrone.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SalvageDrone> types;
        }
    }
}
