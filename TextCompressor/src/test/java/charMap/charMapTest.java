package charMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class charMapTest {
    
    CharMap charMap;
    
    @Test
    public void insertedCharIsFound() {
        charMap = new CharMap();
        
        charMap.addChar('a');
        assertTrue(charMap.hasChar('a'));
    }
    
    @Test
    public void nonExistentCharIsNotFound() {
        charMap = new CharMap();
        
        charMap.addChar('a');
        
        assertFalse(charMap.hasChar('b'));
    }
    
    @Test
    public void charAmountCanBeRaised() {
        charMap = new CharMap();
        
        charMap.addChar('a');
        
        int amount = charMap.getAmount('a');
        
        assertEquals(1, amount);
        
        charMap.addOne('a');
        charMap.addOne('a');
        
        amount = charMap.getAmount('a');
        
        assertEquals(3, amount);
    }
 
}
