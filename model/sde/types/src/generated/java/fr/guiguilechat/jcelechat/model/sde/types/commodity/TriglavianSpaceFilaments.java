package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FilamentDescriptionMessageID;
import fr.guiguilechat.jcelechat.model.sde.attributes.FilamentSpoolupTimeSeconds;
import fr.guiguilechat.jcelechat.model.sde.attributes.LightYearDistanceMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.LocationListID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MjdShipJumpCap;
import fr.guiguilechat.jcelechat.model.sde.attributes.MjfgRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class TriglavianSpaceFilaments
    extends Commodity
{
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(561098)
    public int filamentdescriptionmessageid;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int filamentspooluptimeseconds;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double lightyeardistancemax;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int locationlistid;
    /**
     * The maximum number of ships that can be jumped per activation
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int mjdshipjumpcap;
    /**
     * range effected by mjfg scoop
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int mjfgradius;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {MjdShipJumpCap.INSTANCE, Radius.INSTANCE, FilamentDescriptionMessageID.INSTANCE, MjfgRadius.INSTANCE, Capacity.INSTANCE, FilamentSpoolupTimeSeconds.INSTANCE, LocationListID.INSTANCE, LightYearDistanceMax.INSTANCE })));
    public static final TriglavianSpaceFilaments.MetaGroup METAGROUP = new TriglavianSpaceFilaments.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  3026 :
            {
                return filamentdescriptionmessageid;
            }
            case  38 :
            {
                return capacity;
            }
            case  5783 :
            {
                return filamentspooluptimeseconds;
            }
            case  3097 :
            {
                return lightyeardistancemax;
            }
            case  3096 :
            {
                return locationlistid;
            }
            case  2832 :
            {
                return mjdshipjumpcap;
            }
            case  2067 :
            {
                return mjfgradius;
            }
            case  162 :
            {
                return radius;
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
    public IMetaGroup<TriglavianSpaceFilaments> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TriglavianSpaceFilaments>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/TriglavianSpaceFilaments.yaml";
        private Map<Integer, TriglavianSpaceFilaments> cache = (null);

        @Override
        public IMetaCategory<? super TriglavianSpaceFilaments> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4087;
        }

        @Override
        public String getName() {
            return "TriglavianSpaceFilaments";
        }

        @Override
        public synchronized Map<Integer, TriglavianSpaceFilaments> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(TriglavianSpaceFilaments.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, TriglavianSpaceFilaments> types;
        }
    }
}
