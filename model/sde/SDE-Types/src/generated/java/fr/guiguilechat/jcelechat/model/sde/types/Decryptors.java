package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionMEModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionMaxRunModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionPropabilityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.InventionTEModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.DecryptorsAmarr;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.DecryptorsCaldari;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.DecryptorsGallente;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.DecryptorsHybrid;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.DecryptorsMinmatar;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.GenericDecryptor;

public abstract class Decryptors
    extends EveType
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacity;
    /**
     * Modifies the mineral efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double inventionmemodifier;
    /**
     * Modifies the max runs in a blueprint created through invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double inventionmaxrunmodifier;
    /**
     * Modifies base chance of successful invention
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double inventionpropabilitymultiplier;
    /**
     * Modifies the time efficiency of invented BPCs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double inventiontemodifier;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, InventionMaxRunModifier.INSTANCE, Mass.INSTANCE, Capacity.INSTANCE, InventionPropabilityMultiplier.INSTANCE, InventionMEModifier.INSTANCE, InventionTEModifier.INSTANCE })));
    public static final Decryptors.MetaCat METACAT = new Decryptors.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return capacity;
            }
            case  1113 :
            {
                return inventionmemodifier;
            }
            case  1124 :
            {
                return inventionmaxrunmodifier;
            }
            case  1112 :
            {
                return inventionpropabilitymultiplier;
            }
            case  1114 :
            {
                return inventiontemodifier;
            }
            case  4 :
            {
                return mass;
            }
            case  162 :
            {
                return radius;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
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
