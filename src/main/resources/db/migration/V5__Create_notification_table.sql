create table notification
(
	id bigint auto_increment,
	notifier bigint not null,
	receiver bigint not null,
	outerId bigint not null,
	type int,
	gmt_create bigint not null,
	status int default 0,
	constraint notification_pk
		primary key (id)
);