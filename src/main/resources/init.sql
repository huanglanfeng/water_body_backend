-- ============================================================
-- 水体污染智能化监管平台 - 示例数据
-- ============================================================
-- 先删除旧表并重建（使用后端代码实际使用的表名）
-- 然后插入示例数据
-- ============================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ============================================================
-- 删除旧表（按依赖关系逆序）
-- ============================================================
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS fault;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS address_data_image_info;
DROP TABLE IF EXISTS water_amount;
DROP TABLE IF EXISTS statistics;
DROP TABLE IF EXISTS level;
DROP TABLE IF EXISTS percent;
DROP TABLE IF EXISTS soluble;
DROP TABLE IF EXISTS radiation;
DROP TABLE IF EXISTS organism;
DROP TABLE IF EXISTS garbage;
DROP TABLE IF EXISTS ph;
DROP TABLE IF EXISTS mental;
DROP TABLE IF EXISTS gas;
DROP TABLE IF EXISTS element_dissolve;
DROP TABLE IF EXISTS dissolved_oxygen;
DROP TABLE IF EXISTS turbidity;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS water_data;
DROP TABLE IF EXISTS warning;
DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS device;
DROP TABLE IF EXISTS address_data;

-- ============================================================
-- 1. address_data（监测站点）
-- ============================================================
CREATE TABLE IF NOT EXISTS address_data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点名',
    address VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '位置',
    river VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '河流',
    pollution_index DECIMAL(5,2) COMMENT '污染指数',
    alert_number INT DEFAULT 0 COMMENT '预警次数',
    alert_time DATETIME COMMENT '预警时刻',
    alert_level VARCHAR(20) CHARACTER SET utf8mb4 COMMENT '预警等级',
    longitude DECIMAL(10,6) COMMENT '经度',
    latitude DECIMAL(10,6) COMMENT '纬度',
    water_quality VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '水质情况',
    garbage_number INT DEFAULT 0 COMMENT '垃圾数量',
    metal_number DECIMAL(8,4) COMMENT '重金属含量',
    organism_number DECIMAL(8,4) COMMENT '微生物含量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='监测站点';

INSERT INTO address_data (site, address, river, pollution_index, alert_number, alert_time, alert_level, longitude, latitude, water_quality, garbage_number, metal_number, organism_number) VALUES
('瑶湖', '江西省南昌市高新区瑶湖郊野公园', '瑶湖', 0.35, 3, '2026-04-10 08:30:00', '黄色预警', 116.010000, 28.680000, '良好', 12, 0.0230, 0.0150),
('青山湖', '江西省南昌市青山湖区青山湖风景区', '青山湖', 0.52, 5, '2026-04-08 14:20:00', '橙色预警', 115.950000, 28.690000, '轻度污染', 28, 0.0450, 0.0280),
('赣江', '江西省南昌市东湖区赣江大桥段', '赣江', 0.41, 4, '2026-04-09 10:15:00', '黄色预警', 115.890000, 28.680000, '良好', 18, 0.0310, 0.0190),
('艾溪湖', '江西省南昌市高新区艾溪湖湿地公园', '艾溪湖', 0.28, 2, '2026-04-12 09:00:00', '蓝色预警', 115.980000, 28.700000, '优', 8, 0.0180, 0.0120),
('抚河', '江西省南昌市西湖区抚河故道', '抚河', 0.67, 7, '2026-04-07 16:45:00', '红色预警', 115.940000, 28.630000, '中度污染', 35, 0.0580, 0.0380),
('鄱阳湖', '江西省南昌市新建区鄱阳湖南矶湿地', '鄱阳湖', 0.22, 1, '2026-04-11 07:00:00', '蓝色预警', 116.300000, 29.150000, '优', 5, 0.0120, 0.0090);

