DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '主键',
  `account_name` varchar(50) NOT NULL COMMENT '账户名称',
  `status` smallint(5) NOT NULL COMMENT '状态（1：停用；0：启用）',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `user_name` varchar(50) DEFAULT '' COMMENT '姓名',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号码',
  `role_id` int(11) DEFAULT 0 COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_account_name` (`account_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='用户表';

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `remark` varchar(256) DEFAULT NULL COMMENT '角色描述',
  `code` varchar(32) DEFAULT NULL COMMENT '角色编码',
  `status` smallint(5) NOT NULL COMMENT '状态（1：停用；0：启用）',
  `role_level` smallint(5) NOT NULL COMMENT '级别：0：总行 1：分行',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role` (
  `account_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY `idx_account_role` (`account_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8  COMMENT='用户角色表';



DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL COMMENT '主键',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID（一级菜单为0)',
  `name` varchar(128) DEFAULT NULL COMMENT '菜单名',
  `url` varchar(100) DEFAULT NULL COMMENT '菜单URL地址',
  `status` int(11) NOT NULL COMMENT '状态：0：启用1：停用',
  `type` int(11) NOT NULL COMMENT '类型   0：目录 1：菜单 2：按钮',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标地址',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `code` varchar(32) DEFAULT NULL COMMENT '关键字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `idx_menu_code` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '系统管理', null, '0', '0','fa fa-cog', '0', null,null,null);
INSERT INTO `menu` VALUES ('2', '1', '用户管理', '/sys/user', '0', '0','fa fa-cog', '1', null,null,null);
INSERT INTO `menu` VALUES ('3', '1', '角色管理', '/sys/user', '0', '0','fa fa-cog', '1', null,null,null);



DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';


DROP TABLE IF EXISTS `login_ticket`;

CREATE TABLE `login_ticket` (
  `account_id` int(11) NOT NULL COMMENT '用户ID',
  `ticket` varchar(45) NOT NULL COMMENT '登陆凭证',
  `expired` datetime NOT NULL COMMENT '失效时间',
  `status` int(11) DEFAULT '0' COMMENT '状态 0：有效 1：无效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `ticket_UNIQUE` (`ticket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登陆信息表';


DROP TABLE IF EXISTS `channel`;

CREATE TABLE `channel` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '渠道ID',
  `name` varchar(200) NOT NULL COMMENT '渠道名称',
  `type` varchar(45) DEFAULT NULL COMMENT '渠道类型',
  `contract_person` varchar(45) DEFAULT NULL COMMENT '渠道联系人',
  `contract_information` varchar(45) DEFAULT NULL COMMENT '渠道联系方式',
  `account` varchar(45) DEFAULT NULL COMMENT '关联用户账号',
  `account_id` int(11) DEFAULT 0 COMMENT '关联用户账号id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道表';


DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '产品类别ID',
  `name` varchar(200) NOT NULL COMMENT '产品类别名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品类别表';

DROP TABLE IF EXISTS `product_tag`;

CREATE TABLE `product_tag` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '产品标签ID',
  `name` varchar(200) NOT NULL COMMENT '产品标签名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品标签表';



DROP TABLE IF EXISTS `product`;


CREATE TABLE `product` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '产品ID',
  `name` varchar(45) NOT NULL COMMENT '产品名称',
  `status` int(2) NOT NULL COMMENT '状态',
  `category` varchar(45) NOT NULL COMMENT '产品类别ID',
  `term` varchar(45) DEFAULT 0 COMMENT '产品期数',
  `repayment` varchar(45) DEFAULT NULL COMMENT '还款方式',
  `amount` varchar(45) DEFAULT NULL COMMENT '借款额度',
  `rate` double(11,5) DEFAULT 0 COMMENT '年化利率',
  `standard` varchar(200) DEFAULT NULL COMMENT '准入标准',
  `pawn` varchar(200) DEFAULT NULL COMMENT '抵押物',
  `city` varchar(200) DEFAULT NULL COMMENT '城市',
  `credit` varchar(200) DEFAULT NULL COMMENT '征信要求',
  `requred_material` varchar(200) DEFAULT NULL COMMENT '进件时需要的资料',
  `process` varchar(200) DEFAULT NULL COMMENT '操作流程',
  `tags` varchar(200) DEFAULT NULL COMMENT '产品标签ID',
  `channel` varchar(200) DEFAULT NULL COMMENT '渠道名称',
  `description` varchar(500) DEFAULT NULL COMMENT '产品描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品表';


DROP TABLE IF EXISTS `income`;

CREATE TABLE `income` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '进件ID',
  `business_no` varchar(20) NOT NULL COMMENT '业务编号',
  `title` varchar(200) DEFAULT NULL COMMENT '流程标题',
  `status` int(2) NOT NULL COMMENT '状态',
   --1.未提交（保存） 2.待认领（已提交） 3.待初审（已认领） 4.待复审认领；（已初审） 5.待复审（已认领） 6.待终审（已复审） 7.渠道咨询（已终审） 8.待渠道确认（渠道认领） 9.待合同确认（渠道确认）
   --10.待收费确认（合同确认） 11.待放款（已收费确认） 12.待收款（已放款） 13.流程结束（已收尾款） 14.中止 15.初审退回  16.复审退回   17.终审退回 18.收费调整
  `step` varchar(45) DEFAULT NULL COMMENT '当前步骤',
  `product_id` int(11) COMMENT '产品编号',

  `corp_name` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `legal_person` varchar(200) DEFAULT NULL COMMENT '法定代表人',
  `setup_date` date DEFAULT NULL COMMENT '成立日期',
  `registered_capital` double(11,2) DEFAULT 0  COMMENT '注册资金',
  `paid_up_capital` double(11,2) DEFAULT 0 COMMENT '实缴资本',
  `business_flow` varchar(200) DEFAULT NULL COMMENT '经营流水',
  `liabilities` varchar(200) DEFAULT NULL COMMENT '负债情况',
  `address` varchar(200)  DEFAULT NULL COMMENT '公司地址',

  `collateral_area` varchar(200) DEFAULT NULL COMMENT '物业抵押区域',
  `collateral_name` varchar(200) DEFAULT NULL COMMENT '抵押物名称',
  `collateral_value` varchar(200) DEFAULT NULL COMMENT '抵押物价值',
  `property_overview` varchar(200) DEFAULT NULL COMMENT '物业概况',

  `loan_amount` double(11,2) DEFAULT 0 COMMENT '借款金额',
  `loan_term` int(11) DEFAULT 0 COMMENT '借款期限',
  `loan_rate` double(11,5) DEFAULT 0 COMMENT '综合利率',
  `loan_use` varchar(200) DEFAULT NULL COMMENT '款项用途',
  `loan_repayment` varchar(200) DEFAULT NULL COMMENT '还款来源',
  `loan_guarantee` varchar(200) DEFAULT NULL COMMENT '还款保证',
  `loan_information` varchar(200) DEFAULT NULL COMMENT '其他说明',

  `is_loan` varchar(500) DEFAULT 0 COMMENT '是否在银行按揭过',
  `is_complaint` varchar(500) DEFAULT 0 COMMENT '是否涉诉',
  `is_executed` varchar(500) DEFAULT 0 COMMENT '是否被执行',
  `is_broken_promise` varchar(500) DEFAULT 0 COMMENT '是否失信',

  `idcrad_path` varchar(200) DEFAULT NULL COMMENT '身份证',
  `marriage_path` varchar(200) DEFAULT NULL COMMENT '婚姻证明',
  `hukou_path` varchar(200) DEFAULT NULL COMMENT '户口本',
  `bankcrad_path` varchar(200) DEFAULT NULL COMMENT '银行卡',
  `work_permit_path` varchar(200) DEFAULT NULL COMMENT '工作证明',
  `bank_flow_path` varchar(200) DEFAULT NULL COMMENT '银行流水',
  `estate_path` varchar(200) DEFAULT NULL COMMENT '房产资料',
  `credit_path` varchar(200) DEFAULT NULL COMMENT '征信报告',

   `comment` varchar(200) DEFAULT NULL COMMENT '处理意见',
   `processor_id` int(11) DEFAULT NULL COMMENT '处理人ID',
   `processor` varchar(45) DEFAULT NULL COMMENT '处理人',
   `process_time` datetime DEFAULT NULL COMMENT '处理时间',
   `producter_id`  int(11) DEFAULT NULL COMMENT '经理ID',
   `last_processor_id` int(11) DEFAULT NULL COMMENT '上一处理人ID',
   `last_processor` varchar(45) DEFAULT NULL COMMENT '上一处理人',

   `agent` varchar(45) DEFAULT NULL COMMENT '代理人',

   `start_date` date DEFAULT NULL COMMENT '进件开始日期',
   `end_date` date DEFAULT NULL COMMENT '进件结束日期',


   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进件表';



DROP TABLE IF EXISTS `income_payment`;

CREATE TABLE `income_payment` (
  `income_id` int(11) NOT NULL COMMENT '进件编号',

    `channel_id` int(11) DEFAULT NULL COMMENT '渠道id',
    `provide_amount` double(11,2) DEFAULT 0 COMMENT '放款金额',
    `provide_date` date DEFAULT NULL COMMENT '放款日期',
    `provide_rate` double(11,5) DEFAULT 0 COMMENT '放款利率',
    `fee_amount` double(11,2) DEFAULT 0 COMMENT '收费金额',
    `advance_amount` double(11,2) DEFAULT 0 COMMENT '定金金额',
    `end_amount` double(11,2) DEFAULT 0 COMMENT '尾款金额',

    `real_advance_amount` double(11,2) DEFAULT 0 COMMENT '实际定金金额',
    `real_advance_date` date DEFAULT NULL COMMENT '实际定金打款日期',
    `advance_payer` varchar(45) DEFAULT NULL COMMENT '定金打款人',
    `advance_account` varchar(45) DEFAULT NULL COMMENT '定金打款账号',

    `real_endpay_amount` double(11,2) DEFAULT 0 COMMENT '实际尾款金额',
    `real_endpay_date` date DEFAULT NULL COMMENT '实际尾款打款日期',
    `endpay_payer` varchar(45) DEFAULT NULL COMMENT '尾款打款人',
    `endpay_account` varchar(45) DEFAULT NULL COMMENT '尾款打款账号',

    `real_provide_amount` double(11,2) DEFAULT 0 COMMENT '实际放款金额',
    `real_provide_date` date DEFAULT NULL COMMENT '实际放款日期',
    `real_start_date` date DEFAULT NULL COMMENT '借款开始日期',
    `real_end_date` date DEFAULT NULL COMMENT '借款结束日期',


  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`income_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进件付款信息表';


DROP TABLE IF EXISTS `income_channel`;

CREATE TABLE `income_channel` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '记录ID',
  `income_id` int(11) NOT NULL COMMENT '进件编号',
  `channel_id` int(11) NOT NULL COMMENT '渠道编号',
  `status` int(2) NOT NULL COMMENT '状态', --01待发送短信  02未认领 03已认领
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进件附加信息表';


DROP TABLE IF EXISTS `collateral_detail`;

CREATE TABLE `collateral_detail` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '记录ID',
  `income_id` int(11) NOT NULL COMMENT '进件编号',
  `collateral_area` varchar(200) DEFAULT NULL COMMENT '物业抵押区域',
  `collateral_name` varchar(200) DEFAULT NULL COMMENT '抵押物名称',
  `collateral_value` varchar(200) DEFAULT NULL COMMENT '抵押物价值',
  `property_overview` varchar(200) DEFAULT NULL COMMENT '物业概况',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抵押物明细表';

DROP TABLE IF EXISTS `income_addition`;

CREATE TABLE `income_addition` (
  `id` int(11) unsigned  AUTO_INCREMENT COMMENT '进件ID',
  `business_no` varchar(20) NOT NULL COMMENT '业务编号',
  `title` varchar(200) DEFAULT NULL COMMENT '流程标题',
  `status` int(2) NOT NULL COMMENT '状态',
   --1.未提交（保存） 2.待认领（已提交） 3.待初审（已认领） 4.待复审认领；（已初审） 5.待复审（已认领） 6.待终审（已复审） 7.渠道咨询（已终审） 8.待渠道确认（渠道认领） 9.待合同确认（渠道确认）
   --10.待收费确认（合同确认） 11.待放款（已收费确认） 12.待收款（已放款） 13.流程结束（已收尾款） 14.中止 15.初审退回  16.复审退回   17.终审退回
  `step` varchar(45) DEFAULT NULL COMMENT '当前步骤',
  `product_id` int(11) COMMENT '产品编号',

  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进件附加信息表';





DROP TABLE IF EXISTS `custom`;

CREATE TABLE `custom` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '客户ID',
  `corp_name` varchar(200) NOT NULL COMMENT '公司名称',
  `legal_person` varchar(200) DEFAULT NULL COMMENT '法定代表人',
  `setup_date` date NOT NULL COMMENT '成立日期',
  `registered_capital` double(11,2) DEFAULT 0  COMMENT '注册资金',
  `paid_up_capital` double(11,2) DEFAULT 0 COMMENT '实缴资本',
  `business_flow` varchar(200) DEFAULT NULL COMMENT '经营流水',
  `liabilities` varchar(200) DEFAULT NULL COMMENT '负债情况',
  `address` varchar(200) NOT NULL COMMENT '公司地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

DROP TABLE IF EXISTS `income_send`;

CREATE TABLE `income_send` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '记录ID',
  `income_id` int(11) unsigned NOT NULL  COMMENT '进件ID',
  `status` int(2) NOT NULL COMMENT '状态',
  `receiver` varchar(200) NOT NULL COMMENT '接收人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进件发送表';


DROP TABLE IF EXISTS `process_detail`;

CREATE TABLE `process_detail` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '记录ID',
  `income_id` int(11) unsigned NOT NULL  COMMENT '进件ID',
  `step_id` int(11) unsigned DEFAULT 0  COMMENT '当前步骤ID',
  `status` int(2) DEFAULT 0 COMMENT '状态',
  `comment` varchar(200) DEFAULT NULL COMMENT '处理意见',
  `processor` varchar(45) DEFAULT NULL COMMENT '处理人',
  `process_time` datetime DEFAULT NULL COMMENT '处理时间',
  `last_processor` varchar(45) DEFAULT 0  COMMENT '上一处理人',
  `last_process_time` datetime DEFAULT NULL COMMENT '上一处理时间',
  `use_time` time DEFAULT NULL COMMENT '处理耗时',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程详情表';


DROP TABLE IF EXISTS `income_send`;

CREATE TABLE `income_send` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '记录ID',
  `imcome_id` int(11) unsigned NOT NULL  COMMENT '进件ID',
  `channel_id` int(11) unsigned NOT NULL  COMMENT '渠道ID',
  `status` int(2) NOT NULL COMMENT '状态', --0-未发送 1-已发送 2-已认领
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进件发送表';



DROP TABLE IF EXISTS `commission`;

CREATE TABLE `commission` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '记录ID',
  `product_id` int(11) unsigned NOT NULL  COMMENT '产品ID',
  `channel_id` int(11) unsigned NOT NULL  COMMENT '渠道ID',
  `status` int(2) NOT NULL COMMENT '状态',
  `processor` varchar(45) NOT NULL COMMENT '处理人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提成系数表';

DROP TABLE IF EXISTS `commission_detail`;

CREATE TABLE `commission_detail` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '记录ID',
  `commission_id` int(11) unsigned NOT NULL  COMMENT '提成系数记录ID',
  `role_id` int(11) unsigned NOT NULL  COMMENT '角色ID',
  `ratio` double(11,5) NOT NULL COMMENT '系数',
  `information` varchar(200) DEFAULT NULL COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提成系数明细表';


DROP TABLE IF EXISTS `commission_standard`;

CREATE TABLE `commission_standard` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '记录ID',
  `product_id` int(11) unsigned NOT NULL  COMMENT '产品ID',
  `channel_id` int(11) unsigned NOT NULL  COMMENT '渠道ID',
  `standard` varchar(45) NOT NULL COMMENT '收费标准',
  `status` int(2) NOT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提成标准表';


DROP TABLE IF EXISTS `fee_standard`;

CREATE TABLE `fee_standard` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '记录ID',
  `product_id` int(11) unsigned NOT NULL  COMMENT '产品ID',
  `channel_id` int(11) unsigned NOT NULL  COMMENT '渠道ID',
  `standard` varchar(45) NOT NULL COMMENT '收费标准',
  `status` int(2) NOT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收费标准表';

DROP TABLE IF EXISTS `standing_book`;

CREATE TABLE `standing_book` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '记录ID',
  `status` int(2) NOT NULL COMMENT '状态',   -- 0待审核 1已审核通过 2审核不通过 3作废
  `name` varchar(200) NOT NULL COMMENT '台账名称',
  `business_no` varchar(200) NOT NULL COMMENT '业务编号',
  `start_date` date NOT NULL COMMENT '开始日期',
  `end_date` date NOT NULL COMMENT '结束日期',
  `creater` int(11) NOT NULL COMMENT '创建人Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='台账表';


DROP TABLE IF EXISTS `standing_book_detail`;

CREATE TABLE `standing_book_detail` (
  `id` int(11) unsigned AUTO_INCREMENT COMMENT '记录ID',
  `standing_book_id` int(11) NOT NULL COMMENT '台账ID',
  `income_id` int(11) NOT NULL COMMENT '进件ID',

  `earn` varchar(50) DEFAULT NULL COMMENT '净收入',
  `total_commission_amount` varchar(50) DEFAULT NULL COMMENT '提成提成总金额',
  `manager_amount` varchar(50) DEFAULT NULL COMMENT '客户经理',
  `channel_amount` varchar(50) DEFAULT NULL COMMENT '资金渠道',
  `first_review_amount` varchar(50) DEFAULT NULL COMMENT '风控初审岗',
  `second_review_amount` varchar(50) DEFAULT NULL COMMENT '风控复审岗',
  `final_review_amount` varchar(50) DEFAULT NULL COMMENT '风控终审岗',
  `finance_amount` varchar(50) DEFAULT NULL COMMENT '财务岗',

  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='台账明细表';

