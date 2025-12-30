package fr.guiguilechat.jcelechat.model.sde.types.ship;

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
import fr.guiguilechat.jcelechat.model.sde.types.Ship;

public class MiningBarge
    extends Ship
{
    /**
     * Tells if this type (ship) can be affected by the Rorqual Invulnerability Module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int affectedbyindustrialinvulnmodule;
    /**
     * Tells if this type (ship) can be placed in the maintenance bay of a capital industrial ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int allowedincapindustrialmaintenancebay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
    /**
     * Capacity of general mining hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int generalminingholdcapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationhi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationlow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationmed;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hislots;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int integratedminingscanner;
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int iscarrierjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to be carried through an Industrial Jump Conduit.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isindustrialjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through an Industrial Jump Portal
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isindustrialjumpportalpassenger;
    /**
     * The number of low power slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int lowslots;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int maxdirectionalvelocity;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int medslots;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double mintargetveldmgmultiplier;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusgasharvestingduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusgeneralminingholdcapacity;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusiceharvestingduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusiceharvestingrange;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusoreminingrange;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusoreminingyield;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusshieldcapacity;
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
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigslots;
    /**
     * Bonus to drone damage, HP and mining yield
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiedronebonus;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int scanspeed;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusdronedamage;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusdronehitpoints;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shiprolebonusgasharvesterduration;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusiceharvesteractivationcost;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shiprolebonusiceharvestingduration;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusoreminingduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusoreminingyield;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusstripmineractivationcost;
    /**
     * Chance of being able to resist a ship scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipscanresistance;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, GeneralMiningHoldCapacity.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, RookieDroneBonus.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, IsCarrierJumpConduitPassenger.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, WarpSpeedMultiplier.INSTANCE, IntegratedMiningScanner.INSTANCE, AllowedInCapIndustrialMaintenanceBay.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, ShipRoleBonusOreMiningYield.INSTANCE, ShipRoleBonusIceHarvestingDuration.INSTANCE, ShipRoleBonusDroneDamage.INSTANCE, UpgradeCapacity.INSTANCE, ShipRoleBonusDroneHitPoints.INSTANCE, MiningBargeBonusOreMiningYield.INSTANCE, KineticDamageResonance.INSTANCE, MiningBargeBonusIceHarvestingDuration.INSTANCE, ThermalDamageResonance.INSTANCE, MiningBargeBonusGasHarvestingDuration.INSTANCE, ExplosiveDamageResonance.INSTANCE, MiningBargeBonusOreMiningRange.INSTANCE, RigSlots.INSTANCE, MiningBargeBonusIceHarvestingRange.INSTANCE, EmDamageResonance.INSTANCE, MiningBargeBonusGeneralMiningHoldCapacity.INSTANCE, MiningBargeBonusShieldCapacity.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, ShipRoleBonusGasHarvesterDuration.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, ShipRoleBonusStripMinerActivationCost.INSTANCE, ShipRoleBonusIceHarvesterActivationCost.INSTANCE, ShipRoleBonusOreMiningDuration.INSTANCE, AffectedByIndustrialInvulnModule.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, IsIndustrialJumpConduitPassenger.INSTANCE, IsIndustrialJumpPortalPassenger.INSTANCE, EnergyWarfareResistance.INSTANCE, ShipScanResistance.INSTANCE })));
    public static final MiningBarge.MetaGroup METAGROUP = new MiningBarge.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2464 :
            {
                return affectedbyindustrialinvulnmodule;
            }
            case  1891 :
            {
                return allowedincapindustrialmaintenancebay;
            }
            case  1555 :
            {
                return fwlpkill;
            }
            case  1556 :
            {
                return generalminingholdcapacity;
            }
            case  1259 :
            {
                return heatattenuationhi;
            }
            case  1262 :
            {
                return heatattenuationlow;
            }
            case  1261 :
            {
                return heatattenuationmed;
            }
            case  14 :
            {
                return hislots;
            }
            case  5978 :
            {
                return integratedminingscanner;
            }
            case  5682 :
            {
                return iscarrierjumpconduitpassenger;
            }
            case  3324 :
            {
                return isindustrialjumpconduitpassenger;
            }
            case  3325 :
            {
                return isindustrialjumpportalpassenger;
            }
            case  12 :
            {
                return lowslots;
            }
            case  124 :
            {
                return maincolor;
            }
            case  661 :
            {
                return maxdirectionalvelocity;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  13 :
            {
                return medslots;
            }
            case  662 :
            {
                return mintargetveldmgmultiplier;
            }
            case  3183 :
            {
                return miningbargebonusgasharvestingduration;
            }
            case  3187 :
            {
                return miningbargebonusgeneralminingholdcapacity;
            }
            case  3182 :
            {
                return miningbargebonusiceharvestingduration;
            }
            case  3185 :
            {
                return miningbargebonusiceharvestingrange;
            }
            case  3184 :
            {
                return miningbargebonusoreminingrange;
            }
            case  3181 :
            {
                return miningbargebonusoreminingyield;
            }
            case  3188 :
            {
                return miningbargebonusshieldcapacity;
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
            case  1547 :
            {
                return rigsize;
            }
            case  1137 :
            {
                return rigslots;
            }
            case  1831 :
            {
                return rookiedronebonus;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  3179 :
            {
                return shiprolebonusdronedamage;
            }
            case  3180 :
            {
                return shiprolebonusdronehitpoints;
            }
            case  3225 :
            {
                return shiprolebonusgasharvesterduration;
            }
            case  3229 :
            {
                return shiprolebonusiceharvesteractivationcost;
            }
            case  3178 :
            {
                return shiprolebonusiceharvestingduration;
            }
            case  3230 :
            {
                return shiprolebonusoreminingduration;
            }
            case  3177 :
            {
                return shiprolebonusoreminingyield;
            }
            case  3228 :
            {
                return shiprolebonusstripmineractivationcost;
            }
            case  511 :
            {
                return shipscanresistance;
            }
            case  1768 :
            {
                return typecolorscheme;
            }
            case  1132 :
            {
                return upgradecapacity;
            }
            case  1154 :
            {
                return upgradeslotsleft;
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
    public IMetaGroup<MiningBarge> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MiningBarge>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/MiningBarge.yaml";
        private Map<Integer, MiningBarge> cache = (null);

        @Override
        public IMetaCategory<? super MiningBarge> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  463;
        }

        @Override
        public String getName() {
            return "MiningBarge";
        }

        @Override
        public synchronized Map<Integer, MiningBarge> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MiningBarge.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, MiningBarge> types;
        }
    }
}
