import request from '@/utils/request'

// 创建文档
export function createDocument(data) {
  return request({
    url: '/plm/document/create',
    method: 'post',
    data: data
  })
}

// 更新文档
export function updateDocument(data) {
  return request({
    url: '/plm/document/update',
    method: 'put',
    data: data
  })
}

// 删除文档
export function deleteDocument(id) {
  return request({
    url: '/plm/document/delete?id=' + id,
    method: 'delete'
  })
}

// 获得文档
export function getDocument(id) {
  return request({
    url: '/plm/document/get?id=' + id,
    method: 'get'
  })
}

// 获得文档分页
export function getDocumentPage(query) {
  return request({
    url: '/plm/document/page',
    method: 'get',
    params: query
  })
}

// 导出文档 Excel
export function exportDocumentExcel(query) {
  return request({
    url: '/plm/document/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 获取所有分类精简信息列表
export function listSimpleCategories() {
  return request({
    url: '/plm/document-category/list-all-simple',
    method: 'get'
  })

}
