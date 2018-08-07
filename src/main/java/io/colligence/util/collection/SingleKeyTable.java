package io.colligence.util.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SingleKeyTable<K, V extends SingleKeyObject<K>> implements Serializable {
	private static final long serialVersionUID = 8394452348597373907L;

	private final Hashtable<K, V> ht = new Hashtable<>();

	public Hashtable<K, V> __getRawData() {
		return ht;
	}


	// ===============================================================
	// key
	// ===============================================================
	public synchronized Enumeration<K> selectKeys() {
		return ht.keys();
	}

	public synchronized Set<K> selectKeySet() {
		return ht.keySet();
	}


	// ===============================================================
	// check
	// ===============================================================
	public synchronized boolean has(K key) {
		return ht.containsKey(key);
	}

	public synchronized boolean has(V sko) {
		return ht.containsKey(sko.__getSKey__());
	}


	// ===============================================================
	// insert
	// ===============================================================
	public synchronized V insert(V sko) {
		return ht.put(sko.__getSKey__(), sko);
	}


	// ===============================================================
	// select
	// ===============================================================
	public synchronized Collection<V> select() {
		return ht.values();
	}

	public synchronized V select(K key) {
		return ht.get(key);
	}


	// ===============================================================
	// delete
	// ===============================================================
	public synchronized V delete(V sko) {
		return delete(sko.__getSKey__());
	}

	public synchronized V delete(K key) {
		return ht.remove(key);
	}

	public synchronized void deleteAll() {
		ht.clear();
	}

	public synchronized void deleteAll(Consumer<? super V> action) {
		forEach(action);
		deleteAll();
	}

	public synchronized void deleteIf(Predicate<? super V> filter) {
		ht.entrySet().removeIf(_es -> filter.test(_es.getValue()));
	}

	// ===============================================================
	// count
	// ===============================================================
	public synchronized int countAll() {
		return ht.size();
	}


	// ===============================================================
	// stream / collection
	// ===============================================================
	public synchronized Stream<V> stream_V() {
		return ht.values().stream();
	}

	public synchronized void forEach(Consumer<? super V> action) {
		ht.values().forEach(action);
	}
}
