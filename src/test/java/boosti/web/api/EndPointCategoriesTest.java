package boosti.web.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import boosti.domain.Category;
import boosti.repo.CategoryRepository;
import boosti.web.model.SimpleRefData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EndPointCategoriesTest {

  @Mock CategoryRepository categoryRepo;

  @InjectMocks EndPointCategories endPointCategories;

  @Test
  void shouldCallRepositoryFindAllWhenGetAll() {
    // when
    endPointCategories.getAll();

    // then
    verify(categoryRepo, times(1)).findAll();
  }

  @Test
  void shouldReturnCategoryRetrievedFomRepository() {
    // given
    var id = 42L;
    var name = "Java";

    var category = new Category();
    category.setId(id);
    category.setName(name);
    when(categoryRepo.findAll()).thenReturn(List.of(category));

    var data = SimpleRefData.builder().withId(id).withName(name).build();

    // when
    var result = endPointCategories.getAll();

    // then
    verify(categoryRepo, times(1)).findAll();
    assertThat(result, hasSize(1));
    assertThat(result, containsInAnyOrder(data));
  }
}
