package net.hiskarma.stellarTest.util.collection;


public class ObjectPair<F, S> {
	private final F f;
	private final S s;

	public ObjectPair(F f, S s) {
		this.f = f;
		this.s = s;
	}

	public F first() {
		return f;
	}

	public S second() {
		return s;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		ObjectPair<?, ?> that = (ObjectPair<?, ?>) o;

		if(f != null ? !f.equals(that.f) : that.f != null) return false;
		return !(s != null ? !s.equals(that.s) : that.s != null);

	}

	@Override
	public int hashCode() {
		int result = f != null ? f.hashCode() : 0;
		result = 31 * result + (s != null ? s.hashCode() : 0);
		return result;
	}
}
