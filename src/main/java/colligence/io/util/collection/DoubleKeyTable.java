package colligence.io.util.collection;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DoubleKeyTable<MK, SK, V extends DoubleKeyObject<MK, SK>> implements Serializable {
	private static final long serialVersionUID = 4369417501141756631L;

	private final Hashtable<MK, SingleKeyTable<SK, V>> ht = new Hashtable<>();

	public Hashtable<MK, SingleKeyTable<SK, V>> __getRawData() {
		return ht;
	}

	// ===============================================================
	// key
	// ===============================================================
	public synchronized Enumeration<MK> selectKeys() {
		return ht.keys();
	}

	public synchronized Set<MK> selectKeySet() {
		return ht.keySet();
	}


	// ===============================================================
	// check
	// ===============================================================
	public synchronized boolean has(MK mainKey, SK subKey) {
		return ht.containsKey(mainKey) && ht.get(mainKey).has(subKey);
	}

	public synchronized boolean has(V dko) {
		return has(dko.__getMKey__(), dko.__getSKey__());
	}


	// ===============================================================
	// insert/update
	// ===============================================================
	public synchronized V insert(V dko) {
		return Optional.ofNullable(ht.get(dko.__getMKey__())).orElseGet(() -> {
			SingleKeyTable<SK, V> skt = new SingleKeyTable<>();
			ht.put(dko.__getMKey__(), skt);
			return skt;
		}).insert(dko);
	}


	// ===============================================================
	// select
	// ===============================================================
	public synchronized Collection<SingleKeyTable<SK, V>> select() {
		return ht.values();
	}

	public synchronized SingleKeyTable<SK, V> select(MK mainKey) {
		return ht.get(mainKey);
	}

	public synchronized V select(MK mainKey, SK subKey) {
		return ht.containsKey(mainKey) ? ht.get(mainKey).select(subKey) : null;
	}

	// ===============================================================
	// delete
	// ===============================================================
	public synchronized V delete(V dko) {
		return delete(dko.__getMKey__(), dko.__getSKey__());
	}

	public synchronized V delete(MK mainKey, SK subKey) {
		return ht.containsKey(mainKey) ? ht.get(mainKey).delete(subKey) : null;
	}

	public synchronized void deleteAll() {
		ht.values().forEach(SingleKeyTable::deleteAll);
		ht.clear();
	}

	public synchronized void deleteAll(Consumer<? super V> action) {
		forEach(action);
		deleteAll();
	}

	public synchronized void deleteIf(Predicate<? super V> filter) {
		ht.values().forEach(_skt -> _skt.deleteIf(filter));
		ht.entrySet().removeIf(_es -> _es.getValue().countAll() == 0);
	}

	// ===============================================================
	// count
	// ===============================================================
	public synchronized int countKey() {
		return ht.size();
	}

	public synchronized int countAll() {
		return ht.values().stream().mapToInt(SingleKeyTable::countAll).sum();
	}


	// ===============================================================
	// stream
	// ===============================================================
	public synchronized Stream<V> stream_V() {
		return ht.values().stream().flatMap(SingleKeyTable::stream_V);
	}

	public synchronized void forEach(Consumer<? super V> action) {
		ht.values().forEach(_skt -> _skt.forEach(action));
	}
}
