package patterns.singletons;

/**
 * Created by rexer.
 */

/**
 * Good.
 */
public class DoubleCheckedLockingSingleton {
	private static volatile DoubleCheckedLockingSingleton instance;

	public static DoubleCheckedLockingSingleton getInstance() {
		DoubleCheckedLockingSingleton localInstance = instance;
		if (localInstance == null) {
			synchronized (DoubleCheckedLockingSingleton.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new DoubleCheckedLockingSingleton();
				}
			}
		}
		return localInstance;
	}
}
