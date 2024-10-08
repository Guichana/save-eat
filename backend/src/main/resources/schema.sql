
    create table eat (
        eat_date date not null,
        id int4 generated by default as identity,
        price int4 not null,
        rating smallint not null,
        user_id int4 not null,
        comment varchar(255) not null,
        food_name varchar(255) not null,
        place_name varchar(255) not null,
        primary key (id)
    );

    create table eat_photo (
        eat_id int4,
        id int4 generated by default as identity,
        file_id varchar(255) not null,
        primary key (id)
    );

    create table eat_tag (
        eat_id int4,
        id int4 generated by default as identity,
        tag_value varchar(255),
        primary key (id),
        constraint UNIQUE_TAG_IN_EAT unique (eat_id, tag_value)
    );

    create table oauth (
        id int4 generated by default as identity,
        user_id int4,
        provider_id varchar(255),
        uid varchar(255),
        primary key (id),
        constraint UNIQUE_OAUTH_ACCOUNT unique (provider_id, uid),
        constraint ONE_PROVIDER_PER_USER unique (user_id, provider_id)
    );

    create table users (
        id int4 generated by default as identity,
        join_at timestamp(6) not null,
        email varchar(255),
        image_url varchar(255),
        name varchar(255) not null,
        primary key (id)
    );

    alter table if exists eat 
       add constraint FK8wb38r7kd5c8ym2l61oow0tb3 
       foreign key (user_id) 
       references users;

    alter table if exists eat_photo 
       add constraint FK987m76ct8qe7mm5w27jlwy6h6 
       foreign key (eat_id) 
       references eat;

    alter table if exists eat_tag 
       add constraint FKo8mbr9a9a4fpakncwqowc6asq 
       foreign key (eat_id) 
       references eat;

    alter table if exists oauth 
       add constraint FKhr1b41mlnnt4rmhcbdpti7hd 
       foreign key (user_id) 
       references users;
