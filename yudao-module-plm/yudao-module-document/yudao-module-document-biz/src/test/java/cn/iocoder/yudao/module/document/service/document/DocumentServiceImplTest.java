package cn.iocoder.yudao.module.document.service.document;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.document.controller.admin.document.vo.*;
import cn.iocoder.yudao.module.document.dal.dataobject.document.DocumentDO;
import cn.iocoder.yudao.module.document.dal.mysql.document.DocumentMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.document.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link DocumentServiceImpl} 的单元测试类
*
* @author 洋芋
*/
@Import(DocumentServiceImpl.class)
public class DocumentServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DocumentServiceImpl documentService;

    @Resource
    private DocumentMapper documentMapper;

    @Test
    public void testCreateDocument_success() {
        // 准备参数
        DocumentCreateReqVO reqVO = randomPojo(DocumentCreateReqVO.class);

        // 调用
        Long documentId = documentService.createDocument(reqVO);
        // 断言
        assertNotNull(documentId);
        // 校验记录的属性是否正确
        DocumentDO document = documentMapper.selectById(documentId);
        assertPojoEquals(reqVO, document);
    }

    @Test
    public void testUpdateDocument_success() {
        // mock 数据
        DocumentDO dbDocument = randomPojo(DocumentDO.class);
        documentMapper.insert(dbDocument);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DocumentUpdateReqVO reqVO = randomPojo(DocumentUpdateReqVO.class, o -> {
            o.setId(dbDocument.getId()); // 设置更新的 ID
        });

        // 调用
        documentService.updateDocument(reqVO);
        // 校验是否更新正确
        DocumentDO document = documentMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, document);
    }

    @Test
    public void testUpdateDocument_notExists() {
        // 准备参数
        DocumentUpdateReqVO reqVO = randomPojo(DocumentUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> documentService.updateDocument(reqVO), DOCUMENT_NOT_EXISTS);
    }

    @Test
    public void testDeleteDocument_success() {
        // mock 数据
        DocumentDO dbDocument = randomPojo(DocumentDO.class);
        documentMapper.insert(dbDocument);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDocument.getId();

        // 调用
        documentService.deleteDocument(id);
       // 校验数据不存在了
       assertNull(documentMapper.selectById(id));
    }

    @Test
    public void testDeleteDocument_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> documentService.deleteDocument(id), DOCUMENT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDocumentPage() {
       // mock 数据
       DocumentDO dbDocument = randomPojo(DocumentDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setVersion(null);
           o.setDescription(null);
           o.setSize(null);
           o.setPath(null);
           o.setUrl(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       documentMapper.insert(dbDocument);
       // 测试 name 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setName(null)));
       // 测试 version 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setVersion(null)));
       // 测试 description 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setDescription(null)));
       // 测试 size 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setSize(null)));
       // 测试 path 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setPath(null)));
       // 测试 url 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setUrl(null)));
       // 测试 status 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setCreateTime(null)));
       // 准备参数
       DocumentPageReqVO reqVO = new DocumentPageReqVO();
       reqVO.setName(null);
       reqVO.setVersion(null);
       reqVO.setDescription(null);
       reqVO.setSize(null);
       reqVO.setPath(null);
       reqVO.setUrl(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime((new LocalDateTime[]{}));

       // 调用
       PageResult<DocumentDO> pageResult = documentService.getDocumentPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDocument, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDocumentList() {
       // mock 数据
       DocumentDO dbDocument = randomPojo(DocumentDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setVersion(null);
           o.setDescription(null);
           o.setSize(null);
           o.setPath(null);
           o.setUrl(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       documentMapper.insert(dbDocument);
       // 测试 name 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setName(null)));
       // 测试 version 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setVersion(null)));
       // 测试 description 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setDescription(null)));
       // 测试 size 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setSize(null)));
       // 测试 path 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setPath(null)));
       // 测试 url 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setUrl(null)));
       // 测试 status 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       documentMapper.insert(cloneIgnoreId(dbDocument, o -> o.setCreateTime(null)));
       // 准备参数
       DocumentExportReqVO reqVO = new DocumentExportReqVO();
       reqVO.setName(null);
       reqVO.setVersion(null);
       reqVO.setDescription(null);
       reqVO.setSize(null);
       reqVO.setPath(null);
       reqVO.setUrl(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime((new LocalDateTime[]{}));

       // 调用
       List<DocumentDO> list = documentService.getDocumentList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDocument, list.get(0));
    }

}
