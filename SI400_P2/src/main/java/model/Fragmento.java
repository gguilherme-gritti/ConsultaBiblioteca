package model;

/* 
 * Classe Fragmento, Tabela MODEL do BD
 *
 * @author Grupo C
 * @version 1.0
 * @since 1.0
 */
public class Fragmento extends DAO {

    private int groupId;
    private String file;
    private int line;
    private String text;

    public Fragmento(int groupId, String file, int line, String text) {
        this.groupId = groupId;
        this.file = file;
        this.line = line;
        this.text = text;
    }

    /**
     * Retorna o Id do grupo
     *
     * @return id do grupo
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * "Seta" o id do grupo
     *
     * @param groupId id do grupo
     */
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    /**
     * Retorna o nome do arquivo
     *
     * @return nome do arquivo
     */
    public String getFile() {
        return file;
    }

    /**
     * "Seta" o nome do arquivo
     *
     * @param file arquivo
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Retorna a linha do texto
     *
     * @return linha do texto
     */
    public int getLine() {
        return line;
    }

    /**
     * "Seta" o numero da linha
     *
     * @param line linha
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * Retorna o texto
     *
     * @return texto
     */
    public String getText() {
        return text;
    }

    /**
     * "Seta" o valor do texto
     *
     * @param text texto
     */
    public void setText(String text) {
        this.text = text;
    }

}
