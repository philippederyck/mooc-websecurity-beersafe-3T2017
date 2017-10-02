package eu.beersafe.mooc.logger;

public class Logger {

	private static int LEVEL_DEBUG = 0;
	private static int LEVEL_INFO = 1;
	private static int LEVEL_WARNING = 2;
	private static int LEVEL_ERROR = 3;
	private static int LEVEL_FATAL = 4;
	private static String[] levels = { "DEBUG", "INFO", "WARNING", "ERROR", "FATAL" };
	
	private static String prefix = "BeerSafe - ";
	private static int logLevel = 0;
	private static Logger instance = null;
	
	private static Logger getInstance() {
		if(instance == null) instance = new Logger();
		return instance;
	}
	
	private void logMessage(int level, String message) {
		if(level >= logLevel) {
			System.out.println(prefix + levels[level] + " - " + message);
		}
	}
	
	private void logMessage(int level, String message, Exception e) {
		if(level >= logLevel) {
			System.out.println(prefix + levels[level] + " - " + message + "(" + e.getMessage() + ")");
			e.printStackTrace();
		}
	}
	
	public static void debug(String message) {
		Logger.getInstance().logMessage(LEVEL_DEBUG, message);
	}
	
	public static void debug(String message, Exception e) {
		Logger.getInstance().logMessage(LEVEL_DEBUG, message, e);
	}
	
	public static void info(String message) {
		Logger.getInstance().logMessage(LEVEL_INFO, message);
	}
	
	public static void info(String message, Exception e) {
		Logger.getInstance().logMessage(LEVEL_INFO, message, e);
	}
	
	public static void warn(String message) {
		Logger.getInstance().logMessage(LEVEL_WARNING, message);
	}
	
	public static void warn(String message, Exception e) {
		Logger.getInstance().logMessage(LEVEL_WARNING, message, e);
	}
	
	public static void error(String message) {
		Logger.getInstance().logMessage(LEVEL_ERROR, message);
	}
	
	public static void error(String message, Exception e) {
		Logger.getInstance().logMessage(LEVEL_ERROR, message, e);
	}
	
	public static void fatal(String message) {
		Logger.getInstance().logMessage(LEVEL_FATAL, message);
	}
	
	public static void fatal(String message, Exception e) {
		Logger.getInstance().logMessage(LEVEL_FATAL, message, e);
	}
}
