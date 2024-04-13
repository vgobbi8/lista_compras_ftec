package br.com.aula.listadecompras.infra.data;

public class SqlTableColumn {

    private String name;
    private String type;
    private boolean isPrimaryKey;
    private boolean isAutoIncrement;

    public SqlTableColumn(String name, String type, boolean isPrimaryKey, boolean isAutoIncrement) {
        this.name = name;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
        this.isAutoIncrement = isAutoIncrement;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }
    

}
