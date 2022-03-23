package boosti.domain

import spock.lang.Specification

class TagSpec extends Specification {

  def 'tag should have id and name'() {
    def tag = new Tag(id: 42, name: 'jdk')

    expect: 'id and name are not null'
    tag.id
    tag.name
  }

}
