package patterns.singletons;

/**
 * Created by rexer.
 */

/**
 * Very good.
 * Lazy initialization. 
 */

public class BillPughSingleton {
	
	private BillPughSingleton() {
	}

	private static class SingletonHelper {
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
	}
	
	public static BillPughSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}
}
