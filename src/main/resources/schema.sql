-- ============================================================
-- 水体污染智能化监管平台 - 建表语句
-- ============================================================

-- 1. address_data（监测站点）
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

-- 2. device（设备）
CREATE TABLE IF NOT EXISTS device (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '传感器/摄像头',
    status VARCHAR(20) CHARACTER SET utf8mb4 DEFAULT '在线' COMMENT '在线/离线/维修',
    update_time DATETIME COMMENT '更新时间',
    site_id VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '对应站点名',
    `interval` INT DEFAULT 60 COMMENT '采集间隔(秒)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备';

-- 3. user（用户）
CREATE TABLE IF NOT EXISTS `user` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '账号',
    password VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '密码',
    name VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '姓名',
    gender VARCHAR(10) CHARACTER SET utf8mb4 COMMENT '性别',
    mail VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '邮箱',
    photo VARCHAR(255) CHARACTER SET utf8mb4 COMMENT '头像',
    role VARCHAR(20) CHARACTER SET utf8mb4 DEFAULT '普通用户' COMMENT '角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- 4. notice（公告）
CREATE TABLE IF NOT EXISTS notice (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '标题',
    name VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '发布人',
    content TEXT CHARACTER SET utf8mb4 COMMENT '内容',
    release_time DATETIME COMMENT '发布时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告';

-- 5. warning（预警记录）
CREATE TABLE IF NOT EXISTS warning (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    warning VARCHAR(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '预警内容',
    value DECIMAL(10,4) COMMENT '预警值',
    time DATETIME COMMENT '预警时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预警记录';

-- 6. water_data（水质数据）
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

-- 7. feedback（用户反馈）
CREATE TABLE IF NOT EXISTS feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    time DATETIME COMMENT '反馈时间',
    account VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '账号',
    content TEXT CHARACTER SET utf8mb4 COMMENT '反馈内容',
    site VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '站点'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户反馈';

-- 8. turbidity（浊度）
CREATE TABLE IF NOT EXISTS turbidity (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    phos DECIMAL(8,4) COMMENT '磷',
    sulfur DECIMAL(8,4) COMMENT '硫',
    calcium DECIMAL(8,4) COMMENT '钙',
    turbidity DECIMAL(8,2) COMMENT '浊度'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='浊度';

-- 9. dissolved_oxygen（溶解氧）
CREATE TABLE IF NOT EXISTS dissolved_oxygen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sulfate DECIMAL(8,4) COMMENT '硫酸盐',
    nitrate DECIMAL(8,4) COMMENT '硝酸盐',
    permanganate DECIMAL(8,4) COMMENT '高锰酸盐',
    ph DECIMAL(4,2) COMMENT 'pH',
    conductivity DECIMAL(10,2) COMMENT '电导率',
    site_id VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '站点名'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='溶解氧';

-- 10. element_dissolve（元素检测）
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

-- 11. gas（气体检测）
CREATE TABLE IF NOT EXISTS gas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site_id VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点名',
    ammonia1 DECIMAL(8,4), ammonia2 DECIMAL(8,4), ammonia3 DECIMAL(8,4), ammonia4 DECIMAL(8,4), ammonia5 DECIMAL(8,4), ammonia6 DECIMAL(8,4), ammonia7 DECIMAL(8,4),
    sulfur1 DECIMAL(8,4), sulfur2 DECIMAL(8,4), sulfur3 DECIMAL(8,4), sulfur4 DECIMAL(8,4), sulfur5 DECIMAL(8,4), sulfur6 DECIMAL(8,4), sulfur7 DECIMAL(8,4),
    hydrogen1 DECIMAL(8,4), hydrogen2 DECIMAL(8,4), hydrogen3 DECIMAL(8,4), hydrogen4 DECIMAL(8,4), hydrogen5 DECIMAL(8,4), hydrogen6 DECIMAL(8,4), hydrogen7 DECIMAL(8,4),
    nitrogen1 DECIMAL(8,4), nitrogen2 DECIMAL(8,4), nitrogen3 DECIMAL(8,4), nitrogen4 DECIMAL(8,4), nitrogen5 DECIMAL(8,4), nitrogen6 DECIMAL(8,4), nitrogen7 DECIMAL(8,4),
    carbon1 DECIMAL(8,4), carbon2 DECIMAL(8,4), carbon3 DECIMAL(8,4), carbon4 DECIMAL(8,4), carbon5 DECIMAL(8,4), carbon6 DECIMAL(8,4), carbon7 DECIMAL(8,4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='气体检测';

-- 12. mental（重金属）
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

-- 13. ph（酸碱度异常）
CREATE TABLE IF NOT EXISTS ph (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ph DECIMAL(4,2) COMMENT 'pH值',
    exception VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常描述',
    exception_area VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常区域',
    warning_time DATETIME COMMENT '预警时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='酸碱度异常';

-- 14. garbage（垃圾堆积）
CREATE TABLE IF NOT EXISTS garbage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    device_id INT COMMENT '设备ID',
    exception VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常描述',
    exception_area VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常区域',
    warning_time DATETIME COMMENT '预警时间',
    content1 VARCHAR(200) CHARACTER SET utf8mb4, content2 VARCHAR(200) CHARACTER SET utf8mb4, content3 VARCHAR(200) CHARACTER SET utf8mb4, content4 VARCHAR(200) CHARACTER SET utf8mb4, content5 VARCHAR(200) CHARACTER SET utf8mb4,
    exist_content VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '现有情况'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='垃圾堆积';

-- 15. organism（微生物）
CREATE TABLE IF NOT EXISTS organism (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '微生物名称',
    number DECIMAL(12,2) COMMENT '数量',
    exception_area VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常区域',
    warning_time DATETIME COMMENT '预警时间',
    content1 VARCHAR(200) CHARACTER SET utf8mb4, content2 VARCHAR(200) CHARACTER SET utf8mb4, content3 VARCHAR(200) CHARACTER SET utf8mb4, content4 VARCHAR(200) CHARACTER SET utf8mb4, content5 VARCHAR(200) CHARACTER SET utf8mb4,
    exist_content VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '现有含量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微生物';

-- 16. radiation（放射性）
CREATE TABLE IF NOT EXISTS radiation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    radioactive_element VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '放射性元素',
    exception_area VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '异常区域',
    warning_time DATETIME COMMENT '预警时间',
    content1 VARCHAR(200) CHARACTER SET utf8mb4, content2 VARCHAR(200) CHARACTER SET utf8mb4, content3 VARCHAR(200) CHARACTER SET utf8mb4, content4 VARCHAR(200) CHARACTER SET utf8mb4, content5 VARCHAR(200) CHARACTER SET utf8mb4,
    exist_content VARCHAR(200) CHARACTER SET utf8mb4 COMMENT '现有含量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='放射性';

-- 17. soluble（水溶物）
CREATE TABLE IF NOT EXISTS soluble (
    id INT AUTO_INCREMENT PRIMARY KEY,
    phos DECIMAL(8,4) COMMENT '磷',
    sulfur DECIMAL(8,4) COMMENT '硫',
    nitrogen DECIMAL(8,4) COMMENT '氮',
    dissolved_oxygen DECIMAL(6,2) COMMENT '溶解氧',
    detect_time DATETIME COMMENT '检测时间',
    site VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '站点'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水溶物';

-- 18. percent（水质百分比统计）
CREATE TABLE IF NOT EXISTS percent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    excellent1 DECIMAL(5,2), excellent2 DECIMAL(5,2), excellent3 DECIMAL(5,2), excellent4 DECIMAL(5,2), excellent5 DECIMAL(5,2), excellent6 DECIMAL(5,2),
    good1 DECIMAL(5,2), good2 DECIMAL(5,2), good3 DECIMAL(5,2), good4 DECIMAL(5,2), good5 DECIMAL(5,2), good6 DECIMAL(5,2),
    pollute1 DECIMAL(5,2), pollute2 DECIMAL(5,2), pollute3 DECIMAL(5,2), pollute4 DECIMAL(5,2), pollute5 DECIMAL(5,2), pollute6 DECIMAL(5,2),
    serious1 DECIMAL(5,2), serious2 DECIMAL(5,2), serious3 DECIMAL(5,2), serious4 DECIMAL(5,2), serious5 DECIMAL(5,2), serious6 DECIMAL(5,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水质百分比统计';

-- 19. level（水位周数据）
CREATE TABLE IF NOT EXISTS level (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    level1 DECIMAL(6,2), level2 DECIMAL(6,2), level3 DECIMAL(6,2), level4 DECIMAL(6,2), level5 DECIMAL(6,2), level6 DECIMAL(6,2), level7 DECIMAL(6,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水位周数据';

-- 20. statistics（水质参数统计）
CREATE TABLE IF NOT EXISTS statistics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site_id VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '站点名',
    water_quality_parameters TEXT CHARACTER SET utf8mb4 COMMENT '水质参数JSON'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水质参数统计';

-- 21. water_amount（水量）
CREATE TABLE IF NOT EXISTS water_amount (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    amount DECIMAL(10,2) COMMENT '水量(万立方米)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='水量';

-- 22. address_data_image_info（站点预警详情）
CREATE TABLE IF NOT EXISTS address_data_image_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    site VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '站点',
    time DATETIME COMMENT '时间',
    alter_level VARCHAR(20) CHARACTER SET utf8mb4 COMMENT '预警等级',
    pollutant VARCHAR(100) CHARACTER SET utf8mb4 COMMENT '污染物',
    people VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '负责人',
    content TEXT CHARACTER SET utf8mb4 COMMENT '详情'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点预警详情';

-- 23. news（新闻）
CREATE TABLE IF NOT EXISTS news (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '标题',
    content TEXT CHARACTER SET utf8mb4 COMMENT '内容',
    photos VARCHAR(500) CHARACTER SET utf8mb4 COMMENT '图片',
    time DATETIME COMMENT '发布时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻';

-- 24. fault（故障上报）
CREATE TABLE IF NOT EXISTS fault (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '上报人',
    phone VARCHAR(20) CHARACTER SET utf8mb4 COMMENT '联系电话',
    number VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '设备编号',
    description TEXT CHARACTER SET utf8mb4 COMMENT '故障描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='故障上报';

-- 25. admin（管理员）
CREATE TABLE IF NOT EXISTS admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '姓名',
    role VARCHAR(50) CHARACTER SET utf8mb4 COMMENT '角色'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员';
