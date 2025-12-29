package fr.guiguilechat.jcelechat.model.sde.types.starbase;

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
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;

public class EnergyNeutralizingBattery
    extends Starbase
{
    /**
     * Minimum distance where a starbase structure can be anchored at from the control tower shield extremity in meters.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerminimumdistance;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double emdamageresonance;
    /**
     * An amount to modify the power of the target by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double energyneutralizeramount;
    /**
     * Falloff Range of Energy Neutralizer
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int energyneutralizerrangefalloff;
    /**
     * Optimal Range of Energy Neutralizer
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int energyneutralizerrangeoptimal;
    /**
     * Signature Resolution of Energy Neutralizer
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int energyneutralizersignatureresolution;
    /**
     * Maximum attack delay time for entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityattackdelaymax;
    /**
     * Minimum attack delay time for entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityattackdelaymin;
    /**
     * The distance at which the entity orbits, follows.. and more.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(500.0)
    public double entityflyrange;
    /**
     * The chance of an entity attacking the same person as its group members.  Scales delay in joining in on fights too.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double entityreactionfactor;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double explosivedamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityanticapitalmissileresistance;
    /**
     * The hull damage proportion at which an entity becomes incapacitated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double incapacitationratio;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double kineticdamageresonance;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxlockedtargets;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxtargetrange;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
    /**
     * If a starbase structure has this attribute = 1 then it can be controlled by owners with infrastructure tactical officer skill and corp role.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int posplayercontrolstructure;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * The distance at which to react when relevant objects come within range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int proximityrange;
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
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shielduniformity;
    /**
     * The amount of time after attacking a target that an entity will wait before switching to a new one.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int targetswitchdelay;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double thermaldamageresonance;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double uniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {MaxLockedTargets.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, MaxTargetRange.INSTANCE, ArmorUniformity.INSTANCE, ControlTowerMinimumDistance.INSTANCE, StructureUniformity.INSTANCE, PosPlayerControlStructure.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, EntityReactionFactor.INSTANCE, ScanMagnetometricStrength.INSTANCE, EnergyNeutralizerSignatureResolution.INSTANCE, ScanGravimetricStrength.INSTANCE, EnergyNeutralizerRangeFalloff.INSTANCE, RequiredSkill1Level.INSTANCE, ProximityRange.INSTANCE, EntityAttackDelayMin.INSTANCE, IncapacitationRatio.INSTANCE, EntityAttackDelayMax.INSTANCE, MetaGroupID.INSTANCE, Power.INSTANCE, ShieldRechargeRate.INSTANCE, EntityFlyRange.INSTANCE, EnergyNeutralizerAmount.INSTANCE, EnergyNeutralizerRangeOptimal.INSTANCE, ShieldUniformity.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, SignatureRadius.INSTANCE, DisallowOffensiveModifiers.INSTANCE, AnchoringDelay.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, Cpu.INSTANCE, TargetSwitchDelay.INSTANCE, ScanResolution.INSTANCE, RequiredSkill1 .INSTANCE })));
    public static final EnergyNeutralizingBattery.MetaGroup METAGROUP = new EnergyNeutralizingBattery.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1165 :
            {
                return controltowerminimumdistance;
            }
            case  50 :
            {
                return cpu;
            }
            case  73 :
            {
                return duration;
            }
            case  113 :
            {
                return emdamageresonance;
            }
            case  97 :
            {
                return energyneutralizeramount;
            }
            case  2452 :
            {
                return energyneutralizerrangefalloff;
            }
            case  98 :
            {
                return energyneutralizerrangeoptimal;
            }
            case  2451 :
            {
                return energyneutralizersignatureresolution;
            }
            case  476 :
            {
                return entityattackdelaymax;
            }
            case  475 :
            {
                return entityattackdelaymin;
            }
            case  416 :
            {
                return entityflyrange;
            }
            case  466 :
            {
                return entityreactionfactor;
            }
            case  111 :
            {
                return explosivedamageresonance;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  156 :
            {
                return incapacitationratio;
            }
            case  109 :
            {
                return kineticdamageresonance;
            }
            case  192 :
            {
                return maxlockedtargets;
            }
            case  76 :
            {
                return maxtargetrange;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  1167 :
            {
                return posplayercontrolstructure;
            }
            case  30 :
            {
                return power;
            }
            case  154 :
            {
                return proximityrange;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  484 :
            {
                return shielduniformity;
            }
            case  691 :
            {
                return targetswitchdelay;
            }
            case  110 :
            {
                return thermaldamageresonance;
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
    public IMetaGroup<EnergyNeutralizingBattery> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EnergyNeutralizingBattery>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/EnergyNeutralizingBattery.yaml";
        private Map<Integer, EnergyNeutralizingBattery> cache = (null);

        @Override
        public IMetaCategory<? super EnergyNeutralizingBattery> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  837;
        }

        @Override
        public String getName() {
            return "EnergyNeutralizingBattery";
        }

        @Override
        public synchronized Map<Integer, EnergyNeutralizingBattery> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(EnergyNeutralizingBattery.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, EnergyNeutralizingBattery> types;
        }
    }
}
