package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class CargoContainer
    extends Celestial
{
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    public static final CargoContainer.MetaGroup METAGROUP = new CargoContainer.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  525 :
            {
                return StructureUniformity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<CargoContainer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CargoContainer>
    {
        public static final String RESOURCE_PATH = "SDE/items/celestial/CargoContainer.yaml";
        private Map<String, CargoContainer> cache = (null);

        @Override
        public IMetaCategory<? super CargoContainer> category() {
            return Celestial.METACAT;
        }

        @Override
        public int getGroupId() {
            return  12;
        }

        @Override
        public String getName() {
            return "CargoContainer";
        }

        @Override
        public synchronized Map<String, CargoContainer> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CargoContainer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CargoContainer> items;
        }
    }
}
