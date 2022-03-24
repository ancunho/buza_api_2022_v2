-- 메뉴테이블
CREATE TABLE `sys_menu` (
                            `ID` bigint NOT NULL AUTO_INCREMENT,
                            `PARENT_ID` bigint DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
                            `NAME` varchar(64) CHARACTER SET utf8 NOT NULL,
                            `PATH` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单URL',
                            `PERMS` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
                            `COMPONENT` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
                            `TYPE` int NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
                            `ICON` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单图标',
                            `ORDER_NUM` int DEFAULT NULL COMMENT '排序',
                            `OPTION01` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `OPTION02` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `OPTION03` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `OPTION04` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `OPTION05` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `CREATETIME` datetime DEFAULT CURRENT_TIMESTAMP,
                            `UPDATETIME` datetime DEFAULT NULL,
                            `STATUS` int NOT NULL,
                            PRIMARY KEY (`ID`),
                            UNIQUE KEY `NAME` (`NAME`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--권한테이블
CREATE TABLE `sys_role` (
                            `ID` bigint NOT NULL AUTO_INCREMENT,
                            `NAME` varchar(64) CHARACTER SET utf8 NOT NULL,
                            `CODE` varchar(64) CHARACTER SET utf8 NOT NULL,
                            `REMARK` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
                            `CREATETIME` datetime DEFAULT CURRENT_TIMESTAMP,
                            `UPDATETIME` datetime DEFAULT NULL,
                            `STATUS` int NOT NULL,
                            PRIMARY KEY (`ID`),
                            UNIQUE KEY `NAME` (`NAME`) USING BTREE,
                            UNIQUE KEY `CODE` (`CODE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--메뉴권한 테이블
CREATE TABLE `sys_role_menu` (
                                 `ID` bigint NOT NULL AUTO_INCREMENT,
                                 `ROLE_ID` bigint NOT NULL,
                                 `MENU_ID` bigint NOT NULL,
                                 PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--사용자테이블
CREATE TABLE `sys_user` (
                            `USER_SEQ` int NOT NULL AUTO_INCREMENT,
                            `OPENID` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
                            `UNIONID` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
                            `USERNAME` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `PASSWORD` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
                            `ROLE_NO` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
                            `ROLE` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
                            `STATUS` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
                            `USER_TYPE` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
                            `REALNAME` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `COMPANY` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
                            `COMPANY_TYPE` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `MOBILE_NO` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `EMAIL` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `SEX` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
                            `BIRTHDAY` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `WECHAT` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
                            `PROVINCE_CODE` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `CITY_CODE` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
                            `DISTRICT_CODE` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `ADDRESS` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
                            `QUESTION` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `ANSWER` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `IMAGE_PHOTO` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
                            `OPTION01` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `OPTION02` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `OPTION03` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `OPTION04` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `OPTION05` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `CREATETIME` datetime DEFAULT CURRENT_TIMESTAMP,
                            `UPDATETIME` datetime DEFAULT NULL,
                            PRIMARY KEY (`USER_SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원정보테이블';

--사용자-권한테이블
CREATE TABLE `sys_user_role` (
                                 `ID` bigint NOT NULL AUTO_INCREMENT,
                                 `USER_SEQ` bigint NOT NULL,
                                 `ROLE_ID` bigint NOT NULL,
                                 PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--공통코드테이블

CREATE TABLE IF NOT EXISTS `TB_COMMON_CODE` (
                                `CODE_ID` INT NOT NULL AUTO_INCREMENT COMMENT '번호',
                                `CODE_TYPE` VARCHAR(45) NULL,
                                `CODE_CD` VARCHAR(45) NULL,
                                `CODE_NAME` VARCHAR(45) NULL,
                                `USE_YN` VARCHAR(2) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
                                `REMARK` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
                                `SORT_ORDER` INT NULL,
                                `OPTION01` VARCHAR(45) NULL,
                                `OPTION02` VARCHAR(45) NULL,
                                `OPTION03` VARCHAR(45) NULL,
                                `OPTION04` VARCHAR(45) NULL,
                                `OPTION05` VARCHAR(45) NULL,
                                `CREATE_TIME` DATETIME NULL,
                                `UPDATE_TIME` DATETIME NULL,
    PRIMARY KEY (`CODE_ID`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='공통코드테이블';


--고객테이블 - 클라이언트정보
CREATE TABLE `tb_customer` (
                               `CUSTOMER_ID` int NOT NULL AUTO_INCREMENT COMMENT '고객번호',
                               `WEIXIN_OPEN_ID` varchar(100) DEFAULT NULL COMMENT '위챗오픈아이디',
                               `WEIXIN_UNION_ID` varchar(100) DEFAULT NULL COMMENT '위챗UNION아이디',
                               `USERNAME` varchar(45) DEFAULT NULL COMMENT '아이디(로그인용)',
                               `PASSWORD` varchar(100) DEFAULT NULL COMMENT '비밀번호',
                               `MOBILE_NO` varchar(45) DEFAULT NULL COMMENT '전화번호',
                               `NICKNAME` varchar(45) DEFAULT NULL,
                               `GRADE` varchar(45) DEFAULT NULL,
                               `CUSTOMER_TYPE` varchar(10) DEFAULT NULL,
                               `REALNAME` varchar(45) DEFAULT NULL,
                               `BIRTHDAY` varchar(45) DEFAULT NULL,
                               `SEX` varchar(2) DEFAULT NULL,
                               `EMAIL` varchar(100) DEFAULT NULL,
                               `COUNTRY` varchar(45) DEFAULT NULL,
                               `PROVINCE` varchar(45) DEFAULT NULL,
                               `CITY` varchar(45) DEFAULT NULL,
                               `ADDRESS` varchar(100) DEFAULT NULL,
                               `STATUS` varchar(10) DEFAULT NULL,
                               `WECHAT_ID` varchar(45) DEFAULT NULL,
                               `OPTION01` varchar(45) DEFAULT NULL,
                               `OPTION02` varchar(45) DEFAULT NULL,
                               `OPTION03` varchar(45) DEFAULT NULL,
                               `OPTION04` varchar(45) DEFAULT NULL,
                               `OPTION05` varchar(45) DEFAULT NULL,
                               `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
                               `UPDATE_TIME` datetime DEFAULT NULL,
                               PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='고객테이블 - 클라이언트 고객정보  ';

-- 매장정보테이블
CREATE TABLE `tb_shop` (
                           `SHOP_ID` int NOT NULL AUTO_INCREMENT,
                           `SHOP_NAME` varchar(45) DEFAULT NULL,
                           `SHOP_INTRO` varchar(200) DEFAULT NULL,
                           `MANAGER_NAME` varchar(45) DEFAULT NULL,
                           `MANAGER_MOBILE` varchar(45) DEFAULT NULL,
                           `SHOP_MOBILE` varchar(45) DEFAULT NULL,
                           `SHOP_STATUS` varchar(2) DEFAULT NULL,
                           `SHOP_PROVINCE` varchar(45) DEFAULT NULL,
                           `SHOP_CITY` varchar(45) DEFAULT NULL,
                           `SHOP_DISTRICT` varchar(45) DEFAULT NULL,
                           `SHOP_ADDRESS` varchar(100) DEFAULT NULL,
                           `SHOP_IMAGE` varchar(45) DEFAULT NULL,
                           `OPTION01` varchar(45) DEFAULT NULL,
                           `OPTION02` varchar(45) DEFAULT NULL,
                           `OPTION03` varchar(45) DEFAULT NULL,
                           `OPTION04` varchar(45) DEFAULT NULL,
                           `OPTION05` varchar(45) DEFAULT NULL,
                           `CREATE_TIME` datetime DEFAULT NULL,
                           `UPDATE_TIME` datetime DEFAULT NULL,
                           PRIMARY KEY (`SHOP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='매장정보테이블';

--방/타석정보
CREATE TABLE `tb_room` (
                           `ROOM_ID` int NOT NULL AUTO_INCREMENT,
                           `SHOP_ID` int NOT NULL,
                           `ROOM_TYPE` varchar(2) DEFAULT NULL,
                           `ROOM_NAME` varchar(45) DEFAULT NULL,
                           `ROOM_THUMBNAIL` varchar(200) DEFAULT NULL,
                           `ROOM_INTRO` varchar(1000) DEFAULT NULL,
                           `ROOM_CODE` varchar(45) DEFAULT NULL,
                           `ROOM_CAPACITY` varchar(45) DEFAULT NULL,
                           `ROOM_STATUS` varchar(2) DEFAULT NULL,
                           `OPTION01` varchar(45) DEFAULT NULL,
                           `OPTION02` varchar(45) DEFAULT NULL,
                           `OPTION03` varchar(45) DEFAULT NULL,
                           `OPTION04` varchar(45) DEFAULT NULL,
                           `OPTION05` varchar(45) DEFAULT NULL,
                           `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
                           `UPDATE_TIME` datetime DEFAULT NULL,
                           PRIMARY KEY (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='방/타석정보';

--예약정보

CREATE TABLE `tb_booking` (
                              `BOOKING_ID` bigint NOT NULL AUTO_INCREMENT,
                              `BOOKING_ORDER_NO` varchar(50) DEFAULT NULL,
                              `BOOKING_ORDER_STATUS` varchar(20) DEFAULT NULL,
                              `BOOKING_TYPE` varchar(2) DEFAULT NULL,
                              `SHOP_ID` int DEFAULT NULL,
                              `ROOM_ID` int DEFAULT NULL,
                              `CUSTOMER_ID` int DEFAULT NULL,
                              `BOOKING_PERSON_COUNT` varchar(45) DEFAULT NULL,
                              `BOOKING_STARTDAY` varchar(10) DEFAULT NULL,
                              `BOOKING_ENDDAY` varchar(10) DEFAULT NULL,
                              `BOOKING_STARTTIME` varchar(45) DEFAULT NULL,
                              `BOOKING_ENDTIME` varchar(45) DEFAULT NULL,
                              `BOOKING_STATUS` varchar(2) DEFAULT NULL,
                              `BOOKING_MOBILE` varchar(45) DEFAULT NULL,
                              `BOOKING_PERSON_NAME` varchar(45) DEFAULT NULL,
                              `PAY_STATUS` varchar(2) DEFAULT NULL,
                              `REMARK` varchar(100) DEFAULT NULL,
                              `COMMENT` varchar(100) DEFAULT NULL,
                              `CHANGE_USER_ID` int DEFAULT NULL,
                              `CHANGE_TIME` datetime DEFAULT NULL,
                              `CUSTOMER_CHANGE_TIME` datetime DEFAULT NULL,
                              `BOOKING_TIME` datetime DEFAULT NULL,
                              `OPTION01` varchar(45) DEFAULT NULL,
                              `OPTION02` varchar(45) DEFAULT NULL,
                              `OPTION03` varchar(45) DEFAULT NULL,
                              `OPTION04` varchar(45) DEFAULT NULL,
                              `OPTION05` varchar(45) DEFAULT NULL,
                              `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
                              `UPDATE_TIME` datetime DEFAULT NULL,
                              PRIMARY KEY (`BOOKING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='예약정보(메인)';

--포스트 테이블
CREATE TABLE `tb_post` (
                           `POST_ID` int NOT NULL AUTO_INCREMENT,
                           `POST_TYPE` varchar(10) NOT NULL,
                           `POST_CATEGORY_ID` int DEFAULT NULL,
                           `POST_TITLE` varchar(100) DEFAULT NULL,
                           `POST_CONTENT` varchar(2000) DEFAULT NULL,
                           `EVENT_START_TIME` varchar(45) DEFAULT NULL,
                           `EVENT_END_TIME` varchar(45) DEFAULT NULL,
                           `POST_THUMBNAIL_SMALL` varchar(100) DEFAULT NULL,
                           `POST_THUMBNAIL_BIG` varchar(100) DEFAULT NULL,
                           `POST_AUTHOR` varchar(45) DEFAULT NULL,
                           `IS_JOIN` varchar(10) DEFAULT NULL COMMENT '참여여부(0:불필요, 1: 필요), 회원이 참여하는 포스트 , 예: 레슨',
                           `IS_NEED_PAY` varchar(10) DEFAULT NULL COMMENT '지불이 필요한 포스트인가?',
                           `POST_PRICE` decimal(20,2) DEFAULT NULL,
                           `STATUS` varchar(10) DEFAULT NULL,
                           `OPTION01` varchar(45) DEFAULT NULL,
                           `OPTION02` varchar(45) DEFAULT NULL,
                           `OPTION03` varchar(45) DEFAULT NULL,
                           `OPTION04` varchar(45) DEFAULT NULL,
                           `OPTION05` varchar(45) DEFAULT NULL,
                           `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
                           `UPDATE_TIME` datetime DEFAULT NULL,
                           PRIMARY KEY (`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
;

--포스트 태그 테이블
CREATE TABLE `tb_post_tag` (
                           `TAG_ID` int NOT NULL AUTO_INCREMENT,
                           `POST_ID` int NOT NULL,
                           `TAG_NAME` varchar(100) DEFAULT NULL,
                           PRIMARY KEY (`TAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='포스트 태그 테이블';


--포스트 - 참여고객 테이블
CREATE TABLE `tb_post_customer` (
                                    `POST_CUSTOMER_ID` int NOT NULL AUTO_INCREMENT,
                                    `POST_ID` int DEFAULT NULL,
                                    `CUSTOMER_ID` int DEFAULT NULL,
                                    `PAID_YN` varchar(10) DEFAULT NULL COMMENT '포스트테이블',
                                    `JOINED_YN` varchar(10) DEFAULT NULL,
                                    `JOINED_TIME` datetime DEFAULT NULL,
                                    `PC_ORDER_NO` varchar(50) DEFAULT NULL,
                                    `OPTION01` varchar(45) DEFAULT NULL,
                                    `OPTION02` varchar(45) DEFAULT NULL,
                                    `OPTION03` varchar(45) DEFAULT NULL,
                                    `OPTION04` varchar(45) DEFAULT NULL,
                                    `OPTION05` varchar(45) DEFAULT NULL,
                                    `CREATE_TIME` datetime DEFAULT NULL,
                                    `UPDATE_TIME` datetime DEFAULT NULL,
                                    PRIMARY KEY (`POST_CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- TB_BASE_DATE
CREATE TABLE `tb_base_date` (
                                `BASE_YMD` varchar(10) NOT NULL,
                                `BASE_YYYY` varchar(10) DEFAULT NULL,
                                `BASE_MM` varchar(45) DEFAULT NULL,
                                `BASE_DD` varchar(45) DEFAULT NULL,
                                `BASE_WEEKDAY` varchar(45) DEFAULT NULL,
                                `BASE_WEEKDAY_NAME` varchar(45) DEFAULT NULL,
                                `BASE_WW` varchar(45) DEFAULT NULL,
                                `LUNAR_YMD` varchar(45) DEFAULT NULL,
                                `HOLIDAY_YN` varchar(45) DEFAULT NULL,
                                `HOLIDAY_NAME` varchar(45) DEFAULT NULL,
                                `USE_YN` varchar(45) DEFAULT NULL,
                                `MEMO` varchar(45) DEFAULT NULL,
                                `OPTION01` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'TB_BASE_DATE',
                                `OPTION02` varchar(45) DEFAULT NULL,
                                `OPTION03` varchar(45) DEFAULT NULL,
                                `OPTION04` varchar(45) DEFAULT NULL,
                                `OPTION05` varchar(45) DEFAULT NULL,
                                PRIMARY KEY (`BASE_YMD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- TB_SPU
CREATE TABLE `tb_spu` (
                          `SPU_ID` int NOT NULL AUTO_INCREMENT,
                          `CLASSIFICATION_ID` int DEFAULT NULL,
                          `DEPTH01_ID` int DEFAULT NULL,
                          `DEPTH01_NAME` varchar(45) DEFAULT NULL,
                          `DEPTH02_ID` int DEFAULT NULL,
                          `DEPTH02_NAME` varchar(45) DEFAULT NULL,
                          `DEPTH03_ID` int DEFAULT NULL,
                          `DEPTH03_NAME` varchar(45) DEFAULT NULL,
                          `BRAND_ID` int DEFAULT NULL,
                          `SPU_NAME` varchar(100) DEFAULT NULL,
                          `SPU_TYPE` varchar(45) DEFAULT NULL,
                          `STATUS` varchar(2) DEFAULT NULL,
                          `MAIN_IMAGE_01` varchar(100) DEFAULT NULL,
                          `MAIN_IMAGE_02` varchar(100) DEFAULT NULL,
                          `MAIN_IMAGE_03` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_01` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_02` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_03` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_04` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_05` varchar(100) DEFAULT NULL,
                          `OPTION01` varchar(45) DEFAULT NULL,
                          `OPTION02` varchar(45) DEFAULT NULL,
                          `OPTION03` varchar(45) DEFAULT NULL,
                          `OPTION04` varchar(45) DEFAULT NULL,
                          `OPTION05` varchar(45) DEFAULT NULL,
                          `CREATE_TIME` datetime DEFAULT NULL,
                          `UPDATE_TIME` datetime DEFAULT NULL,
                          PRIMARY KEY (`SPU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--TB_SPU_ATTRIBUTE
CREATE TABLE `tb_spu_attribute` (
                                    `SPU_ATTR_ID` int NOT NULL AUTO_INCREMENT,
                                    `SPU_ID` int NOT NULL,
                                    `ATTR_ID` int NOT NULL,
                                    `STATUS` varchar(2) DEFAULT NULL,
                                    `OPTION01` varchar(45) DEFAULT NULL,
                                    `OPTION02` varchar(45) DEFAULT NULL,
                                    `OPTION03` varchar(45) DEFAULT NULL,
                                    `OPTION04` varchar(45) DEFAULT NULL,
                                    `OPTION05` varchar(45) DEFAULT NULL,
                                    `CREATE_TIME` datetime DEFAULT NULL,
                                    `UPDATE_TIME` datetime DEFAULT NULL,
                                    PRIMARY KEY (`SPU_ATTR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--TB_SKU
CREATE TABLE `tb_sku` (
                          `SKU_ID` int NOT NULL AUTO_INCREMENT,
                          `SPU_ID` int NOT NULL,
                          `SKU_NAME` varchar(100) DEFAULT NULL,
                          `SKU_INTRO` varchar(1000) DEFAULT NULL,
                          `SKU_PRICE` decimal(20,2) DEFAULT NULL,
                          `SKU_CODE` varchar(50) DEFAULT NULL COMMENT 'SKU 编码',
                          `SKU_BARCODE` varchar(100) DEFAULT NULL,
                          `SKU_STOCK` int DEFAULT NULL,
                          `SKU_UNIT` int DEFAULT NULL,
                          `SKU_UNIT_NAME` varchar(10) DEFAULT NULL,
                          `PRICE` decimal(20,2) DEFAULT NULL,
                          `VIP_PRICE` decimal(20,2) DEFAULT NULL,
                          `STATUS` varchar(10) DEFAULT NULL,
                          `MAIN_IMAGE_01` varchar(100) DEFAULT NULL,
                          `MAIN_IMAGE_02` varchar(100) DEFAULT NULL,
                          `MAIN_IMAGE_03` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_01` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_02` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_03` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_04` varchar(100) DEFAULT NULL,
                          `SUB_IMAGE_05` varchar(100) DEFAULT NULL,
                          `OPTION01` varchar(45) DEFAULT NULL,
                          `OPTION02` varchar(45) DEFAULT NULL,
                          `OPTION03` varchar(45) DEFAULT NULL,
                          `OPTION04` varchar(45) DEFAULT NULL,
                          `OPTION05` varchar(45) DEFAULT NULL,
                          `CREATE_TIME` datetime DEFAULT NULL,
                          `UPDATE_TIME` datetime DEFAULT NULL,
                          PRIMARY KEY (`SKU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



--TB_SKU_ATTRIBUTE

CREATE TABLE `tb_sku_attribute` (
                                    `SKU_ATTR_ID` int NOT NULL AUTO_INCREMENT,
                                    `SKU_ID` int NOT NULL,
                                    `ATTR_ID` int DEFAULT NULL,
                                    `ATTR_VALUE_ID` int NOT NULL,
                                    `IS_MUST` varchar(2) DEFAULT NULL,
                                    `IS_DEFAULT` varchar(2) DEFAULT NULL,
                                    `PRICE_PLUS` decimal(20,2) DEFAULT NULL,
                                    `STATUS` varchar(10) DEFAULT NULL,
                                    `OPTION01` varchar(45) DEFAULT NULL,
                                    `OPTION02` varchar(45) DEFAULT NULL,
                                    `OPTION03` varchar(45) DEFAULT NULL,
                                    `OPTION04` varchar(45) DEFAULT NULL,
                                    `OPTION05` varchar(45) DEFAULT NULL,
                                    `CREATE_TIME` datetime DEFAULT NULL,
                                    `UPDATE_TIME` datetime DEFAULT NULL,
                                    PRIMARY KEY (`SKU_ATTR_ID`),
                                    KEY `tb_sku_attribute_attr_id` (`ATTR_ID` ASC),
                                    KEY `tb_sku_attribute_attr_value_id` (`ATTR_VALUE_ID` ASC)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--TB_ATTRIBUTE
CREATE TABLE `tb_attribute` (
                                `ATTR_ID` int NOT NULL AUTO_INCREMENT,
                                `ATTR_NAME` varchar(45) DEFAULT NULL,
                                `ATTR_TYPE` varchar(45) DEFAULT NULL,
                                `STATUS` varchar(2) DEFAULT NULL,
                                `ATTR_IMAGE` varchar(100) DEFAULT NULL,
                                `OPTION01` varchar(45) DEFAULT NULL,
                                `OPTION02` varchar(45) DEFAULT NULL,
                                `OPTION03` varchar(45) DEFAULT NULL,
                                `OPTION04` varchar(45) DEFAULT NULL,
                                `OPTION05` varchar(45) DEFAULT NULL,
                                `CREATE_TIME` datetime DEFAULT NULL,
                                `UPDATE_TIME` datetime DEFAULT NULL,
                                PRIMARY KEY (`ATTR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--TB_ATTRIBUTE_VALUE
CREATE TABLE `tb_attribute_value` (
                                      `ATTR_VALUE_ID` int NOT NULL AUTO_INCREMENT,
                                      `ATTR_ID` int NOT NULL,
                                      `ATTR_VALUE` varchar(45) DEFAULT NULL,
                                      `PRICE` decimal(20,2) DEFAULT NULL,
                                      `VIP_PRICE` decimal(20,2) DEFAULT NULL,
                                      `DELIVERY_PRICE` decimal(20,2) DEFAULT NULL,
                                      `DELIVERY_TYPE` varchar(45) DEFAULT NULL,
                                      `STATUS` varchar(10) DEFAULT NULL,
                                      `ATTR_VALUE_IMAGE` varchar(100) DEFAULT NULL,
                                      `OPTION01` varchar(45) DEFAULT NULL,
                                      `OPTION02` varchar(45) DEFAULT NULL,
                                      `OPTION03` varchar(45) DEFAULT NULL,
                                      `OPTION04` varchar(45) DEFAULT NULL,
                                      `OPTION05` varchar(45) DEFAULT NULL,
                                      `CREATE_TIME` datetime DEFAULT NULL,
                                      `UPDATE_TIME` datetime DEFAULT NULL,
                                      PRIMARY KEY (`ATTR_VALUE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- TB_CLASSIFICATION
CREATE TABLE IF NOT EXISTS `TB_CLASSIFICATION` (
                                                `CLASSIFICATION_ID` INT NOT NULL AUTO_INCREMENT,
                                                `PARENT_CLASSIFICATION_ID` INT NULL,
                                                `CLASSIFICATION_NAME` VARCHAR(100) NULL,
                                                `CLASSIFICATION_TYPE` VARCHAR(45) NULL,
                                                `CLASSIFICATION_IMAGE` VARCHAR(100) NULL,
                                                `SORT_ORDER` INT NULL,
                                                `DEPTH_NUM` INT NULL,
                                                `STATUS` VARCHAR(10) NULL,
                                                `OPTION01` VARCHAR(45) NULL,
                                                `OPTION02` VARCHAR(45) NULL,
                                                `OPTION03` VARCHAR(45) NULL,
                                                `OPTION04` VARCHAR(45) NULL,
                                                `OPTION05` VARCHAR(45) NULL,
                                                `CREATE_TIME` DATETIME NULL,
                                                `UPDATE_TIME` DATETIME NULL,
                                                PRIMARY KEY (`CLASSIFICATION_ID`)
)   ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- InnoDB-> 提供事务
CREATE TABLE TB_SECKILL (
`SECKILL_ID` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
`SKU_ID` INT NOT NULL COMMENT '商品ID',
`SKU_NAME` VARCHAR(120) NOT NULL COMMENT '商品名称',
`QTY` INT NOT NULL COMMENT '库存数量',
`SECKILL_START_TIME` TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
`SECKILL_END_TIME` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
`STATUS` VARCHAR(10) NULL,
`OPTION01` VARCHAR(45) NULL,
`OPTION02` VARCHAR(45) NULL,
`OPTION03` VARCHAR(45) NULL,
`OPTION04` VARCHAR(45) NULL,
`OPTION05` VARCHAR(45) NULL,
`CREATE_TIME` DATETIME NULL DEFAULT NOW() COMMENT '数据创建时间',
`UPDATE_TIME` DATETIME NULL COMMENT '数据更新时间',
PRIMARY KEY (`SECKILL_ID`),
KEY IDX_START_TIME(`SECKILL_START_TIME`),
KEY IDX_END_TIME(`SECKILL_END_TIME`),
KEY IDX_CREATE_TIME(`CREATE_TIME`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='秒杀库存表';

-- 初始化数据
INSERT INTO TB_SECKILL (SKU_ID, SKU_NAME, QTY, SECKILL_START_TIME, SECKILL_END_TIME)
VALUES  (1, '1000元秒杀iphone8', 1000, '2022-01-01 00:00:00', '2022-01-02 00:00:00'),
        (2, '500元秒杀iphone8', 500, '2022-01-01 00:00:00', '2022-01-02 00:00:00'),
        (3, '300元秒杀iphone8', 300, '2022-01-01 00:00:00', '2022-01-02 00:00:00'),
        (4, '200元秒杀iphone8', 200, '2022-01-01 00:00:00', '2022-01-02 00:00:00');

--秒杀成功明细表
--用户登录认证相关的信息
create table TB_SUCCESS_KILLED (
`SECKILL_ID` BIGINT NOT NULL COMMENT '商品库存ID',
`USER_PHONE` VARCHAR(30) NOT NULL COMMENT '用户手机',
`STATUS` TINYINT NOT NULL DEFAULT -1 COMMENT '状态表示： -1：无效， 0：成功， 1：已付款， 2：已发货',
`CREATE_TIME` DATETIME NULL DEFAULT NOW() COMMENT '数据创建时间',
`UPDATE_TIME` DATETIME NULL COMMENT '数据更新时间',
PRIMARY KEY (`SECKILL_ID`, `USER_PHONE`), /*联合主键*/
KEY IDX_CREATE_TIME(`CREATE_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='秒杀成功明细表';

-- tb_blog_cagegory
CREATE TABLE `tb_blog_category` (
                                    `CATEGORY_ID` int NOT NULL AUTO_INCREMENT,
                                    `PARENT_CATEGORY_ID` int DEFAULT NULL,
                                    `CATEGORY_NAME` varchar(100) DEFAULT NULL,
                                    `CATEGORY_TYPE` varchar(45) DEFAULT NULL,
                                    `SORT_ORDER` int DEFAULT NULL,
                                    `STATUS` varchar(10) DEFAULT NULL,
                                    `OPTION01` varchar(45) DEFAULT NULL,
                                    `OPTION02` varchar(45) DEFAULT NULL,
                                    `OPTION03` varchar(45) DEFAULT NULL,
                                    `OPTION04` varchar(45) DEFAULT NULL,
                                    `OPTION05` varchar(45) DEFAULT NULL,
                                    `CREATE_TIME` datetime DEFAULT NULL,
                                    `UPDATE_TIME` datetime DEFAULT NULL,
                                    PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--ma_city
CREATE TABLE `ma_city` (
                       `CITY_ID` int NOT NULL AUTO_INCREMENT,
                       `CITY_NAME` varchar(45) DEFAULT NULL,
                       `PROVINCE_ID` int DEFAULT NULL,
                       `CITY_NAME_PINYIN` varchar(45) DEFAULT NULL,
                       `CITY_GRADE_ID` varchar(10) DEFAULT NULL,
                       `CITY_ENAME` varchar(45) DEFAULT NULL,
                       PRIMARY KEY (`CITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=513 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='城市表';

--ma_province
CREATE TABLE `ma_province` (
                           `PROVINCE_ID` int NOT NULL AUTO_INCREMENT,
                           `PROVINCE_NAME` varchar(45) DEFAULT NULL,
                           `PROVINCE_NAME_PINYIN` varchar(45) DEFAULT NULL,
                           `PROVINCE_FULL_NAME` varchar(45) DEFAULT NULL,
                           `PROVINCE_ENAME` varchar(45) DEFAULT NULL,
                           PRIMARY KEY (`PROVINCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='省份表';

--
CREATE TABLE `tb_file_list` (
                                `FILE_ID` int NOT NULL AUTO_INCREMENT,
                                `FILE_TYPE` varchar(45) DEFAULT NULL,
                                `FILE_PURPOSE` varchar(45) DEFAULT NULL,
                                `FILE_ORIGIN_NAME` varchar(100) DEFAULT NULL,
                                `FILE_NAME` varchar(100) DEFAULT NULL,
                                `FILE_PATH` varchar(45) DEFAULT NULL,
                                `FILE_BUCKET_NAME` varchar(45) DEFAULT NULL,
                                `FILE_BUCKET_OBJECT` varchar(45) DEFAULT NULL,
                                `FILE_SIZE` varchar(45) DEFAULT NULL,
                                `FILE_EXTENTION` varchar(20) DEFAULT NULL,
                                `FILE_URL` varchar(100) DEFAULT NULL,
                                `FILE_SORT` int DEFAULT NULL,
                                `FILE_IS_MAIN` varchar(2) DEFAULT NULL,
                                `FILE_REMARK` varchar(45) DEFAULT NULL,
                                `STATUS` varchar(2) DEFAULT NULL,
                                `OPTION01` varchar(45) DEFAULT NULL,
                                `OPTION02` varchar(45) DEFAULT NULL,
                                `OPTION03` varchar(45) DEFAULT NULL,
                                `OPTION04` varchar(45) DEFAULT NULL,
                                `OPTION05` varchar(45) DEFAULT NULL,
                                `CREATE_TIME` datetime DEFAULT NULL,
                                `UPDATE_TIME` datetime DEFAULT NULL,
                                PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;