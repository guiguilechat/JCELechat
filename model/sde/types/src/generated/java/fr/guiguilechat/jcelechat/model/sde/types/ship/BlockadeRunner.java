package fr.guiguilechat.jcelechat.model.sde.types.ship;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AffectedByIndustrialInvulnModule;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.AllowedInCapIndustrialMaintenanceBay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseWarpSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CovertOpsAndReconOpsCloakModuleDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EliteBonusIndustrial1;
import fr.guiguilechat.jcelechat.model.sde.attributes.EliteBonusIndustrial2;
import fr.guiguilechat.jcelechat.model.sde.attributes.EliteIndustrialCovertCloakBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAttenuationHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAttenuationLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAttenuationMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatGenerationMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialBonusDroneDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsBlackOpsJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsBlackOpsJumpPortalPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsCarrierJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpFatigueMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerToSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusAI;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusCI;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusGI;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusMI;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusUH1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusUH2;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialColonyResourcesHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleStatus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class BlockadeRunner
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
    @Stackable(false)
    @DefaultIntValue(30000)
    public int covertopsandreconopscloakmoduledelay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonusindustrial1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonusindustrial2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int eliteindustrialcovertcloakbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialbonusdronedamage;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to be carried through a BlackOps Jump Conduit
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isblackopsjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through a BlackOps Jump Portal
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isblackopsjumpportalpassenger;
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int iscarrierjumpconduitpassenger;
    /**
     * Multiplier for jump fatigue distance
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double jumpfatiguemultiplier;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
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
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusai;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusci;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusgi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusmi;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusuh1;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusuh2;
    /**
     * Infrastructure Hold Capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialcolonyresourcesholdcapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    /**
     * Warp ability of a ship.  If greater than zero than the ship cannot warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblestatus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, CovertOpsAndReconOpsCloakModuleDelay.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, SpecialColonyResourcesHoldCapacity.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShipBonusUH1 .INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShipBonusUH2 .INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, IndustrialBonusDroneDamage.INSTANCE, WarpFactor.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, EliteBonusIndustrial1 .INSTANCE, EliteBonusIndustrial2 .INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, IsCarrierJumpConduitPassenger.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, EliteIndustrialCovertCloakBonus.INSTANCE, WarpSpeedMultiplier.INSTANCE, AllowedInCapIndustrialMaintenanceBay.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, WarpScrambleStatus.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, AffectedByIndustrialInvulnModule.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, JumpFatigueMultiplier.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, ShipBonusMI.INSTANCE, HeatAttenuationLow.INSTANCE, ShipBonusAI.INSTANCE, ShipBonusCI.INSTANCE, ShipBonusGI.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, IsBlackOpsJumpPortalPassenger.INSTANCE, IsBlackOpsJumpConduitPassenger.INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final BlockadeRunner.MetaGroup METAGROUP = new BlockadeRunner.MetaGroup();

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
            case  1034 :
            {
                return covertopsandreconopscloakmoduledelay;
            }
            case  807 :
            {
                return elitebonusindustrial1;
            }
            case  808 :
            {
                return elitebonusindustrial2;
            }
            case  1361 :
            {
                return eliteindustrialcovertcloakbonus;
            }
            case  1555 :
            {
                return fwlpkill;
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
            case  2580 :
            {
                return industrialbonusdronedamage;
            }
            case  3322 :
            {
                return isblackopsjumpconduitpassenger;
            }
            case  3320 :
            {
                return isblackopsjumpportalpassenger;
            }
            case  5682 :
            {
                return iscarrierjumpconduitpassenger;
            }
            case  1971 :
            {
                return jumpfatiguemultiplier;
            }
            case  124 :
            {
                return maincolor;
            }
            case  129 :
            {
                return maxpassengers;
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
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  494 :
            {
                return shipbonusai;
            }
            case  495 :
            {
                return shipbonusci;
            }
            case  496 :
            {
                return shipbonusgi;
            }
            case  493 :
            {
                return shipbonusmi;
            }
            case  5647 :
            {
                return shipbonusuh1;
            }
            case  5648 :
            {
                return shipbonusuh2;
            }
            case  5646 :
            {
                return specialcolonyresourcesholdcapacity;
            }
            case  1154 :
            {
                return upgradeslotsleft;
            }
            case  104 :
            {
                return warpscramblestatus;
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
    public IMetaGroup<BlockadeRunner> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<BlockadeRunner>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/BlockadeRunner.yaml";
        private Map<Integer, BlockadeRunner> cache = (null);

        @Override
        public IMetaCategory<? super BlockadeRunner> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1202;
        }

        @Override
        public String getName() {
            return "BlockadeRunner";
        }

        @Override
        public synchronized Map<Integer, BlockadeRunner> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(BlockadeRunner.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, BlockadeRunner> types;
        }
    }
}
