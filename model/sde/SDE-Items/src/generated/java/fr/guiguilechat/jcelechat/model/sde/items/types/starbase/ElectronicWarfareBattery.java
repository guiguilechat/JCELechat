package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;
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
    public int ControlTowerMinimumDistance;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double EmDamageResonance;
    /**
     * Maximum attack delay time for entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityAttackDelayMax;
    /**
     * Minimum attack delay time for entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityAttackDelayMin;
    /**
     * The distance at which the entity orbits, follows.. and more.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(500.0)
    public double EntityFlyRange;
    /**
     * The chance of an entity attacking the same person as its group members.  Scales delay in joining in on fights too.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double EntityReactionFactor;
    /**
     * Used for target jam effects to reduce max locked targets of victem to a negative value to ensure the victem looses its targets, use extreme value
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EwTargetJam;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ExplosiveDamageResonance;
    /**
     * distance from maximum range at which effectiveness has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FalloffEffectiveness;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * The hull damage proportion at which an entity becomes incapacitated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double IncapacitationRatio;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double KineticDamageResonance;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxRange;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxTargetRange;
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
    public int MetaGroupID;
    /**
     * If a starbase structure has this attribute = 1 then it can be controlled by owners with infrastructure tactical officer skill and corp role.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PosPlayerControlStructure;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * The distance at which to react when relevant objects come within range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ProximityRange;
    /**
     * Attribute ID of the resistance type v's this Ewar module.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RemoteResistanceID;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * +/- modifier to the gravimetric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanGravimetricStrengthBonus;
    /**
     * +/- modifier to the ladar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanLadarStrengthBonus;
    /**
     * +/- modifier to the magnetometric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanMagnetometricStrengthBonus;
    /**
     * +/- modifier to the radar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanRadarStrengthBonus;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanResolution;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldUniformity;
    /**
     * The amount of time after attacking a target that an entity will wait before switching to a new one.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TargetSwitchDelay;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ThermalDamageResonance;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    public final static ElectronicWarfareBattery.MetaGroup METAGROUP = new ElectronicWarfareBattery.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1165 :
            {
                return ControlTowerMinimumDistance;
            }
            case  50 :
            {
                return Cpu;
            }
            case  73 :
            {
                return Duration;
            }
            case  113 :
            {
                return EmDamageResonance;
            }
            case  476 :
            {
                return EntityAttackDelayMax;
            }
            case  475 :
            {
                return EntityAttackDelayMin;
            }
            case  416 :
            {
                return EntityFlyRange;
            }
            case  466 :
            {
                return EntityReactionFactor;
            }
            case  831 :
            {
                return EwTargetJam;
            }
            case  111 :
            {
                return ExplosiveDamageResonance;
            }
            case  2044 :
            {
                return FalloffEffectiveness;
            }
            case  2244 :
            {
                return FighterAbilityAntiCapitalMissileResistance;
            }
            case  156 :
            {
                return IncapacitationRatio;
            }
            case  109 :
            {
                return KineticDamageResonance;
            }
            case  192 :
            {
                return MaxLockedTargets;
            }
            case  54 :
            {
                return MaxRange;
            }
            case  76 :
            {
                return MaxTargetRange;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  1167 :
            {
                return PosPlayerControlStructure;
            }
            case  30 :
            {
                return Power;
            }
            case  154 :
            {
                return ProximityRange;
            }
            case  2138 :
            {
                return RemoteResistanceID;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  238 :
            {
                return ScanGravimetricStrengthBonus;
            }
            case  239 :
            {
                return ScanLadarStrengthBonus;
            }
            case  240 :
            {
                return ScanMagnetometricStrengthBonus;
            }
            case  241 :
            {
                return ScanRadarStrengthBonus;
            }
            case  564 :
            {
                return ScanResolution;
            }
            case  484 :
            {
                return ShieldUniformity;
            }
            case  691 :
            {
                return TargetSwitchDelay;
            }
            case  110 :
            {
                return ThermalDamageResonance;
            }
            case  136 :
            {
                return Uniformity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<ElectronicWarfareBattery> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ElectronicWarfareBattery>
    {
        public final static String RESOURCE_PATH = "SDE/items/starbase/ElectronicWarfareBattery.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(ElectronicWarfareBattery.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ElectronicWarfareBattery> items;
        }
    }
}
