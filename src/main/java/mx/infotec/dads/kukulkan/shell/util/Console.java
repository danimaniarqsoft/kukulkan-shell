package mx.infotec.dads.kukulkan.shell.util;

public class Console {

    public static void printf(String text) {
        System.out.println(AnsiConstants.ANSI_GREEN + text + AnsiConstants.ANSI_RESET);
    }

    public static void printf(String key, String message) {
        System.out.println(AnsiConstants.ANSI_GREEN + key + AnsiConstants.ANSI_RESET + " : " + message);
    }

}
