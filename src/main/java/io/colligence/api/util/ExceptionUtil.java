package io.colligence.api.util;

import java.util.ArrayList;
import java.util.List;

public class ExceptionUtil {

	/**
	 * Get specific cause of throwable (including itself)
	 *
	 * @param throwable source throwable
	 * @param type      type of cause to search
	 * @return throwable if found, or else null
	 */
	public static Throwable getSpecificCause(Throwable throwable, final Class<?> type) {
		return getSpecificCause(throwable, type, false);
	}

	/**
	 * Get specific cause of throwable (including itself)
	 *
	 * @param throwable     source throwable
	 * @param type          type of cause to search
	 * @param checkSubClass match if throwable is sub class(or interface) of type
	 * @return throwable if found, or else null
	 */
	public static Throwable getSpecificCause(Throwable throwable, final Class<?> type, boolean checkSubClass) {
		final List<Throwable> list = new ArrayList<>();
		while(throwable != null && !list.contains(throwable)) {
			if(checkSubClass) {
				if(type.isAssignableFrom(throwable.getClass())) {
					return throwable;
				}
			} else {
				if(type.equals(throwable.getClass())) {
					return throwable;
				}
			}

			list.add(throwable);
			throwable = throwable.getCause();
		}
		return null;
	}
}
