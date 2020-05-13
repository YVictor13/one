create table question
(
	id BIGINT auto_increment,
	title VARCHAR(50),
	description TEXT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator BIGINT,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag VARCHAR(20),
	constraint question_pk
		primary key (id)
);

comment on table question is '问题表';