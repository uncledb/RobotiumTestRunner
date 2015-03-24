package main;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapUtil {
	public static void work(Map<String, String> map) {
		Collection<String> c = map.values();
		Iterator<String> it = c.iterator();
		for (; it.hasNext();) {
			System.out.println(it.next());
		}
	}

	public static void workByKeySet(Map<String, String> map) {
		Set<String> key = map.keySet();
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			System.out.println(map.get(s));
		}
	}

	public static void workByEntry(Map<String, String> map) {
		Set<Map.Entry<String, String>> set = map.entrySet();
		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it
				.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			System.out.println(entry.getKey() + "--->" + entry.getValue());
		}
	}
}
