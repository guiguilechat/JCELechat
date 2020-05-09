package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Arkonor;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Bezdnacine;
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
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Rakovene;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.RareMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Scordite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Spodumain;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Talassonite;
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
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    /**
     * The skill required to reprocess this ore type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ReprocessingSkillType;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {fr.guiguilechat.jcelechat.model.sde.attributes.Radius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Mass.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Capacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidMetaLevel.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ReprocessingSkillType.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidRadiusGrowthFactor.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidRadiusUnitSize.INSTANCE })));
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
            case  38 :
            {
                return Capacity;
            }
            case  4 :
            {
                return Mass;
            }
            case  162 :
            {
                return Radius;
            }
            case  790 :
            {
                return ReprocessingSkillType;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
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
            return Arrays.asList(Arkonor.METAGROUP, Bistot.METAGROUP, Crokite.METAGROUP, DarkOchre.METAGROUP, Hedbergite.METAGROUP, Hemorphite.METAGROUP, Jaspet.METAGROUP, Kernite.METAGROUP, Plagioclase.METAGROUP, Pyroxeres.METAGROUP, Scordite.METAGROUP, Spodumain.METAGROUP, Veldspar.METAGROUP, Ice.METAGROUP, Gneiss.METAGROUP, Mercoxit.METAGROUP, Omber.METAGROUP, UbiquitousMoonAsteroids.METAGROUP, CommonMoonAsteroids.METAGROUP, UncommonMoonAsteroids.METAGROUP, RareMoonAsteroids.METAGROUP, ExceptionalMoonAsteroids.METAGROUP, Talassonite.METAGROUP, Rakovene.METAGROUP, Bezdnacine.METAGROUP);
        }
    }
}
