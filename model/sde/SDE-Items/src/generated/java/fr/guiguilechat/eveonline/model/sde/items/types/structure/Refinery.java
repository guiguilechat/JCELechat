package fr.guiguilechat.eveonline.model.sde.items.types.structure;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class Refinery
    extends Structure
{
    /**
     * Delay for exploding moon mining chunk into asteroid field
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10800)
    public int AutoFractureDelay;
    /**
     * This defines the total capacity of fighters allowed in the fighter bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterCapacity;
    /**
     * Number of Heavy Fighters the structure can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterStandupHeavySlots;
    /**
     * Number of Light Fighters the structure can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterStandupLightSlots;
    /**
     * Number of Support Fighters the structure can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterStandupSupportSlots;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterTubes;
    /**
     * Defines whether an entity can be hacked or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hackable;
    /**
     * Armor hitpoint attribute used by structures as a workaround for implementing Standup layered plating stacking penalties
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int HiddenArmorHPMultiplier;
    /**
     * Missile damage attribute used by structures as a workaround for implementing Standup BCS stacking penalties
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int HiddenMissileDamageMultiplier;
    /**
     * The number of remaining unused launcher slots.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherSlotsLeft;
    /**
     * The maximum possible target range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int MaximumRangeCap;
    /**
     * This attribute doesn't directly impact the asteroid decay, but is used to expose the decay time to the show-info window
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(48)
    public int MoonAsteroidDecayDisplayValue;
    /**
     * Decay time for asteroid created from moon spew
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MoonAsteroidDecayTimeMultiplier;
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
    public int ServiceSlots;
    /**
     * Time bonus for Refinery Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StrReactionTimeMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StrRefiningYieldBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureAoERoFRoleBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int StructureFullPowerStateHitpointMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureServiceRoleBonus;
    /**
     * Distance which tethering will engage / disengage piloted ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TetheringRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TierDifficulty;
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
    public final static String RESOURCE_PATH = "SDE/items/structure/Refinery.yaml";
    private static LinkedHashMap<String, Refinery> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2698 :
            {
                return AutoFractureDelay;
            }
            case  2055 :
            {
                return FighterCapacity;
            }
            case  2739 :
            {
                return FighterStandupHeavySlots;
            }
            case  2737 :
            {
                return FighterStandupLightSlots;
            }
            case  2738 :
            {
                return FighterStandupSupportSlots;
            }
            case  2216 :
            {
                return FighterTubes;
            }
            case  1927 :
            {
                return Hackable;
            }
            case  2751 :
            {
                return HiddenArmorHPMultiplier;
            }
            case  2750 :
            {
                return HiddenMissileDamageMultiplier;
            }
            case  101 :
            {
                return LauncherSlotsLeft;
            }
            case  797 :
            {
                return MaximumRangeCap;
            }
            case  2728 :
            {
                return MoonAsteroidDecayDisplayValue;
            }
            case  2706 :
            {
                return MoonAsteroidDecayTimeMultiplier;
            }
            case  1547 :
            {
                return RigSize;
            }
            case  1137 :
            {
                return RigSlots;
            }
            case  2056 :
            {
                return ServiceSlots;
            }
            case  2721 :
            {
                return StrReactionTimeMultiplier;
            }
            case  2722 :
            {
                return StrRefiningYieldBonus;
            }
            case  2749 :
            {
                return StructureAoERoFRoleBonus;
            }
            case  2743 :
            {
                return StructureFullPowerStateHitpointMultiplier;
            }
            case  2339 :
            {
                return StructureServiceRoleBonus;
            }
            case  2268 :
            {
                return TetheringRange;
            }
            case  1919 :
            {
                return TierDifficulty;
            }
            case  1132 :
            {
                return UpgradeCapacity;
            }
            case  1154 :
            {
                return UpgradeSlotsLeft;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1406;
    }

    @Override
    public Class<?> getGroup() {
        return Refinery.class;
    }

    public static synchronized LinkedHashMap<String, Refinery> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Refinery.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Refinery> items;
    }
}
