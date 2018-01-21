
package fr.guiguilechat.eveonline.model.sde.items.types.ship;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class Carrier
    extends Ship
{

    /**
     * This defines the total capacity of fighters allowed in the fighter bay of the ship
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterCapacity;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSize;
    /**
     * The number of low power slots on the ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LowSlots;
    /**
     * special fuel bay capacity
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int SpecialFuelBayCapacity;
    /**
     * tbd
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MedSlots;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RoleBonusCommandBurstAoERange;
    /**
     * tbd
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HiSlots;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FwLpKill;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Required skill level for skill 2
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * The maximum possible target range.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int MaximumRangeCap;
    /**
     * The resolution that the vessel can target other objects at.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanResolution;
    /**
     * Multiplied by Amarr Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierA1;
    /**
     * Multiplied by Amarr Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierA2;
    /**
     * Multiplied by Amarr Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierA3;
    /**
     * Multiplied by Amarr Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierA4;
    /**
     * Multiplied by Caldari Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierC1;
    /**
     * Multiplied by Caldari Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierC2;
    /**
     * Multiplied by Caldari Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierC3;
    /**
     * Multiplied by Caldari Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierC4;
    /**
     * Multiplied by Gallente Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierG1;
    /**
     * Resistance against Remote Sensor Dampeners.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double SensorDampenerResistance;
    /**
     * Multiplied by Gallente Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierG2;
    /**
     * Resistance against Remote Weapon Disruptors.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double WeaponDisruptionResistance;
    /**
     * Multiplied by Gallente Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShipBonusCarrierG3;
    /**
     * Resistance against Target Painters
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double TargetPainterResistance;
    /**
     * Multiplied by Gallente Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierG4;
    /**
     * Resistance against Stasis Webifiers
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double StasisWebifierResistance;
    /**
     * Multiplied by Minmatar Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierM1;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double RemoteRepairImpedance;
    /**
     * Multiplied by Minmatar Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierM2;
    /**
     * Multiplied by Minmatar Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShipBonusCarrierM3;
    /**
     * Multiplied by Minmatar Carrier skill level.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusCarrierM4;
    /**
     * scanning speed in milliseconds
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    /**
     * Attribute on ship to make advanced command affect only ships that we want.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int AdvancedAgility;
    /**
     * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double RemoteAssistanceImpedance;
    /**
     *  1 = ship can use jump drive
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanJump;
    /**
     * Type that is used for consumption from cargo hold when activating jump drive operation.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int JumpDriveConsumptionType;
    /**
     * Range in light years the ship can maximum jump to.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double JumpDriveRange;
    /**
     * Number of units it consumes per light year.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int JumpDriveConsumptionAmount;
    /**
     * The amount of time before the ship actually jumps.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int JumpDriveDuration;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int AdvancedCapitalAgility;
    /**
     * Attribute on ships used for ship upgrades
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeCapacity;
    /**
     * The number of rig slots on the ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSlots;
    /**
     * The main color of a ship type.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityKamikazeResistance;
    /**
     * How many upgrades can by fitted to this ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeSlotsLeft;
    /**
     * Minimum capacitor need for jump drive operation from full capacitor in modifier%.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double JumpDriveCapacitorNeed;
    /**
     * Indicates whether a ship type has a ship maintenance bay.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HasShipMaintenanceBay;
    /**
     * The capacity of the hangar in a ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipMaintenanceBayCapacity;
    /**
     * Whether this ship has fleet hangars.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HasFleetHangars;
    /**
     * The capacity of the fleet hangar.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FleetHangarCapacity;
    /**
     * meta group of type
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterTubes;
    /**
     * Number of Light Fighters the ship can launch.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterLightSlots;
    /**
     * Number of Support Fighters the ship can launch.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSupportSlots;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowInHighSec;
    /**
     * If greater than zero than the ship cannot activate gates. Set this to 0 on a type if you want it to be gate scramble-able.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(-1000)
    public int GateScrambleStatus;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * The maximum distance at which the object can be used.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxOperationalDistance;
    /**
     * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxOperationalUsers;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int EntosisDurationMultiplier;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int JumpHarmonics;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int TypeColorScheme;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double HeatAttenuationHi;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double HeatAttenuationMed;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double HeatAttenuationLow;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IsCapitalSize;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShipBonusRole1;
    /**
     * Resistance against Energy Neutralizing and Nosferatu
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double EnergyWarfareResistance;
    public final static String RESOURCE_PATH = "SDE/items/ship/Carrier.yaml";
    private static LinkedHashMap<String, Carrier> cache = (null);

    @Override
    public int getGroupId() {
        return  547;
    }

    @Override
    public Class<?> getGroup() {
        return Carrier.class;
    }

    public static LinkedHashMap<String, Carrier> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Carrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Carrier> items;

    }

}
