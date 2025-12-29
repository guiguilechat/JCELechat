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

public class LancerDreadnought
    extends Ship
{
    /**
     * Attribute on ship to make advanced command affect only ships that we want.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int advancedagility;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int advancedcapitalagility;
    /**
     *  1 = ship can use jump drive
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canjump;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinhighsec;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double entosisassistanceimpedancemultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int entosisdurationmultiplier;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityanticapitalmissileresistance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitykamikazeresistance;
    /**
     * The capacity of the fleet hangar.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fleethangarcapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
    /**
     * If greater than zero than the ship cannot activate gates. Set this to 0 on a type if you want it to be gate scramble-able.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(-1000)
    public int gatescramblestatus;
    /**
     * Whether this ship has fleet hangars.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hasfleethangars;
    /**
     * Indicates whether a ship type has a ship maintenance bay.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hasshipmaintenancebay;
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
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int iscapitalsize;
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int iscarrierjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through a Titan Jump Portal
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int istitanjumpportalpassenger;
    /**
     * Minimum capacitor need for jump drive operation from full capacitor in modifier%.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double jumpdrivecapacitorneed;
    /**
     * Number of units it consumes per light year.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(2000)
    public int jumpdriveconsumptionamount;
    /**
     * Type that is used for consumption from cargo hold when activating jump drive operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpdriveconsumptiontype;
    /**
     * The amount of time before the ship actually jumps.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int jumpdriveduration;
    /**
     * Range in light years the ship can maximum jump to.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double jumpdriverange;
    /**
     * Pointer to type-list that describes which beacons a ship's jump drive can connect to
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int jumpdrivetargetbeacontypelistid;
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
     * The maximum distance at which the object can be used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationaldistance;
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
    /**
     * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double remoteassistanceimpedance;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double remoterepairimpedance;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3level;
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
     * Multiplied by Lancer Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusadvanceddreadnought1;
    /**
     * Multiplied by Lancer Dreadnoughts skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusadvanceddreadnought2;
    /**
     * Multiplied by Amarr Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusdreadnoughta1;
    /**
     * Multiplied by Amarr Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusdreadnoughta2;
    /**
     * Multiplied by Caldari Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusdreadnoughtc1;
    /**
     * Multiplied by Caldari Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusdreadnoughtc2;
    /**
     * Multiplied by Gallente Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusdreadnoughtg1;
    /**
     * Multiplied by Gallente Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusdreadnoughtg2;
    /**
     * Multiplied by Gallente Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusdreadnoughtg3;
    /**
     * Multiplied by Minmatar Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusdreadnoughtm1;
    /**
     * Multiplied by Minmatar Dreadnought skill level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusdreadnoughtm2;
    /**
     * The capacity of the hangar in a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipmaintenancebaycapacity;
    /**
     * special fuel bay capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialfuelbaycapacity;
    /**
     * Resistance against Target Painters
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double targetpainterresistance;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, SpecialFuelBayCapacity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, SignatureRadius.INSTANCE, ShipBonusAdvancedDreadnought1 .INSTANCE, ShipBonusAdvancedDreadnought2 .INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, IsCarrierJumpConduitPassenger.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, TargetPainterResistance.INSTANCE, StasisWebifierResistance.INSTANCE, RemoteRepairImpedance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, AdvancedAgility.INSTANCE, RemoteAssistanceImpedance.INSTANCE, WarpSpeedMultiplier.INSTANCE, CanJump.INSTANCE, JumpDriveConsumptionType.INSTANCE, JumpDriveRange.INSTANCE, JumpDriveConsumptionAmount.INSTANCE, JumpDriveDuration.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, AdvancedCapitalAgility.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, FighterAbilityKamikazeResistance.INSTANCE, JumpDriveCapacitorNeed.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, HasShipMaintenanceBay.INSTANCE, ShipMaintenanceBayCapacity.INSTANCE, HasFleetHangars.INSTANCE, FleetHangarCapacity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, DisallowInHighSec.INSTANCE, GateScrambleStatus.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, RequiredSkill3 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, EntosisAssistanceImpedanceMultiplier.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, HeatGenerationMultiplier.INSTANCE, MaxOperationalDistance.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, EntosisDurationMultiplier.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, ShipBonusDreadnoughtA1 .INSTANCE, ShipBonusDreadnoughtA2 .INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, ShipBonusDreadnoughtC1 .INSTANCE, ShipBonusDreadnoughtC2 .INSTANCE, ShipBonusDreadnoughtG1 .INSTANCE, ShipBonusDreadnoughtG2 .INSTANCE, ShipBonusDreadnoughtG3 .INSTANCE, ShipBonusDreadnoughtM1 .INSTANCE, JumpDriveTargetBeaconTypelistID.INSTANCE, ShipBonusDreadnoughtM2 .INSTANCE, GfxBoosterID.INSTANCE, IsTitanJumpPortalPassenger.INSTANCE, DroneBandwidth.INSTANCE, IsCapitalSize.INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final LancerDreadnought.MetaGroup METAGROUP = new LancerDreadnought.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  853 :
            {
                return advancedagility;
            }
            case  874 :
            {
                return advancedcapitalagility;
            }
            case  861 :
            {
                return canjump;
            }
            case  1970 :
            {
                return disallowinhighsec;
            }
            case  2754 :
            {
                return entosisassistanceimpedancemultiplier;
            }
            case  2021 :
            {
                return entosisdurationmultiplier;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  2433 :
            {
                return fighterabilitykamikazeresistance;
            }
            case  912 :
            {
                return fleethangarcapacity;
            }
            case  1555 :
            {
                return fwlpkill;
            }
            case  1973 :
            {
                return gatescramblestatus;
            }
            case  911 :
            {
                return hasfleethangars;
            }
            case  907 :
            {
                return hasshipmaintenancebay;
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
            case  1785 :
            {
                return iscapitalsize;
            }
            case  5682 :
            {
                return iscarrierjumpconduitpassenger;
            }
            case  3319 :
            {
                return istitanjumpportalpassenger;
            }
            case  898 :
            {
                return jumpdrivecapacitorneed;
            }
            case  868 :
            {
                return jumpdriveconsumptionamount;
            }
            case  866 :
            {
                return jumpdriveconsumptiontype;
            }
            case  869 :
            {
                return jumpdriveduration;
            }
            case  867 :
            {
                return jumpdriverange;
            }
            case  3317 :
            {
                return jumpdrivetargetbeacontypelistid;
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
            case  715 :
            {
                return maxoperationaldistance;
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
            case  2135 :
            {
                return remoteassistanceimpedance;
            }
            case  2116 :
            {
                return remoterepairimpedance;
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
            case  184 :
            {
                return requiredskill3;
            }
            case  279 :
            {
                return requiredskill3level;
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
            case  5417 :
            {
                return shipbonusadvanceddreadnought1;
            }
            case  5418 :
            {
                return shipbonusadvanceddreadnought2;
            }
            case  2283 :
            {
                return shipbonusdreadnoughta1;
            }
            case  2284 :
            {
                return shipbonusdreadnoughta2;
            }
            case  2286 :
            {
                return shipbonusdreadnoughtc1;
            }
            case  2287 :
            {
                return shipbonusdreadnoughtc2;
            }
            case  2289 :
            {
                return shipbonusdreadnoughtg1;
            }
            case  2290 :
            {
                return shipbonusdreadnoughtg2;
            }
            case  2291 :
            {
                return shipbonusdreadnoughtg3;
            }
            case  2292 :
            {
                return shipbonusdreadnoughtm1;
            }
            case  2293 :
            {
                return shipbonusdreadnoughtm2;
            }
            case  908 :
            {
                return shipmaintenancebaycapacity;
            }
            case  1549 :
            {
                return specialfuelbaycapacity;
            }
            case  2114 :
            {
                return targetpainterresistance;
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
    public IMetaGroup<LancerDreadnought> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LancerDreadnought>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/LancerDreadnought.yaml";
        private Map<Integer, LancerDreadnought> cache = (null);

        @Override
        public IMetaCategory<? super LancerDreadnought> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4594;
        }

        @Override
        public String getName() {
            return "LancerDreadnought";
        }

        @Override
        public synchronized Map<Integer, LancerDreadnought> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(LancerDreadnought.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, LancerDreadnought> types;
        }
    }
}
