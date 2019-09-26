package charMap;

public class CharMapEntry {

    private Character ch;
    private Integer amount;
    private String string;

    public CharMapEntry(Character ch) {
        this.ch = ch;
        this.amount = 1;
    }

    public CharMapEntry(Character ch, String s) {
        this.ch = ch;
        this.string = s;
    }

    public Character getCh() {
        return ch;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void addOne() {
        this.amount++;
    }
}
