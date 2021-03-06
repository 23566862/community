create table question
(
	id int auto_increment,
	title varchar(50) null,
	description text null,
	creator int null,
	comment_count int default 0 null,
	view_count int default 0 null,
	like_count int default 0 null,
	tag varchar(256) null,
	gmt_create bigint null,
	gmt_modified bigint null,
	constraint question_pk
		primary key (id)
);

