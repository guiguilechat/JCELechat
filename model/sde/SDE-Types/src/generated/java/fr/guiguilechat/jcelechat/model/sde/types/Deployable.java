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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.EncounterSurveillanceSystem;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileAnalysisBeacon;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileCynoInhibitor;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileCynosuralBeacon;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileDepot;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileMicroJumpUnit;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileObservatory;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileScanInhibitor;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileSiphonUnit;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileTractorUnit;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileWarpDisruptor;

public abstract class Deployable
    extends EveType
{
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armorhp;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanladarstrength;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcapacity;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     * The amount of starting shield capacity of the NPC.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcharge;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldrechargerate;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(100.0)
    public double signatureradius;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Capacity.INSTANCE, TechLevel.INSTANCE, ShieldCapacity.INSTANCE, SignatureRadius.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ScanLadarStrength.INSTANCE, ShieldRechargeRate.INSTANCE })));
    public static final Deployable.MetaCat METACAT = new Deployable.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  265 :
            {
                return armorhp;
            }
            case  38 :
            {
                return capacity;
            }
            case  9 :
            {
                return hp;
            }
            case  162 :
            {
                return radius;
            }
            case  209 :
            {
                return scanladarstrength;
            }
            case  263 :
            {
                return shieldcapacity;
            }
            case  264 :
            {
                return shieldcharge;
            }
            case  479 :
            {
                return shieldrechargerate;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaCategory<Deployable> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Deployable>
    {

        @Override
        public int getCategoryId() {
            return  22;
        }

        @Override
        public String getName() {
            return "Deployable";
        }

        @Override
        public Collection<IMetaGroup<? extends Deployable>> groups() {
            return Arrays.asList(MobileObservatory.METAGROUP, MobileAnalysisBeacon.METAGROUP, MobileWarpDisruptor.METAGROUP, MobileDepot.METAGROUP, MobileSiphonUnit.METAGROUP, MobileCynoInhibitor.METAGROUP, MobileTractorUnit.METAGROUP, EncounterSurveillanceSystem.METAGROUP, MobileScanInhibitor.METAGROUP, MobileMicroJumpUnit.METAGROUP, MobileCynosuralBeacon.METAGROUP);
        }
    }
}
