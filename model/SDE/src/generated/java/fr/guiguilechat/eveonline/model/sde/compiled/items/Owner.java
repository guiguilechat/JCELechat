
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;

public abstract class Owner
    extends EveItem
{

    /**
     * Research speed 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double ManufacturingTimeResearchSpeed;
    /**
     * Blueprint copying speed
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double CopySpeedPercent;
    /**
     * The % of Frigate assembly cost a player has to pay to assemble a frigate
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double FrigateConstructionCost;
    /**
     * The % of cruiser assembly cost a player has to pay to assemble a cruiser
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CruiserConstructionCost;
    /**
     * The % of industrial assembly cost a player has to pay to assemble a industrial
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double IndustrialConstructionCost;
    /**
     * The % of battleship assembly cost a player has to pay to assemble a battleship
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double BattleshipConstructionCost;
    /**
     * %chance of new asteroid releasing damage cloud each mining turn.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DamageCloudChance;
    /**
     * Titan construction time
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double TitanConstructionTime;
    /**
     * Station construction time
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double StationConstructionTime;
    /**
     * Repair cost percent
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double RepairCostPercent;
    /**
     * Chance of making a research breakthrough when researching a higher tech level of a blueprint
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ReverseEngineeringChance;
    /**
     * speed bonus when researching blueprint mineral need
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double MineralNeedResearchSpeed;
    /**
     * Chance of upgrading a module to next tech level 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DuplicatingChance;
    /**
     * Missiles velocity multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double MissileStandardVelocityPecent;
    /**
     * Damage Bonus for Cruise Missiles
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CruiseMissileVelocityPercent;
    /**
     * Heavy missile speed percent
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double HeavyMissileSpeedPercent;
    /**
     * Torpedo velocity percent
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double TorpedoVelocityPercent;
    /**
     * Missile FOF velocity percent
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double MissileFOFVelocityPercent;
    /**
     * Max non  race corporationMembers
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxNonRaceCorporationMembers;
    /**
     * The charisma of a character.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Charisma;
    /**
     * The intelligence of a character.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Intelligence;
    /**
     * The memory of a character.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Memory;
    /**
     * The perception of a character.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Perception;
    /**
     * Speed bonus when doing invention or reverse engineering
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double InventionReverseEngineeringResearchSpeed;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double RocketVelocityPercent;
    /**
     * The willpower of a character.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Willpower;
    /**
     * Bonus to the charisma of a character specified by the player in character creation.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CustomCharismaBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double HeavyDroneDamagePercent;
    /**
     * Bonus to the willpower of a character specified by the player in character creation.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CustomWillpowerBonus;
    /**
     * Mining drone speed percent
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double MiningDroneAmountPercent;
    /**
     * Bonus to the perception of a character specified by the player in character creation.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CustomPerceptionBonus;
    /**
     * Bonus to the memory of a character specified by the player in character creation.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CustomMemoryBonus;
    /**
     * Bonus to the intelligence of a character specified by the player in character creation.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CustomIntelligenceBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(3.0D)
    public double MaxGangModules;
    /**
     * skill discount when selling to npc corps
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double BarterDiscount;
    /**
     * price bonus when selling to npc corps
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double TradePremium;
    /**
     * Multiplier to adjust the cost of repairs.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double RepairCostMultiplier;
    /**
     * Chance of being caught selling contraband on market. 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ContrabandFencingChance;
    /**
     * Chance of being caught Transporting contraband. 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SmugglingChance;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxLockedTargets;
    /**
     * The maximum amount of manufacture slots that can be used at a time.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ManufactureSlotLimit;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(20000.0D)
    public double DroneControlDistance;
    /**
     * The chance that the customs official has of detecting contraband on board a scanned vessel
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ContrabandDetectionChance;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double MaxLaborotorySlots;
    /**
     * The characters missile use efficiency, scales the damage missiles do.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double MissileDamageMultiplier;
    /**
     * Scales the time it takes to manufacture something.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double ManufactureTimeMultiplier;
    /**
     * Percentage of pay from agent 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double NegotiationPercentage;
    /**
     * Bonus to Effective Standing towards Hostile NPC
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double DiplomacyBonus;
    /**
     * a percentage multiplyer to sec status recovery
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double FastTalkPercentage;
    /**
     * Maximum amount of Reactions slots that can be used at a time
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ReactionSlotLimit;
    /**
     * Bonus to standing towards Friendly npcs
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ConnectionsBonus;
    /**
     * a bonus to standing towards npc with negativa security status
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CriminalConnectionsBonus;
    /**
     * Bonus To standing gain towards non CONCORD npcs  
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double SocialBonus;
    /**
     * Refining speed percentage
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(100.0D)
    public double RefiningTimePercentage;
    /**
     * Manufacturing cost multiplyer
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double ManufactureCostMultiplier;
    /**
     * Flat Bonus To NPC Bountys
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double BountyBonus;
    /**
     * Npc Bounty Multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double BountyMultiplier;
    /**
     * dictates how many hitpoints you can repair per minute
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(10.0D)
    public double ModuleRepairRate;
    /**
     * Refining yield percentage
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RefiningYieldPercentage;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.5D)
    public double ShipBrokenModuleRepairCostMultiplier;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(15000.0D)
    public double WarpAccuracyMaxRange;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double WarpAccuracyFactor;

    @Override
    public int getCategoryId() {
        return  1;
    }

    @Override
    public Class<?> getCategory() {
        return Owner.class;
    }

}
