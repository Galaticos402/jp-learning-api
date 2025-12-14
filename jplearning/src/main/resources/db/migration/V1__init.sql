CREATE DATABASE "jp-elearning"
OWNER "quandm18"
ENCODING 'UTF8';

 create table "system_user" (
         user_id uuid not null,
         email varchar(50),
         primary key (user_id)
     );

 create table "repetition_track" (
        track_id uuid not null,
        due_date timestamp(6),
        ease_factor float4,
        interval integer,
        learning_state varchar(255),
        learning_step integer,
        user_id uuid,
        vocab_id uuid,
        primary key (track_id)
 );

 create table "vocab" (
         vocab_id uuid not null,
         audio_path varchar(100),
         ex_sentence text,
         ex_sentence_meaning text,
         furigana varchar(50),
         image_path varchar(100),
         kanji varchar(50),
         meaning text,
         sino_vi varchar(50),
         group_id uuid,
         primary key (vocab_id)
     );

 create table "vocab_group" (
         group_id uuid not null,
         group_name varchar(50),
         image_key varchar(50),
         is_leaf boolean,
         ord varchar(10),
         parent_group_id uuid,
         primary key (group_id)
     );

alter table if exists "repetition_track"
       add constraint FKj0atxtf9gd6jmhvhpjvv6xnmx
       foreign key (user_id)
       references "system_user";

alter table if exists "repetition_track"
        add constraint FKft1ig0mplue8jrsbl4ij1i9cj
        foreign key (vocab_id)
        references "vocab";

alter table if exists "vocab"
       add constraint FKd6qmjsyfxpqw17uvvewm2i1dt
       foreign key (group_id)
       references "vocab_group";

alter table if exists "vocab_group"
       add constraint FKjxymkorn5grdbaxhqaywchkc7
       foreign key (parent_group_id)
       references "vocab_group";

ALTER TABLE "vocab"
ALTER COLUMN "vocab_id" SET DEFAULT gen_random_uuid();

ALTER TABLE "vocab_group"
ALTER COLUMN "group_id" SET DEFAULT gen_random_uuid();

ALTER TABLE "system_user"
ALTER COLUMN "user_id" SET DEFAULT gen_random_uuid();

ALTER TABLE "repetition_track"
ALTER COLUMN "track_id" SET DEFAULT gen_random_uuid();