package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class StructureFestivalCharges
    extends Charge
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Agility;
    /**
     * Determines wether a missile launches aligned with the ship (0) or directly at the target (1).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AimedLaunch;
    /**
     * Chance of piercing the armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorPiercingChance;
    /**
     * Just for the UI to display base damage on armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BaseArmorDamage;
    /**
     * Just for the UI to display base damage on shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BaseShieldDamage;
    /**
     * the range in meters for an object to trigger detonation of missile. (own ship excluded)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DetonationRange;
    /**
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ExplosionDelay;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxVelocity;
    /**
     * If present on a type which is used like a missile, signifies that it should never do damage (whether it has any to do or not).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MissileNeverDoesDamage;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * Typically scales the firing speed of a weapon.  Reducing speed means faster, strangely..
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double SpeedMultiplier;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static String RESOURCE_PATH = "SDE/items/charge/StructureFestivalCharges.yaml";
    private static LinkedHashMap<String, StructureFestivalCharges> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return Agility;
            }
            case  644 :
            {
                return AimedLaunch;
            }
            case  122 :
            {
                return ArmorPiercingChance;
            }
            case  613 :
            {
                return BaseArmorDamage;
            }
            case  612 :
            {
                return BaseShieldDamage;
            }
            case  108 :
            {
                return DetonationRange;
            }
            case  281 :
            {
                return ExplosionDelay;
            }
            case  9 :
            {
                return Hp;
            }
            case  137 :
            {
                return LauncherGroup;
            }
            case  37 :
            {
                return MaxVelocity;
            }
            case  1075 :
            {
                return MissileNeverDoesDamage;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            case  204 :
            {
                return SpeedMultiplier;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1976;
    }

    @Override
    public Class<?> getGroup() {
        return StructureFestivalCharges.class;
    }

    public static synchronized LinkedHashMap<String, StructureFestivalCharges> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureFestivalCharges.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StructureFestivalCharges> items;
    }
}
