package base;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import base.Post;

public class PostTest {
  
  private static final long NUMBER = 42;

  @Test
  public void testPost() {
    Post testPost = new Post();
    testPost.setPostText("aaa");
    Assert.assertTrue("aaa".equals(testPost.getPostText()));
    testPost.setEmailAddr("a@b.c");
    Assert.assertTrue("a@b.c".equals(testPost.getEmailAddr()));
    long l = NUMBER;
    testPost.setListIndex(l);
    Assert.assertTrue(l == testPost.getListIndex());
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    testPost.setTagList(aList);
    Assert.assertTrue(aList.equals(testPost.getTagList()));

  }

  // @Test
  // public void testGetListIndex() {
  // Post testPost = new Post();
  // long l = 42;
  // Assert.assertTrue(l == testPost.getListIndex());
  // }
  //
  // @Test
  // public void testSetListIndex() {
  // Post testPost = new Post();
  // long l = 42;
  // Assert.assertTrue(l == testPost.getListIndex());
  // }

  @Test
  public void testGetEmailAddr() {
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    String mail = "a@b.c";
    Post testPost = new Post("","Title", "blabla", aList, "Test");
    Assert.assertFalse(mail.equals(testPost.getEmailAddr()));
    testPost.setEmailAddr(mail);
    Assert.assertTrue(mail.equalsIgnoreCase(testPost.getEmailAddr()));
  }

  @Test
  public void testSetEmailAddr() {
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    String mail = "a@b.c";
    Post testPost = new Post("","Title", "blabla", aList, "Test");
    Assert.assertFalse(mail.equals(testPost.getEmailAddr()));
    testPost.setEmailAddr(mail);
    Assert.assertTrue(mail.equalsIgnoreCase(testPost.getEmailAddr()));
  }

  @Test
  public void testGetPostText() {
    Post testPost = new Post();
    testPost.setPostText("aaa");
    Assert.assertTrue(testPost.getPostText().equals("aaa"));
    Assert.assertFalse(testPost.getPostText().equals("bbb"));
    testPost.setPostText("bbb");
    Assert.assertFalse(testPost.getPostText().equals("aaa"));
    Assert.assertTrue(testPost.getPostText().equals("bbb"));
  }

  @Test
  public void testSetPostText() {
    Post testPost = new Post();
    testPost.setPostText("aaa");
    Assert.assertTrue(testPost.getPostText().equals("aaa"));
    Assert.assertFalse(testPost.getPostText().equals("bbb"));
    testPost.setPostText("bbb");
    Assert.assertFalse(testPost.getPostText().equals("aaa"));
    Assert.assertTrue(testPost.getPostText().equals("bbb"));
  }

  @Test
  public void testGetTagList() {
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    ArrayList<String> bList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    aList.add("#hash3");
    Post testPost = new Post("a@b.c", "TestTile", "blabla", aList, "Test");
    Assert.assertTrue(aList.equals(testPost.getTagList()));
    testPost.setTagList(bList);
    Assert.assertFalse(aList.equals(testPost.getTagList()));
    Assert.assertTrue(bList.equals(testPost.getTagList()));
  }

  @Test
  public void testSetTagList() {
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    ArrayList<String> bList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    aList.add("#hash3");
    Post testPost = new Post("a@b.c", "TestTile", "blabla", aList, "Test");
    Assert.assertTrue(aList.equals(testPost.getTagList()));
    testPost.setTagList(bList);
    Assert.assertFalse(aList.equals(testPost.getTagList()));
    Assert.assertTrue(bList.equals(testPost.getTagList()));
  }
  
  @Test
  public void testGetTitle() {
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    Post testPost = new Post("a@b.c", "TestTitle", "blabla", aList, "Test");
    Assert.assertTrue(testPost.getTitle().equals("TestTitle"));
  }
  
  @Test
  public void testSetTitle() {
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    Post testPost = new Post("a@b.c", "TestTitle", "blabla", aList, "Test");
    Assert.assertTrue(testPost.getTitle().equals("TestTitle"));
    testPost.setTitle("TestChanged");
    Assert.assertTrue(testPost.getTitle().equals("TestChanged"));
  }
  
  @Test
  public void testGetCategory() {
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    Post testPost = new Post("a@b.c", "TestTitle", "blabla", aList, "Test");
    Assert.assertTrue(testPost.getCategory().equals("Test"));
  }
  
  @Test
  public void testSetCategory() {
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("#hash1");
    aList.add("#hash2");
    Post testPost = new Post("a@b.c", "TestTitle", "blabla", aList, "Test");
    Assert.assertTrue(testPost.getCategory().equals("Test"));
    testPost.setCategory("TestChanged");
    Assert.assertTrue(testPost.getCategory().equals("TestChanged"));
  }
  
}
