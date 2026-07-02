-- 迁移脚本：为已有 clubsystem 数据库添加封面图片字段
ALTER TABLE activity ADD COLUMN cover_image VARCHAR(255) AFTER status;
