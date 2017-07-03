package hello.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hello.model.Book;
import hello.model.Parent;

/**
 * person的rest
 * @author wei.w.zhou.integle.com
 * @copyright 2017年7月1日下午1:46:12
 */
@RepositoryRestResource
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
