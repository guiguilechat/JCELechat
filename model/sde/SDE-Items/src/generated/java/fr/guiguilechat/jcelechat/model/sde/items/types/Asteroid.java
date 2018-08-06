package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Arkonor;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Bistot;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.CommonMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Crokite;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.DarkOchre;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.ExceptionalMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Gneiss;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Hedbergite;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Hemorphite;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Ice;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Jaspet;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Kernite;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Mercoxit;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Omber;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Plagioclase;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Pyroxeres;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.RareMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Scordite;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Spodumain;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.UbiquitousMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.UncommonMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.items.types.asteroid.Veldspar;

public abstract class Asteroid
    extends Item
{
    /**
     *  0: Mission/NPE Ore
     *  1: Standard Ore/Ice
     *  2: +5% Ore
     *  3: +10% Ore
     *  4: High Quality Ice or Extracted Ore
     *  5: Jackpot Moon Ore
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AsteroidMetaLevel;
    /**
     * Controls how quickly an asteroid radius increases as its quantity grows.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double AsteroidRadiusGrowthFactor;
    /**
     * Sets the radius of the asteroid ball when it has a quantity of 1 unit
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(90)
    public int AsteroidRadiusUnitSize;
    /**
     * Reference for grouping ores in visual displays. All variants of one ore should have the same BasicType ID
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int OreBasicType;
    /**
     * The skill required to reprocess this ore type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ReprocessingSkillType;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StasisWebifierResistance;

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2699 :
            {
                return AsteroidMetaLevel;
            }
            case  1980 :
            {
                return AsteroidRadiusGrowthFactor;
            }
            case  1981 :
            {
                return AsteroidRadiusUnitSize;
            }
            case  2711 :
            {
                return OreBasicType;
            }
            case  790 :
            {
                return ReprocessingSkillType;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  2115 :
            {
                return StasisWebifierResistance;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getCategoryId() {
        return  25;
    }

    @Override
    public Class<?> getCategory() {
        return Asteroid.class;
    }

    public static Map<String, ? extends Asteroid> loadCategory() {
        return Stream.of(Arkonor.load(), Bistot.load(), CommonMoonAsteroids.load(), Crokite.load(), DarkOchre.load(), ExceptionalMoonAsteroids.load(), Gneiss.load(), Hedbergite.load(), Hemorphite.load(), Ice.load(), Jaspet.load(), Kernite.load(), Mercoxit.load(), Omber.load(), Plagioclase.load(), Pyroxeres.load(), RareMoonAsteroids.load(), Scordite.load(), Spodumain.load(), UbiquitousMoonAsteroids.load(), UncommonMoonAsteroids.load(), Veldspar.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
