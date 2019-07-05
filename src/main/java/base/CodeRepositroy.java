package base;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CodeRepositroy extends CrudRepository<Code, Integer> {
	
	public Iterable<Code> findByMail(@Param("mail") String mail);

}
