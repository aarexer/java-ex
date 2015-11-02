package patterns.singletons;

/**
 * Created by rexer.
 */

/**
 * Without thread-safe.
 */
public class LazyInitializedSingleton {
	private static LazyInitializedSingleton instance;

	private LazyInitializedSingleton() {
	}

	public static LazyInitializedSingleton getInstance() {
		if (instance == null)
			instance = new LazyInitializedSingleton();
		
		return instance;
	}
}
