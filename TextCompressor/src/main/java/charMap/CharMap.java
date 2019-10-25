package charMap;

/**
 *
 * @author holmbrob
 */
public class CharMap {

    private CharMapEntry[] arr;
    private int size;

    /**
     * Constructor for setting up the charmap
     */
    public CharMap() {
        this.arr = new CharMapEntry[200];
        this.size = 0;
    }

    /**
     * Method for checking if the map contains a character already.
     * @param ch, the character to be checked
     * @return, a boolean value, true if it already exists, false if it doesn't
     */
    public boolean hasChar(Character ch) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i].getCh().equals(ch)) {
                return true;
            }
        }
        return false;
    }

    private CharMapEntry getEntry(Character ch) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i].getCh().equals(ch)) {
                return this.arr[i];
            }
        }
        return null;
    }

    /**
     * Method for getting the amount of times a character shows up in the text
     * @param ch, the character to be searched for
     * @return, the amount
     */
    public int getAmount(Character ch) {
        CharMapEntry entry = getEntry(ch);

        if (entry != null) {
            return entry.getAmount();
        }

        return 0;
    }

    /**
     * Method for getting the binary string representation of a character
     * @param ch, the character to be searched for
     * @return its binary string representation
     */
    public String getString(Character ch) {
        CharMapEntry entry = getEntry(ch);

        if (entry != null) {
            return entry.getString();
        }

        return "";
    }

    /**
     * method for setting the binary string representation of a character
     * @param ch, the character
     * @param string, its intended binary string representation
     */
    public void setString(Character ch, String string) {
        CharMapEntry entry = getEntry(ch);

        if (entry != null) {
            entry.setString(string);
        }
    }

    /**
     * Method for adding one to the amount of times a character has shown up
     * @param ch, the character to which one should be added
     */
    public void addOne(Character ch) {
        CharMapEntry entry = getEntry(ch);

        if (entry != null) {
            entry.addOne();
        }
    }

    /**
     * Method for adding a new character to the map
     * @param ch, the character to be added
     */
    public void addChar(Character ch) {
        CharMapEntry entry = getEntry(ch);

        if (entry == null) {
            arr[this.size] = new CharMapEntry(ch);
            this.size++;
        } else {
            entry.addOne();
        }
    }

    /**
     * Method for adding a new character to the map, along with its binary string representation
     * @param ch, the character to be added
     * @param string, its corresponding binary string representation
     */
    public void addChar(Character ch, String string) {
        CharMapEntry entry = getEntry(ch);

        if (entry == null) {
            arr[this.size] = new CharMapEntry(ch, string);
            this.size++;
        }
    }

    /**
     * Method for getting the map array
     * @return an array of CharMapEntry objects
     */
    public CharMapEntry[] getArr() {
        return arr;
    }

    /**
     * Method for getting the size of the map
     * @return, the size of the map
     */
    public int getSize() {
        return size;
    }

    /**
     * Method for adding a CharMapEntry object
     * @param entry, the entry object to be added
     */
    public void addEntry(CharMapEntry entry) {
        arr[this.size] = entry;
        this.size++;
    }
}
