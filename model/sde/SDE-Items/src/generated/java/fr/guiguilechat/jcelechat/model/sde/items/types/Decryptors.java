package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.decryptors.DecryptorsAmarr;
import fr.guiguilechat.jcelechat.model.sde.items.types.decryptors.DecryptorsCaldari;
import fr.guiguilechat.jcelechat.model.sde.items.types.decryptors.DecryptorsGallente;
import fr.guiguilechat.jcelechat.model.sde.items.types.decryptors.DecryptorsHybrid;
import fr.guiguilechat.jcelechat.model.sde.items.types.decryptors.DecryptorsMinmatar;
import fr.guiguilechat.jcelechat.model.sde.items.types.decryptors.GenericDecryptor;

public abstract class Decryptors
    extends Item
{
    /**
     * Modifies the mineral efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionMEModifier;
    /**
     * Modifies the max runs in a blueprint created through invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionMaxRunModifier;
    /**
     * Modifies base chance of successful invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionPropabilityMultiplier;
    /**
     * Modifies the time efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double InventionTEModifier;
    public final static Decryptors.MetaCat METACAT = new Decryptors.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1113 :
            {
                return InventionMEModifier;
            }
            case  1124 :
            {
                return InventionMaxRunModifier;
            }
            case  1112 :
            {
                return InventionPropabilityMultiplier;
            }
            case  1114 :
            {
                return InventionTEModifier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaCategory<Decryptors> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Decryptors>
    {

        @Override
        public int getCategoryId() {
            return  35;
        }

        @Override
        public String getName() {
            return "Decryptors";
        }

        @Override
        public Collection<IMetaGroup<? extends Decryptors>> groups() {
            return Arrays.asList(DecryptorsAmarr.METAGROUP, DecryptorsMinmatar.METAGROUP, DecryptorsGallente.METAGROUP, DecryptorsCaldari.METAGROUP, DecryptorsHybrid.METAGROUP, GenericDecryptor.METAGROUP);
        }
    }
}
