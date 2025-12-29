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

public class Supercarrier
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
     * Number of units needed to conduit jump
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int conduitjumpdriveconsumptionamount;
    /**
     * How many passengers can be carried in a Conduit Jump
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int conduitjumppassengercount;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int conduitpassengerbonuspercent;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinhighsec;
    /**
     * Grants the ability to perform conduit jumps
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int enableperformconduitjump;
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
     * This defines the total capacity of fighters allowed in the fighter bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightercapacity;
    /**
     * Number of Heavy Fighters the ship can launch.Heavy 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterheavyslots;
    /**
     * Number of Light Fighters the ship can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterlightslots;
    /**
     * Number of Support Fighters the ship can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightersupportslots;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightertubes;
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
     * This attribute enables a ship to activate a Jump Conduit. Its value specifies a dogma attribute ID that a passenger ship must possess in order to be carried though that Jump Conduit.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int jumpconduitpassengerrequiredattributeid;
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
     * The maximum distance at which the object can be used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationaldistance;
    /**
     * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationalusers;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
    /**
     * The maximum possible target range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int maximumrangecap;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int medslots;
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
     * Type of fuel consumed by tactical capsuleer recloner
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int reclonerfueltype;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscommandburstaoerange;
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
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole1;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusrole2;
    /**
     * Fixed Role Bonus on a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole7;
    /**
     * Multiplied by Amarr Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonussupercarriera1;
    /**
     * Multiplied by Amarr Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarriera2;
    /**
     * Multiplied by Amarr Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarriera3;
    /**
     * Multiplied by Amarr Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarriera4;
    /**
     * Multiplied by Amarr Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarriera5;
    /**
     * Multiplied by Caldari Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonussupercarrierc1;
    /**
     * Multiplied by Caldari Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierc2;
    /**
     * Multiplied by Caldari Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierc3;
    /**
     * Multiplied by Caldari Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierc4;
    /**
     * Multiplied by Caldari Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierc5;
    /**
     * Multiplied by Gallente Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierg1;
    /**
     * Multiplied by Gallente Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierg2;
    /**
     * Multiplied by Gallente Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierg3;
    /**
     * Multiplied by Gallente Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierg4;
    /**
     * Multiplied by Gallente Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierg5;
    /**
     * Multiplied by Minmatar Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierm1;
    /**
     * Multiplied by Minmatar Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierm2;
    /**
     * Multiplied by Minmatar Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierm3;
    /**
     * Multiplied by Minmatar Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierm4;
    /**
     * Multiplied by Minmatar Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonussupercarrierm5;
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
    /**
     * Warp ability of a ship.  If greater than zero than the ship cannot warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblestatus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, FighterCapacity.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, SpecialFuelBayCapacity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, HiSlots.INSTANCE, RoleBonusCommandBurstAoERange.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, ShipBonusRole7 .INSTANCE, DroneCapacity.INSTANCE, MaximumRangeCap.INSTANCE, ReclonerFuelType.INSTANCE, MaxVelocity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, ConduitPassengerBonusPercent.INSTANCE, CpuLoad.INSTANCE, IsCarrierJumpConduitPassenger.INSTANCE, ScanResolution.INSTANCE, EnablePerformConduitJump.INSTANCE, RechargeRate.INSTANCE, ConduitJumpDriveConsumptionAmount.INSTANCE, ConduitJumpPassengerCount.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, TargetPainterResistance.INSTANCE, StasisWebifierResistance.INSTANCE, RemoteRepairImpedance.INSTANCE, Agility.INSTANCE, ShipBonusSupercarrierA1 .INSTANCE, ShipBonusSupercarrierA2 .INSTANCE, ShipBonusSupercarrierA3 .INSTANCE, ShipBonusSupercarrierA4 .INSTANCE, ShipBonusSupercarrierA5 .INSTANCE, ShipBonusSupercarrierC1 .INSTANCE, MaxTargetRange.INSTANCE, ShipBonusSupercarrierC2 .INSTANCE, ShipBonusSupercarrierC3 .INSTANCE, ScanSpeed.INSTANCE, ShipBonusSupercarrierC4 .INSTANCE, ShipBonusSupercarrierC5 .INSTANCE, ShipBonusSupercarrierG1 .INSTANCE, ShipBonusSupercarrierG2 .INSTANCE, ShipBonusSupercarrierG3 .INSTANCE, ShipBonusSupercarrierG4 .INSTANCE, AdvancedAgility.INSTANCE, ShipBonusSupercarrierG5 .INSTANCE, ShipBonusSupercarrierM1 .INSTANCE, RemoteAssistanceImpedance.INSTANCE, ShipBonusSupercarrierM2 .INSTANCE, ShipBonusSupercarrierM3 .INSTANCE, WarpSpeedMultiplier.INSTANCE, ShipBonusSupercarrierM4 .INSTANCE, ShipBonusSupercarrierM5 .INSTANCE, CanJump.INSTANCE, JumpDriveConsumptionType.INSTANCE, JumpDriveRange.INSTANCE, JumpDriveConsumptionAmount.INSTANCE, JumpDriveDuration.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, WarpScrambleStatus.INSTANCE, AdvancedCapitalAgility.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, FighterAbilityKamikazeResistance.INSTANCE, JumpDriveCapacitorNeed.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, HasShipMaintenanceBay.INSTANCE, ShipMaintenanceBayCapacity.INSTANCE, HasFleetHangars.INSTANCE, FleetHangarCapacity.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, MetaGroupID.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, FighterTubes.INSTANCE, FighterLightSlots.INSTANCE, FighterSupportSlots.INSTANCE, FighterHeavySlots.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, DisallowInHighSec.INSTANCE, GateScrambleStatus.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, RequiredSkill3 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, EntosisAssistanceImpedanceMultiplier.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, HeatGenerationMultiplier.INSTANCE, MaxOperationalDistance.INSTANCE, MaxOperationalUsers.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, EntosisDurationMultiplier.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, JumpDriveTargetBeaconTypelistID.INSTANCE, GfxBoosterID.INSTANCE, IsTitanJumpPortalPassenger.INSTANCE, DroneBandwidth.INSTANCE, IsCapitalSize.INSTANCE, JumpConduitPassengerRequiredAttributeID.INSTANCE, ShipBonusRole1 .INSTANCE, ShipBonusRole2 .INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final Supercarrier.MetaGroup METAGROUP = new Supercarrier.MetaGroup();

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
            case  3131 :
            {
                return conduitjumpdriveconsumptionamount;
            }
            case  3133 :
            {
                return conduitjumppassengercount;
            }
            case  5681 :
            {
                return conduitpassengerbonuspercent;
            }
            case  1970 :
            {
                return disallowinhighsec;
            }
            case  3126 :
            {
                return enableperformconduitjump;
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
            case  2055 :
            {
                return fightercapacity;
            }
            case  2219 :
            {
                return fighterheavyslots;
            }
            case  2217 :
            {
                return fighterlightslots;
            }
            case  2218 :
            {
                return fightersupportslots;
            }
            case  2216 :
            {
                return fightertubes;
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
            case  3321 :
            {
                return jumpconduitpassengerrequiredattributeid;
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
            case  715 :
            {
                return maxoperationaldistance;
            }
            case  716 :
            {
                return maxoperationalusers;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  797 :
            {
                return maximumrangecap;
            }
            case  13 :
            {
                return medslots;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  3105 :
            {
                return reclonerfueltype;
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
            case  2574 :
            {
                return rolebonuscommandburstaoerange;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  2298 :
            {
                return shipbonusrole1;
            }
            case  2299 :
            {
                return shipbonusrole2;
            }
            case  793 :
            {
                return shipbonusrole7;
            }
            case  2375 :
            {
                return shipbonussupercarriera1;
            }
            case  2376 :
            {
                return shipbonussupercarriera2;
            }
            case  2377 :
            {
                return shipbonussupercarriera3;
            }
            case  2378 :
            {
                return shipbonussupercarriera4;
            }
            case  2379 :
            {
                return shipbonussupercarriera5;
            }
            case  2380 :
            {
                return shipbonussupercarrierc1;
            }
            case  2381 :
            {
                return shipbonussupercarrierc2;
            }
            case  2382 :
            {
                return shipbonussupercarrierc3;
            }
            case  2383 :
            {
                return shipbonussupercarrierc4;
            }
            case  2384 :
            {
                return shipbonussupercarrierc5;
            }
            case  2385 :
            {
                return shipbonussupercarrierg1;
            }
            case  2386 :
            {
                return shipbonussupercarrierg2;
            }
            case  2387 :
            {
                return shipbonussupercarrierg3;
            }
            case  2388 :
            {
                return shipbonussupercarrierg4;
            }
            case  2389 :
            {
                return shipbonussupercarrierg5;
            }
            case  2390 :
            {
                return shipbonussupercarrierm1;
            }
            case  2391 :
            {
                return shipbonussupercarrierm2;
            }
            case  2392 :
            {
                return shipbonussupercarrierm3;
            }
            case  2393 :
            {
                return shipbonussupercarrierm4;
            }
            case  2394 :
            {
                return shipbonussupercarrierm5;
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
    public IMetaGroup<Supercarrier> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Supercarrier>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/Supercarrier.yaml";
        private Map<Integer, Supercarrier> cache = (null);

        @Override
        public IMetaCategory<? super Supercarrier> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  659;
        }

        @Override
        public String getName() {
            return "Supercarrier";
        }

        @Override
        public synchronized Map<Integer, Supercarrier> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Supercarrier.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Supercarrier> types;
        }
    }
}
