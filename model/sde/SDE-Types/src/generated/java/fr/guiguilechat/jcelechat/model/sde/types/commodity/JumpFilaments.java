package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class JumpFilaments
    extends Commodity
{
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ActiveSystemJump;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(561098)
    public int FilamentDescriptionMessageID;
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
     * The maximum number of ships that can be jumped per activation
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MjdShipJumpCap;
    /**
     * range effected by mjfg scoop
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MjfgRadius;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    public static final JumpFilaments.MetaGroup METAGROUP = new JumpFilaments.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  3025 :
            {
                return ActiveSystemJump;
            }
            case  3026 :
            {
                return FilamentDescriptionMessageID;
            }
            case  38 :
            {
                return Capacity;
            }
            case  4 :
            {
                return Mass;
            }
            case  2832 :
            {
                return MjdShipJumpCap;
            }
            case  2067 :
            {
                return MjfgRadius;
            }
            case  162 :
            {
                return Radius;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<JumpFilaments> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<JumpFilaments>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/JumpFilaments.yaml";
        private Map<String, JumpFilaments> cache = (null);

        @Override
        public IMetaCategory<? super JumpFilaments> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4041;
        }

        @Override
        public String getName() {
            return "JumpFilaments";
        }

        @Override
        public synchronized Map<String, JumpFilaments> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(JumpFilaments.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, JumpFilaments> types;
        }
    }
}
