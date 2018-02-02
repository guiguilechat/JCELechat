package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.apparel.Augmentations;
import fr.guiguilechat.eveonline.model.sde.items.types.apparel.Bottoms;
import fr.guiguilechat.eveonline.model.sde.items.types.apparel.Eyewear;
import fr.guiguilechat.eveonline.model.sde.items.types.apparel.Footwear;
import fr.guiguilechat.eveonline.model.sde.items.types.apparel.Headwear;
import fr.guiguilechat.eveonline.model.sde.items.types.apparel.Outer;
import fr.guiguilechat.eveonline.model.sde.items.types.apparel.Prosthetics;
import fr.guiguilechat.eveonline.model.sde.items.types.apparel.Tattoos;
import fr.guiguilechat.eveonline.model.sde.items.types.apparel.Tops;

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

    @Override
    public int getCategoryId() {
        return  30;
    }

    @Override
    public Class<?> getCategory() {
        return Apparel.class;
    }

    public static Map<String, ? extends Apparel> loadCategory() {
        return Stream.of(Headwear.load(), Augmentations.load(), Tattoos.load(), Prosthetics.load(), Tops.load(), Footwear.load(), Eyewear.load(), Outer.load(), Bottoms.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
