
    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `announcement` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `link` varchar(255),
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `answer` (
       `id` integer not null,
        `version` integer not null,
        `answer` varchar(255),
        `keylet` varchar(255),
        `password` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `application` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `qualifications` varchar(255),
        `reference` varchar(255),
        `skills` varchar(255),
        `statement` varchar(255),
        `status` integer,
        `answer_id` integer,
        `job_id` integer not null,
        `worker_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `audit_record` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `status` integer,
        `title` varchar(255),
        `auditor_id` integer not null,
        `job_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `auditor` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `firm` varchar(255),
        `responsibility` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `challenge` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(255),
        `goalbronze` varchar(255),
        `goalgold` varchar(255),
        `goalsilver` varchar(255),
        `rewardbronze_amount` double precision,
        `rewardbronze_currency` varchar(255),
        `rewardgold_amount` double precision,
        `rewardgold_currency` varchar(255),
        `rewardsilver_amount` double precision,
        `rewardsilver_currency` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `commercial_banner` (
       `id` integer not null,
        `version` integer not null,
        `link` varchar(255),
        `picture` varchar(255),
        `slogan` varchar(255),
        `credit_card` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `company_record` (
       `id` integer not null,
        `version` integer not null,
        `activities` varchar(255),
        `email` varchar(255),
        `incorporated` bit not null,
        `name` varchar(255),
        `nameceo` varchar(255),
        `phone_number` varchar(255),
        `sector` varchar(255),
        `stars` integer not null,
        `web_site` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `consumer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `descriptor` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `duty` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `time` double precision not null,
        `title` varchar(255),
        `descriptor_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `employer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `investor_records` (
       `id` integer not null,
        `version` integer not null,
        `investing_statement` varchar(255),
        `name` varchar(255),
        `sector` varchar(255),
        `stars` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `job` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `link` varchar(255),
        `reference` varchar(255),
        `salary_amount` double precision,
        `salary_currency` varchar(255),
        `status` integer,
        `title` varchar(255),
        `descriptor_id` integer not null,
        `employer_id` integer not null,
        `solim_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `justification` (
       `id` integer not null,
        `version` integer not null,
        `justification` varchar(255),
        `application_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `moment` datetime(6),
        `tags` varchar(255),
        `title` varchar(255),
        `message_thread_id` integer not null,
        `user_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `message_thread` (
       `id` integer not null,
        `version` integer not null,
        `moment` datetime(6),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `message_thread_user_account` (
       `message_thread_id` integer not null,
        `users_id` integer not null
    ) engine=InnoDB;

    create table `non_commercial_banner` (
       `id` integer not null,
        `version` integer not null,
        `link` varchar(255),
        `picture` varchar(255),
        `slogan` varchar(255),
        `jingle` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `offer` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `max_money_amount` double precision,
        `max_money_currency` varchar(255),
        `min_money_amount` double precision,
        `min_money_currency` varchar(255),
        `moment` datetime(6),
        `text` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `provider` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `company` varchar(255),
        `sector` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `request_auditor` (
       `id` integer not null,
        `version` integer not null,
        `firm` varchar(255),
        `responsibility` varchar(255),
        `status` integer,
        `user_account_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `requests` (
       `id` integer not null,
        `version` integer not null,
        `deadline` datetime(6),
        `description` varchar(255),
        `moment` datetime(6),
        `reward_amount` double precision,
        `reward_currency` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `solim` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `keylet` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `spam` (
       `id` integer not null,
        `version` integer not null,
        `spam` varchar(255),
        `threshold` double precision not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `worker` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `qualifications` varchar(255),
        `skills` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXnhikaa2dj3la6o2o7e9vo01y0 on `announcement` (`moment`);
create index IDX5wwxv107kvi5si12nh4226lnx on `application` (`status`, `moment` desc);

    alter table `application` 
       add constraint UK_ct7r18vvxl5g4c4k7aefpa4do unique (`reference`);
create index IDXof878cqun8l1ynh0ao94bw3au on `audit_record` (`status`);
create index IDXnr284tes3x8hnd3h716tmb3fr on `challenge` (`deadline`);
create index IDXarifnivwmckxueloi09uraj6o on `consumer` (`user_account_id`);
create index IDX3e0qjxdhy5mdlo9e3cm15akly on `employer` (`user_account_id`);
create index IDXal59yunywnkwi09ps7jxpr18c on `job` (`status`, `deadline`);
create index IDX28ur9xm72oo1df9g14xhnh8h3 on `job` (`status`);

    alter table `job` 
       add constraint UK_qpodqtu8nvqkof3olnqnqcv2l unique (`descriptor_id`);

    alter table `job` 
       add constraint UK_7jmfdvs0b0jx7i33qxgv22h7b unique (`reference`);

    alter table `justification` 
       add constraint UK_2ctploatlatw5btxcjwembifo unique (`application_id`);
create index IDXq2o9psuqfuqmq59f0sq57x9uf on `offer` (`deadline`);

    alter table `offer` 
       add constraint UK_iex7e8fs0fh89yxpcnm1orjkm unique (`ticker`);
create index IDX1amtl2d1x4a6qs0l55i3fo68j on `provider` (`user_account_id`);
create index IDX4riem6gjhfr8dy2ex40hckw6d on `request_auditor` (`status`);

    alter table `request_auditor` 
       add constraint UK_ie2ocrruj5nai12m6h4a0fmtw unique (`user_account_id`);
create index IDXmly5kwrpgadjkxv5t5dgw36hr on `requests` (`deadline`);

    alter table `requests` 
       add constraint UK_5v1h0kdr8vcps4i9e55k5gnc8 unique (`ticker`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);
create index IDXldvd2iai9jb731mxg9he7vw31 on `worker` (`user_account_id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `application` 
       add constraint `FK7ssxk74xvvk7eicjxk2s1ns3a` 
       foreign key (`answer_id`) 
       references `answer` (`id`);

    alter table `application` 
       add constraint `FKoa6p4s2oyy7tf80xwc4r04vh6` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `application` 
       add constraint `FKmbjdoxi3o93agxosoate4sxbt` 
       foreign key (`worker_id`) 
       references `worker` (`id`);

    alter table `audit_record` 
       add constraint `FKdcrrgv6rkfw2ruvdja56un4ji` 
       foreign key (`auditor_id`) 
       references `auditor` (`id`);

    alter table `audit_record` 
       add constraint `FKlbvbyimxf6pxvbhkdd4vfhlnd` 
       foreign key (`job_id`) 
       references `job` (`id`);

    alter table `auditor` 
       add constraint FK_clqcq9lyspxdxcp6o4f3vkelj 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `consumer` 
       add constraint FK_6cyha9f1wpj0dpbxrrjddrqed 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `duty` 
       add constraint `FK3cc3garl37bl7gswreqwr7pj4` 
       foreign key (`descriptor_id`) 
       references `descriptor` (`id`);

    alter table `employer` 
       add constraint FK_na4dfobmeuxkwf6p75abmb2tr 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `job` 
       add constraint `FKfqwyynnbcsq0htxho3vchpd2u` 
       foreign key (`descriptor_id`) 
       references `descriptor` (`id`);

    alter table `job` 
       add constraint `FK3rxjf8uh6fh2u990pe8i2at0e` 
       foreign key (`employer_id`) 
       references `employer` (`id`);

    alter table `job` 
       add constraint `FK56shwrbgy3ug729w5himb9w5u` 
       foreign key (`solim_id`) 
       references `solim` (`id`);

    alter table `justification` 
       add constraint `FK8ma9xucf9mh9736jhtdumt8x3` 
       foreign key (`application_id`) 
       references `application` (`id`);

    alter table `message` 
       add constraint `FKn5adlx3oqjna7aupm8gwg3fuj` 
       foreign key (`message_thread_id`) 
       references `message_thread` (`id`);

    alter table `message` 
       add constraint `FK9o6wsmyyjow8oqtoxdp3iein9` 
       foreign key (`user_id`) 
       references `user_account` (`id`);

    alter table `message_thread_user_account` 
       add constraint `FKnbmip5t870fxbecafgaxvyde8` 
       foreign key (`users_id`) 
       references `user_account` (`id`);

    alter table `message_thread_user_account` 
       add constraint `FKtchis3o5qij98x87mty6hdk4d` 
       foreign key (`message_thread_id`) 
       references `message_thread` (`id`);

    alter table `provider` 
       add constraint FK_b1gwnjqm6ggy9yuiqm0o4rlmd 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `request_auditor` 
       add constraint `FKa6m3imjvm1a1xjc0u4o4dxmks` 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `worker` 
       add constraint FK_l5q1f33vs2drypmbdhpdgwfv3 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
