import com.jokes.NorasJavaJokes;
import org.junit.Test;

/**
 * Created by Nora on 1/1/2016.
 */
public class NorasJavaJokesTest {

    @Test
    public void testGetJoke_ShouldNotReturnEmptyString() {
        NorasJavaJokes joker = new NorasJavaJokes();
        assert joker.getJoke().length() != 0;
    }

}
