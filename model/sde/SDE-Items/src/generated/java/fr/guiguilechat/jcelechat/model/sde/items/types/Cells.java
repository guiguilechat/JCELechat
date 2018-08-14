package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Cells
    extends Item
{
    public final static Cells.MetaCat METACAT = new Cells.MetaCat();

    @Override
    public IMetaCategory<Cells> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Cells>
    {

        @Override
        public int getCategoryId() {
            return  59;
        }

        @Override
        public String getName() {
            return "Cells";
        }

        @Override
        public Collection<IMetaGroup<? extends Cells>> groups() {
            return Arrays.asList();
        }
    }
}
