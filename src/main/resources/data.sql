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

insert into boosti.user(id, email, password, roles)
values (400, 'root@email.org', '$2a$10$SdQNpTwCpYUTA05AMOcVSOC8OzLnOqod6Evpq3luH4s722Mp8fImu', 'ROOT'),
       (401, 'author@email.org', '$2a$10$HFBLpD4eLX0fBY7PCsoFzObcZhb5/qy8Znc59huxuy.qx/C.KnosS', 'AUTHOR'),
       (402, 'user@email.org', '$2a$10$N1R6Zh5BKIgmDuEUNxJN0.61HB4RCOELPcJKTjIzeY.ozgUqUk6WK', 'USER');
