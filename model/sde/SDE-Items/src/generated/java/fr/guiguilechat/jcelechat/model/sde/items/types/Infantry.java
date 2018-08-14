package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Infantry
    extends Item
{
    public final static Infantry.MetaCat METACAT = new Infantry.MetaCat();

    @Override
    public IMetaCategory<Infantry> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Infantry>
    {

        @Override
        public int getCategoryId() {
            return  350001;
        }

        @Override
        public String getName() {
            return "Infantry";
        }

        @Override
        public Collection<IMetaGroup<? extends Infantry>> groups() {
            return Arrays.asList();
        }
    }
}
