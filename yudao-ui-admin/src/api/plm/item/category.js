import request from '@/utils/request'

// 创建物料分类
export function createCategory(data) {
  return request({
    url: '/item/category/create',
    method: 'post',
    data: data
  })
}

// 更新物料分类
export function updateCategory(data) {
  return request({
    url: '/item/category/update',
    method: 'put',
    data: data
  })
}

// 删除物料分类
export function deleteCategory(id) {
  return request({
    url: '/item/category/delete?id=' + id,
    method: 'delete'
  })
}

// 获得物料分类
export function getCategory(id) {
  return request({
    url: '/item/category/get?id=' + id,
    method: 'get'
  })
}

// 获得物料分类分页
export function getCategoryPage(query) {
  return request({
    url: '/item/category/page',
    method: 'get',
    params: query
  })
}
// 获取所有物料分类精简信息列表
export function listSimpleCategories() {
  return request({
    url: '/item/category/list-all-simple',
    method: 'get'
  })
}
// 导出物料分类 Excel
export function exportCategoryExcel(query) {
  return request({
    url: '/item/category/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
