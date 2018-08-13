package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.apparel.Augmentations;
import fr.guiguilechat.jcelechat.model.sde.items.types.apparel.Bottoms;
import fr.guiguilechat.jcelechat.model.sde.items.types.apparel.Eyewear;
import fr.guiguilechat.jcelechat.model.sde.items.types.apparel.Footwear;
import fr.guiguilechat.jcelechat.model.sde.items.types.apparel.Headwear;
import fr.guiguilechat.jcelechat.model.sde.items.types.apparel.Outer;
import fr.guiguilechat.jcelechat.model.sde.items.types.apparel.Prosthetics;
import fr.guiguilechat.jcelechat.model.sde.items.types.apparel.Tattoos;
import fr.guiguilechat.jcelechat.model.sde.items.types.apparel.Tops;

public abstract class Apparel
    extends Item
{
    /**
     * Used to describe what sex a given item is meant for.
     * 
     *  1 = Male,
     *  2 = Unisex,
     *  3 = Female
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2)
    public int Gender;
    public final static Apparel.MetaCat METACAT = new Apparel.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1773 :
            {
                return Gender;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getCategoryId() {
        return  30;
    }

    @Override
    public MetaCategory<Apparel> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Apparel> loadCategory() {
        return Stream.of(Augmentations.load(), Bottoms.load(), Eyewear.load(), Footwear.load(), Headwear.load(), Outer.load(), Prosthetics.load(), Tattoos.load(), Tops.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<Apparel>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Apparel> [] groups = new MetaGroup[] {Eyewear.METAGROUP, Tattoos.METAGROUP, Outer.METAGROUP, Tops.METAGROUP, Bottoms.METAGROUP, Footwear.METAGROUP, Headwear.METAGROUP, Prosthetics.METAGROUP, Augmentations.METAGROUP };

        @Override
        public String getName() {
            return "Apparel";
        }

        public Collection<MetaGroup<? extends Apparel>> groups() {
            return Arrays.asList(groups);
        }
    }
}
