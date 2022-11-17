package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import model.DAO;
import java.util.ArrayList;
import java.util.List;
import model.Fragmento;
import model.FragmentoDAO;

/* 
 * Classe Controller, Controlador de metodos e intermediário entre View e Model
 *
 * @author Grupo C
 * @version 1.0
 * @since 1.0
 */
public class Controller {

    private static char[] groups = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private static String finalText = "";
    private static String finalTextName = "";

    /**
     * Faz a autenticação no banco
     *
     * @param user usuario
     * @param password senha
     * @return sucesso ou falha
     */
    public static boolean authentication(String user, String password) {
        Connection con = DAO.getConnAuthenticate(user, password);

        if (con == null) {
            return false;
        }

        return true;
    }

    /**
     * Retorna o status da conexão com o banco
     *
     * @return status
     */
    public static boolean getStatusConnection() {
        return DAO.getStatusConnection();
    }

    /**
     * Fecha a conexão com o banco
     *
     */
    public static void closeConnection() {
        DAO.close();
    }

    /**
     * Retorna o texto buscado
     *
     * @return texto montado
     */
    public static String getSelectedText() {
        return finalText;
    }

    /**
     * Retorna o nome do texto buscado
     *
     * @return nome do texto
     */
    public static String getSelectedTextName() {
        return finalTextName;
    }

    /**
     * Monta e retorna o texto final
     *
     * @param fragmentos lista de fragmentos retornados do banco
     * @return texto final montado
     */
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

    /**
     * Faz a busca de fragmentos pelo id do grupo
     *
     * @param character string inserida pelo usuário
     * @return lista de fragmentos
     */
    public static List search(String character) {
        List<Fragmento> fragmentos = new ArrayList();
        int groupdId = getGroupId(character);

        fragmentos = searchByGroupId(groupdId);

        return fragmentos;
    }

    /**
     * Gera um arquivo txt
     *
     * @param text conteudo do txt
     * @param name nome do txt
     * @catch Exception erro
     */
    public static void fileText(String text, String name) {

        try {
            BufferedWriter br = new BufferedWriter(new FileWriter("src/main/java/texts/" + name + ".txt"));
            br.write(text);
            br.close();
        } catch (Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }

    }

    /**
     * Busca a lista de fragmentos pelo ID do gurpo
     *
     * @param groupId id do grupo
     * @return consulta
     */
    protected static List searchByGroupId(int groupId) {
        return FragmentoDAO.getInstance().getByGroupId(groupId);
    }

    /**
     * Valida a entrada do usuário se pertence a um grupo existente
     *
     * @param character entrada do usuário
     * @return verdadeiro ou falso
     */
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

    /**
     * Retorna o ID do grupo a partir da entrada do usuário
     *
     * @param character entrada do usuário
     * @return id do grupo
     */
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
