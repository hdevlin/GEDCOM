


package MyPackage;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import MyPackage.date;
import MyPackage.family;
import MyPackage.person;
public class project3Test {

	@Test
	public void test() {
		
		date d1 = new date("24 AUG 2000");
		date d2 = new date( "24 AUG 2019");
		date d3 = new date("24 08 2000");
		date d4 = new date("23 08 2000");
		date d5 = new date( "12 01");
		date d6 = new date("2000");
		assertTrue(d5.day == -1);
		assertTrue(d5.month == 12);
		assertTrue(d5.year == 1);
		assertTrue(d6.year == 2000);
		assertTrue(d6.day == -1);
		assertTrue(d6.month == -1);
		assertTrue(d3.complete().toString().equals("24 1 2000"));
		assertTrue(d6.complete().toString().equals("1 1 2000"));
		assertTrue(d1.complete().toString().equals(d1.toString()));
		date d7 = new date("13 3000");
		assertTrue(d7.isIncomplete());
		assertTrue(!d7.isValid());
		date d8 = new date("30 FEB 3000");
		assertTrue(!d8.isIncomplete());
		assertTrue(!d8.isValid());
		assertTrue(date.before(d1, d2) == true);
		assertTrue(date.before(d1, d3) == false);
		assertTrue(date.before(d1, d4) == false);
		assertTrue(d2.differenceInYears(d1) == 19);
		person p1 = new person();
		assertTrue(p1.birthBeforeDeath() == true);
		p1.setDeathdate("30 12 200");
		p1.setBirthday("30 12 200");
		assertTrue(p1.birthBeforeDeath() == false);
		
		family f1 = new family();
		assertTrue(f1.marryBeforeDivorce());
		f1.setMarrdate("1 1 100");
		assertTrue(f1.marryBeforeDivorce());

		f1.setDivdate("1 1 0");
		assertTrue(f1.marryBeforeDivorce() == false);
		
		person p2 = new person();
		family f2 = new family();
		assertTrue(p2.birthBeforeCurrentDate());
		assertTrue(p2.deathBeforeCurrentDate());
		assertTrue(p2.marriageBeforeCurrentDate());
		assertTrue(p2.divorceBeforeCurrentDate());
		assertTrue(f2.marriageBeforeCurrentDate());
		assertTrue(f2.divorceBeforeCurrentDate());
		p2.setBirthday("01 01 0001");
		p2.setDeathdate("01 01 0001");
		p2.setWeddingdate("01 01 0001");
		p2.setDivorcedate("01 01 0001");
		f2.setMarrdate("01 01 0001");
		f2.setDivdate("01 01 0001");
		assertTrue(p2.birthBeforeCurrentDate());
		assertTrue(p2.deathBeforeCurrentDate());
		assertTrue(p2.marriageBeforeCurrentDate());
		assertTrue(p2.divorceBeforeCurrentDate());
		assertTrue(f2.marriageBeforeCurrentDate());
		assertTrue(f2.divorceBeforeCurrentDate());
		p2.setBirthday("01 01 3000");
		p2.setDeathdate("01 01 3000");
		p2.setWeddingdate("01 01 3000");
		p2.setDivorcedate("01 01 3000");
		f2.setMarrdate("01 01 3000");
		f2.setDivdate("01 01 3000");
		f2.setChildren("@1@");
		
	}

}
