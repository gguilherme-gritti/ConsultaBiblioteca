package controller;

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
    private static List<Fragmento> selectedText = new ArrayList();

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
    
    public static List<Fragmento> getSelectedText(){
        return selectedText;
    }
    
    public static String getText(List<Fragmento> fragmentos){
        String text = "";
        for (Fragmento item : fragmentos) {
            text += "\n " + item.getText();
        }
        
        return text;
    }

    public static List search(String character) {
        List<Fragmento> fragmentos = new ArrayList();
        int groupdId = getGroupId(character);

        fragmentos = searchByGroupId(groupdId);
        selectedText = fragmentos;

        return fragmentos;
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
