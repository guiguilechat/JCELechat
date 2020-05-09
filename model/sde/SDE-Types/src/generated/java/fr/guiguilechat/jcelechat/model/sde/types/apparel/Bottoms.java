package fr.guiguilechat.jcelechat.model.sde.types.apparel;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ClothingAlsoCoversCategory;
import fr.guiguilechat.jcelechat.model.sde.attributes.ClothingRemovesCategory;
import fr.guiguilechat.jcelechat.model.sde.attributes.Gender;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.Apparel;
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
    public int clothingalsocoverscategory;
    /**
     * This item of clothing does not allow the category specified to be equipped with this item. It will be removed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int clothingremovescategory;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, ClothingRemovesCategory.INSTANCE, Mass.INSTANCE, ClothingAlsoCoversCategory.INSTANCE, Capacity.INSTANCE, Gender.INSTANCE })));
    public static final Bottoms.MetaGroup METAGROUP = new Bottoms.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1797 :
            {
                return clothingalsocoverscategory;
            }
            case  1956 :
            {
                return clothingremovescategory;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Bottoms> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Bottoms>
    {
        public static final String RESOURCE_PATH = "SDE/types/apparel/Bottoms.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(Bottoms.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Bottoms> types;
        }
    }
}
