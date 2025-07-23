package fr.guiguilechat.jcelechat.jcesi.holders.transform;

import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.jcesi.holders.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public class TransformBoolHolder<U> extends TransformHolder<Boolean, U> implements BoolHolder {

	public TransformBoolHolder(Holder<U> source, Predicate<U> predicate) {
		super(source, predicate::test);
	}

	private final Object fieldsLock = new Object();

	private BoolHolder not;

	@Override
	public BoolHolder not() {
		if (not == null) {
			// we can't lock on this because creating the not
			synchronized (fieldsLock) {
				if (not == null) {
					not = makeNot();
				}
			}
		}
		return not;
	}

	BoolHolder makeNot() {
		TransformBoolHolder<Boolean> created = new TransformBoolHolder<>(this, b -> !b);
		created.not = this;
		return created;
	}

	public static BoolHolder not(BoolHolder source) {
		TransformBoolHolder<Boolean> ret = new TransformBoolHolder<>(source, b -> !b);
		ret.not = source;
		return ret;
	}

}
