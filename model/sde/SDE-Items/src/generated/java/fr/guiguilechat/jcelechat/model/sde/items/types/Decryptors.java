package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
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
    public int getCategoryId() {
        return  35;
    }

    @Override
    public Class<?> getCategory() {
        return Decryptors.class;
    }

    public static Map<String, ? extends Decryptors> loadCategory() {
        return Stream.of(DecryptorsAmarr.load(), DecryptorsCaldari.load(), DecryptorsGallente.load(), DecryptorsHybrid.load(), DecryptorsMinmatar.load(), GenericDecryptor.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
