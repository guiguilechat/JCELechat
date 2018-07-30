package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.AbyssalMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.AncientSalvage;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.BiochemicalMaterial;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.Composite;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.FuelBlock;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.GasIsotopes;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.HybridPolymers;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.IceProduct;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.IntermediateMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.Mineral;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.Money;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.MoonMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.NamedComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.RogueDroneComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.SalvagedMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.WormholeMinerals;

public abstract class Material
    extends Item
{

    @Override
    public int getCategoryId() {
        return  4;
    }

    @Override
    public Class<?> getCategory() {
        return Material.class;
    }

    public static Map<String, ? extends Material> loadCategory() {
        return Stream.of(AbyssalMaterials.load(), AncientSalvage.load(), BiochemicalMaterial.load(), Composite.load(), FuelBlock.load(), GasIsotopes.load(), HybridPolymers.load(), IceProduct.load(), IntermediateMaterials.load(), Mineral.load(), Money.load(), MoonMaterials.load(), NamedComponents.load(), RogueDroneComponents.load(), SalvagedMaterials.load(), WormholeMinerals.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
