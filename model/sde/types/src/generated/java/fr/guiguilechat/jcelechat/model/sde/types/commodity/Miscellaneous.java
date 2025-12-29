package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;

public class Miscellaneous
    extends Commodity
{
    /**
     * attribute that, along with aspectRatioWidth, describes the proportions of videos/images in a puzzle
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(1)
    public int aspectratioheight;
    /**
     * attribute that, along with aspectRatioHeight, describes the proportions of videos/images in a puzzle
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(1)
    public int aspectratiowidth;
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
     * Export tax multiplier when exporting this commodity off a planet.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int exporttaxmultiplier;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * If set to true, this results in no mining waste.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ignoreminingwaste;
    /**
     * Cost multiplier per m3 volume of this commodity when importing to a planet
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int importtaxmultiplier;
    /**
     * Reference for grouping ores in visual displays. All variants of one ore should have the same BasicType ID
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int orebasictype;
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double stasiswebifierresistance;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {AspectRatioHeight.INSTANCE, StasisWebifierResistance.INSTANCE, IgnoreMiningWaste.INSTANCE, OreBasicType.INSTANCE, ImportTaxMultiplier.INSTANCE, Hp.INSTANCE, ExportTaxMultiplier.INSTANCE, AsteroidMetaLevel.INSTANCE, AsteroidRadiusGrowthFactor.INSTANCE, AspectRatioWidth.INSTANCE })));
    public static final Miscellaneous.MetaGroup METAGROUP = new Miscellaneous.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  5760 :
            {
                return aspectratioheight;
            }
            case  5759 :
            {
                return aspectratiowidth;
            }
            case  2699 :
            {
                return asteroidmetalevel;
            }
            case  1980 :
            {
                return asteroidradiusgrowthfactor;
            }
            case  1641 :
            {
                return exporttaxmultiplier;
            }
            case  9 :
            {
                return hp;
            }
            case  3236 :
            {
                return ignoreminingwaste;
            }
            case  1640 :
            {
                return importtaxmultiplier;
            }
            case  2711 :
            {
                return orebasictype;
            }
            case  2115 :
            {
                return stasiswebifierresistance;
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
    public IMetaGroup<Miscellaneous> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Miscellaneous>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/Miscellaneous.yaml";
        private Map<Integer, Miscellaneous> cache = (null);

        @Override
        public IMetaCategory<? super Miscellaneous> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  314;
        }

        @Override
        public String getName() {
            return "Miscellaneous";
        }

        @Override
        public synchronized Map<Integer, Miscellaneous> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Miscellaneous.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Miscellaneous> types;
        }
    }
}
