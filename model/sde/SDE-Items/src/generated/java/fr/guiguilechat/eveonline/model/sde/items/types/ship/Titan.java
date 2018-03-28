package fr.guiguilechat.eveonline.model.sde.items.types.ship;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class Titan
    extends Ship
{
    /**
     * Something to do with accuracy.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AccuracyBonus;
    /**
     * Attribute on ship to make advanced command affect only ships that we want.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int AdvancedAgility;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int AdvancedCapitalAgility;
    /**
     *  1 = ship can use jump drive
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanJump;
    /**
     * Defines whether a ship has the functionality to allow it to receive clone jumps and host jump clones.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanReceiveCloneJumps;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowInHighSec;
    /**
     * Resistance against Energy Neutralizing and Nosferatu
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double EnergyWarfareResistance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double EntosisAssistanceImpedanceMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int EntosisDurationMultiplier;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityKamikazeResistance;
    /**
     * This defines the total capacity of fighters allowed in the fighter bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterCapacity;
    /**
     * Number of Heavy Fighters the ship can launch.Heavy 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterHeavySlots;
    /**
     * Number of Light Fighters the ship can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterLightSlots;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterTubes;
    /**
     * The capacity of the fleet hangar.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FleetHangarCapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FwLpKill;
    /**
     * If greater than zero than the ship cannot activate gates. Set this to 0 on a type if you want it to be gate scramble-able.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(-1000)
    public int GateScrambleStatus;
    /**
     * Whether this ship has fleet hangars.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HasFleetHangars;
    /**
     * Indicates whether a ship type has a ship maintenance bay.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HasShipMaintenanceBay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatAttenuationHi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatAttenuationLow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatAttenuationMed;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HiSlots;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IsCapitalSize;
    /**
     * The remaining number of unused clone vats on the ship that are available for installation of jump clones.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int JumpClonesLeft;
    /**
     * Minimum capacitor need for jump drive operation from full capacitor in modifier%.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double JumpDriveCapacitorNeed;
    /**
     * Number of units it consumes per light year.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int JumpDriveConsumptionAmount;
    /**
     * Type that is used for consumption from cargo hold when activating jump drive operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int JumpDriveConsumptionType;
    /**
     * The amount of time before the ship actually jumps.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int JumpDriveDuration;
    /**
     * Range in light years the ship can maximum jump to.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double JumpDriveRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int JumpHarmonics;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int JumpPortalCapacitorNeed;
    /**
     * Multiplier used to calculate amount of quantity used for jumping via portals based on mass of ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double JumpPortalConsumptionMassFactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int JumpPortalDuration;
    /**
     * The number of low power slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LowSlots;
    /**
     * The maximum amount of jump clones that the character may have in existence or ship may have stored.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxJumpClones;
    /**
     * The maximum distance at which the object can be used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxOperationalDistance;
    /**
     * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxOperationalUsers;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxPassengers;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MedSlots;
    /**
     * meta group of type
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * NOS override allows a nosferatu module to drain the target capacitor below the current ships capacitor level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NosOverride;
    /**
     * Range of the explosion when the ship dies.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OnDeathAOERadius;
    /**
     * EM Damage when the ship dies
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OnDeathDamageEM;
    /**
     * Explosive Damage when the ship dies
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OnDeathDamageExp;
    /**
     * Kinetic Damage when the ship dies
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OnDeathDamageKin;
    /**
     * Thermal Damage when the ship dies
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OnDeathDamageTherm;
    /**
     * Signature Radius of Explosion (cloud factor) when ship dies
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int OnDeathSignatureRadius;
    /**
     * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double RemoteAssistanceImpedance;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double RemoteRepairImpedance;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill3Level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSize;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSlots;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RoleBonusCommandBurstAoERange;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanResolution;
    /**
     * Resistance against Remote Sensor Dampeners.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double SensorDampenerResistance;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusRole1;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusRole2;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusRole3;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusRole4;
    /**
     * Multiplied by Amarr Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanA1;
    /**
     * Multiplied by Amarr Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanA2;
    /**
     * Multiplied by Amarr Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanA3;
    /**
     * Multiplied by Caldari Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanC1;
    /**
     * Multiplied by Caldari Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanC2;
    /**
     * Multiplied by Caldari Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanC3;
    /**
     * Multiplied by Caldari Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanC5;
    /**
     * Multiplied by Gallente Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanG1;
    /**
     * Multiplied by Gallente Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanG2;
    /**
     * Multiplied by Gallente Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanG3;
    /**
     * Multiplied by Minmatar Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanM1;
    /**
     * Multiplied by Minmatar Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanM2;
    /**
     * Multiplied by Minmatar Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusTitanM3;
    /**
     * The capacity of the hangar in a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipMaintenanceBayCapacity;
    /**
     * special corpse hold capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int SpecialCorpseHoldCapacity;
    /**
     * special fuel bay capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int SpecialFuelBayCapacity;
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StasisWebifierResistance;
    /**
     * Resistance against Target Painters
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double TargetPainterResistance;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int TypeColorScheme;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeCapacity;
    /**
     * How many upgrades can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeSlotsLeft;
    /**
     * Warp ability of a ship.  If greater than zero than the ship cannot warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int WarpScrambleStatus;
    /**
     * Resistance against Remote Weapon Disruptors.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double WeaponDisruptionResistance;
    public final static String RESOURCE_PATH = "SDE/items/ship/Titan.yaml";
    private static LinkedHashMap<String, Titan> cache = (null);

    @Override
    public int getGroupId() {
        return  30;
    }

    @Override
    public Class<?> getGroup() {
        return Titan.class;
    }

    public static synchronized LinkedHashMap<String, Titan> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Titan.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Titan> items;
    }
}
