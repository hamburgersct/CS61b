import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(3);

    @Test
    public void testOffByN(){
        assertTrue(offByN.equalChars('a', 'd'));
        assertFalse(offByN.equalChars('%', '('));
        assertTrue(offByN.equalChars('d', 'a'));
    }
}
