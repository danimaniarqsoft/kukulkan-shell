package mx.infotec.dads.kukulkan.shell.util;

public class Console {

	public static void printf(String text) {
		System.out.println(AnsiConstants.ANSI_YELLOW + text + AnsiConstants.ANSI_RESET);
	}
	
	public static void printf(String ansicolor, String text) {
		System.out.println(ansicolor + text + AnsiConstants.ANSI_RESET);
	}
}
