insert into boosti.category(id, name)
values (1, 'Java'),
       (2, 'Maven'),
       (3, 'Kotlin');

insert into boosti.question(id, category_id, text, tags)
values (100, 1, 'What is JVM?', 'jvm,java,jdk'),
       (101, 1, 'What is JRE', 'jvm,jdk,jre'),
       (102, 3, 'What is Kotlin', 'kotlin,jvm');
