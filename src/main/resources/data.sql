insert into boosti.category(id, name)
values (11, 'Java'),
       (12, 'Maven'),
       (13, 'Kotlin');

insert into boosti.tag(id, name)
values (21, 'jvm'),
       (22, 'java'),
       (23, 'jdk'),
       (24, 'jre'),
       (25, 'kotlin');

insert into boosti.question(id, category_id, text)
values (100, 11, 'What is JVM?'),
       (101, 11, 'What is JRE'),
       (102, 13, 'What is Kotlin');

insert into boosti.question_tags(question_id, tag_id)
values (100, 21),
       (100, 22),
       (100, 23),
       --
       (101, 21),
       (101, 23),
       (101, 24),
       --
       (102, 21),
       (102, 25);

insert into boosti.quiz(id, status)
values (500, 'DRAFT');

insert into boosti.quiz_questions(quiz_id, question_id)
values (500, 100),
       (500, 101);
