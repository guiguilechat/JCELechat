package fr.guiguilechat.jcelechat.model.sde.types.owner;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.BarterDiscount;
import fr.guiguilechat.jcelechat.model.sde.attributes.BattleshipConstructionCost;
import fr.guiguilechat.jcelechat.model.sde.attributes.BountyBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.BountyMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charisma;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConnectionsBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ContrabandDetectionChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ContrabandFencingChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.CopySpeedPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.CriminalConnectionsBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CruiseMissileVelocityPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.CruiserConstructionCost;
import fr.guiguilechat.jcelechat.model.sde.attributes.CustomCharismaBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CustomIntelligenceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CustomMemoryBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CustomPerceptionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CustomWillpowerBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageCloudChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.DiplomacyBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneControlDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.DuplicatingChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FastTalkPercentage;
import fr.guiguilechat.jcelechat.model.sde.attributes.FrigateConstructionCost;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeavyDroneDamagePercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeavyMissileSpeedPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialConstructionCost;
import fr.guiguilechat.jcelechat.model.sde.attributes.Intelligence;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionReverseEngineeringResearchSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.ManufactureCostMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ManufactureSlotLimit;
import fr.guiguilechat.jcelechat.model.sde.attributes.ManufactureTimeMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ManufacturingTimeResearchSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGangModules;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLaborotorySlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxNonRaceCorporationMembers;
import fr.guiguilechat.jcelechat.model.sde.attributes.Memory;
import fr.guiguilechat.jcelechat.model.sde.attributes.MineralNeedResearchSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningDroneAmountPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileDamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileFOFVelocityPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileStandardVelocityPecent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModuleRepairRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.NegotiationPercentage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Perception;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReactionSlotLimit;
import fr.guiguilechat.jcelechat.model.sde.attributes.RefiningTimePercentage;
import fr.guiguilechat.jcelechat.model.sde.attributes.RefiningYieldPercentage;
import fr.guiguilechat.jcelechat.model.sde.attributes.RepairCostMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.RepairCostPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReverseEngineeringChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RocketVelocityPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBrokenModuleRepairCostMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.SmugglingChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.SocialBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StationConstructionTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.TitanConstructionTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.TorpedoVelocityPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.TradePremium;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpAccuracyFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpAccuracyMaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.Willpower;
import fr.guiguilechat.jcelechat.model.sde.types.Owner;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Character
    extends Owner
{
    /**
     * skill discount when selling to npc corps
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int barterdiscount;
    /**
     * The % of battleship assembly cost a player has to pay to assemble a battleship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int battleshipconstructioncost;
    /**
     * Flat Bonus To NPC Bountys
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int bountybonus;
    /**
     * Npc Bounty Multiplier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int bountymultiplier;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * The charisma of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int charisma;
    /**
     * Bonus to standing towards Friendly npcs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int connectionsbonus;
    /**
     * The chance that the customs official has of detecting contraband on board a scanned vessel
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double contrabanddetectionchance;
    /**
     * Chance of being caught selling contraband on market. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double contrabandfencingchance;
    /**
     * Blueprint copying speed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int copyspeedpercent;
    /**
     * a bonus to standing towards npc with negativa security status
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int criminalconnectionsbonus;
    /**
     * Damage Bonus for Cruise Missiles
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cruisemissilevelocitypercent;
    /**
     * The % of cruiser assembly cost a player has to pay to assemble a cruiser
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cruiserconstructioncost;
    /**
     * Bonus to the charisma of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int customcharismabonus;
    /**
     * Bonus to the intelligence of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int customintelligencebonus;
    /**
     * Bonus to the memory of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int custommemorybonus;
    /**
     * Bonus to the perception of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int customperceptionbonus;
    /**
     * Bonus to the willpower of a character specified by the player in character creation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int customwillpowerbonus;
    /**
     * %chance of new asteroid releasing damage cloud each mining turn.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double damagecloudchance;
    /**
     * Bonus to Effective Standing towards Hostile NPC
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int diplomacybonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(20000)
    public int dronecontroldistance;
    /**
     * Chance of upgrading a module to next tech level 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int duplicatingchance;
    /**
     * a percentage multiplyer to sec status recovery
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int fasttalkpercentage;
    /**
     * The % of Frigate assembly cost a player has to pay to assemble a frigate
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int frigateconstructioncost;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int heavydronedamagepercent;
    /**
     * Heavy missile speed percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int heavymissilespeedpercent;
    /**
     * The % of industrial assembly cost a player has to pay to assemble a industrial
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialconstructioncost;
    /**
     * The intelligence of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int intelligence;
    /**
     * Speed bonus when doing invention or reverse engineering
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int inventionreverseengineeringresearchspeed;
    /**
     * Manufacturing cost multiplyer
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int manufacturecostmultiplier;
    /**
     * The maximum amount of manufacture slots that can be used at a time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int manufactureslotlimit;
    /**
     * Scales the time it takes to manufacture something.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int manufacturetimemultiplier;
    /**
     * Research speed 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int manufacturingtimeresearchspeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(3)
    public int maxgangmodules;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int maxlaborotoryslots;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxlockedtargets;
    /**
     * Max non  race corporationMembers
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxnonracecorporationmembers;
    /**
     * The memory of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int memory;
    /**
     * speed bonus when researching blueprint mineral need
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int mineralneedresearchspeed;
    /**
     * Mining drone speed percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int miningdroneamountpercent;
    /**
     * The characters missile use efficiency, scales the damage missiles do.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double missiledamagemultiplier;
    /**
     * Missile FOF velocity percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int missilefofvelocitypercent;
    /**
     * Missiles velocity multiplier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int missilestandardvelocitypecent;
    /**
     * dictates how many hitpoints you can repair per minute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10)
    public int modulerepairrate;
    /**
     * Percentage of pay from agent 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int negotiationpercentage;
    /**
     * The perception of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int perception;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    /**
     * Maximum amount of Reactions slots that can be used at a time
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int reactionslotlimit;
    /**
     * Refining speed percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int refiningtimepercentage;
    /**
     * Refining yield percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int refiningyieldpercentage;
    /**
     * Multiplier to adjust the cost of repairs.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double repaircostmultiplier;
    /**
     * Repair cost percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int repaircostpercent;
    /**
     * Chance of making a research breakthrough when researching a higher tech level of a blueprint
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int reverseengineeringchance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int rocketvelocitypercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.5)
    public double shipbrokenmodulerepaircostmultiplier;
    /**
     * Chance of being caught Transporting contraband. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double smugglingchance;
    /**
     * Bonus To standing gain towards non CONCORD npcs  
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int socialbonus;
    /**
     * Station construction time
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int stationconstructiontime;
    /**
     * Titan construction time
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int titanconstructiontime;
    /**
     * Torpedo velocity percent
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int torpedovelocitypercent;
    /**
     * price bonus when selling to npc corps
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int tradepremium;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int warpaccuracyfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(15000)
    public int warpaccuracymaxrange;
    /**
     * The willpower of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int willpower;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ManufacturingTimeResearchSpeed.INSTANCE, CopySpeedPercent.INSTANCE, FrigateConstructionCost.INSTANCE, CruiserConstructionCost.INSTANCE, IndustrialConstructionCost.INSTANCE, BattleshipConstructionCost.INSTANCE, TitanConstructionTime.INSTANCE, DamageCloudChance.INSTANCE, StationConstructionTime.INSTANCE, RepairCostPercent.INSTANCE, ReverseEngineeringChance.INSTANCE, MineralNeedResearchSpeed.INSTANCE, DuplicatingChance.INSTANCE, MissileStandardVelocityPecent.INSTANCE, CruiseMissileVelocityPercent.INSTANCE, HeavyMissileSpeedPercent.INSTANCE, TorpedoVelocityPercent.INSTANCE, MissileFOFVelocityPercent.INSTANCE, MaxNonRaceCorporationMembers.INSTANCE, Radius.INSTANCE, Charisma.INSTANCE, Intelligence.INSTANCE, Memory.INSTANCE, Capacity.INSTANCE, Perception.INSTANCE, InventionReverseEngineeringResearchSpeed.INSTANCE, RocketVelocityPercent.INSTANCE, Willpower.INSTANCE, CustomCharismaBonus.INSTANCE, HeavyDroneDamagePercent.INSTANCE, CustomWillpowerBonus.INSTANCE, CustomPerceptionBonus.INSTANCE, MiningDroneAmountPercent.INSTANCE, CustomMemoryBonus.INSTANCE, CustomIntelligenceBonus.INSTANCE, MaxGangModules.INSTANCE, BarterDiscount.INSTANCE, RepairCostMultiplier.INSTANCE, TradePremium.INSTANCE, ContrabandFencingChance.INSTANCE, SmugglingChance.INSTANCE, MaxLockedTargets.INSTANCE, ManufactureSlotLimit.INSTANCE, DroneControlDistance.INSTANCE, MaxLaborotorySlots.INSTANCE, ContrabandDetectionChance.INSTANCE, MissileDamageMultiplier.INSTANCE, ManufactureTimeMultiplier.INSTANCE, NegotiationPercentage.INSTANCE, DiplomacyBonus.INSTANCE, FastTalkPercentage.INSTANCE, ReactionSlotLimit.INSTANCE, ConnectionsBonus.INSTANCE, CriminalConnectionsBonus.INSTANCE, SocialBonus.INSTANCE, RefiningTimePercentage.INSTANCE, BountyBonus.INSTANCE, ManufactureCostMultiplier.INSTANCE, BountyMultiplier.INSTANCE, ModuleRepairRate.INSTANCE, RefiningYieldPercentage.INSTANCE, ShipBrokenModuleRepairCostMultiplier.INSTANCE, WarpAccuracyMaxRange.INSTANCE, WarpAccuracyFactor.INSTANCE })));
    public static final Character.MetaGroup METAGROUP = new Character.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  442 :
            {
                return barterdiscount;
            }
            case  393 :
            {
                return battleshipconstructioncost;
            }
            case  625 :
            {
                return bountybonus;
            }
            case  626 :
            {
                return bountymultiplier;
            }
            case  38 :
            {
                return capacity;
            }
            case  164 :
            {
                return charisma;
            }
            case  360 :
            {
                return connectionsbonus;
            }
            case  723 :
            {
                return contrabanddetectionchance;
            }
            case  444 :
            {
                return contrabandfencingchance;
            }
            case  387 :
            {
                return copyspeedpercent;
            }
            case  361 :
            {
                return criminalconnectionsbonus;
            }
            case  401 :
            {
                return cruisemissilevelocitypercent;
            }
            case  389 :
            {
                return cruiserconstructioncost;
            }
            case  170 :
            {
                return customcharismabonus;
            }
            case  174 :
            {
                return customintelligencebonus;
            }
            case  173 :
            {
                return custommemorybonus;
            }
            case  172 :
            {
                return customperceptionbonus;
            }
            case  171 :
            {
                return customwillpowerbonus;
            }
            case  522 :
            {
                return damagecloudchance;
            }
            case  356 :
            {
                return diplomacybonus;
            }
            case  458 :
            {
                return dronecontroldistance;
            }
            case  399 :
            {
                return duplicatingchance;
            }
            case  359 :
            {
                return fasttalkpercentage;
            }
            case  388 :
            {
                return frigateconstructioncost;
            }
            case  426 :
            {
                return heavydronedamagepercent;
            }
            case  402 :
            {
                return heavymissilespeedpercent;
            }
            case  392 :
            {
                return industrialconstructioncost;
            }
            case  165 :
            {
                return intelligence;
            }
            case  1959 :
            {
                return inventionreverseengineeringresearchspeed;
            }
            case  369 :
            {
                return manufacturecostmultiplier;
            }
            case  196 :
            {
                return manufactureslotlimit;
            }
            case  219 :
            {
                return manufacturetimemultiplier;
            }
            case  385 :
            {
                return manufacturingtimeresearchspeed;
            }
            case  435 :
            {
                return maxgangmodules;
            }
            case  467 :
            {
                return maxlaborotoryslots;
            }
            case  192 :
            {
                return maxlockedtargets;
            }
            case  417 :
            {
                return maxnonracecorporationmembers;
            }
            case  166 :
            {
                return memory;
            }
            case  398 :
            {
                return mineralneedresearchspeed;
            }
            case  428 :
            {
                return miningdroneamountpercent;
            }
            case  212 :
            {
                return missiledamagemultiplier;
            }
            case  406 :
            {
                return missilefofvelocitypercent;
            }
            case  400 :
            {
                return missilestandardvelocitypecent;
            }
            case  1267 :
            {
                return modulerepairrate;
            }
            case  355 :
            {
                return negotiationpercentage;
            }
            case  167 :
            {
                return perception;
            }
            case  162 :
            {
                return radius;
            }
            case  2664 :
            {
                return reactionslotlimit;
            }
            case  368 :
            {
                return refiningtimepercentage;
            }
            case  378 :
            {
                return refiningyieldpercentage;
            }
            case  187 :
            {
                return repaircostmultiplier;
            }
            case  396 :
            {
                return repaircostpercent;
            }
            case  397 :
            {
                return reverseengineeringchance;
            }
            case  551 :
            {
                return rocketvelocitypercent;
            }
            case  1277 :
            {
                return shipbrokenmodulerepaircostmultiplier;
            }
            case  445 :
            {
                return smugglingchance;
            }
            case  362 :
            {
                return socialbonus;
            }
            case  395 :
            {
                return stationconstructiontime;
            }
            case  394 :
            {
                return titanconstructiontime;
            }
            case  404 :
            {
                return torpedovelocitypercent;
            }
            case  443 :
            {
                return tradepremium;
            }
            case  1022 :
            {
                return warpaccuracyfactor;
            }
            case  1021 :
            {
                return warpaccuracymaxrange;
            }
            case  168 :
            {
                return willpower;
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
    public IMetaGroup<Character> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Character>
    {
        public static final String RESOURCE_PATH = "SDE/types/owner/Character.yaml";
        private Map<Integer, Character> cache = (null);

        @Override
        public IMetaCategory<? super Character> category() {
            return Owner.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1;
        }

        @Override
        public String getName() {
            return "Character";
        }

        @Override
        public synchronized Map<Integer, Character> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Character.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Character> types;
        }
    }
}
