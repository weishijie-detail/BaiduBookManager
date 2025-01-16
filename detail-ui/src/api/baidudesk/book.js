import request from '@/utils/request'

// 查询书籍信息管理列表
export function listBook(query) {
  return request({
    url: '/baidudesk/book/list',
    method: 'get',
    params: query
  })
}

// 查询书籍信息管理详细
export function getBook(id) {
  return request({
    url: '/baidudesk/book/' + id,
    method: 'get'
  })
}

// 新增书籍信息管理
export function addBook(data) {
  return request({
    url: '/baidudesk/book',
    method: 'post',
    data: data
  })
}

// 修改书籍信息管理
export function updateBook(data) {
  return request({
    url: '/baidudesk/book',
    method: 'put',
    data: data
  })
}

// 删除书籍信息管理
export function delBook(id) {
  return request({
    url: '/baidudesk/book/' + id,
    method: 'delete'
  })
}
