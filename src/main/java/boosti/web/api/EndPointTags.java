package boosti.web.api;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import boosti.domain.TagRepository;
import boosti.web.model.SimpleRefData;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class EndPointTags {

  private final TagRepository tagRepository;

  public EndPointTags(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @GetMapping
  public Set<SimpleRefData> getAllTags() {
    return tagRepository.findAll().stream()
        .map(it -> new ModelMapper().map(it, SimpleRefData.class))
        .collect(toSet());
  }
}
