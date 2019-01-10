package in.amant.opencrowdcommunication.model;

public class FuncContext {

    private String funcContext;
    /*private enum Type {
        ALL, SUBJECT, VOTE, MENU, QUESTION
    }*/
    private String type;
    private int amout;

    public FuncContext(String funcContext, String type, int amout) {
        this.funcContext = funcContext;
        this.type = type;
        this.amout = amout;
    }

    public String getFuncContext() {
        return funcContext;
    }

    public void setFuncContext(String funcContext) {
        this.funcContext = funcContext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmout() {
        return amout;
    }

    public void setAmout(int amout) {
        this.amout = amout;
    }

}
