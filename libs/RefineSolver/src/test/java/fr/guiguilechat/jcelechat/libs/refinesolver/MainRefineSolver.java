package fr.guiguilechat.jcelechat.libs.refinesolver;

public class MainRefineSolver {

	public static void main(String[] args) {
		// we just want 20M trit.

		new RefineSolver().solve(new Params().withQuantity(34, 20000000)
				// 500 isk/m³ to move
				.withVolumicCost(500));

	}

}
