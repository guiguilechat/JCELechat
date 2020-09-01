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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionMEModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionMaxRunModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionPropabilityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionTEModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsYanJung
    extends Commodity
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * Modifies the mineral efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double inventionmemodifier;
    /**
     * Modifies the max runs in a blueprint created through invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double inventionmaxrunmodifier;
    /**
     * Modifies base chance of successful invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double inventionpropabilitymultiplier;
    /**
     * Modifies the time efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double inventiontemodifier;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, InventionMaxRunModifier.INSTANCE, Mass.INSTANCE, Capacity.INSTANCE, InventionPropabilityMultiplier.INSTANCE, InventionMEModifier.INSTANCE, InventionTEModifier.INSTANCE })));
    public static final DecryptorsYanJung.MetaGroup METAGROUP = new DecryptorsYanJung.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return capacity;
            }
            case  1113 :
            {
                return inventionmemodifier;
            }
            case  1124 :
            {
                return inventionmaxrunmodifier;
            }
            case  1112 :
            {
                return inventionpropabilitymultiplier;
            }
            case  1114 :
            {
                return inventiontemodifier;
            }
            case  4 :
            {
                return mass;
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
    public IMetaGroup<DecryptorsYanJung> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DecryptorsYanJung>
    {
        public static final String RESOURCE_PATH = "SDE/types/commodity/DecryptorsYanJung.yaml";
        private Map<String, DecryptorsYanJung> cache = (null);

        @Override
        public IMetaCategory<? super DecryptorsYanJung> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  733;
        }

        @Override
        public String getName() {
            return "DecryptorsYanJung";
        }

        @Override
        public synchronized Map<String, DecryptorsYanJung> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DecryptorsYanJung.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DecryptorsYanJung> types;
        }
    }
}
