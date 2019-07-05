package base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/board")
public class MeterController {

  @Autowired
  private PostRepository repo;
  
  @Autowired
  private CodeRepositroy codeRepo;
  
//for Verification, can be used when the frontend changes  
  private SendMail sender = new SendMail();

  @GetMapping
  public ArrayList<Post> listAll() {
    ArrayList<Post> posts = new ArrayList<>();
    repo.findAll().forEach(post -> posts.add(post));
    return posts;
  }


  @PostMapping("{code}")
  public Post create(@PathVariable String code ,@RequestBody Post input) {
	  if (code != null && input != null) {
  	    if ((input.getEmailAddr().contains("hm") || input.getEmailAddr().contains("calpoly"))) {
  	    	if (codeRepo.findByMail(input.getEmailAddr()) != null) { 
  	    		Code result = new Code();
  	    		for(Code find : codeRepo.findByMail(input.getEmailAddr())) {
  	    			result = find;
  	    		}
  	    		if(result != null && code.equals(result.getCode())) {
  			    	codeRepo.delete(result);
  			    	return repo.save(new Post(input.getEmailAddr(), input.getTitle(), input.getPostText(), input.getTagList(), input.getCategory()));
  	    		}
  	          	else {
  	          		return null;
  	          	}
  	    	}
  	      	else {
  	      		return null;
  	      	}
  	    }
  	  	else {
  	  		return null;
  	  	}
	  }
	  else {
	    return null;
	  }
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable long id) {
    repo.delete(id);
  }

  @PutMapping("{index}")
  public Post update(@PathVariable Long index, @RequestBody Post input) {
    Post post = repo.findOne(index);
    if (post == null) {
      return null;
    }
    else {
      post.setEmailAddr(input.getEmailAddr());
      post.setTitle(input.getTitle());
      post.setPostText(input.getPostText());
      post.setTagList(input.getTagList());
      post.setCategory(input.getCategory());
      return repo.save(post);
    }
  }

  @DeleteMapping("/deleteAll")
  public void delete() {
    repo.deleteAll();
  }
  
// for Verification, can be used when the frontend changes
  @PutMapping("/getCode/{mail}")
  public boolean getCode(@PathVariable String mail) {
	  if (mail.contains("@hm") || mail.contains("@calpoly")) {
		  String mailAdr = mail + ".edu";
		  Code code = new Code(mailAdr);
		  codeRepo.save(code);
		  sender.send(mailAdr, code.getCode());
		  return true;
	  } else {
		  return false;
	  }
  }

}
