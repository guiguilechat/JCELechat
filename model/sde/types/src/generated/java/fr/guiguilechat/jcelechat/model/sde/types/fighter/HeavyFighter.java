package fr.guiguilechat.jcelechat.model.sde.types.fighter;

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
import fr.guiguilechat.jcelechat.model.sde.types.Fighter;

public class HeavyFighter
    extends Fighter
{
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityafterburnerduration;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(100)
    public int fighterabilityafterburnerspeedbonus;
    /**
     * EM Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityattackmissiledamageem;
    /**
     * Explosive Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissiledamageexp;
    /**
     * Kinetic Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityattackmissiledamagekin;
    /**
     * Damage Multiplier
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissiledamagemultiplier;
    /**
     * Thermal Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissiledamagetherm;
    /**
     * Rate of fire
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissileduration;
    /**
     * Explosion Radius
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissileexplosionradius;
    /**
     * Explosion Velocity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissileexplosionvelocity;
    /**
     * Accuracy Falloff
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissilerangefalloff;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissilerangeoptimal;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityattackmissilereductionfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityattackmissilereductionsensitivity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitykamikazedamageem;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitykamikazedamageexp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitykamikazedamagekin;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitykamikazedamagetherm;
    /**
     * Range at which the fighters Explode from the target
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(500)
    public int fighterabilitykamikazerange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitykamikazeresistanceid;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitykamikazesignatureradius;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitylaunchbombduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitylaunchbombtype;
    /**
     * Jump Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitymicrojumpdrivedistance;
    /**
     * Duration
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitymicrojumpdriveduration;
    /**
     * Signature Radius Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitymicrojumpdrivesignatureradiusbonus;
    /**
     * EM Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesdamageem;
    /**
     * Explosive Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilitymissilesdamageexp;
    /**
     * Kinetic Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilitymissilesdamagekin;
    /**
     * Damage Multiplier
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesdamagemultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilitymissilesdamagereductionfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilitymissilesdamagereductionsensitivity;
    /**
     * Thermal Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesdamagetherm;
    /**
     * Rate of fire
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesduration;
    /**
     * Explosion Radius
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesexplosionradius;
    /**
     * Explosion Velocity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesexplosionvelocity;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitymissilesresistanceid;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightersquadronisheavy;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {FighterAbilityKamikazeResistanceID.INSTANCE, FighterAbilityMissilesDuration.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, StructureUniformity.INSTANCE, FighterAbilityAntiFighterMissileResistance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FighterAbilityLaunchBombType.INSTANCE, RequiredSkill1Level.INSTANCE, FighterAbilityKamikazeDamageEM.INSTANCE, RequiredSkill2Level.INSTANCE, FighterAbilityKamikazeDamageTherm.INSTANCE, FighterAbilityKamikazeDamageKin.INSTANCE, FighterAbilityKamikazeDamageExp.INSTANCE, FighterAbilityKamikazeSignatureRadius.INSTANCE, FighterAbilityKamikazeRange.INSTANCE, MetaGroupID.INSTANCE, MaxVelocity.INSTANCE, FighterSquadronIsHeavy.INSTANCE, TechLevel.INSTANCE, FighterSquadronMaxSize.INSTANCE, SignatureRadius.INSTANCE, FighterAbilityLaunchBombDuration.INSTANCE, FighterSquadronOrbitRange.INSTANCE, FighterAbilityAttackMissileDamageMultiplier.INSTANCE, FighterAbilityAttackMissileDamageEM.INSTANCE, FighterAbilityAttackMissileDamageTherm.INSTANCE, ScanResolution.INSTANCE, FighterAbilityAttackMissileDamageKin.INSTANCE, RequiredSkill1 .INSTANCE, FighterAbilityAttackMissileDamageExp.INSTANCE, RequiredSkill2 .INSTANCE, FighterAbilityAttackMissileReductionFactor.INSTANCE, FighterAbilityAttackMissileReductionSensitivity.INSTANCE, FighterAbilityAttackMissileDuration.INSTANCE, FighterAbilityAttackMissileExplosionRadius.INSTANCE, FighterAbilityAttackMissileExplosionVelocity.INSTANCE, FighterAbilityAttackMissileRangeOptimal.INSTANCE, FighterAbilityAttackMissileRangeFalloff.INSTANCE, MaxLockedTargets.INSTANCE, RemoteRepairImpedance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, FighterAbilityMissilesExplosionRadius.INSTANCE, FighterAbilityMissilesExplosionVelocity.INSTANCE, FighterAbilityMissilesDamageReductionFactor.INSTANCE, FighterAbilityMissilesDamageReductionSensitivity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, FighterAbilityMissilesDamageMultiplier.INSTANCE, ScanMagnetometricStrength.INSTANCE, FighterAbilityMissilesDamageEM.INSTANCE, ScanGravimetricStrength.INSTANCE, FighterAbilityMissilesDamageTherm.INSTANCE, FighterAbilityMissilesDamageKin.INSTANCE, FighterAbilityMissilesDamageExp.INSTANCE, RemoteAssistanceImpedance.INSTANCE, WarpSpeedMultiplier.INSTANCE, FighterSquadronRole.INSTANCE, ShieldRechargeRate.INSTANCE, ShieldUniformity.INSTANCE, FighterAbilityMissilesRange.INSTANCE, FighterAbilityAfterburnerSpeedBonus.INSTANCE, FighterAbilityMicroWarpDriveSpeedBonus.INSTANCE, FighterAbilityMicroWarpDriveSignatureRadiusBonus.INSTANCE, FighterAbilityMicroJumpDriveDistance.INSTANCE, FighterAbilityMicroJumpDriveDuration.INSTANCE, FighterAbilityMicroJumpDriveSignatureRadiusBonus.INSTANCE, FighterAbilityMicroWarpDriveDuration.INSTANCE, FighterAbilityAfterburnerDuration.INSTANCE, MetaLevelOld.INSTANCE, FighterAbilityMissilesResistanceID.INSTANCE, FighterRefuelingTime.INSTANCE })));
    public static final HeavyFighter.MetaGroup METAGROUP = new HeavyFighter.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2158 :
            {
                return fighterabilityafterburnerduration;
            }
            case  2151 :
            {
                return fighterabilityafterburnerspeedbonus;
            }
            case  2227 :
            {
                return fighterabilityattackmissiledamageem;
            }
            case  2230 :
            {
                return fighterabilityattackmissiledamageexp;
            }
            case  2229 :
            {
                return fighterabilityattackmissiledamagekin;
            }
            case  2226 :
            {
                return fighterabilityattackmissiledamagemultiplier;
            }
            case  2228 :
            {
                return fighterabilityattackmissiledamagetherm;
            }
            case  2233 :
            {
                return fighterabilityattackmissileduration;
            }
            case  2234 :
            {
                return fighterabilityattackmissileexplosionradius;
            }
            case  2235 :
            {
                return fighterabilityattackmissileexplosionvelocity;
            }
            case  2237 :
            {
                return fighterabilityattackmissilerangefalloff;
            }
            case  2236 :
            {
                return fighterabilityattackmissilerangeoptimal;
            }
            case  2231 :
            {
                return fighterabilityattackmissilereductionfactor;
            }
            case  2232 :
            {
                return fighterabilityattackmissilereductionsensitivity;
            }
            case  2325 :
            {
                return fighterabilitykamikazedamageem;
            }
            case  2328 :
            {
                return fighterabilitykamikazedamageexp;
            }
            case  2327 :
            {
                return fighterabilitykamikazedamagekin;
            }
            case  2326 :
            {
                return fighterabilitykamikazedamagetherm;
            }
            case  2330 :
            {
                return fighterabilitykamikazerange;
            }
            case  2432 :
            {
                return fighterabilitykamikazeresistanceid;
            }
            case  2329 :
            {
                return fighterabilitykamikazesignatureradius;
            }
            case  2349 :
            {
                return fighterabilitylaunchbombduration;
            }
            case  2324 :
            {
                return fighterabilitylaunchbombtype;
            }
            case  2154 :
            {
                return fighterabilitymicrojumpdrivedistance;
            }
            case  2155 :
            {
                return fighterabilitymicrojumpdriveduration;
            }
            case  2156 :
            {
                return fighterabilitymicrojumpdrivesignatureradiusbonus;
            }
            case  2131 :
            {
                return fighterabilitymissilesdamageem;
            }
            case  2134 :
            {
                return fighterabilitymissilesdamageexp;
            }
            case  2133 :
            {
                return fighterabilitymissilesdamagekin;
            }
            case  2130 :
            {
                return fighterabilitymissilesdamagemultiplier;
            }
            case  2127 :
            {
                return fighterabilitymissilesdamagereductionfactor;
            }
            case  2128 :
            {
                return fighterabilitymissilesdamagereductionsensitivity;
            }
            case  2132 :
            {
                return fighterabilitymissilesdamagetherm;
            }
            case  2182 :
            {
                return fighterabilitymissilesduration;
            }
            case  2125 :
            {
                return fighterabilitymissilesexplosionradius;
            }
            case  2126 :
            {
                return fighterabilitymissilesexplosionvelocity;
            }
            case  2149 :
            {
                return fighterabilitymissilesrange;
            }
            case  2170 :
            {
                return fighterabilitymissilesresistanceid;
            }
            case  2214 :
            {
                return fightersquadronisheavy;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
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
    public IMetaGroup<HeavyFighter> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HeavyFighter>
    {
        public static final String RESOURCE_PATH = "SDE/types/fighter/HeavyFighter.yaml";
        private Map<Integer, HeavyFighter> cache = (null);

        @Override
        public IMetaCategory<? super HeavyFighter> category() {
            return Fighter.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1653;
        }

        @Override
        public String getName() {
            return "HeavyFighter";
        }

        @Override
        public synchronized Map<Integer, HeavyFighter> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(HeavyFighter.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, HeavyFighter> types;
        }
    }
}
