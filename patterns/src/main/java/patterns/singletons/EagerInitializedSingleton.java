package patterns.singletons;

/**
 * Created by rexer.
 */

/**
 * If low resource usage.
 * And without Exceptions.
 */
public class EagerInitializedSingleton {
	private static final EagerInitializedSingleton INSTANCE = new EagerInitializedSingleton();

	private EagerInitializedSingleton() {
	}

	public static EagerInitializedSingleton getInstance() {
		return INSTANCE;
	}
}
