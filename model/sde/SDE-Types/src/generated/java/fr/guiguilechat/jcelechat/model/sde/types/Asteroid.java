package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Arkonor;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Bistot;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.CommonMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Crokite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.DarkOchre;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.ExceptionalMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Gneiss;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Hedbergite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Hemorphite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Ice;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Jaspet;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Kernite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Mercoxit;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Omber;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Plagioclase;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Pyroxeres;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.RareMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Scordite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Spodumain;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.UbiquitousMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.UncommonMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Veldspar;

public abstract class Asteroid
    extends EveType
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
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StasisWebifierResistance;
    public static final Asteroid.MetaCat METACAT = new Asteroid.MetaCat();

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
            case  277 :
            {
                return RequiredSkill1Level;
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
    public IMetaCategory<Asteroid> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Asteroid>
    {

        @Override
        public int getCategoryId() {
            return  25;
        }

        @Override
        public String getName() {
            return "Asteroid";
        }

        @Override
        public Collection<IMetaGroup<? extends Asteroid>> groups() {
            return Arrays.asList(Arkonor.METAGROUP, Bistot.METAGROUP, Crokite.METAGROUP, DarkOchre.METAGROUP, Hedbergite.METAGROUP, Hemorphite.METAGROUP, Jaspet.METAGROUP, Kernite.METAGROUP, Plagioclase.METAGROUP, Pyroxeres.METAGROUP, Scordite.METAGROUP, Spodumain.METAGROUP, Veldspar.METAGROUP, Ice.METAGROUP, Gneiss.METAGROUP, Mercoxit.METAGROUP, Omber.METAGROUP, UbiquitousMoonAsteroids.METAGROUP, CommonMoonAsteroids.METAGROUP, UncommonMoonAsteroids.METAGROUP, RareMoonAsteroids.METAGROUP, ExceptionalMoonAsteroids.METAGROUP);
        }
    }
}
