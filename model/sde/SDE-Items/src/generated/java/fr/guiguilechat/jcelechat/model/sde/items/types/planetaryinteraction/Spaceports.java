package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryinteraction;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryInteraction;
import org.yaml.snakeyaml.Yaml;

public class Spaceports
    extends PlanetaryInteraction
{
    /**
     * CPU load of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuLoad;
    /**
     * Base export tax (ISK per m3 of volume) on commodities exported from a planet via this pin.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ExportTax;
    /**
     * Base importation tax (ISK per m3 of volume) for commodities imported to pin.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ImportTax;
    /**
     * This type can only be found/used/created on a planet matching this type ID.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PlanetRestriction;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerLoad;
    public final static Spaceports.MetaGroup METAGROUP = new Spaceports.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/planetaryinteraction/Spaceports.yaml";
    private static Map<String, Spaceports> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  49 :
            {
                return CpuLoad;
            }
            case  1639 :
            {
                return ExportTax;
            }
            case  1638 :
            {
                return ImportTax;
            }
            case  1632 :
            {
                return PlanetRestriction;
            }
            case  15 :
            {
                return PowerLoad;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1030;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Spaceports> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Spaceports> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Spaceports.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Spaceports> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Spaceports>
    {

        @Override
        public MetaCategory<? super Spaceports> category() {
            return PlanetaryInteraction.METACAT;
        }

        @Override
        public String getName() {
            return "Spaceports";
        }

        @Override
        public Collection<Spaceports> items() {
            return (load().values());
        }
    }
}
