package charMap;

/**
 *
 * @author holmbrob
 */
public class CharMapEntry {

    private Character ch;
    private Integer amount;
    private String string;

    /**
     * Empty constructor
     */
    public CharMapEntry() {
    }

    /**
     * Constructor for when you want to add the character right away.
     * @param ch, the character to be added
     */
    public CharMapEntry(Character ch) {
        this.ch = ch;
        this.amount = 1;
    }

    /**
     * Constructor for when you add a character and its binary string representation.
     * @param ch, the character to be added
     * @param s, the corresponding binary string
     */
    public CharMapEntry(Character ch, String s) {
        this.ch = ch;
        this.string = s;
    }

    /**
     * Method for getting the character
     * @return the character.
     */
    public Character getCh() {
        return ch;
    }

    /**
     * Method for setting the character
     * @param ch, the character to be set.
     */
    public void setCh(Character ch) {
        this.ch = ch;
    }

    /**
     * Method for getting the amount of times a character has shown up
     * @return, the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Method for setting the amount of times a character has shown up
     * @param amount, the amount to be set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Method for getting the binary string representation.
     * @return the string
     */
    public String getString() {
        return string;
    }

    /**
     * Method for setting the binary string representation
     * @param string, the string to be set.
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Method for adding one to the amount of a character
     */
    public void addOne() {
        this.amount++;
    }
}
