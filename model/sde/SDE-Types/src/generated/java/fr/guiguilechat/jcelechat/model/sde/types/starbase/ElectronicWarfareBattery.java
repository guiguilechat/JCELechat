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
import fr.guiguilechat.jcelechat.model.sde.attributes.ControlTowerMinimumDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowOffensiveModifiers;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityAttackDelayMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityAttackDelayMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFlyRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityReactionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.EwTargetJam;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffEffectiveness;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiCapitalMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IncapacitationRatio;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.OnliningDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.PosPlayerControlStructure;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.ProximityRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TargetSwitchDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.UnanchoringDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class ElectronicWarfareBattery
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
     * Used for target jam effects to reduce max locked targets of victem to a negative value to ensure the victem looses its targets, use extreme value
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ewtargetjam;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double explosivedamageresonance;
    /**
     * distance from maximum range at which effectiveness has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int falloffeffectiveness;
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
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
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
     * Attribute ID of the resistance type v's this Ewar module.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int remoteresistanceid;
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
     * +/- modifier to the gravimetric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scangravimetricstrengthbonus;
    /**
     * +/- modifier to the ladar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanladarstrengthbonus;
    /**
     * +/- modifier to the magnetometric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanmagnetometricstrengthbonus;
    /**
     * +/- modifier to the radar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanradarstrengthbonus;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldCapacity.INSTANCE, Uniformity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, ControlTowerMinimumDistance.INSTANCE, StructureUniformity.INSTANCE, PosPlayerControlStructure.INSTANCE, RequiredSkill1Level.INSTANCE, ProximityRange.INSTANCE, IncapacitationRatio.INSTANCE, MetaGroupID.INSTANCE, Power.INSTANCE, EntityFlyRange.INSTANCE, Radius.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, AnchoringDelay.INSTANCE, Cpu.INSTANCE, TargetSwitchDelay.INSTANCE, ScanResolution.INSTANCE, RequiredSkill1 .INSTANCE, MaxRange.INSTANCE, EwTargetJam.INSTANCE, MaxLockedTargets.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, Duration.INSTANCE, MaxTargetRange.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, EntityReactionFactor.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteResistanceID.INSTANCE, EntityAttackDelayMin.INSTANCE, EntityAttackDelayMax.INSTANCE, ShieldRechargeRate.INSTANCE, ShieldUniformity.INSTANCE, DisallowOffensiveModifiers.INSTANCE, KineticDamageResonance.INSTANCE, ScanGravimetricStrengthBonus.INSTANCE, ThermalDamageResonance.INSTANCE, ScanLadarStrengthBonus.INSTANCE, ExplosiveDamageResonance.INSTANCE, ScanMagnetometricStrengthBonus.INSTANCE, ScanRadarStrengthBonus.INSTANCE, EmDamageResonance.INSTANCE, FalloffEffectiveness.INSTANCE })));
    public static final ElectronicWarfareBattery.MetaGroup METAGROUP = new ElectronicWarfareBattery.MetaGroup();

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
            case  831 :
            {
                return ewtargetjam;
            }
            case  111 :
            {
                return explosivedamageresonance;
            }
            case  2044 :
            {
                return falloffeffectiveness;
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
            case  54 :
            {
                return maxrange;
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
            case  2138 :
            {
                return remoteresistanceid;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  238 :
            {
                return scangravimetricstrengthbonus;
            }
            case  239 :
            {
                return scanladarstrengthbonus;
            }
            case  240 :
            {
                return scanmagnetometricstrengthbonus;
            }
            case  241 :
            {
                return scanradarstrengthbonus;
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
    public IMetaGroup<ElectronicWarfareBattery> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ElectronicWarfareBattery>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/ElectronicWarfareBattery.yaml";
        private Map<String, ElectronicWarfareBattery> cache = (null);

        @Override
        public IMetaCategory<? super ElectronicWarfareBattery> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  439;
        }

        @Override
        public String getName() {
            return "ElectronicWarfareBattery";
        }

        @Override
        public synchronized Map<String, ElectronicWarfareBattery> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ElectronicWarfareBattery.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, ElectronicWarfareBattery> types;
        }
    }
}
