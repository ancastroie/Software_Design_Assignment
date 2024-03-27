create sequence users_userid_seq;

alter sequence users_userid_seq owner to postgres;

create sequence question_questionid_seq
    as integer;

alter sequence question_questionid_seq owner to postgres;

create sequence answer_answerid_seq
    as integer;

alter sequence answer_answerid_seq owner to postgres;

create table users
(
    userid      integer        default nextval('users_userid_seq'::regclass) not null
        primary key,
    username    varchar(50)                                                  not null,
    password    varchar(255)                                                 not null,
    email       varchar(100),
    phonenumber varchar(20),
    userscore   numeric(10, 2) default 0,
    ismoderator boolean        default false
);

alter table users
    owner to postgres;

create table tag
(
    tagid   serial
        primary key,
    tagname varchar(50)
        unique
);

alter table tag
    owner to postgres;

create table questions
(
    questionid       integer   default nextval('question_questionid_seq'::regclass) not null
        constraint question_pkey
            primary key,
    authorid         integer
        constraint question_authorid_fkey
            references users,
    title            varchar(255),
    text             text,
    creationdatetime timestamp default CURRENT_TIMESTAMP,
    pictureurl       bytea,
    tagid            integer
        constraint question_tagid_fkey
            references tag,
    votecount        integer   default 0,
    isdeleted        boolean
);

alter table questions
    owner to postgres;

alter sequence question_questionid_seq owned by questions.questionid;

create table answers
(
    answerid         integer   default nextval('answer_answerid_seq'::regclass) not null
        constraint answer_pkey
            primary key,
    questionid       integer
        references questions,
    authorid         integer
        constraint answer_authorid_fkey
            references users,
    text             text,
    creationdatetime timestamp default CURRENT_TIMESTAMP,
    pictureurl       varchar(255),
    votecount        integer   default 0,
    isdeleted        boolean
);

alter table answers
    owner to postgres;

alter sequence answer_answerid_seq owned by answers.answerid;

create table vote
(
    voteid     serial
        primary key,
    userid     integer
        references users,
    questionid integer
        references questions,
    answerid   integer
        references answers,
    votetype   varchar(10) not null
);

alter table vote
    owner to postgres;

create table ban
(
    banid       integer not null
        primary key,
    userid      integer
        references users,
    moderatorid integer
        references users,
    bandatetime timestamp default CURRENT_TIMESTAMP,
    isbanned    boolean,
    banreason   varchar(255)
);

alter table ban
    owner to postgres;


