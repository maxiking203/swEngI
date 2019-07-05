package base;

import org.junit.Test;
import org.junit.Assert;



public class CodeTest {
  
  @Test
  public void createCodeTest() {
    Code test = new Code();
    test.setCode("1234AbCd");
    test.setMail("test@hm.edu");
    Assert.assertEquals("test@hm.edu", test.getMail());
    Assert.assertEquals("1234AbCd", test.getCode());
  }
  
  @Test
  public void createCodeTestSec() {
    Code test = new Code("test@hm.edu");
    Assert.assertEquals("test@hm.edu", test.getMail());
    Assert.assertFalse(test.getCode().isEmpty());
  }
  
  @Test
  public void setCodeMailTest() {
    Code test = new Code("test@hm.edu");
    test.setMail("changed@hm.edu");
    Assert.assertEquals("changed@hm.edu", test.getMail());
    Assert.assertFalse(test.getCode().isEmpty());
  }
  
  @Test
  public void setCodeTest() {
    Code test = new Code("test@hm.edu");
    test.setCode("AAAAAAAA");
    Assert.assertEquals("AAAAAAAA", test.getCode());

  }

}
