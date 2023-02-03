-- -------------
-- 物料管理菜单
-- -------------
-- 菜单 SQL
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料管理', '', 2, 0, 1254,
        'item', '', 'plm/warehouse/item/index.vue', 0);

-- 按钮父菜单ID
-- 暂时只支持 MySQL。如果你是 Oracle、PostgreSQL、SQLServer 的话，需要手动修改 @parentId 的部分的代码
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料查询', 'plm:item:query', 3, 1, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料创建', 'plm:item:create', 3, 2, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料更新', 'plm:item:update', 3, 3, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料删除', 'plm:item:delete', 3, 4, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料导出', 'plm:item:export', 3, 5, @parentId,
        '', '', '', 0);

-- -------------
-- 物料分类菜单
-- -------------
-- 菜单 SQL
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料分类管理', '', 2, 0, 2165,
        'category', '', 'plm/warehouse/category/index.vue', 0);

-- 按钮父菜单ID
-- 暂时只支持 MySQL。如果你是 Oracle、PostgreSQL、SQLServer 的话，需要手动修改 @parentId 的部分的代码
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料分类查询', 'plm:category:query', 3, 1, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料分类创建', 'plm:category:create', 3, 2, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料分类更新', 'plm:category:update', 3, 3, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料分类删除', 'plm:category:delete', 3, 4, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('物料分类导出', 'plm:category:export', 3, 5, @parentId,
        '', '', '', 0);

-- -------------
-- 文档管理菜单
-- -------------
-- 菜单 SQL
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档管理', '', 2, 0, 1254,
        'document', '', 'plm/document/index.vue', 0);
-- 按钮父菜单ID
-- 暂时只支持 MySQL。如果你是 Oracle、PostgreSQL、SQLServer 的话，需要手动修改 @parentId 的部分的代码
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档查询', 'plm:document:query', 3, 1, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档创建', 'plm:document:create', 3, 2, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档更新', 'plm:document:update', 3, 3, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档删除', 'plm:document:delete', 3, 4, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档导出', 'plm:document:export', 3, 5, @parentId,
        '', '', '', 0);


-- -------------
-- 文档分类菜单
-- -------------
-- 菜单 SQL
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档分类管理', '', 2, 0, 2172,
        'document-category', '', 'plm/document/category/index.vue', 0);

-- 按钮父菜单ID
-- 暂时只支持 MySQL。如果你是 Oracle、PostgreSQL、SQLServer 的话，需要手动修改 @parentId 的部分的代码
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档分类查询', 'plm:document-category:query', 3, 1, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档分类创建', 'plm:document-category:create', 3, 2, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档分类更新', 'plm:document-category:update', 3, 3, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档分类删除', 'plm:document-category:delete', 3, 4, @parentId,
        '', '', '', 0);
INSERT INTO system_menu(name, permission, type, sort, parent_id,
                        path, icon, component, status)
VALUES ('文档分类导出', 'plm:document-category:export', 3, 5, @parentId,
        '', '', '', 0);



