package fr.guiguilechat.jcelechat.model.sde.types.blueprint;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustryBlueprintRank;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.Blueprint;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class MicroJumpFieldGeneratorBlueprint
    extends Blueprint
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double industryblueprintrank;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, IndustryBlueprintRank.INSTANCE, Capacity.INSTANCE })));
    public static final MicroJumpFieldGeneratorBlueprint.MetaGroup METAGROUP = new MicroJumpFieldGeneratorBlueprint.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return capacity;
            }
            case  1955 :
            {
                return industryblueprintrank;
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
    public IMetaGroup<MicroJumpFieldGeneratorBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MicroJumpFieldGeneratorBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/types/blueprint/MicroJumpFieldGeneratorBlueprint.yaml";
        private Map<Integer, MicroJumpFieldGeneratorBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super MicroJumpFieldGeneratorBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1543;
        }

        @Override
        public String getName() {
            return "MicroJumpFieldGeneratorBlueprint";
        }

        @Override
        public synchronized Map<Integer, MicroJumpFieldGeneratorBlueprint> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MicroJumpFieldGeneratorBlueprint.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, MicroJumpFieldGeneratorBlueprint> types;
        }
    }
}
