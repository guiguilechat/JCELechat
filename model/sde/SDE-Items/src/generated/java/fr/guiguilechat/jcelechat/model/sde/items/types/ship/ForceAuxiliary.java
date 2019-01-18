package fr.guiguilechat.jcelechat.model.sde.items.types.ship;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class ForceAuxiliary
    extends Ship
{
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IsCapitalSize;
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
    @DefaultIntValue(2000)
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
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(3)
    public int MaxGangModules;
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
     * NOS override allows a nosferatu module to drain the target capacitor below the current ships capacitor level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NosOverride;
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
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    /**
     * Resistance against Remote Sensor Dampeners.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double SensorDampenerResistance;
    /**
     * Multiplied by Amarr Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryA1;
    /**
     * Multiplied by Amarr Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryA2;
    /**
     * Multiplied by Amarr Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryA3;
    /**
     * Multiplied by Amarr Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryA4;
    /**
     * Multiplied by Caldari Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryC1;
    /**
     * Multiplied by Caldari Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryC2;
    /**
     * Multiplied by Caldari Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryC3;
    /**
     * Multiplied by Caldari Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryC4;
    /**
     * Multiplied by Gallente Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryG1;
    /**
     * Multiplied by Gallente Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusForceAuxiliaryG2;
    /**
     * Multiplied by Gallente Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryG3;
    /**
     * Multiplied by Gallente Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryG4;
    /**
     * Multiplied by Minmatar Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryM1;
    /**
     * Multiplied by Minmatar Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShipBonusForceAuxiliaryM2;
    /**
     * Multiplied by Minmatar Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryM3;
    /**
     * Multiplied by Minmatar Carrier skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusForceAuxiliaryM4;
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
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipBonusRole5;
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
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeSlotsLeft;
    /**
     * Resistance against Remote Weapon Disruptors.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double WeaponDisruptionResistance;
    public static final ForceAuxiliary.MetaGroup METAGROUP = new ForceAuxiliary.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  853 :
            {
                return AdvancedAgility;
            }
            case  874 :
            {
                return AdvancedCapitalAgility;
            }
            case  861 :
            {
                return CanJump;
            }
            case  1970 :
            {
                return DisallowInHighSec;
            }
            case  2045 :
            {
                return EnergyWarfareResistance;
            }
            case  2754 :
            {
                return EntosisAssistanceImpedanceMultiplier;
            }
            case  2021 :
            {
                return EntosisDurationMultiplier;
            }
            case  2244 :
            {
                return FighterAbilityAntiCapitalMissileResistance;
            }
            case  2433 :
            {
                return FighterAbilityKamikazeResistance;
            }
            case  912 :
            {
                return FleetHangarCapacity;
            }
            case  1555 :
            {
                return FwLpKill;
            }
            case  1973 :
            {
                return GateScrambleStatus;
            }
            case  911 :
            {
                return HasFleetHangars;
            }
            case  907 :
            {
                return HasShipMaintenanceBay;
            }
            case  1259 :
            {
                return HeatAttenuationHi;
            }
            case  1262 :
            {
                return HeatAttenuationLow;
            }
            case  1261 :
            {
                return HeatAttenuationMed;
            }
            case  1785 :
            {
                return IsCapitalSize;
            }
            case  898 :
            {
                return JumpDriveCapacitorNeed;
            }
            case  868 :
            {
                return JumpDriveConsumptionAmount;
            }
            case  866 :
            {
                return JumpDriveConsumptionType;
            }
            case  869 :
            {
                return JumpDriveDuration;
            }
            case  867 :
            {
                return JumpDriveRange;
            }
            case  1253 :
            {
                return JumpHarmonics;
            }
            case  124 :
            {
                return MainColor;
            }
            case  435 :
            {
                return MaxGangModules;
            }
            case  715 :
            {
                return MaxOperationalDistance;
            }
            case  716 :
            {
                return MaxOperationalUsers;
            }
            case  129 :
            {
                return MaxPassengers;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  1945 :
            {
                return NosOverride;
            }
            case  2135 :
            {
                return RemoteAssistanceImpedance;
            }
            case  2116 :
            {
                return RemoteRepairImpedance;
            }
            case  183 :
            {
                return RequiredSkill2;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            case  184 :
            {
                return RequiredSkill3;
            }
            case  279 :
            {
                return RequiredSkill3Level;
            }
            case  1547 :
            {
                return RigSize;
            }
            case  1137 :
            {
                return RigSlots;
            }
            case  2574 :
            {
                return RoleBonusCommandBurstAoERange;
            }
            case  564 :
            {
                return ScanResolution;
            }
            case  79 :
            {
                return ScanSpeed;
            }
            case  2112 :
            {
                return SensorDampenerResistance;
            }
            case  2308 :
            {
                return ShipBonusForceAuxiliaryA1;
            }
            case  2309 :
            {
                return ShipBonusForceAuxiliaryA2;
            }
            case  2310 :
            {
                return ShipBonusForceAuxiliaryA3;
            }
            case  2320 :
            {
                return ShipBonusForceAuxiliaryA4;
            }
            case  2311 :
            {
                return ShipBonusForceAuxiliaryC1;
            }
            case  2312 :
            {
                return ShipBonusForceAuxiliaryC2;
            }
            case  2313 :
            {
                return ShipBonusForceAuxiliaryC3;
            }
            case  2321 :
            {
                return ShipBonusForceAuxiliaryC4;
            }
            case  2314 :
            {
                return ShipBonusForceAuxiliaryG1;
            }
            case  2315 :
            {
                return ShipBonusForceAuxiliaryG2;
            }
            case  2316 :
            {
                return ShipBonusForceAuxiliaryG3;
            }
            case  2322 :
            {
                return ShipBonusForceAuxiliaryG4;
            }
            case  2317 :
            {
                return ShipBonusForceAuxiliaryM1;
            }
            case  2318 :
            {
                return ShipBonusForceAuxiliaryM2;
            }
            case  2319 :
            {
                return ShipBonusForceAuxiliaryM3;
            }
            case  2323 :
            {
                return ShipBonusForceAuxiliaryM4;
            }
            case  2298 :
            {
                return ShipBonusRole1;
            }
            case  2299 :
            {
                return ShipBonusRole2;
            }
            case  2300 :
            {
                return ShipBonusRole3;
            }
            case  2301 :
            {
                return ShipBonusRole4;
            }
            case  2302 :
            {
                return ShipBonusRole5;
            }
            case  908 :
            {
                return ShipMaintenanceBayCapacity;
            }
            case  2467 :
            {
                return SpecialCorpseHoldCapacity;
            }
            case  1549 :
            {
                return SpecialFuelBayCapacity;
            }
            case  2115 :
            {
                return StasisWebifierResistance;
            }
            case  2114 :
            {
                return TargetPainterResistance;
            }
            case  1154 :
            {
                return UpgradeSlotsLeft;
            }
            case  2113 :
            {
                return WeaponDisruptionResistance;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<ForceAuxiliary> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ForceAuxiliary>
    {
        public static final String RESOURCE_PATH = "SDE/items/ship/ForceAuxiliary.yaml";
        private Map<String, ForceAuxiliary> cache = (null);

        @Override
        public IMetaCategory<? super ForceAuxiliary> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1538;
        }

        @Override
        public String getName() {
            return "ForceAuxiliary";
        }

        @Override
        public synchronized Map<String, ForceAuxiliary> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(ForceAuxiliary.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ForceAuxiliary> items;
        }
    }
}
