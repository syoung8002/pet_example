package petexample.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import petexample.domain.*;

@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface ItemRepository
    extends PagingAndSortingRepository<Item, Long> {}
