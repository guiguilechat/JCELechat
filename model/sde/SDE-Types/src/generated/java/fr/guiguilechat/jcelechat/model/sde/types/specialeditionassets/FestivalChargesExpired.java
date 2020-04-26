package fr.guiguilechat.jcelechat.model.sde.types.specialeditionassets;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.SpecialEditionAssets;
import org.yaml.snakeyaml.Yaml;

public class FestivalChargesExpired
    extends SpecialEditionAssets
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
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
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
    @DefaultDoubleValue(0.0)
    public double Hp;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
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
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
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
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ThermalDamage;
    public static final FestivalChargesExpired.MetaGroup METAGROUP = new FestivalChargesExpired.MetaGroup();

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
            case  38 :
            {
                return Capacity;
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
            case  4 :
            {
                return Mass;
            }
            case  37 :
            {
                return MaxVelocity;
            }
            case  1075 :
            {
                return MissileNeverDoesDamage;
            }
            case  162 :
            {
                return Radius;
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
            case  118 :
            {
                return ThermalDamage;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<FestivalChargesExpired> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<FestivalChargesExpired>
    {
        public static final String RESOURCE_PATH = "SDE/types/specialeditionassets/FestivalChargesExpired.yaml";
        private Map<String, FestivalChargesExpired> cache = (null);

        @Override
        public IMetaCategory<? super FestivalChargesExpired> category() {
            return SpecialEditionAssets.METACAT;
        }

        @Override
        public int getGroupId() {
            return  976;
        }

        @Override
        public String getName() {
            return "FestivalChargesExpired";
        }

        @Override
        public synchronized Map<String, FestivalChargesExpired> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(FestivalChargesExpired.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, FestivalChargesExpired> types;
        }
    }
}
