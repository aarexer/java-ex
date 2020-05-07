create table if not exists message
(
    id       BIGINT        NOT NULL AUTO_INCREMENT,
    filename VARCHAR(255),
    tag      VARCHAR(255),
    text     VARCHAR(2048) NOT NULL,
    user_id  BIGINT,
    primary key (id)
) engine = InnoDB;

create table if not exists user_role
(
    user_id BIGINT NOT NULL,
    roles   VARCHAR(255)
) engine = InnoDB;

create table if not exists usr
(
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    activation_code VARCHAR(255),
    active          BOOLEAN      NOT NULL,
    email           VARCHAR(255),
    password        VARCHAR(255) NOT NULL,
    username        VARCHAR(255) NOT NULL,
    primary key (id)
) engine = InnoDB;

alter table message
    add constraint message_user_fk
        foreign key (user_id) references usr (id);

alter table user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr (id);