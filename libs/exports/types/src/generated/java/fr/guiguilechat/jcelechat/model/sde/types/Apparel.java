package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.*;

public abstract class Apparel
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Apparel.MetaCat METACAT = new Apparel.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
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
            return Arrays.asList(Eyewear.METAGROUP, Tattoos.METAGROUP, Outer.METAGROUP, Tops.METAGROUP, Bottoms.METAGROUP, Footwear.METAGROUP, Headwear.METAGROUP, Prosthetics.METAGROUP, Augmentations.METAGROUP, Masks.METAGROUP);
        }
    }
}
