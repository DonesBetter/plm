import request from '@/utils/request'

// 创建文档分类
export function createDocumentCategory(data) {
  return request({
    url: '/plm/document-category/create',
    method: 'post',
    data: data
  })
}

// 更新文档分类
export function updateDocumentCategory(data) {
  return request({
    url: '/plm/document-category/update',
    method: 'put',
    data: data
  })
}

// 删除文档分类
export function deleteDocumentCategory(id) {
  return request({
    url: '/plm/document-category/delete?id=' + id,
    method: 'delete'
  })
}

// 获得文档分类
export function getDocumentCategory(id) {
  return request({
    url: '/plm/document-category/get?id=' + id,
    method: 'get'
  })
}

// 获得文档分类分页
export function getDocumentCategoryPage(query) {
  return request({
    url: '/plm/document-category/page',
    method: 'get',
    params: query
  })
}

// 导出文档分类 Excel
export function exportDocumentCategoryExcel(query) {
  return request({
    url: '/plm/document-category/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
