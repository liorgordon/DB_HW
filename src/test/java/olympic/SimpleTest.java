package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import org.junit.Test;

import static olympic.business.ReturnValue.*;
import static org.junit.Assert.assertEquals;

public class SimpleTest extends AbstractTest{

    @Test
    public void simpleTestCreateAthlete()
    {
        Athlete a = new Athlete();
        a.setId(1);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        a.setCountry("jamaica");
        a.setId(-10);
        a.setIsActive(true);
        a.setName("lior");
        ret = Solution.addAthlete(a);
        assertEquals(BAD_PARAMS, ret);

        a.setCountry(null);
        a.setId(2);
        a.setIsActive(true);
        a.setName("lior");
        ret = Solution.addAthlete(a);
        assertEquals(BAD_PARAMS, ret);

        a.setCountry("jamaica");
        a.setId(1);
        a.setIsActive(true);
        a.setName("lior");
        ret = Solution.addAthlete(a);
        assertEquals(ALREADY_EXISTS, ret);

    }
    @Test
    public void testGetUserProfile(){
        Athlete a = new Athlete();
        a.setId(1);
        a.setName("Artur");
        a.setCountry("Brazil");
        a.setIsActive(true);
        ReturnValue ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        Athlete c =  Solution.getAthleteProfile(1);
        Athlete b= Athlete.badAthlete();
        b.setCountry("Brazil");
        b.setId(1);
        b.setIsActive(true);
        b.setName("Artur");
        assertEquals(b.toString(), c.toString());
    }
    @Test
    public void testDeleteUser(){
        Athlete a = new Athlete();
        a.setId(10);
        a.setName("Eli");
        a.setCountry("Argentina");
        a.setIsActive(true);
        ReturnValue ret = Solution.deleteAthlete(a);
        assertEquals(NOT_EXISTS , ret);

        ret = Solution.addAthlete(a);
        assertEquals(OK, ret);

        ret = Solution.deleteAthlete(a);
        assertEquals(OK , ret);


    }
}