package charMap;

public class CharMap {

    private CharMapEntry[] arr;
    private int size;

    public CharMap() {
        this.arr = new CharMapEntry[200];
        this.size = 0;
    }

    /*
    Method returns true if map contains the character. Used by the tests.
    */
    public boolean hasChar(Character ch) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i].getCh().equals(ch)) {
                return true;
            }
        }
        return false;
    }

    /*
    Method returns the CharMapEntry if it exists. Used by the other methods.
    */
    private CharMapEntry getEntry(Character ch) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i].getCh().equals(ch)) {
                return this.arr[i];
            }
        }
        return null;
    }

    /*
    Returns the amount of times the characters exists. Used by the tests.
    */
    public int getAmount(Character ch) {
        CharMapEntry entry = getEntry(ch);

        if (entry != null) {
            return entry.getAmount();
        }

        return 0;
    }

    /*
    Returns the string representation of a character.
    */
    public String getString(Character ch) {
        CharMapEntry entry = getEntry(ch);

        if (entry != null) {
            return entry.getString();
        }

        return "";
    }

    /*
    Sets the encoding string of a character
    */
    public void setString(Character ch, String string) {
        CharMapEntry entry = getEntry(ch);

        if (entry != null) {
            entry.setString(string);
        }
    }

    /*
    Adds one to the amount of times the character is used. Used by the tests.
    */
    public void addOne(Character ch) {
        CharMapEntry entry = getEntry(ch);

        if (entry != null) {
            entry.addOne();
        }
    }

    /*
    Adds the character to the map
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

    /*
    Adds the character to the map, with the encoding string
    */
    public void addChar(Character ch, String string) {
        CharMapEntry entry = getEntry(ch);

        if (entry == null) {
            arr[this.size] = new CharMapEntry(ch, string);
            this.size++;
        }
    }

    /*
    Returns the map
    */
    public CharMapEntry[] getArr() {
        return arr;
    }

    /*
    Returns the amount of characters
    */
    public int getSize() {
        return size;
    }
}
