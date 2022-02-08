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
CREATE TABLE `tb_common_code` (
                                  `CODE_ID` int NOT NULL AUTO_INCREMENT COMMENT '번호',
                                  `CODE_TYPE` varchar(45) DEFAULT NULL,
                                  `CODE_CD` varchar(45) DEFAULT NULL,
                                  `CODE_NAME` varchar(45) DEFAULT NULL,
                                  `USE_YN` varchar(2) DEFAULT NULL,
                                  `REMARK` varchar(100) DEFAULT NULL,
                                  `OPTION01` varchar(45) DEFAULT NULL,
                                  `OPTION02` varchar(45) DEFAULT NULL,
                                  `OPTION03` varchar(45) DEFAULT NULL,
                                  `OPTION04` varchar(45) DEFAULT NULL,
                                  `OPTION05` varchar(45) DEFAULT NULL,
                                  `CREATE_TIME` datetime DEFAULT NULL,
                                  `UPDATE_TIME` datetime DEFAULT NULL,
                                  PRIMARY KEY (`CODE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='공통코드테이블';

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
                           `OPTION01` varchar(45) DEFAULT NULL,
                           `OPTION02` varchar(45) DEFAULT NULL,
                           `OPTION03` varchar(45) DEFAULT NULL,
                           `OPTION04` varchar(45) DEFAULT NULL,
                           `OPTION05` varchar(45) DEFAULT NULL,
                           `CREATE_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
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
                              `BOOKING_ID` bigint NOT NULL,
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
CREATE TABLE IF NOT EXISTS `tb_post` (
                                    `POST_ID` INT NOT NULL AUTO_INCREMENT,
                                    `POST_TYPE` VARCHAR(10) NOT NULL,
                                    `POST_CATEGORY_ID` INT NULL,
                                    `POST_TITLE` VARCHAR(100) NULL,
                                    `POST_CONTENT` VARCHAR(1000) NULL,
                                    `POST_THUMBNAIL_SMALL` VARCHAR(100) NULL,
                                    `POST_THUMBNAIL_BIG` VARCHAR(100) NULL,
                                    `POST_AUTHOR` VARCHAR(45) NULL,
                                    `STATUS` VARCHAR(10) NULL,
                                    `OPTION01` VARCHAR(45) NULL,
                                    `OPTION02` VARCHAR(45) NULL,
                                    `OPTION03` VARCHAR(45) NULL,
                                    `OPTION04` VARCHAR(45) NULL,
                                    `OPTION05` VARCHAR(45) NULL,
                                    `CREATE_TIME` DATETIME NULL DEFAULT NOW(),
                                    `UPDATE_TIME` DATETIME NULL,
    PRIMARY KEY (`POST_ID`))
    ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='포스트테이블';

--포스트 태그 테이블
CREATE TABLE `tb_post_tag` (
                           `TAG_ID` int NOT NULL AUTO_INCREMENT,
                           `POST_ID` int NOT NULL,
                           `TAG_NAME` varchar(100) DEFAULT NULL,
                           PRIMARY KEY (`TAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='포스트 태그 테이블';
