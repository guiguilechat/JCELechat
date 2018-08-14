package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
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
    public IMetaCategory<Apparel> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Apparel>
    {

        @Override
        public int getCategoryId() {
            return  30;
        }

        @Override
        public String getName() {
            return "Apparel";
        }

        @Override
        public Collection<IMetaGroup<? extends Apparel>> groups() {
            return Arrays.asList(Eyewear.METAGROUP, Tattoos.METAGROUP, Outer.METAGROUP, Tops.METAGROUP, Bottoms.METAGROUP, Footwear.METAGROUP, Headwear.METAGROUP, Prosthetics.METAGROUP, Augmentations.METAGROUP);
        }
    }
}
