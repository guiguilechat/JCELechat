package fr.guiguilechat.jcelechat.model.sde.items.types.ship;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class Shuttle
    extends Ship
{
    /**
     * Multiplier for jump fatigue distance
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double JumpFatigueMultiplier;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxPassengers;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    public final static Shuttle.MetaGroup METAGROUP = new Shuttle.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/ship/Shuttle.yaml";
    private static Map<String, Shuttle> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1971 :
            {
                return JumpFatigueMultiplier;
            }
            case  124 :
            {
                return MainColor;
            }
            case  129 :
            {
                return MaxPassengers;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  79 :
            {
                return ScanSpeed;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  31;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Shuttle> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Shuttle> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Shuttle.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Shuttle> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Shuttle>
    {

        @Override
        public MetaCategory<? super Shuttle> category() {
            return Ship.METACAT;
        }

        @Override
        public String getName() {
            return "Shuttle";
        }

        @Override
        public Collection<Shuttle> items() {
            return (load().values());
        }
    }
}
