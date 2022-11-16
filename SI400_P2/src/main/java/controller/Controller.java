package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import model.DAO;
import java.util.ArrayList;
import java.util.List;
import model.Fragmento;
import model.FragmentoDAO;

/**
 *
 * @author Gritti
 */
public class Controller {

    private static char[] groups = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private static String finalText = "";
    private static String finalTextName = "";

    public static boolean authentication(String user, String password) {
        Connection con = DAO.getConnAuthenticate(user, password);

        if (con == null) {
            return false;
        }

        return true;
    }

    public static boolean getStatusConnection() {
        return DAO.getStatusConnection();
    }

    public static void closeConnection() {
        DAO.close();
    }

    public static String getSelectedText() {
        return finalText;
    }

    public static String getSelectedTextName() {
        return finalTextName;
    }

    public static String getText(List<Fragmento> fragmentos) {
        String text = "";
        String textName = "";
        for (Fragmento item : fragmentos) {
            textName = item.getFile();
            text += "\n " + item.getText();
        }

        finalText = text;
        finalTextName = textName;

        return text;
    }

    public static List search(String character) {
        List<Fragmento> fragmentos = new ArrayList();
        int groupdId = getGroupId(character);

        fragmentos = searchByGroupId(groupdId);

        return fragmentos;
    }

    public static void fileText(String text, String name) {

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("src/main/java/texts/" + name + ".txt"));
            br.write(text);
            br.close();
        } catch (Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }

    }

    protected static List searchByGroupId(int groupId) {
        return FragmentoDAO.getInstance().getByGroupId(groupId);
    }

    public static boolean isValidGroup(String character) {
        boolean test = false;
        for (char item : groups) {
            if (character.equals(String.valueOf(item))) {
                test = true;
                break;
            }
        }
        return test;
    }

    protected static int getGroupId(String character) {
        int id = 0;

        for (char item : groups) {
            id++;
            if (character.equals(String.valueOf(item))) {
                break;
            }
        }

        return id;
    }

}
