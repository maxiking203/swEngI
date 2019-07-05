package base;

import org.junit.Test;
import org.junit.Assert;

public class MailTest {

  @Test
  public void sendMailTest() {
    SendMail sender = new SendMail();
    Assert.assertNotNull(sender);
    sender.send("testn@hm.edu", "1234AbCd");
  }
   
}
