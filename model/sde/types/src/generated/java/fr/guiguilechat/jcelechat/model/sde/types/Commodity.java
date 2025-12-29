package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.*;

public abstract class Commodity
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Commodity.MetaCat METACAT = new Commodity.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Commodity> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Commodity>
    {

        @Override
        public int getCategoryId() {
            return  17;
        }

        @Override
        public String getName() {
            return "Commodity";
        }

        @Override
        public Collection<IMetaGroup<? extends Commodity>> groups() {
            return Arrays.asList(ESSReserveBankKeys.METAGROUP, RogueDroneAnalysisData.METAGROUP, WarpMatrixFilaments.METAGROUP, PeculiarMaterials.METAGROUP, General.METAGROUP, Frozen.METAGROUP, Radioactive.METAGROUP, Livestock.METAGROUP, Biohazard.METAGROUP, Drugs.METAGROUP, Miscellaneous.METAGROUP, Tool.METAGROUP, Datacores.METAGROUP, ConstructionComponents.METAGROUP, Refinables.METAGROUP, ShipLogs.METAGROUP, CriminalTags.METAGROUP, EmpireInsigniaDrops.METAGROUP, AccelerationGateKeys.METAGROUP, HomefrontOperationsCommodity.METAGROUP, OverseerPersonalEffects.METAGROUP, Identification.METAGROUP, Commodities.METAGROUP, ArtifactsAndPrototypes.METAGROUP, MaterialsAndCompounds.METAGROUP, StructureComponents.METAGROUP, AbyssalBattlefieldFilamentMaterials.METAGROUP, Lease.METAGROUP, ExpiredAbyssalBattlefieldsFilaments.METAGROUP, DataInterfaces.METAGROUP, Atavum.METAGROUP, InfomorphSystems.METAGROUP, EDENCOMData.METAGROUP, DecryptorsSleepers.METAGROUP, DecryptorsYanJung.METAGROUP, DecryptorsTakmahl.METAGROUP, DecryptorsTalocan.METAGROUP, LimitedRarities.METAGROUP, StabilityTelemetry.METAGROUP, CapitalConstructionComponents.METAGROUP, SlaveReception.METAGROUP, SleeperComponents.METAGROUP, AdvancedCapitalConstructionComponents.METAGROUP, HybridTechComponents.METAGROUP, ResearchData.METAGROUP, SecurityTags.METAGROUP, BountyEncryptedBonds.METAGROUP, UnknownComponents.METAGROUP, StrongBoxes.METAGROUP, TechnicalDataChips.METAGROUP, Mutaplasmids.METAGROUP, AbyssalFilaments.METAGROUP, TriglavianData.METAGROUP, TriglavianDatastreams.METAGROUP, JumpFilaments.METAGROUP, ExpiredJumpFilaments.METAGROUP, TriglavianSpaceFilaments.METAGROUP);
        }
    }
}
