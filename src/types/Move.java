package types;

public abstract class Move implements Comparable<Move> {
    
    protected String name;
    protected Type type;
    
    public Move() {
        this.name = null;
        this.type = null;
    }
    
    public Move(String n, Type t) {
        this.name = n;
        this.type = t;
    }
    
    public String getName() {
        return name;
    }
    
    public Type getType() {
        return type;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int compareTo(Move m) {
        return name.compareTo(m.getName());
    }

    public int hashCode() {
        return name.hashCode();
    }
}
