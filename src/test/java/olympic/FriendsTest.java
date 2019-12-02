package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FriendsTest extends AbstractTest{

    @Before
    public void createAthletes() {
        Athlete a = new Athlete();
        a.setId(1);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        Solution.addAthlete(a);
        Athlete b = new Athlete();
        b.setId(2);
        b.setName("Artur");
        b.setCountry("Brazil");
        b.setIsActive(true);
        Solution.addAthlete(b);
    }

    @Test
    public void testMakeFriends() {
        ReturnValue ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.OK, ret);
    }

    @Test
    public void testDuplication() {
        ReturnValue ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.ALREADY_EXISTS, ret);
        ret = Solution.makeFriends(2,1);
        assertEquals(ReturnValue.ALREADY_EXISTS, ret);
    }

    @Test
    public void testSameId() {
        ReturnValue ret = Solution.makeFriends(1,1);
        assertEquals(ReturnValue.BAD_PARAMS, ret);
    }

    @Test
    public void testNotExists() {
        ReturnValue ret = Solution.makeFriends(1,3);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
        ret = Solution.makeFriends(3,1);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }

    @Test
    public void testRemove() {
        ReturnValue ret = Solution.makeFriends(1,2);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.removeFriendship(2,1);
        assertEquals(ReturnValue.OK, ret);
        ret = Solution.removeFriendship(1,2);
        assertEquals(ReturnValue.NOT_EXISTS, ret);
    }
}