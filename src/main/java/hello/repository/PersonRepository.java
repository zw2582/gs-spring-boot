package hello.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.web.PageableDefault;

import hello.model.Person;
import java.lang.String;
import java.util.List;

/**
 * CrudRepository是JPA持久化对象的操作类
 * @RepositoryRestResource 用于将JPA仓库制作成rest资源
 */
@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {

	/**
	 * 根据firstName查询person
	 * @see http://127.0.0.1:8090/persons/search/name?name=jkljklj&page=1&size=3
	 */
	@RestResource(path="name", rel="Name")
	public Page<Person> findByFirstName(@Param("name") String name, Pageable p);
	
	/**
	 * 根据firstName的首字母查询person
	 * JPA使用方法名称来组装sql语句，本次使用startsWith
	 * @see http://127.0.0.1:8090/persons/search/first_name?name=jkl&page=0&size=4&id,desc
	 */
	@RestResource(path="first_name", rel="firstName")
	public List<Person> findByFirstNameStartsWith(@Param("name") String firstname, Pageable p);
}
