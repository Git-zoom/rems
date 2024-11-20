/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : rems

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 20/11/2024 17:34:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rems_course_learning
-- ----------------------------
DROP TABLE IF EXISTS `rems_course_learning`;
CREATE TABLE `rems_course_learning`  (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `COURSE_TITLE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程学习标题',
  `COURSE_IMG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程学习图片',
  `COURSE_SM` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程学习简述',
  `COURSE_CONTENT` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程学习内容',
  `COURSE_INDEX` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程学习索引',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程学习管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rems_course_learning
-- ----------------------------
INSERT INTO `rems_course_learning` VALUES (1, '国内研学', 'cl_01.png', '执行力军魂，企业拓展训练', '为什么要全员参训《执行力军魂模式》？\r\n\r\n 一个企业永远做两件事，第一是战略，第二是执行，那什么叫执行？执行就是把我们的战略目标不折不扣的实现，让我们的团队在文化、信仰、价值观、愿景上与公司保持高度的统一，执行只有上下一致，才能保证持久稳定发展。什么是企业的执行力？方向盘是战略的话，油门就是执行。而战略是方向，执行力就是引爆器。 \r\n\r\n《执行力军魂模式》的价值：\r\n\r\n 《执行力军魂模式》为你揭秘如何通过团队执行力打造创造企业的持续发展这是一场颠覆传统训练模式的课程，采用理论与实践结合、理论与训练结合、训练与实战结合的讲训模式，直击企业要害、句句摄人心魄的真正实战、实效的执行力训练场！中国企业真正执行力的打造，必须完成让企业每一个士兵找到兵本位，实现个人在集体目标中的价值体现，从而实现团队的执行力。企业的关键是“做大”“做强”做大是由战略决定的，做强是由团队执行力决定的。 \r\n\r\n《执行力军魂模式》的目标：\r\n\r\n  1.了解执行危机的3大来源，挖出企业执行不力的根本原因；\r\n\r\n 2.系统提升成员执行思维、执行意识、执行技能和执行水平；\r\n\r\n 3.提升团队成员积极性、主动性、成就高执行、高协作团队；\r\n\r\n 4.掌握现代企业执行的核心流程，有效使用执行的工具方法。  \r\n\r\n《执行力军魂模式》解决问题：\r\n\r\n  锁定责任人，人人有责任，每一个人明确自己责任。认清成果的重要性，不折不扣达成成果。始终保持危机感，增强团队对实现目标的动力。 ', 'course01', '2023-06-01 10:00:00', '2023-06-01 10:00:00');
INSERT INTO `rems_course_learning` VALUES (2, '红色文化', 'cl_02.png', '福建红色文化', '福建省革命历史纪念馆\r\n\r\n 福建省革命历史纪念馆，座落于福州著名风景名胜区鼓山脚下，是全国爱国主义教育示范基地、全国红色旅游经典景区，也是福建省精神文明建设和改革开放的重要窗口。以下是中国建筑网给大家带来的关于福建省革命历史纪念馆的详细介绍。\r\n\r\n\r\n纪念馆占地30亩，建筑面积16000平方米。整座建筑融中西建筑风格为一体，以内广场为中心，组织起序厅、陈列厅、半景画馆、办公楼、培训中心等空间序列。馆体建筑具有福建土楼的风格。\r\n\r\n大门两旁叠落水池中耸立两组现代化艺术铜雕，分别为《曲折·崛起》，寓意着福建人民在曲折中奋进，在探索中崛起，给人以坚忍不拔、勇往直前的力感。大门横楣上镶嵌着大型花岗岩石浮雕《迈向新世纪》，浮雕由“奋斗、胜利、腾飞、辉煌”等画面组成，象征着八闽儿女艰苦创业，孜孜探索，在中国共产党领导下进行革命、建设的光辉历程和美好前景。\r\n\r\n内广场为圆形，广场直径20米，抬头上望与蓝天白云直接相通，采天人合一的意境，站在中心点上大喝一声，能听到超平常几倍的声音，这是利用了回音壁的原理。内广场一座高7米的大型铜雕《奋斗》，由星与人物组成。星为未完形，亦如山、如剑、如闪电；人物是以工农兵为主体的10位革命群像，以勇往直前的造型，刚毅坚强的神态，表现出福建人民团结奋斗，前仆后继，追求光明幸福的革命精神。\r\n\r\n序厅大型影雕《闽之魂》，高2.4米，长14米，黑色花岗岩上镌刻着福建百年风云人物和革命文物精华，气势磅礴，栩栩如生，堪称福建工艺美术经典之作。\r\n\r\n福州林则徐纪念馆简介\r\n\r\n 　林则徐纪念馆在鼓楼区澳门路16号。原名林文忠公祠，建于清光绪三十一年（1905）。1982年按原样修复，占地3000平方米，建筑面积1000平方米。屏墙左右石拱门上横额分别石刻“中宗宗衮”、“左海伟人”，屏墙内浮雕虎门销烟图。正门上横额刻“林文忠公祠”。庭置执事牌。仪门厅后石道直达御碑亭，内有光绪三 十一年立的道光三十年“圣旨”、“御赐祭”、“御制碑文”等3块青石碑。亭北为树德堂，中祀林则徐坐像。堂西为南北花厅，厅西有两层的曲尺楼，原为林家子弟读书处，楼前有草坪、鱼池、假山、花木，全馆为古典式园林建筑。今堂、厅、楼均辟为林则徐生平展室，分林则徐生平事略，林则徐与鸦片战争，林则徐与救灾、水利，放眼看世界第一人，林则徐手迹等部分。', 'course02', '2023-06-02 11:30:00', '2023-06-02 11:30:00');
INSERT INTO `rems_course_learning` VALUES (3, '广西文化', 'cl_03.png', '广西百色红色培训', '广西\r\n\r\n 红色文化是中华传统文化的有机组成部分，是“中国共产党领导人民在革命、建设、改革进程中创造的以中国化马克思主义为核心的先进文化”。在中国共产党带领人民进行革命和建设的进程中，形成了许多伟大的革命精神，留下了许多宝贵的物质载体，这些构成了“红色文化”的主要内容。红色文化既是对中华优秀传统文化和中华民族精神的丰富和发展，也是孕育形成社会主义核心价值观的根脉源泉。广西有着丰富的红色资源和浓厚的红色文化，在红色旅游资源开发、红色文化精品推出、红色宣传教育等方面都有突出的成绩，在创建红色文化传承创新区方面有很大优势。加强研究、探讨并推动红色文化发展创新，对于振兴广西文化建设，推动广西经济、政治、社会、生态文明建设等具有独特的价值功能。要大力推进红色文化体制机制创新、内容形式创新、传播手段创新，使改革创新始终成为红色文化发展的强劲动力。\r\n\r\n\r\n百色起义烈士碑园\r\n\r\n 　百色起义纪念碑园坐落在广西百色市城东路后龙山上，占地面积126亩，1996年4月被国务院批准为首批全国烈士纪念建筑物重点保护单位，1998年被自治区党委、政府确定为全区爱国主义教育基地。胡锦涛、江泽民、李鹏、朱镕基、李瑞环、吴邦国、温家宝、李长春、罗干等50多位党和国家领导人曾先后瞻仰了百色起义纪念碑。自1984年建成以来，每年接待观众达30万人（次）以上。雄伟的百色起义纪念碑高23.9米，碑体的造型是直刺苍穹的红缨大刀，高高地耸立在纪念碑园的中央，昭示着革命者顽强不屈的精神和力量，记录了80余年前那场“红旗卷起农奴戟”的光辉历史。碑座正面是邓小平同志亲笔题写的“百色起义的革命先烈永垂不朽”13个金色大字；背面碑文概述了百色起义的过程及其历史意义；两侧汉白玉浮雕由著名雕塑家叶毓山先生创作，以统战工作、农民运动、武装起义、浴血奋战、英勇牺牲、革命胜利为主题，栩栩如生地再现了百色起义的历史风云。纪念碑园主入口为灰白色花岗岩构筑的阶梯，阶梯高49米、宽11米，共314级台阶。主入口两侧各有一条上山步道，右侧有通往山顶的车道。纪念碑前是一个面积为3000多平方米的瞻仰广场。广场东西两侧矗立着两组群雕，一组刻画了红七军战士和赤卫队员紧握刀枪，投身革命洪流的场面；另一组再现了各族人民欢庆胜利的情景。园内松柏滴率，红棉怒放，碧草如茵，亭台隐现，既庄严肃穆，又幽静秀雅。从碑前广场向南远眺，百色城“一围青山一江水，半城绿树半城楼”的美景尽收眼底。百色起义纪念碑与周边的百色起义纪念馆、红七军军部旧址、红七军政治部旧址遥相呼应，既是缅怀革命先烈、进行爱国主义和革命传统教育的重要场所，也是全国著名的红色旅游风景点。', 'course03', '2023-06-03 14:45:00', '2023-06-03 14:45:00');
INSERT INTO `rems_course_learning` VALUES (4, '江西文化', 'cl_04.png', '江西红色培训', '江西省，地处中国东南偏中部长江中下游南岸，面积为16.69万平方公里，自古以来物产富饶、人文荟萃，素有“物华天宝、人杰地灵”之誉，是中国“红色旅游”的故乡。江西是毛泽东发起革命活动的重要场所。承载着中国光荣的革命历史，从安源工人运动到秋收起义，从八一南昌起义到井冈山斗争，从开创瑞金中央革命根据地到红军长征，从赣南三年游击战争到上饶集中营茅家岭，一系列重大革命活动都发生在江西。每一个景点都展现着江西独特的历史故事和历史细节，让我们在那历史长河中去感受它的深邃与沉静吧！', 'course04', '2023-06-04 09:20:00', '2023-06-04 09:20:00');
INSERT INTO `rems_course_learning` VALUES (5, '深度学习入门', 'cl_01.png', '深度学习的基本概念和神经网络。', '本课程介绍了深度学习的基础知识和神经网络的构建方法，适合对深度学习感兴趣的学员。', 'course01', '2023-06-05 16:10:00', '2023-06-05 16:10:00');

-- ----------------------------
-- Table structure for rems_message
-- ----------------------------
DROP TABLE IF EXISTS `rems_message`;
CREATE TABLE `rems_message`  (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `MSG_USERNAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言用户',
  `MSG_USER_FACE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'face.gif' COMMENT '留言用户头像',
  `MSG_CONTENT` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '留言内容：不能超过200个字',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户留言' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rems_message
-- ----------------------------
INSERT INTO `rems_message` VALUES (1, 'username1', 'face.gif', 'content_test', '2024-07-08 00:00:00', '2024-07-08 00:00:00');
INSERT INTO `rems_message` VALUES (2, 'john_doe', 'face1.gif', '这是一个测试留言，内容不超过200个字。', '2023-06-01 10:00:00', '2023-06-01 10:00:00');
INSERT INTO `rems_message` VALUES (3, 'jane_smith', 'face2.gif', '感谢你们提供的优质服务，我非常满意。', '2023-06-02 11:30:00', '2023-06-02 11:30:00');
INSERT INTO `rems_message` VALUES (4, 'alice_wong', 'face3.gif', '网站设计得很漂亮，但加载速度可以优化一下。', '2023-06-03 14:45:00', '2023-06-03 14:45:00');
INSERT INTO `rems_message` VALUES (5, 'bob_jones', 'face4.gif', '请问你们的服务支持海外用户吗？', '2023-06-04 09:20:00', '2023-06-04 09:20:00');
INSERT INTO `rems_message` VALUES (6, 'carol_king', 'face5.gif', '有些功能似乎不能正常使用，能否帮我检查一下？', '2023-06-05 16:10:00', '2023-06-05 16:10:00');
INSERT INTO `rems_message` VALUES (7, 'admin', 'face.gif', '123', '2024-07-08 00:00:00', '2024-07-08 00:00:00');
INSERT INTO `rems_message` VALUES (8, 'admin', 'face.gif', '334', '2024-07-08 00:00:00', '2024-07-08 00:00:00');
INSERT INTO `rems_message` VALUES (9, 'admin', 'face.gif', '555', '2024-07-08 00:00:00', '2024-07-08 00:00:00');
INSERT INTO `rems_message` VALUES (10, 'admin', 'face.gif', '333', '2024-07-08 00:00:00', '2024-07-08 00:00:00');
INSERT INTO `rems_message` VALUES (11, 'admin', 'face.gif', '111', '2024-07-08 12:23:41', '2024-07-08 12:23:41');
INSERT INTO `rems_message` VALUES (12, 'admin', 'face.gif', '123', '2024-07-08 12:29:12', '2024-07-08 12:29:12');

-- ----------------------------
-- Table structure for rems_popular_nav
-- ----------------------------
DROP TABLE IF EXISTS `rems_popular_nav`;
CREATE TABLE `rems_popular_nav`  (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `NAV_NUM` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '导航排名',
  `NAV_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '导航名称',
  `NAV_HEAT` int NOT NULL COMMENT '导航热度',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '热门导航' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rems_popular_nav
-- ----------------------------
INSERT INTO `rems_popular_nav` VALUES (1, '1', '首页', 0, '2023-06-01 09:00:00', '2023-06-01 09:00:00');
INSERT INTO `rems_popular_nav` VALUES (2, '2', '课程学习', 4, '2023-06-01 09:10:00', '2024-08-08 17:26:53');
INSERT INTO `rems_popular_nav` VALUES (3, '3', '热门导航', 8, '2023-06-01 09:20:00', '2024-08-08 17:27:44');
INSERT INTO `rems_popular_nav` VALUES (4, '4', '网站留言', 9, '2023-06-01 09:30:00', '2024-08-08 17:28:35');
INSERT INTO `rems_popular_nav` VALUES (5, '5', '套餐', 0, '2023-06-01 09:40:00', '2023-06-01 09:40:00');
INSERT INTO `rems_popular_nav` VALUES (6, '6', '联系我们', 0, '2024-07-09 16:59:36', '2024-07-10 17:13:26');
INSERT INTO `rems_popular_nav` VALUES (7, '7', '后台管理', 58, '2024-07-09 16:59:36', '2024-11-20 17:33:42');

-- ----------------------------
-- Table structure for rems_question
-- ----------------------------
DROP TABLE IF EXISTS `rems_question`;
CREATE TABLE `rems_question`  (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `QUES_TITLE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '问题标题',
  `QUES_AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章作者',
  `QUES_DATE` date NOT NULL COMMENT '发表日期',
  `QUES_SOURCE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章来源',
  `QUES_TXT` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章正文',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '问题查询' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rems_question
-- ----------------------------
INSERT INTO `rems_question` VALUES (1, '如何提高代码质量？', 'Alice Wang', '2023-01-10', 'Tech Blog', '在这篇文章中，我们将探讨一些提高代码质量的方法，包括代码审查、单元测试和持续集成等。', '2023-01-10 10:00:00', '2023-01-10 10:00:00');
INSERT INTO `rems_question` VALUES (2, '深度学习的未来趋势1001', 'Bob Li', '2023-02-15', 'AI Magazine1001', '深度学习正在迅速发展，这篇文章探讨了未来几年可能的趋势，包括自动机器学习和边缘计算。', '2023-02-15 14:30:00', '2024-07-08 14:21:41');
INSERT INTO `rems_question` VALUES (3, '如何进行有效的团队协作', 'Carol King', '2023-03-05', 'Management Weekly', '团队协作是项目成功的关键。在这篇文章中，我们将介绍一些有效的团队协作方法和工具。', '2023-03-05 09:45:00', '2024-07-08 14:22:51');
INSERT INTO `rems_question` VALUES (4, '大数据分析的最佳实践', 'David Chen', '2023-04-20', 'Data Science Journal', '大数据分析需要有效的方法和工具。本篇文章分享了一些最佳实践，包括数据清洗、建模和可视化。', '2023-04-20 16:20:00', '2023-04-20 16:20:00');
INSERT INTO `rems_question` VALUES (5, '人工智能伦理问题探讨', 'Eva Zhang', '2023-05-18', 'Ethics in AI', '随着人工智能的发展，伦理问题越来越受到关注。这篇文章讨论了AI发展中面临的伦理挑战及其解决方案。', '2023-05-18 11:00:00', '2023-05-18 11:00:00');

-- ----------------------------
-- Table structure for rems_user
-- ----------------------------
DROP TABLE IF EXISTS `rems_user`;
CREATE TABLE `rems_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `gender` enum('MALE','FEMALE','OTHER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'OTHER' COMMENT '性别',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `status` enum('ACTIVE','INACTIVE','PENDING') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'PENDING' COMMENT '状态',
  `role` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户角色',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rems_user
-- ----------------------------
INSERT INTO `rems_user` VALUES (1, 'admin', '系统管理员', '25447433d42cd4b51280a32c25c839a39b1ba12e04115566', 'MALE', 'face.gif', '123@123.com', '15691338444', 'ACTIVE', 'admin', '测试修改123111', '2024-07-08 00:00:00', '2024-08-29 17:14:12');
INSERT INTO `rems_user` VALUES (2, 'zhangsan', '张三', '747300c5897079135f40a252415177a4542a21ab2ae5b60a', 'MALE', 'face3.gif', '12567@123.com', '15691338333', 'ACTIVE', 'user', '测试备注123', '2023-01-15 12:34:56', '2024-08-28 12:18:54');
INSERT INTO `rems_user` VALUES (3, 'lisi', '李四', '747300c5897079135f40a252415177a4542a21ab2ae5b60a', 'FEMALE', 'face2.gif', '123@123.com', '15691338444', 'ACTIVE', 'user', '', '2023-02-10 08:22:30', '2024-08-28 15:45:49');
INSERT INTO `rems_user` VALUES (4, 'student', '学生', '747300c5897079135f40a252415177a4542a21ab2ae5b60a', 'MALE', 'face3.gif', '123@123.com', '15691338444', 'ACTIVE', 'student', NULL, '2023-03-05 14:56:45', '2023-03-05 14:56:45');
INSERT INTO `rems_user` VALUES (5, 'teacher', '老师', '747300c5897079135f40a252415177a4542a21ab2ae5b60a', 'MALE', 'face4.gif', '123@123.com', '15691338444', 'ACTIVE', 'teacher', NULL, '2023-04-01 19:15:00', '2023-04-01 19:15:00');
INSERT INTO `rems_user` VALUES (6, 'carol_king', '测试账号', '747300c5897079135f40a252415177a4542a21ab2ae5b60a', 'FEMALE', 'face5.gif', '123@123.com', '15691338444', 'ACTIVE', 'user', NULL, '2023-05-12 11:45:12', '2023-05-12 11:45:12');
INSERT INTO `rems_user` VALUES (7, 'ceshi1001', '测试1001', '747300c5897079135f40a252415177a4542a21ab2ae5b60a', 'MALE', 'face7.gif', '123@123.com', '15691338444', 'ACTIVE', 'user', '', '2024-07-08 12:29:44', '2024-08-29 12:05:49');
INSERT INTO `rems_user` VALUES (8, 'wubingge', '吴冰格', '123456', 'FEMALE', 'face7.gif', '123@123.com', '15691338444', 'PENDING', 'user', '', '2024-07-10 18:16:59', '2024-08-29 12:06:21');
INSERT INTO `rems_user` VALUES (9, 'houshanxiong', '侯山熊', '747300c5897079135f40a252415177a4542a21ab2ae5b60a', 'MALE', 'face7.gif', '123@123.com', '15691338444', 'ACTIVE', 'user', '侯山熊本熊', '2024-07-15 16:10:19', '2024-08-13 10:12:23');
INSERT INTO `rems_user` VALUES (11, 'ceshi', '测试', '747300c5897079135f40a252415177a4542a21ab2ae5b60a', 'MALE', 'face7.gif', '123@123.com', '15691338444', 'PENDING', 'user', '', '2024-07-15 17:36:24', '2024-08-29 12:11:37');

SET FOREIGN_KEY_CHECKS = 1;
