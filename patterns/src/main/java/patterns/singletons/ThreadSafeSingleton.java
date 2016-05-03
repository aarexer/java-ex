package patterns.singletons;

/**
 * Created by rexer.
 */

/**
 * Good, but extra costs due synchronized.
 */
public class ThreadSafeSingleton {
	private static ThreadSafeSingleton instance;

	private ThreadSafeSingleton() {
	}

	public synchronized static ThreadSafeSingleton getInstance() {
		if (instance == null)
			instance = new ThreadSafeSingleton();

		return instance;
	}
}
