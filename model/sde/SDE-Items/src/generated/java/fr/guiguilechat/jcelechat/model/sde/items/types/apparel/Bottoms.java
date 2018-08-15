package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Bottoms
    extends Apparel
{
    /**
     * This item of clothing covers multiple areas of the body, so the category of clothes specified by this attribute is no longer mandatory
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ClothingAlsoCoversCategory;
    public final static Bottoms.MetaGroup METAGROUP = new Bottoms.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1797 :
            {
                return ClothingAlsoCoversCategory;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<Bottoms> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Bottoms>
    {
        public final static String RESOURCE_PATH = "SDE/items/apparel/Bottoms.yaml";
        private Map<String, Bottoms> cache = (null);

        @Override
        public IMetaCategory<? super Bottoms> category() {
            return Apparel.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1090;
        }

        @Override
        public String getName() {
            return "Bottoms";
        }

        @Override
        public synchronized Map<String, Bottoms> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Bottoms.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Bottoms> items;
        }
    }
}