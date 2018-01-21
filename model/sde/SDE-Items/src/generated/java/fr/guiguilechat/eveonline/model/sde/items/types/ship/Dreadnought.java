
package fr.guiguilechat.eveonline.model.sde.items.types.ship;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class Dreadnought
    extends Ship
{

    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill4;
    /**
     * Required skill level for skill 4
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill4Level;
    /**
     * This defines the total capacity of fighters allowed in the fighter bay of the ship
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterCapacity;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RigSize;
    /**
     * The number of low power slots on the ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double LowSlots;
    /**
     * special fuel bay capacity
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double SpecialFuelBayCapacity;
    /**
     * tbd
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MedSlots;
    /**
     * tbd
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double HiSlots;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FwLpKill;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1Level;
    /**
     * Required skill level for skill 2
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill2Level;
    /**
     * Required skill level for skill 3
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill3Level;
    /**
     * The resolution that the vessel can target other objects at.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanResolution;
    /**
     * Resistance against Remote Sensor Dampeners.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double SensorDampenerResistance;
    /**
     * Resistance against Remote Weapon Disruptors.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double WeaponDisruptionResistance;
    /**
     * Resistance against Target Painters
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double TargetPainterResistance;
    /**
     * Resistance against Stasis Webifiers
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double StasisWebifierResistance;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double RemoteRepairImpedance;
    /**
     * scanning speed in milliseconds
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanSpeed;
    /**
     * Attribute on ship to make advanced command affect only ships that we want.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double AdvancedAgility;
    /**
     * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double RemoteAssistanceImpedance;
    /**
     *  1 = ship can use jump drive
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CanJump;
    /**
     * Type that is used for consumption from cargo hold when activating jump drive operation.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double JumpDriveConsumptionType;
    /**
     * Range in light years the ship can maximum jump to.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double JumpDriveRange;
    /**
     * Number of units it consumes per light year.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double JumpDriveConsumptionAmount;
    /**
     * The amount of time before the ship actually jumps.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(300000.0D)
    public double JumpDriveDuration;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double AdvancedCapitalAgility;
    /**
     * Attribute on ships used for ship upgrades
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double UpgradeCapacity;
    /**
     * The number of rig slots on the ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RigSlots;
    /**
     * The main color of a ship type.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MainColor;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityKamikazeResistance;
    /**
     * Minimum capacitor need for jump drive operation from full capacitor in modifier%.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double JumpDriveCapacitorNeed;
    /**
     * How many upgrades can by fitted to this ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double UpgradeSlotsLeft;
    /**
     * Indicates whether a ship type has a ship maintenance bay.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double HasShipMaintenanceBay;
    /**
     * The capacity of the hangar in a ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipMaintenanceBayCapacity;
    /**
     * Whether this ship has fleet hangars.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double HasFleetHangars;
    /**
     * The capacity of the fleet hangar.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FleetHangarCapacity;
    /**
     * Deprecated.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double MaxDirectionalVelocity;
    /**
     * Deprecated.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double MinTargetVelDmgMultiplier;
    /**
     * NOS override allows a nosferatu module to drain the target capacitor below the current ships capacitor level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double NosOverride;
    /**
     * meta group of type
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MetaGroupID;
    /**
     * special corpse hold capacity
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double SpecialCorpseHoldCapacity;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterTubes;
    /**
     * Number of Light Fighters the ship can launch.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterLightSlots;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DisallowInHighSec;
    /**
     * If greater than zero than the ship cannot activate gates. Set this to 0 on a type if you want it to be gate scramble-able.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(-1000.0D)
    public double GateScrambleStatus;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill2;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill3;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.1D)
    public double FighterAbilityAntiCapitalMissileResistance;
    /**
     * The maximum distance at which the object can be used.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxOperationalDistance;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double EntosisDurationMultiplier;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double JumpHarmonics;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double TypeColorScheme;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double HeatAttenuationHi;
    /**
     * Multiplied by Amarr Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtA1;
    /**
     * Multiplied by Amarr Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtA2;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double HeatAttenuationMed;
    /**
     * Multiplied by Amarr Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtA3;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double HeatAttenuationLow;
    /**
     * Multiplied by Caldari Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtC1;
    /**
     * Multiplied by Caldari Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtC2;
    /**
     * Multiplied by Caldari Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtC3;
    /**
     * Multiplied by Gallente Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtG1;
    /**
     * Multiplied by Gallente Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtG2;
    /**
     * Multiplied by Gallente Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtG3;
    /**
     * Multiplied by Minmatar Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtM1;
    /**
     * Multiplied by Minmatar Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtM2;
    /**
     * Multiplied by Minmatar Dreadnought skill level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusDreadnoughtM3;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double IsCapitalSize;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusRole1;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusRole3;
    /**
     * Resistance against Energy Neutralizing and Nosferatu
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double EnergyWarfareResistance;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShipBonusRole4;
    public final static String RESOURCE_PATH = "SDE/items/ship/Dreadnought.yaml";
    private static LinkedHashMap<String, Dreadnought> cache = (null);

    @Override
    public int getGroupId() {
        return  485;
    }

    @Override
    public Class<?> getGroup() {
        return Dreadnought.class;
    }

    public static LinkedHashMap<String, Dreadnought> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Dreadnought.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Dreadnought> items;

    }

}
