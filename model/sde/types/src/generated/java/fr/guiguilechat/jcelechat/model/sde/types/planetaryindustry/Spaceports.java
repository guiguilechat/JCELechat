package fr.guiguilechat.jcelechat.model.sde.types.planetaryindustry;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExportTax;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImportTax;
import fr.guiguilechat.jcelechat.model.sde.attributes.PlanetRestriction;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryIndustry;

public class Spaceports
    extends PlanetaryIndustry
{
    /**
     * CPU load of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cpuload;
    /**
     * Base export tax (ISK per m3 of volume) on commodities exported from a planet via this pin.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int exporttax;
    /**
     * Base importation tax (ISK per m3 of volume) for commodities imported to pin.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double importtax;
    /**
     * This type can only be found/used/created on a planet matching this type ID.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int planetrestriction;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int powerload;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {PlanetRestriction.INSTANCE, CpuLoad.INSTANCE, ImportTax.INSTANCE, ExportTax.INSTANCE, PowerLoad.INSTANCE })));
    public static final Spaceports.MetaGroup METAGROUP = new Spaceports.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  49 :
            {
                return cpuload;
            }
            case  1639 :
            {
                return exporttax;
            }
            case  1638 :
            {
                return importtax;
            }
            case  1632 :
            {
                return planetrestriction;
            }
            case  15 :
            {
                return powerload;
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
    public IMetaGroup<Spaceports> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Spaceports>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetaryindustry/Spaceports.yaml";
        private Map<Integer, Spaceports> cache = (null);

        @Override
        public IMetaCategory<? super Spaceports> category() {
            return PlanetaryIndustry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1030;
        }

        @Override
        public String getName() {
            return "Spaceports";
        }

        @Override
        public synchronized Map<Integer, Spaceports> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Spaceports.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Spaceports> types;
        }
    }
}