-- ============================================================
-- 2. device（设备）
-- ============================================================
CREATE TABLE IF NOT EXISTS device (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '传感器/摄像头',
    status VARCHAR(20) CHARACTER SET utf8mb4 DEFAULT '在线' COMMENT '在线/离线/维修',
    update_time DATETIME COMMENT '更新时间',
    site_id VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '对应站点名',
    `interval` INT DEFAULT 60 COMMENT '采集间隔(秒)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备';

INSERT INTO device (type, status, update_time, site_id, `interval`) VALUES
('温度传感器', '在线', '2026-04-15 10:00:00', '瑶湖', 60),
('pH传感器', '在线', '2026-04-15 10:00:00', '瑶湖', 60),
('高清摄像头', '在线', '2026-04-15 10:00:00', '瑶湖', 30),
('温度传感器', '在线', '2026-04-15 10:05:00', '青山湖', 60),
('pH传感器', '维修', '2026-04-14 18:30:00', '青山湖', 60),
('高清摄像头', '在线', '2026-04-15 10:05:00', '青山湖', 30),
('温度传感器', '在线', '2026-04-15 09:55:00', '赣江', 60),
('pH传感器', '在线', '2026-04-15 09:55:00', '赣江', 60),
('高清摄像头', '离线', '2026-04-13 22:10:00', '赣江', 30),
('温度传感器', '在线', '2026-04-15 10:10:00', '艾溪湖', 60),
('pH传感器', '在线', '2026-04-15 10:10:00', '艾溪湖', 60),
('高清摄像头', '在线', '2026-04-15 10:10:00', '艾溪湖', 30),
('温度传感器', '在线', '2026-04-15 09:50:00', '抚河', 60),
('pH传感器', '在线', '2026-04-15 09:50:00', '抚河', 60),
('高清摄像头', '在线', '2026-04-15 09:50:00', '抚河', 30),
('温度传感器', '在线', '2026-04-15 10:15:00', '鄱阳湖', 120),
('pH传感器', '在线', '2026-04-15 10:15:00', '鄱阳湖', 120),
('高清摄像头', '在线', '2026-04-15 10:15:00', '鄱阳湖', 60);

-- ============================================================
-- 3. user（用户）
-- ============================================================
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '账号',
    password VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '密码',
    name VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '姓名',
    gender VARCHAR(10) CHARACTER SET utf8mb4 COMMENT '性别',
    mail VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '邮箱',
    photo VARCHAR(255) CHARACTER SET utf8mb4 COMMENT '头像',
    role VARCHAR(20) CHARACTER SET utf8mb4 DEFAULT '普通用户' COMMENT '角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

INSERT INTO user (account, password, name, gender, mail, photo, role) VALUES
('admin', '123456', '系统管理员', '男', 'admin@water.com', '/images/admin.png', 'admin'),
('zhangsan', '123456', '张三', '男', 'zhangsan@qq.com', '/images/zhangsan.png', '管理员'),
('lisi', '123456', '李四', '女', 'lisi@qq.com', '/images/lisi.png', '普通用户'),
('wangwu', '123456', '王五', '男', 'wangwu@qq.com', '/images/wangwu.png', '普通用户'),
('zhaoliu', '123456', '赵六', '女', 'zhaoliu@qq.com', '/images/zhaoliu.png', '管理员');

-- ============================================================
-- 4. notice（公告）
-- ============================================================
CREATE TABLE IF NOT EXISTS notice (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '标题',
    name VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '发布人',
    content TEXT CHARACTER SET utf8mb4 COMMENT '内容',
    release_time DATETIME COMMENT '发布时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告';

INSERT INTO notice (title, name, content, release_time) VALUES
('关于加强赣江流域水质监测的通知', '系统管理员', '根据省环保厅最新要求，自2026年4月起，赣江流域各监测站点需将数据采集频率提高至每30分钟一次，确保水质数据实时准确。请各站点负责人做好设备维护工作。', '2026-04-01 09:00:00'),
('2026年第一季度水质监测报告发布', '张三', '2026年第一季度南昌市主要水体水质监测报告已发布。总体来看，鄱阳湖和艾溪湖水质保持优良，抚河部分河段需重点关注。详细报告请登录系统查看。', '2026-04-05 14:30:00'),
('青山湖pH传感器维修通知', '赵六', '青山湖站点pH传感器于2026年4月14日出现故障，已安排技术人员进行维修。预计4月17日前恢复正常使用，期间该站点pH数据将暂停更新。', '2026-04-14 18:00:00'),
('关于开展水体污染应急演练的通知', '系统管理员', '为提升水体污染应急处置能力，定于2026年4月20日在抚河监测站点开展突发水污染事件应急演练。请相关工作人员提前做好准备。', '2026-04-10 10:00:00'),
('新增鄱阳湖深层水质监测设备', '张三', '鄱阳湖监测站新增一套深层水质自动监测设备，可对水深5米、10米、15米三个层次的水质进行同步监测。设备已调试完毕，数据已接入平台。', '2026-04-12 11:00:00');

-- ============================================================
-- 5. warning（预警记录）
-- ============================================================
CREATE TABLE IF NOT EXISTS warning (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    warning VARCHAR(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '预警内容',
    value DECIMAL(10,4) COMMENT '预警值',
    time DATETIME COMMENT '预警时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预警记录';

INSERT INTO warning (site, warning, value, time) VALUES
('抚河', 'pH值异常偏低', 5.8000, '2026-04-07 16:45:00'),
('抚河', '溶解氧浓度低于标准值', 3.2000, '2026-04-07 17:00:00'),
('抚河', '浊度超标', 85.5000, '2026-04-08 08:30:00'),
('青山湖', '重金属铅含量超标', 0.0850, '2026-04-08 14:20:00'),
('青山湖', '氨氮含量偏高', 2.3000, '2026-04-08 14:35:00'),
('赣江', '总磷含量超标', 0.4200, '2026-04-09 10:15:00'),
('赣江', '高锰酸盐指数偏高', 8.6000, '2026-04-09 10:30:00'),
('赣江', '水温异常升高', 28.5000, '2026-04-09 13:00:00'),
('瑶湖', '藻类密度偏高', 0.0350, '2026-04-10 08:30:00'),
('瑶湖', '叶绿素a浓度超标', 25.8000, '2026-04-10 09:00:00'),
('瑶湖', '电导率异常', 1250.0000, '2026-04-11 07:30:00'),
('鄱阳湖', '水位持续下降', 8.5000, '2026-04-11 07:00:00'),
('抚河', '化学需氧量超标', 45.6000, '2026-04-12 09:15:00'),
('青山湖', '生化需氧量超标', 12.8000, '2026-04-13 11:20:00'),
('艾溪湖', '水温偏高预警', 27.2000, '2026-04-12 09:00:00');

-- ============================================================
-- 6. water_data（水质数据）
-- ============================================================
CREATE TABLE IF NOT EXISTS water_data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    temperature DECIMAL(5,2) COMMENT '温度',
    ph DECIMAL(4,2) COMMENT 'pH',
    amount DECIMAL(8,2) COMMENT '水量',
    dissolved_oxygen_concentration DECIMAL(6,2) COMMENT '溶解氧浓度',
    turbidity DECIMAL(8,2) COMMENT '浊度',
    site_id VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '站点名',
    t1 DECIMAL(5,2), t2 DECIMAL(5,2), t3 DECIMAL(5,2), t4 DECIMAL(5,2), t5 DECIMAL(5,2), t6 DECIMAL(5,2), t7 DECIMAL(5,2),
    a1 DECIMAL(8,2), a2 DECIMAL(8,2), a3 DECIMAL(8,2), a4 DECIMAL(8,2), a5 DECIMAL(8,2), a6 DECIMAL(8,2), a7 DECIMAL(8,2),
    p1 DECIMAL(4,2), p2 DECIMAL(4,2), p3 DECIMAL(4,2), p4 DECIMAL(4,2), p5 DECIMAL(4,2), p6 DECIMAL(4,2), p7 DECIMAL(4,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水质数据';

INSERT INTO water_data (temperature, ph, amount, dissolved_oxygen_concentration, turbidity, site_id,
    t1, t2, t3, t4, t5, t6, t7,
    a1, a2, a3, a4, a5, a6, a7,
    p1, p2, p3, p4, p5, p6, p7) VALUES
(22.50, 7.20, 520.00, 7.80, 15.30, '瑶湖',
    21.00, 21.50, 22.00, 22.30, 22.80, 23.00, 22.50,
    500.00, 510.00, 515.00, 520.00, 525.00, 518.00, 520.00,
    7.10, 7.15, 7.18, 7.20, 7.22, 7.19, 7.20),
(24.30, 6.80, 380.00, 5.60, 42.50, '青山湖',
    23.00, 23.50, 24.00, 24.20, 24.80, 25.00, 24.30,
    370.00, 375.00, 378.00, 380.00, 382.00, 379.00, 380.00,
    6.70, 6.75, 6.78, 6.80, 6.82, 6.79, 6.80),
(21.80, 7.50, 650.00, 8.20, 22.10, '赣江',
    20.50, 21.00, 21.30, 21.80, 22.00, 21.90, 21.80,
    630.00, 640.00, 645.00, 650.00, 655.00, 648.00, 650.00,
    7.40, 7.45, 7.48, 7.50, 7.52, 7.49, 7.50),
(20.60, 7.80, 450.00, 9.10, 8.50, '艾溪湖',
    19.50, 19.80, 20.00, 20.30, 20.80, 21.00, 20.60,
    440.00, 445.00, 448.00, 450.00, 452.00, 449.00, 450.00,
    7.70, 7.75, 7.78, 7.80, 7.82, 7.79, 7.80),
(25.80, 6.20, 280.00, 4.30, 68.70, '抚河',
    24.50, 25.00, 25.30, 25.80, 26.20, 26.00, 25.80,
    270.00, 275.00, 278.00, 280.00, 282.00, 279.00, 280.00,
    6.10, 6.15, 6.18, 6.20, 6.22, 6.19, 6.20),
(19.20, 8.10, 780.00, 9.80, 5.20, '鄱阳湖',
    18.00, 18.50, 18.80, 19.00, 19.50, 19.80, 19.20,
    760.00, 768.00, 772.00, 780.00, 785.00, 778.00, 780.00,
    8.00, 8.05, 8.08, 8.10, 8.12, 8.09, 8.10);

-- ============================================================
-- 7. feedback（用户反馈）
-- ============================================================
CREATE TABLE IF NOT EXISTS feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    time DATETIME COMMENT '反馈时间',
    account VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '账号',
    content TEXT CHARACTER SET utf8mb4 COMMENT '反馈内容',
    site VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '站点'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户反馈';

INSERT INTO feedback (time, account, content, site) VALUES
('2026-04-08 09:30:00', 'lisi', '抚河监测站附近发现大面积绿色藻类，建议加强巡查和水质检测频率。', '抚河'),
('2026-04-09 15:20:00', 'wangwu', '赣江大桥段水质近期明显变差，河面有油污漂浮，请尽快处理。', '赣江'),
('2026-04-10 11:00:00', 'zhangsan', '青山湖pH传感器数据异常，建议尽快安排维修，避免影响监测准确性。', '青山湖'),
('2026-04-12 16:45:00', 'zhaoliu', '艾溪湖湿地公园游客增多，建议增加垃圾清理频次，防止垃圾入湖。', '艾溪湖'),
('2026-04-14 08:15:00', 'lisi', '瑶湖水位近期持续下降，建议关注是否与上游取水有关。', '瑶湖');

-- ============================================================
-- 8. turbidity（浊度）
-- ============================================================
CREATE TABLE IF NOT EXISTS turbidity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    phos DECIMAL(8,4) COMMENT '磷',
    sulfur DECIMAL(8,4) COMMENT '硫',
    calcium DECIMAL(8,4) COMMENT '钙',
    turbidity DECIMAL(8,2) COMMENT '浊度'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='浊度';

INSERT INTO turbidity (site, phos, sulfur, calcium, turbidity) VALUES
('瑶湖', 0.1200, 0.0800, 45.2000, 15.30),
('青山湖', 0.2800, 0.1500, 52.8000, 42.50),
('赣江', 0.1800, 0.1100, 38.6000, 22.10),
('艾溪湖', 0.0800, 0.0500, 42.1000, 8.50),
('抚河', 0.4500, 0.2800, 58.3000, 68.70),
('鄱阳湖', 0.0500, 0.0300, 35.8000, 5.20);

-- ============================================================
-- 9. dissolved_oxygen（溶解氧）
-- ============================================================
CREATE TABLE IF NOT EXISTS dissolved_oxygen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sulfate DECIMAL(8,4) COMMENT '硫酸盐',
    nitrate DECIMAL(8,4) COMMENT '硝酸盐',
    permanganate DECIMAL(8,4) COMMENT '高锰酸盐',
    ph DECIMAL(4,2) COMMENT 'pH',
    conductivity DECIMAL(10,2) COMMENT '电导率',
    site_id VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '站点名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='溶解氧';

INSERT INTO dissolved_oxygen (sulfate, nitrate, permanganate, ph, conductivity, site_id) VALUES
(85.3000, 3.2000, 4.5000, 7.20, 680.00, '瑶湖'),
(120.5000, 5.8000, 6.8000, 6.80, 920.00, '青山湖'),
(95.2000, 3.8000, 5.2000, 7.50, 750.00, '赣江'),
(72.8000, 2.5000, 3.8000, 7.80, 520.00, '艾溪湖'),
(158.6000, 8.2000, 9.5000, 6.20, 1250.00, '抚河'),
(65.4000, 2.1000, 3.2000, 8.10, 450.00, '鄱阳湖');

-- ============================================================
-- 10. element_dissolve（元素检测）
-- ============================================================
CREATE TABLE IF NOT EXISTS element_dissolve (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    salt1 DECIMAL(8,4), salt2 DECIMAL(8,4), salt3 DECIMAL(8,4), salt4 DECIMAL(8,4), salt5 DECIMAL(8,4),
    organic1 DECIMAL(8,4), organic2 DECIMAL(8,4), organic3 DECIMAL(8,4), organic4 DECIMAL(8,4), organic5 DECIMAL(8,4),
    inorganic1 DECIMAL(8,4), inorganic2 DECIMAL(8,4), inorganic3 DECIMAL(8,4), inorganic4 DECIMAL(8,4), inorganic5 DECIMAL(8,4),
    metal1 DECIMAL(8,4), metal2 DECIMAL(8,4), metal3 DECIMAL(8,4), metal4 DECIMAL(8,4), metal5 DECIMAL(8,4),
    conductivity1 DECIMAL(10,2), conductivity2 DECIMAL(10,2), conductivity3 DECIMAL(10,2), conductivity4 DECIMAL(10,2), conductivity5 DECIMAL(10,2),
    dissolve1 DECIMAL(8,4), dissolve2 DECIMAL(8,4), dissolve3 DECIMAL(8,4), dissolve4 DECIMAL(8,4), dissolve5 DECIMAL(8,4), dissolve6 DECIMAL(8,4), dissolve7 DECIMAL(8,4), dissolve8 DECIMAL(8,4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='元素检测';

INSERT INTO element_dissolve (site,
    salt1, salt2, salt3, salt4, salt5,
    organic1, organic2, organic3, organic4, organic5,
    inorganic1, inorganic2, inorganic3, inorganic4, inorganic5,
    metal1, metal2, metal3, metal4, metal5,
    conductivity1, conductivity2, conductivity3, conductivity4, conductivity5,
    dissolve1, dissolve2, dissolve3, dissolve4, dissolve5, dissolve6, dissolve7, dissolve8) VALUES
('瑶湖',
    0.85, 0.92, 0.88, 0.90, 0.87,
    1.25, 1.30, 1.22, 1.28, 1.20,
    2.15, 2.20, 2.18, 2.22, 2.16,
    0.023, 0.018, 0.015, 0.020, 0.012,
    680, 685, 678, 690, 682,
    7.80, 7.75, 7.82, 7.78, 7.85, 7.80, 7.76, 7.83),
('青山湖',
    1.20, 1.35, 1.28, 1.32, 1.25,
    2.80, 2.95, 2.75, 2.88, 2.70,
    3.50, 3.65, 3.55, 3.60, 3.52,
    0.045, 0.038, 0.042, 0.035, 0.040,
    920, 935, 910, 940, 915,
    5.60, 5.55, 5.65, 5.58, 5.62, 5.50, 5.68, 5.55),
('赣江',
    0.95, 1.02, 0.98, 1.00, 0.96,
    1.50, 1.58, 1.45, 1.52, 1.48,
    2.45, 2.50, 2.42, 2.48, 2.44,
    0.031, 0.025, 0.028, 0.022, 0.026,
    750, 758, 745, 760, 748,
    8.20, 8.15, 8.25, 8.18, 8.22, 8.10, 8.28, 8.15),
('艾溪湖',
    0.72, 0.78, 0.75, 0.77, 0.73,
    1.05, 1.10, 1.02, 1.08, 1.00,
    1.85, 1.90, 1.82, 1.88, 1.84,
    0.018, 0.012, 0.015, 0.010, 0.014,
    520, 528, 515, 530, 518,
    9.10, 9.05, 9.15, 9.08, 9.12, 9.00, 9.18, 9.05),
('抚河',
    1.55, 1.68, 1.60, 1.65, 1.58,
    3.50, 3.65, 3.45, 3.58, 3.40,
    4.20, 4.35, 4.25, 4.30, 4.22,
    0.058, 0.048, 0.052, 0.045, 0.050,
    1250, 1265, 1240, 1270, 1245,
    4.30, 4.25, 4.35, 4.28, 4.32, 4.20, 4.38, 4.25),
('鄱阳湖',
    0.65, 0.70, 0.68, 0.69, 0.66,
    0.85, 0.90, 0.82, 0.88, 0.80,
    1.55, 1.60, 1.52, 1.58, 1.54,
    0.012, 0.008, 0.010, 0.007, 0.009,
    450, 455, 445, 460, 448,
    9.80, 9.75, 9.85, 9.78, 9.82, 9.70, 9.88, 9.75);

-- ============================================================
-- 11. gas（气体检测）
-- ============================================================
CREATE TABLE IF NOT EXISTS gas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site_id VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点名',
    ammonia1 DECIMAL(8,4), ammonia2 DECIMAL(8,4), ammonia3 DECIMAL(8,4), ammonia4 DECIMAL(8,4), ammonia5 DECIMAL(8,4), ammonia6 DECIMAL(8,4), ammonia7 DECIMAL(8,4),
    sulfur1 DECIMAL(8,4), sulfur2 DECIMAL(8,4), sulfur3 DECIMAL(8,4), sulfur4 DECIMAL(8,4), sulfur5 DECIMAL(8,4), sulfur6 DECIMAL(8,4), sulfur7 DECIMAL(8,4),
    hydrogen1 DECIMAL(8,4), hydrogen2 DECIMAL(8,4), hydrogen3 DECIMAL(8,4), hydrogen4 DECIMAL(8,4), hydrogen5 DECIMAL(8,4), hydrogen6 DECIMAL(8,4), hydrogen7 DECIMAL(8,4),
    nitrogen1 DECIMAL(8,4), nitrogen2 DECIMAL(8,4), nitrogen3 DECIMAL(8,4), nitrogen4 DECIMAL(8,4), nitrogen5 DECIMAL(8,4), nitrogen6 DECIMAL(8,4), nitrogen7 DECIMAL(8,4),
    carbon1 DECIMAL(8,4), carbon2 DECIMAL(8,4), carbon3 DECIMAL(8,4), carbon4 DECIMAL(8,4), carbon5 DECIMAL(8,4), carbon6 DECIMAL(8,4), carbon7 DECIMAL(8,4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='气体检测';

INSERT INTO gas (site_id,
    ammonia1, ammonia2, ammonia3, ammonia4, ammonia5, ammonia6, ammonia7,
    sulfur1, sulfur2, sulfur3, sulfur4, sulfur5, sulfur6, sulfur7,
    hydrogen1, hydrogen2, hydrogen3, hydrogen4, hydrogen5, hydrogen6, hydrogen7,
    nitrogen1, nitrogen2, nitrogen3, nitrogen4, nitrogen5, nitrogen6, nitrogen7,
    carbon1, carbon2, carbon3, carbon4, carbon5, carbon6, carbon7) VALUES
('瑶湖',
    0.15, 0.18, 0.16, 0.17, 0.14, 0.19, 0.15,
    0.08, 0.09, 0.07, 0.08, 0.10, 0.07, 0.09,
    0.02, 0.03, 0.02, 0.02, 0.03, 0.02, 0.03,
    1.20, 1.25, 1.18, 1.22, 1.28, 1.15, 1.24,
    2.50, 2.55, 2.48, 2.52, 2.58, 2.45, 2.54),
('青山湖',
    0.35, 0.40, 0.38, 0.42, 0.36, 0.44, 0.37,
    0.18, 0.22, 0.20, 0.21, 0.23, 0.19, 0.22,
    0.05, 0.06, 0.05, 0.06, 0.07, 0.04, 0.06,
    2.80, 2.95, 2.75, 2.88, 3.00, 2.70, 2.92,
    4.20, 4.35, 4.15, 4.28, 4.40, 4.10, 4.32),
('赣江',
    0.22, 0.25, 0.23, 0.24, 0.26, 0.21, 0.25,
    0.12, 0.14, 0.11, 0.13, 0.15, 0.10, 0.14,
    0.03, 0.04, 0.03, 0.03, 0.04, 0.03, 0.04,
    1.80, 1.85, 1.78, 1.82, 1.88, 1.75, 1.84,
    3.20, 3.25, 3.18, 3.22, 3.28, 3.15, 3.24),
('艾溪湖',
    0.10, 0.12, 0.11, 0.12, 0.13, 0.10, 0.12,
    0.05, 0.06, 0.05, 0.06, 0.07, 0.04, 0.06,
    0.01, 0.02, 0.01, 0.02, 0.02, 0.01, 0.02,
    0.85, 0.90, 0.82, 0.88, 0.92, 0.80, 0.90,
    1.80, 1.85, 1.78, 1.82, 1.88, 1.75, 1.84),
('抚河',
    0.55, 0.62, 0.58, 0.60, 0.65, 0.56, 0.63,
    0.32, 0.38, 0.35, 0.36, 0.40, 0.33, 0.38,
    0.08, 0.10, 0.09, 0.09, 0.11, 0.08, 0.10,
    4.50, 4.65, 4.45, 4.58, 4.70, 4.40, 4.62,
    6.80, 6.95, 6.75, 6.88, 7.00, 6.70, 6.92),
('鄱阳湖',
    0.08, 0.10, 0.09, 0.09, 0.11, 0.08, 0.10,
    0.04, 0.05, 0.04, 0.05, 0.06, 0.03, 0.05,
    0.01, 0.01, 0.01, 0.01, 0.02, 0.01, 0.01,
    0.65, 0.70, 0.62, 0.68, 0.72, 0.60, 0.70,
    1.20, 1.25, 1.18, 1.22, 1.28, 1.15, 1.24);

-- ============================================================
-- 12. mental（重金属）
-- ============================================================
CREATE TABLE IF NOT EXISTS mental (
    id INT AUTO_INCREMENT PRIMARY KEY,
    element VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '元素',
    content DECIMAL(10,6) COMMENT '含量',
    device_id INT COMMENT '设备ID',
    exception_area VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常区域',
    warning_time DATETIME COMMENT '预警时间',
    content1 DECIMAL(10,6), content2 DECIMAL(10,6), content3 DECIMAL(10,6), content4 DECIMAL(10,6), content5 DECIMAL(10,6),
    exist_content DECIMAL(10,6) COMMENT '现有含量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='重金属';

INSERT INTO mental (element, content, device_id, exception_area, warning_time, content1, content2, content3, content4, content5, exist_content) VALUES
('铅(Pb)', 0.085000, 5, '青山湖东北角排水口附近', '2026-04-08 14:20:00', 0.050, 0.060, 0.072, 0.080, 0.085, 0.082),
('镉(Cd)', 0.005200, 5, '青山湖西南角', '2026-04-08 15:30:00', 0.003, 0.003, 0.004, 0.005, 0.005, 0.005),
('汞(Hg)', 0.000150, 13, '抚河中段工业排放口', '2026-04-07 17:00:00', 0.0001, 0.0001, 0.0001, 0.0002, 0.0002, 0.0001),
('铬(Cr)', 0.035000, 8, '赣江南支桥下', '2026-04-09 11:00:00', 0.020, 0.025, 0.028, 0.032, 0.035, 0.033),
('砷(As)', 0.012000, 13, '抚河下游居民区段', '2026-04-10 08:00:00', 0.008, 0.009, 0.010, 0.011, 0.012, 0.011),
('铜(Cu)', 0.028000, 2, '瑶湖西岸农业区', '2026-04-11 09:30:00', 0.018, 0.020, 0.022, 0.025, 0.028, 0.026),
('锌(Zn)', 0.065000, 8, '赣江东岸码头区', '2026-04-12 10:00:00', 0.040, 0.048, 0.055, 0.060, 0.065, 0.062),
('镍(Ni)', 0.018000, 13, '抚河上游工厂段', '2026-04-13 14:00:00', 0.010, 0.012, 0.014, 0.016, 0.018, 0.017);

-- ============================================================
-- 13. ph（酸碱度异常）
-- ============================================================
CREATE TABLE IF NOT EXISTS ph (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ph DECIMAL(4,2) COMMENT 'pH值',
    exception VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常描述',
    exception_area VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常区域',
    warning_time DATETIME COMMENT '预警时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='酸碱度异常';

INSERT INTO ph (ph, exception, exception_area, warning_time) VALUES
(5.80, 'pH值严重偏低，疑似工业废水排放', '抚河中段工业排放口', '2026-04-07 16:45:00'),
(6.20, 'pH值偏低，水质偏酸性', '抚河下游居民区段', '2026-04-08 09:00:00'),
(8.80, 'pH值偏高，疑似碱性废水排入', '青山湖北岸施工区', '2026-04-09 13:30:00'),
(6.50, 'pH值略低，需持续关注', '赣江南支桥下', '2026-04-10 11:00:00'),
(5.50, 'pH值异常偏低，水体酸化严重', '抚河上游工厂段', '2026-04-12 08:30:00');

-- ============================================================
-- 14. garbage（垃圾堆积）
-- ============================================================
CREATE TABLE IF NOT EXISTS garbage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    device_id INT COMMENT '设备ID',
    exception VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常描述',
    exception_area VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常区域',
    warning_time DATETIME COMMENT '预警时间',
    content1 VARCHAR(200) CHARACTER SET utf8mb4, content2 VARCHAR(200) CHARACTER SET utf8mb4, content3 VARCHAR(200) CHARACTER SET utf8mb4, content4 VARCHAR(200) CHARACTER SET utf8mb4, content5 VARCHAR(200) CHARACTER SET utf8mb4,
    exist_content VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '现有情况'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='垃圾堆积';

INSERT INTO garbage (device_id, exception, exception_area, warning_time, content1, content2, content3, content4, content5, exist_content) VALUES
(6, '河面漂浮大量塑料垃圾', '青山湖东岸', '2026-04-08 10:00:00', '塑料瓶35个', '塑料袋20个', '泡沫箱5个', '废弃渔网2张', '其他垃圾15件', '塑料瓶28个、塑料袋18个、泡沫箱4个'),
(9, '河岸堆积生活垃圾', '赣江大桥下', '2026-04-09 14:00:00', '纸箱10个', '塑料桶8个', '旧衣物5袋', '食品包装袋30个', '其他12件', '纸箱8个、塑料桶6个、食品包装袋25个'),
(15, '水面发现工业废弃物', '抚河中段', '2026-04-10 09:30:00', '废弃油桶3个', '化工桶2个', '塑料管5根', '铁皮碎片若干', '编织袋8个', '废弃油桶2个、化工桶2个、塑料管4根'),
(3, '湖面有零星漂浮物', '瑶湖南岸', '2026-04-11 08:00:00', '树叶若干', '塑料袋3个', '饮料瓶2个', '纸片若干', '树枝2根', '树叶若干、塑料袋2个、饮料瓶1个'),
(18, '湿地发现游客遗留垃圾', '鄱阳湖南矶湿地', '2026-04-12 15:00:00', '食品包装袋15个', '饮料瓶8个', '纸巾若干', '塑料袋5个', '烟头若干', '食品包装袋12个、饮料瓶6个');

-- ============================================================
-- 15. organism（微生物）
-- ============================================================
CREATE TABLE IF NOT EXISTS organism (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '微生物名称',
    number DECIMAL(12,2) COMMENT '数量',
    exception_area VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常区域',
    warning_time DATETIME COMMENT '预警时间',
    content1 VARCHAR(200) CHARACTER SET utf8mb4, content2 VARCHAR(200) CHARACTER SET utf8mb4, content3 VARCHAR(200) CHARACTER SET utf8mb4, content4 VARCHAR(200) CHARACTER SET utf8mb4, content5 VARCHAR(200) CHARACTER SET utf8mb4,
    exist_content VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '现有含量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微生物';

INSERT INTO organism (name, number, exception_area, warning_time, content1, content2, content3, content4, content5, exist_content) VALUES
('大肠杆菌', 8500.00, '抚河下游居民区段', '2026-04-07 18:00:00', '8200个/L', '8350个/L', '8420个/L', '8480个/L', '8500个/L', '8450个/L'),
('蓝藻', 12000.00, '瑶湖西岸', '2026-04-10 09:00:00', '8500个/L', '9200个/L', '10500个/L', '11200个/L', '12000个/L', '11500个/L'),
('总菌群', 5600.00, '青山湖北岸', '2026-04-11 10:30:00', '4200个/L', '4500个/L', '4800个/L', '5200个/L', '5600个/L', '5400个/L'),
('隐孢子虫', 120.00, '赣江取水口', '2026-04-12 08:00:00', '85个/L', '92个/L', '100个/L', '110个/L', '120个/L', '115个/L'),
('粪大肠菌群', 15000.00, '抚河中段工业排放口', '2026-04-13 09:00:00', '12000个/L', '12800个/L', '13500个/L', '14200个/L', '15000个/L', '14500个/L');

-- ============================================================
-- 16. radiation（放射性）
-- ============================================================
CREATE TABLE IF NOT EXISTS radiation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    radioactive_element VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '放射性元素',
    exception_area VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常区域',
    warning_time DATETIME COMMENT '预警时间',
    content1 VARCHAR(200) CHARACTER SET utf8mb4, content2 VARCHAR(200) CHARACTER SET utf8mb4, content3 VARCHAR(200) CHARACTER SET utf8mb4, content4 VARCHAR(200) CHARACTER SET utf8mb4, content5 VARCHAR(200) CHARACTER SET utf8mb4,
    exist_content VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '现有含量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='放射性';

INSERT INTO radiation (radioactive_element, exception_area, warning_time, content1, content2, content3, content4, content5, exist_content) VALUES
('铀-238', '抚河上游工厂段', '2026-04-08 10:00:00', '0.05Bq/L', '0.06Bq/L', '0.08Bq/L', '0.09Bq/L', '0.10Bq/L', '0.09Bq/L'),
('镭-226', '赣江东岸码头区', '2026-04-09 11:30:00', '0.02Bq/L', '0.03Bq/L', '0.03Bq/L', '0.04Bq/L', '0.04Bq/L', '0.04Bq/L'),
('氡-222', '青山湖排水口', '2026-04-10 14:00:00', '1.5Bq/L', '2.0Bq/L', '2.8Bq/L', '3.5Bq/L', '4.0Bq/L', '3.8Bq/L'),
('钍-232', '抚河中段工业排放口', '2026-04-11 09:00:00', '0.01Bq/L', '0.02Bq/L', '0.02Bq/L', '0.03Bq/L', '0.03Bq/L', '0.03Bq/L'),
('钾-40', '鄱阳湖南矶湿地', '2026-04-12 16:00:00', '0.10Bq/L', '0.12Bq/L', '0.15Bq/L', '0.18Bq/L', '0.20Bq/L', '0.19Bq/L');

-- ============================================================
-- 17. soluble（水溶物）
-- ============================================================
CREATE TABLE IF NOT EXISTS soluble (
    id INT AUTO_INCREMENT PRIMARY KEY,
    phos DECIMAL(8,4) COMMENT '磷',
    sulfur DECIMAL(8,4) COMMENT '硫',
    nitrogen DECIMAL(8,4) COMMENT '氮',
    dissolved_oxygen DECIMAL(6,2) COMMENT '溶解氧',
    detect_time DATETIME COMMENT '检测时间',
    site VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '站点'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水溶物';

INSERT INTO soluble (phos, sulfur, nitrogen, dissolved_oxygen, detect_time, site) VALUES
(0.1200, 0.0800, 1.8500, 7.80, '2026-04-15 08:00:00', '瑶湖'),
(0.2800, 0.1500, 3.2000, 5.60, '2026-04-15 08:10:00', '青山湖'),
(0.1800, 0.1100, 2.1000, 8.20, '2026-04-15 08:20:00', '赣江'),
(0.0800, 0.0500, 1.3500, 9.10, '2026-04-15 08:30:00', '艾溪湖'),
(0.4500, 0.2800, 4.8000, 4.30, '2026-04-15 08:40:00', '抚河'),
(0.0500, 0.0300, 0.9800, 9.80, '2026-04-15 08:50:00', '鄱阳湖');

-- ============================================================
-- 18. percent（水质百分比统计）
-- ============================================================
CREATE TABLE IF NOT EXISTS percent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    excellent1 DECIMAL(5,2), excellent2 DECIMAL(5,2), excellent3 DECIMAL(5,2), excellent4 DECIMAL(5,2), excellent5 DECIMAL(5,2), excellent6 DECIMAL(5,2),
    good1 DECIMAL(5,2), good2 DECIMAL(5,2), good3 DECIMAL(5,2), good4 DECIMAL(5,2), good5 DECIMAL(5,2), good6 DECIMAL(5,2),
    pollute1 DECIMAL(5,2), pollute2 DECIMAL(5,2), pollute3 DECIMAL(5,2), pollute4 DECIMAL(5,2), pollute5 DECIMAL(5,2), pollute6 DECIMAL(5,2),
    serious1 DECIMAL(5,2), serious2 DECIMAL(5,2), serious3 DECIMAL(5,2), serious4 DECIMAL(5,2), serious5 DECIMAL(5,2), serious6 DECIMAL(5,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水质百分比统计';

INSERT INTO percent (site,
    excellent1, excellent2, excellent3, excellent4, excellent5, excellent6,
    good1, good2, good3, good4, good5, good6,
    pollute1, pollute2, pollute3, pollute4, pollute5, pollute6,
    serious1, serious2, serious3, serious4, serious5, serious6) VALUES
('瑶湖',
    45.00, 48.00, 50.00, 52.00, 55.00, 53.00,
    35.00, 33.00, 30.00, 28.00, 25.00, 27.00,
    15.00, 14.00, 13.00, 14.00, 13.00, 14.00,
    5.00, 5.00, 7.00, 6.00, 7.00, 6.00),
('青山湖',
    20.00, 22.00, 18.00, 15.00, 12.00, 10.00,
    30.00, 28.00, 25.00, 22.00, 20.00, 18.00,
    35.00, 33.00, 38.00, 40.00, 42.00, 45.00,
    15.00, 17.00, 19.00, 23.00, 26.00, 27.00),
('赣江',
    35.00, 38.00, 40.00, 42.00, 45.00, 43.00,
    40.00, 38.00, 35.00, 33.00, 30.00, 32.00,
    18.00, 17.00, 16.00, 17.00, 16.00, 17.00,
    7.00, 7.00, 9.00, 8.00, 9.00, 8.00),
('艾溪湖',
    55.00, 58.00, 60.00, 62.00, 65.00, 63.00,
    30.00, 28.00, 25.00, 23.00, 20.00, 22.00,
    10.00, 9.00, 10.00, 10.00, 10.00, 10.00,
    5.00, 5.00, 5.00, 5.00, 5.00, 5.00),
('抚河',
    10.00, 8.00, 5.00, 3.00, 2.00, 2.00,
    20.00, 18.00, 15.00, 12.00, 10.00, 8.00,
    40.00, 38.00, 42.00, 45.00, 48.00, 50.00,
    30.00, 36.00, 38.00, 40.00, 40.00, 40.00),
('鄱阳湖',
    65.00, 68.00, 70.00, 72.00, 75.00, 73.00,
    25.00, 23.00, 20.00, 18.00, 15.00, 17.00,
    7.00, 6.00, 6.00, 6.00, 6.00, 6.00,
    3.00, 3.00, 4.00, 4.00, 4.00, 4.00);

-- ============================================================
-- 19. level（水位周数据）
-- ============================================================
CREATE TABLE IF NOT EXISTS level (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    level1 DECIMAL(6,2), level2 DECIMAL(6,2), level3 DECIMAL(6,2), level4 DECIMAL(6,2), level5 DECIMAL(6,2), level6 DECIMAL(6,2), level7 DECIMAL(6,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水位周数据';

INSERT INTO level (site, level1, level2, level3, level4, level5, level6, level7) VALUES
('瑶湖', 15.20, 15.15, 15.10, 15.05, 15.00, 14.95, 14.90),
('青山湖', 12.80, 12.75, 12.70, 12.65, 12.60, 12.55, 12.50),
('赣江', 18.50, 18.45, 18.40, 18.35, 18.30, 18.25, 18.20),
('艾溪湖', 14.60, 14.55, 14.50, 14.48, 14.45, 14.42, 14.40),
('抚河', 8.50, 8.45, 8.40, 8.35, 8.30, 8.25, 8.20),
('鄱阳湖', 12.30, 12.25, 12.20, 12.15, 12.10, 12.05, 12.00);

-- ============================================================
-- 20. statistics（水质参数统计）
-- ============================================================
CREATE TABLE IF NOT EXISTS statistics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site_id VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '站点名',
    water_quality_parameters TEXT CHARACTER SET utf8mb4 COMMENT '水质参数JSON'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水质参数统计';

INSERT INTO statistics (site_id, water_quality_parameters) VALUES
('瑶湖', '{"temperature":22.5,"ph":7.2,"dissolved_oxygen":7.8,"turbidity":15.3,"conductivity":680,"permanganate":4.5,"ammonia_nitrogen":0.35,"total_phosphorus":0.12,"total_nitrogen":1.85}'),
('青山湖', '{"temperature":24.3,"ph":6.8,"dissolved_oxygen":5.6,"turbidity":42.5,"conductivity":920,"permanganate":6.8,"ammonia_nitrogen":0.85,"total_phosphorus":0.28,"total_nitrogen":3.20}'),
('赣江', '{"temperature":21.8,"ph":7.5,"dissolved_oxygen":8.2,"turbidity":22.1,"conductivity":750,"permanganate":5.2,"ammonia_nitrogen":0.45,"total_phosphorus":0.18,"total_nitrogen":2.10}'),
('艾溪湖', '{"temperature":20.6,"ph":7.8,"dissolved_oxygen":9.1,"turbidity":8.5,"conductivity":520,"permanganate":3.8,"ammonia_nitrogen":0.22,"total_phosphorus":0.08,"total_nitrogen":1.35}'),
('抚河', '{"temperature":25.8,"ph":6.2,"dissolved_oxygen":4.3,"turbidity":68.7,"conductivity":1250,"permanganate":9.5,"ammonia_nitrogen":1.65,"total_phosphorus":0.45,"total_nitrogen":4.80}'),
('鄱阳湖', '{"temperature":19.2,"ph":8.1,"dissolved_oxygen":9.8,"turbidity":5.2,"conductivity":450,"permanganate":3.2,"ammonia_nitrogen":0.15,"total_phosphorus":0.05,"total_nitrogen":0.98}');

-- ============================================================
-- 21. water_amount（水量）
-- ============================================================
CREATE TABLE IF NOT EXISTS water_amount (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    amount DECIMAL(10,2) COMMENT '水量(万立方米)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水量';

INSERT INTO water_amount (site, amount) VALUES
('瑶湖', 5200.00),
('青山湖', 3800.00),
('赣江', 65000.00),
('艾溪湖', 4500.00),
('抚河', 2800.00),
('鄱阳湖', 780000.00);

-- ============================================================
-- 22. address_data_image_info（站点预警详情）
-- ============================================================
CREATE TABLE IF NOT EXISTS address_data_image_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    time DATETIME COMMENT '时间',
    alter_level VARCHAR(20) CHARACTER SET utf8mb4 COMMENT '预警等级',
    pollutant VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '污染物',
    people VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '负责人',
    content TEXT CHARACTER SET utf8mb4 COMMENT '详情'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点预警详情';

INSERT INTO address_data_image_info (site, time, alter_level, pollutant, people, content) VALUES
('抚河', '2026-04-07 16:45:00', '红色预警', '工业废水排放', '张三', '抚河中段工业排放口检测到pH值严重偏低至5.8，疑似附近化工厂违规排放酸性废水。已通知环保执法部门前往调查，同时启动应急监测方案，每15分钟采集一次水样。'),
('青山湖', '2026-04-08 14:20:00', '橙色预警', '重金属铅超标', '赵六', '青山湖东北角排水口附近检测到重金属铅含量达到0.085mg/L，超过地表水III类标准。已对周边排水管网进行排查，发现一处老旧管道渗漏，正在紧急修复中。'),
('赣江', '2026-04-09 10:15:00', '黄色预警', '总磷超标', '张三', '赣江大桥段总磷含量达到0.42mg/L，超过标准限值。初步分析可能与上游农业面源污染有关，已协调农业部门加强沿河农业施肥管理。'),
('瑶湖', '2026-04-10 08:30:00', '黄色预警', '藻类密度偏高', '赵六', '瑶湖西岸检测到藻类密度异常偏高，叶绿素a浓度达到25.8ug/L。可能与近期气温升高和农业面源污染有关，已安排增加人工巡查频次，必要时进行生态调水。'),
('鄱阳湖', '2026-04-11 07:00:00', '蓝色预警', '水位下降', '张三', '鄱阳湖水位持续下降至12.0米，较历史同期偏低1.5米。已启动低水位应急预案，加强沿湖取水口监测，确保周边居民饮水安全。');

-- ============================================================
-- 23. news（新闻）
-- ============================================================
CREATE TABLE IF NOT EXISTS news (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '标题',
    content TEXT CHARACTER SET utf8mb4 COMMENT '内容',
    photos VARCHAR(500) CHARACTER SET utf8mb4 COMMENT '图片',
    time DATETIME COMMENT '发布时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻';

INSERT INTO news (title, content, photos, time) VALUES
('南昌市开展2026年春季水环境专项整治行动', '为深入贯彻习近平生态文明思想，全面落实长江大保护战略，南昌市决定自2026年3月起开展为期三个月的水环境专项整治行动。本次行动重点对赣江、抚河、鄱阳湖等主要水体进行全面排查和治理，严厉打击各类水环境违法行为。', '/images/news1.jpg,/images/news1_2.jpg', '2026-03-15 09:00:00'),
('瑶湖水质连续三年保持优良，获省级表彰', '江西省生态环境厅发布2025年度全省水质状况通报，南昌市瑶湖水质连续三年稳定达到地表水II类标准，荣获"江西省水质保护示范湖"称号。瑶湖通过实施生态修复、截污纳管、退渔还湖等综合措施，水质持续改善。', '/images/news2.jpg', '2026-03-22 10:00:00'),
('智能水质监测系统在南昌全面投入使用', '南昌市水务局宣布，覆盖全市主要水体的智能水质监测系统已全面建成并投入使用。该系统包含68个自动监测站点，可实现对水温、pH、溶解氧、浊度等12项指标的24小时实时监测，数据每30分钟更新一次。', '/images/news3.jpg,/images/news3_2.jpg,/images/news3_3.jpg', '2026-04-01 14:00:00'),
('抚河水环境治理取得阶段性成效', '经过近半年的集中治理，抚河南昌段水环境质量明显改善。通过关停取缔沿岸"散乱污"企业28家、新建污水处理设施3座、清淤疏浚河道12公里等措施，抚河水质已由劣V类提升至IV类，部分河段达到III类标准。', '/images/news4.jpg', '2026-04-08 09:30:00'),
('鄱阳湖生态保护与修复工程启动', '为加强鄱阳湖生态环境保护，国家发改委正式批复鄱阳湖生态保护与修复工程实施方案。工程总投资约85亿元，计划用5年时间，通过湿地恢复、水生植被重建、鱼类增殖放流等措施，全面提升鄱阳湖生态系统质量和稳定性。', '/images/news5.jpg,/images/news5_2.jpg', '2026-04-12 10:00:00');

-- ============================================================
-- 24. fault（故障上报）
-- ============================================================
CREATE TABLE IF NOT EXISTS fault (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '上报人',
    phone VARCHAR(20) CHARACTER SET utf8mb4 COMMENT '联系电话',
    number VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '设备编号',
    description TEXT CHARACTER SET utf8mb4 COMMENT '故障描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='故障上报';

INSERT INTO fault (name, phone, number, description) VALUES
('张三', '13800138001', 'QC-PH-002', '青山湖站点pH传感器读数异常波动，疑似电极老化需要更换。该设备已运行超过18个月，建议更换电极并进行校准。', '赵六', '13900139001', 'GJ-CAM-003', '赣江大桥段高清摄像头自4月13日22:10起离线，检查发现电源适配器损坏，已申请更换配件，预计4月16日恢复。'),
('李四', '13700137001', 'FH-TEMP-001', '抚河站点温度传感器数据采集间隔不稳定，有时超过5分钟才更新一次数据。初步判断为通信模块故障，需要现场检修。');

-- ============================================================
-- 25. admin（管理员）
-- ============================================================
CREATE TABLE IF NOT EXISTS admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '姓名',
    role VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员';

INSERT INTO admin (name, role) VALUES
('系统管理员', '超级管理员'),
('张三', '运维管理员');

-- ============================================================
-- 确保admin用户密码正确
-- ============================================================
UPDATE user SET password = '123456' WHERE account = 'admin';

SET FOREIGN_KEY_CHECKS = 1;

-- ============================================================
-- 示例数据插入完成
-- ============================================================
