package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.EncounterSurveillanceSystem;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileCynoInhibitor;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileDepot;
import fr.guiguilechat.jcelechat.model.sde.types.deployable.MobileMicroJumpUnit;
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
    @DefaultIntValue(0)
    public int ArmorHP;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanLadarStrength;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldRechargeRate;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public static final Deployable.MetaCat METACAT = new Deployable.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  265 :
            {
                return ArmorHP;
            }
            case  9 :
            {
                return Hp;
            }
            case  209 :
            {
                return ScanLadarStrength;
            }
            case  263 :
            {
                return ShieldCapacity;
            }
            case  479 :
            {
                return ShieldRechargeRate;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
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
            return Arrays.asList(MobileWarpDisruptor.METAGROUP, MobileDepot.METAGROUP, MobileSiphonUnit.METAGROUP, MobileCynoInhibitor.METAGROUP, MobileTractorUnit.METAGROUP, EncounterSurveillanceSystem.METAGROUP, MobileScanInhibitor.METAGROUP, MobileMicroJumpUnit.METAGROUP);
        }
    }
}
