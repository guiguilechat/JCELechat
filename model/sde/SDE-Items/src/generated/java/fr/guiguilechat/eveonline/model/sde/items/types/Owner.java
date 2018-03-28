package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.owner.Alliance;
import fr.guiguilechat.eveonline.model.sde.items.types.owner.Character;
import fr.guiguilechat.eveonline.model.sde.items.types.owner.Corporation;
import fr.guiguilechat.eveonline.model.sde.items.types.owner.Faction;

public abstract class Owner
    extends Item
{
    /**
     * skill discount when selling to npc corps
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BarterDiscount;
    /**
     * The % of battleship assembly cost a player has to pay to assemble a battleship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BattleshipConstructionCost;
    /**
     * Flat Bonus To NPC Bountys
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BountyBonus;
    /**
     * Npc Bounty Multiplier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int BountyMultiplier;
    /**
     * The charisma of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Charisma;
    /**
     * Bonus to standing towards Friendly npcs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConnectionsBonus;
    /**
     * The chance that the customs official has of detecting contraband on board a scanned vessel
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ContrabandDetectionChance;
    /**
     * Chance of being caught selling contraband on market. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ContrabandFencingChance;
    /**
     * Blueprint copying speed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int CopySpeedPercent;
    /**
     * a bonus to standing towards npc with negativa security status
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CriminalConnectionsBonus;
    /**
     * Damage Bonus for Cruise Missiles
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CruiseMissileVelocityPercent;
    /**
     * The % of cruiser assembly cost a player has to pay to assemble a cruiser
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CruiserConstructionCost;
    /**
     * Bonus to the charisma of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CustomCharismaBonus;
    /**
     * Bonus to the intelligence of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CustomIntelligenceBonus;
    /**
     * Bonus to the memory of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CustomMemoryBonus;
    /**
     * Bonus to the perception of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CustomPerceptionBonus;
    /**
     * Bonus to the willpower of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CustomWillpowerBonus;
    /**
     * %chance of new asteroid releasing damage cloud each mining turn.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double DamageCloudChance;
    /**
     * Bonus to Effective Standing towards Hostile NPC
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DiplomacyBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(20000)
    public int DroneControlDistance;
    /**
     * Chance of upgrading a module to next tech level 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DuplicatingChance;
    /**
     * a percentage multiplyer to sec status recovery
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int FastTalkPercentage;
    /**
     * The % of Frigate assembly cost a player has to pay to assemble a frigate
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int FrigateConstructionCost;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int HeavyDroneDamagePercent;
    /**
     * Heavy missile speed percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HeavyMissileSpeedPercent;
    /**
     * The % of industrial assembly cost a player has to pay to assemble a industrial
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialConstructionCost;
    /**
     * The intelligence of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Intelligence;
    /**
     * Speed bonus when doing invention or reverse engineering
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int InventionReverseEngineeringResearchSpeed;
    /**
     * Manufacturing cost multiplyer
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ManufactureCostMultiplier;
    /**
     * The maximum amount of manufacture slots that can be used at a time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ManufactureSlotLimit;
    /**
     * Scales the time it takes to manufacture something.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ManufactureTimeMultiplier;
    /**
     * Research speed 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ManufacturingTimeResearchSpeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(3)
    public int MaxGangModules;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MaxLaborotorySlots;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * Max non  race corporationMembers
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxNonRaceCorporationMembers;
    /**
     * The memory of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Memory;
    /**
     * speed bonus when researching blueprint mineral need
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MineralNeedResearchSpeed;
    /**
     * Mining drone speed percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int MiningDroneAmountPercent;
    /**
     * The characters missile use efficiency, scales the damage missiles do.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double MissileDamageMultiplier;
    /**
     * Missile FOF velocity percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int MissileFOFVelocityPercent;
    /**
     * Missiles velocity multiplier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int MissileStandardVelocityPecent;
    /**
     * dictates how many hitpoints you can repair per minute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10)
    public int ModuleRepairRate;
    /**
     * Percentage of pay from agent 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int NegotiationPercentage;
    /**
     * The perception of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Perception;
    /**
     * Maximum amount of Reactions slots that can be used at a time
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ReactionSlotLimit;
    /**
     * Refining speed percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int RefiningTimePercentage;
    /**
     * Refining yield percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RefiningYieldPercentage;
    /**
     * Multiplier to adjust the cost of repairs.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double RepairCostMultiplier;
    /**
     * Repair cost percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int RepairCostPercent;
    /**
     * Chance of making a research breakthrough when researching a higher tech level of a blueprint
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ReverseEngineeringChance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int RocketVelocityPercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.5)
    public double ShipBrokenModuleRepairCostMultiplier;
    /**
     * Chance of being caught Transporting contraband. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SmugglingChance;
    /**
     * Bonus To standing gain towards non CONCORD npcs  
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int SocialBonus;
    /**
     * Station construction time
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int StationConstructionTime;
    /**
     * Titan construction time
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int TitanConstructionTime;
    /**
     * Torpedo velocity percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TorpedoVelocityPercent;
    /**
     * price bonus when selling to npc corps
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TradePremium;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int WarpAccuracyFactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(15000)
    public int WarpAccuracyMaxRange;
    /**
     * The willpower of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Willpower;

    @Override
    public int getCategoryId() {
        return  1;
    }

    @Override
    public Class<?> getCategory() {
        return Owner.class;
    }

    public static Map<String, ? extends Owner> loadCategory() {
        return Stream.of(Alliance.load(), Character.load(), Corporation.load(), Faction.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
