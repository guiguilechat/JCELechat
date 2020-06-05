package fr.guiguilechat.jcelechat.libs.refinesolver;

import org.chocosolver.solver.Model;

public class TestChocoUSe {

	public static void main(String[] args) {
		Model model = new Model();
		var real = model.realVar(0.0, 1.0, 0.1);
		model.post(real.ge(real.add(1)).ibex(0.1));
		model.getSolver().solve();
	}

}
