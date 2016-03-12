/*添加数据角色*/
set identity_insert Role ON  --打开
insert into Role(id,Role_name,Role_type)values(4,'工程师',4);
insert into Role(id,Role_name,Role_type)values(5,'质检人员',5);
set identity_insert Role OFF--关闭

/*关系表*/
create table re_engineer_area_powerstation 
(
   id                   varchar(20)                    not null,
   area_id              varchar(20)                    not null,
   user_id              int                            not null,
   ps_id                int                            not null,
   constraint PK_RE_ENGINEER_AREA_POWERSTATI primary key  (id)
);


/*区域表*/
create table bd_area 
(
   area_id              int                            not null,
   area_code            varchar(12)                    null,
   area_name            varchar(20)                    null,
   area_level           int                            null,
   parent_id            int                            null,
   area_flag            int                            null,
   constraint PK_BD_AREA primary key  (area_id)
);

/*故障信息表*/
create table bd_fault_message 
(
   fault_message_id     int                            not null,
   area_id              varchar(20)                    null,
   ps_id                int                            null,
   equipment_id         varchar(30)                    null,
   equipment_status     int                            null,
   engineer_id          int                            null,
   alert_time           varchar(20)                    null,
   status               int                            null,
   initial_diagnose     text                           null,
   predict_time         varchar(20)                    null,
   alert_cause          text                           null,
   handle_condition     text                           null,
   maintain_date        varchar(20)                    null,
   check_person         varchar(20)                    null,
   check_date           varchar(20)                    null,
   check_text           text                           null,
   check_status         int                            null,
   constraint PK_BD_FAULT_MESSAGE primary key  (fault_message_id)
);


/*巡检管理*/
create table bd_inspection_manager 
(
   id                   varchar(20)                    not null,
   area_id              varchar(20)                    null,
   ps_id                int                            null,
   equipment_id         int                            null,
   user_id              int                            null,
   curr_date            varchar(20)                    null,
   inspection_period    int                            null,
   next_date            varchar(20)                    null,
   constraint PK_BD_INSPECTION_MANAGER primary key  (id)
);

/*巡检记录*/
create table bd_inspection 
(
   id                   varchar(20)                    not null,
   manager_id           varchar(20)                    null,
   should_date          varchar(20)                    null,
   actual_date          varchar(20)                    null,
   inspection_report    text                           null,
   inspection_status    int                            null,
   constraint PK_BD_INSPECTION primary key clustered (id)
);

