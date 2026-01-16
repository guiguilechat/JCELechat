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
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.ActiveSystemJump;
import fr.guiguilechat.jcelechat.model.sde.attributes.FilamentDescriptionMessageID;
import fr.guiguilechat.jcelechat.model.sde.attributes.FilamentSpoolupTimeSeconds;
import fr.guiguilechat.jcelechat.model.sde.attributes.LocationListID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MjdShipJumpCap;
import fr.guiguilechat.jcelechat.model.sde.attributes.MjfgRadius;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class ExpiredJumpFilaments
    extends Commodity
{
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int activesystemjump;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(561098)
    public int filamentdescriptionmessageid;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int filamentspooluptimeseconds;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {MjdShipJumpCap.INSTANCE, ActiveSystemJump.INSTANCE, FilamentDescriptionMessageID.INSTANCE, MjfgRadius.INSTANCE, FilamentSpoolupTimeSeconds.INSTANCE, LocationListID.INSTANCE })));
    public static final ExpiredJumpFilaments.MetaGroup METAGROUP = new ExpiredJumpFilaments.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  3025 :
            {
                return activesystemjump;
            }
            case  3026 :
            {
                return filamentdescriptionmessageid;
            }
            case  5783 :
            {
                return filamentspooluptimeseconds;
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
    public IMetaGroup<ExpiredJumpFilaments> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ExpiredJumpFilaments>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/ExpiredJumpFilaments.yaml";
        private Map<Integer, ExpiredJumpFilaments> cache = (null);

        @Override
        public IMetaCategory<? super ExpiredJumpFilaments> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4072;
        }

        @Override
        public String getName() {
            return "ExpiredJumpFilaments";
        }

        @Override
        public synchronized Map<Integer, ExpiredJumpFilaments> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ExpiredJumpFilaments.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, ExpiredJumpFilaments> types;
        }
    }
}
