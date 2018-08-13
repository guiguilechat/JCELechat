package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.sovereigntystructures.InfrastructureHub;
import fr.guiguilechat.jcelechat.model.sde.items.types.sovereigntystructures.TerritorialClaimUnit;

public abstract class SovereigntyStructures
    extends Item
{
    /**
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanGravimetricStrength;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanLadarStrength;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanMagnetometricStrength;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanRadarStrength;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    public final static SovereigntyStructures.MetaCat METACAT = new SovereigntyStructures.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  211 :
            {
                return ScanGravimetricStrength;
            }
            case  209 :
            {
                return ScanLadarStrength;
            }
            case  210 :
            {
                return ScanMagnetometricStrength;
            }
            case  208 :
            {
                return ScanRadarStrength;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getCategoryId() {
        return  40;
    }

    @Override
    public MetaCategory<SovereigntyStructures> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends SovereigntyStructures> loadCategory() {
        return Stream.of(InfrastructureHub.load(), TerritorialClaimUnit.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static class MetaCat
        implements MetaCategory<SovereigntyStructures>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends SovereigntyStructures> [] groups = new MetaGroup[] {TerritorialClaimUnit.METAGROUP, InfrastructureHub.METAGROUP };

        @Override
        public String getName() {
            return "SovereigntyStructures";
        }

        public Collection<MetaGroup<? extends SovereigntyStructures>> groups() {
            return Arrays.asList(groups);
        }
    }
}
