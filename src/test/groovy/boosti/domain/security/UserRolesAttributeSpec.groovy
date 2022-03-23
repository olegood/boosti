package boosti.domain.security


import spock.lang.Specification

import static org.hamcrest.Matchers.is

class UserRolesAttributeSpec extends Specification {

  def converter = new UserRolesAttribute()

  def 'should convert to database column'() {
    when:
    def result = converter.convertToDatabaseColumn(roles.toSet())

    then:
    result is(column)

    where:
    [roles, column] << dataTable()
  }

  def 'should convert to entity attribute'() {
    when:
    def result = converter.convertToEntityAttribute(column)

    then:
    result is(roles.toSet())

    where:
    [roles, column] << dataTable()
  }

  static def dataTable() {
    [
        [[], null],
        [["ADMIN", "USER"], "ADMIN,USER"],
        [["ROOT", "CONTENT_MANAGER", "AUDITOR"], "AUDITOR,CONTENT_MANAGER,ROOT"]
    ]
  }

  def 'should convert to upper case from database column'() {
    when:
    def result = converter.convertToEntityAttribute("aDmIN,USeR")

    then:
    result == ["ADMIN", "USER"].toSet()
  }

  def 'should convert to upper case from entity attribute'() {
    when:
    def result = converter.convertToDatabaseColumn(["aDmIN", "USeR"].toSet())

    then:
    result == "ADMIN,USER"
  }

}
