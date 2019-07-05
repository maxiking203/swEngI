package base;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long listIndex;
  private String emailAddr;
  private String title;
  private String postText;
  private ArrayList<String> tagList;
  private String category;

  public Post() {

  }

  public Post(String email, String title, String text, ArrayList<String> tags, String category) {
    this.emailAddr = email;
    this.title = title;
    this.postText = text;
    this.tagList = tags;
    this.category = category;
  }

  public Long getListIndex() {
    return listIndex;
  }

  public void setListIndex(Long index) {
    this.listIndex = index;
  }

  public String getEmailAddr() {
    return emailAddr;
  }

  public void setEmailAddr(String mail) {
    this.emailAddr = mail;
  }

  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getPostText() {
    return postText;
  }

  public void setPostText(String text) {
    this.postText = text;
  }

  public ArrayList<String> getTagList() {
    return tagList;
  }

  public void setTagList(ArrayList<String> tags) {
    this.tagList = tags;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

}
