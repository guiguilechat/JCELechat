package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Placeables
    extends Item
{
    public static final Placeables.MetaCat METACAT = new Placeables.MetaCat();

    @Override
    public IMetaCategory<Placeables> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Placeables>
    {

        @Override
        public int getCategoryId() {
            return  49;
        }

        @Override
        public String getName() {
            return "Placeables";
        }

        @Override
        public Collection<IMetaGroup<? extends Placeables>> groups() {
            return Arrays.asList();
        }
    }
}
