package fr.guiguilechat.jcelechat.model.sde.items.types.structure;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class EngineeringComplex
    extends Structure
{
    /**
     * Cost bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StrEngCostBonus;
    /**
     * Material bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StrEngMatBonus;
    /**
     * Time bonus for Engineering Complexes Structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StrEngTimeBonus;
    public final static EngineeringComplex.MetaGroup METAGROUP = new EngineeringComplex.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2601 :
            {
                return StrEngCostBonus;
            }
            case  2600 :
            {
                return StrEngMatBonus;
            }
            case  2602 :
            {
                return StrEngTimeBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<EngineeringComplex> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EngineeringComplex>
    {
        public final static String RESOURCE_PATH = "SDE/items/structure/EngineeringComplex.yaml";
        private Map<String, EngineeringComplex> cache = (null);

        @Override
        public IMetaCategory<? super EngineeringComplex> category() {
            return Structure.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1404;
        }

        @Override
        public String getName() {
            return "EngineeringComplex";
        }

        @Override
        public synchronized Map<String, EngineeringComplex> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(EngineeringComplex.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, EngineeringComplex> items;
        }
    }
}