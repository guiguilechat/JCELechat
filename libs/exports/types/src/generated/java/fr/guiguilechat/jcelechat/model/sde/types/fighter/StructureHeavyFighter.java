package fr.guiguilechat.jcelechat.model.sde.types.fighter;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAfterburnerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAfterburnerSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiFighterMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageEM;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageExp;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageKin;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageTherm;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileExplosionRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileExplosionVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileRangeFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileRangeOptimal;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileReductionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileReductionSensitivity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityKamikazeDamageEM;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityKamikazeDamageExp;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityKamikazeDamageKin;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityKamikazeDamageTherm;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityKamikazeRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityKamikazeResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityKamikazeSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityLaunchBombDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityLaunchBombType;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroJumpDriveDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroJumpDriveDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroJumpDriveSignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageEM;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageExp;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageKin;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageReductionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageReductionSensitivity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageTherm;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesExplosionRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesExplosionVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterRefuelingTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronIsStandupHeavy;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronMaxSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronOrbitRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronRole;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteAssistanceImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepairImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.Fighter;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class StructureHeavyFighter
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
    public int fightersquadronisstandupheavy;
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
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureitemvisualflag;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {FighterAbilityKamikazeResistanceID.INSTANCE, FighterAbilityMissilesDuration.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, StructureUniformity.INSTANCE, FighterAbilityAntiFighterMissileResistance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FighterAbilityLaunchBombType.INSTANCE, FighterAbilityKamikazeDamageEM.INSTANCE, FighterAbilityKamikazeDamageTherm.INSTANCE, FighterAbilityKamikazeDamageKin.INSTANCE, FighterAbilityKamikazeDamageExp.INSTANCE, FighterAbilityKamikazeSignatureRadius.INSTANCE, FighterAbilityKamikazeRange.INSTANCE, MetaGroupID.INSTANCE, StructureItemVisualFlag.INSTANCE, MaxVelocity.INSTANCE, TechLevel.INSTANCE, FighterSquadronMaxSize.INSTANCE, SignatureRadius.INSTANCE, FighterAbilityLaunchBombDuration.INSTANCE, FighterSquadronOrbitRange.INSTANCE, FighterAbilityAttackMissileDamageMultiplier.INSTANCE, FighterAbilityAttackMissileDamageEM.INSTANCE, FighterAbilityAttackMissileDamageTherm.INSTANCE, ScanResolution.INSTANCE, FighterAbilityAttackMissileDamageKin.INSTANCE, FighterAbilityAttackMissileDamageExp.INSTANCE, FighterSquadronIsStandupHeavy.INSTANCE, FighterAbilityAttackMissileReductionFactor.INSTANCE, FighterAbilityAttackMissileReductionSensitivity.INSTANCE, FighterAbilityAttackMissileDuration.INSTANCE, FighterAbilityAttackMissileExplosionRadius.INSTANCE, FighterAbilityAttackMissileExplosionVelocity.INSTANCE, FighterAbilityAttackMissileRangeOptimal.INSTANCE, FighterAbilityAttackMissileRangeFalloff.INSTANCE, MaxLockedTargets.INSTANCE, RemoteRepairImpedance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, FighterAbilityMissilesExplosionRadius.INSTANCE, FighterAbilityMissilesExplosionVelocity.INSTANCE, FighterAbilityMissilesDamageReductionFactor.INSTANCE, FighterAbilityMissilesDamageReductionSensitivity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, FighterAbilityMissilesDamageMultiplier.INSTANCE, ScanMagnetometricStrength.INSTANCE, FighterAbilityMissilesDamageEM.INSTANCE, ScanGravimetricStrength.INSTANCE, FighterAbilityMissilesDamageTherm.INSTANCE, FighterAbilityMissilesDamageKin.INSTANCE, FighterAbilityMissilesDamageExp.INSTANCE, RemoteAssistanceImpedance.INSTANCE, WarpSpeedMultiplier.INSTANCE, FighterSquadronRole.INSTANCE, ShieldRechargeRate.INSTANCE, ShieldUniformity.INSTANCE, FighterAbilityMissilesRange.INSTANCE, FighterAbilityAfterburnerSpeedBonus.INSTANCE, FighterAbilityMicroWarpDriveSpeedBonus.INSTANCE, FighterAbilityMicroWarpDriveSignatureRadiusBonus.INSTANCE, FighterAbilityMicroJumpDriveDistance.INSTANCE, FighterAbilityMicroJumpDriveDuration.INSTANCE, FighterAbilityMicroJumpDriveSignatureRadiusBonus.INSTANCE, FighterAbilityMicroWarpDriveDuration.INSTANCE, FighterAbilityAfterburnerDuration.INSTANCE, MetaLevelOld.INSTANCE, FighterAbilityMissilesResistanceID.INSTANCE, FighterRefuelingTime.INSTANCE })));
    public static final StructureHeavyFighter.MetaGroup METAGROUP = new StructureHeavyFighter.MetaGroup();

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
            case  2742 :
            {
                return fightersquadronisstandupheavy;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  2334 :
            {
                return structureitemvisualflag;
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
    public IMetaGroup<StructureHeavyFighter> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureHeavyFighter>
    {
        public static final String RESOURCE_PATH = "SDE/types/fighter/StructureHeavyFighter.yaml";
        private Map<Integer, StructureHeavyFighter> cache = (null);

        @Override
        public IMetaCategory<? super StructureHeavyFighter> category() {
            return Fighter.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4779;
        }

        @Override
        public String getName() {
            return "StructureHeavyFighter";
        }

        @Override
        public synchronized Map<Integer, StructureHeavyFighter> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureHeavyFighter.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, StructureHeavyFighter> types;
        }
    }
}
