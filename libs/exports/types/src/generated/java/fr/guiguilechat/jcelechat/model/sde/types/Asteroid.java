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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidMetaLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidRadiusGrowthFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Arkonor;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Bezdnacine;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Bistot;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.CommonMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Crokite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.DarkOchre;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Ducinium;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Eifyrium;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.ExceptionalMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Gneiss;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Griemeer;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Hedbergite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Hemorphite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Hezorime;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Ice;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Jaspet;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Kernite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Kylixium;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Mercoxit;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Mordunium;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Mutanite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Nocxite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Omber;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Plagioclase;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Pyroxeres;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Rakovene;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.RareMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Scordite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Spodumain;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Talassonite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Tyranite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.UbiquitousMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Ueganite;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.UncommonMoonAsteroids;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Veldspar;
import fr.guiguilechat.jcelechat.model.sde.types.asteroid.Ytirium;

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
    public int asteroidmetalevel;
    /**
     * Controls how quickly an asteroid radius increases as its quantity grows.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double asteroidradiusgrowthfactor;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, AsteroidMetaLevel.INSTANCE, AsteroidRadiusGrowthFactor.INSTANCE })));
    public static final Asteroid.MetaCat METACAT = new Asteroid.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2699 :
            {
                return asteroidmetalevel;
            }
            case  1980 :
            {
                return asteroidradiusgrowthfactor;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
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
            return Arrays.asList(Mordunium.METAGROUP, Ytirium.METAGROUP, Eifyrium.METAGROUP, Ducinium.METAGROUP, Arkonor.METAGROUP, Bistot.METAGROUP, Crokite.METAGROUP, DarkOchre.METAGROUP, Hedbergite.METAGROUP, Hemorphite.METAGROUP, Jaspet.METAGROUP, Kernite.METAGROUP, Plagioclase.METAGROUP, Pyroxeres.METAGROUP, Scordite.METAGROUP, Spodumain.METAGROUP, Veldspar.METAGROUP, Ice.METAGROUP, Gneiss.METAGROUP, Mercoxit.METAGROUP, Omber.METAGROUP, Mutanite.METAGROUP, Kylixium.METAGROUP, Nocxite.METAGROUP, Ueganite.METAGROUP, Hezorime.METAGROUP, Griemeer.METAGROUP, Tyranite.METAGROUP, UbiquitousMoonAsteroids.METAGROUP, CommonMoonAsteroids.METAGROUP, UncommonMoonAsteroids.METAGROUP, RareMoonAsteroids.METAGROUP, ExceptionalMoonAsteroids.METAGROUP, Talassonite.METAGROUP, Rakovene.METAGROUP, Bezdnacine.METAGROUP);
        }
    }
}
