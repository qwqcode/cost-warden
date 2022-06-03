import notify from "./notify";

/**
 * API 请求封装
 * 
 * @note 文档参考 /docs/api.md
 */

export async function login(username, password) {
    const params = { username, password }
    return await POST(`/user/login`, params);
}

export async function signup(username, email, password, rePassword) {
    const params = { username, email, password, re_password: rePassword }
    return await POST(`/user/signup`, params)
}

export async function logout() {
    return await POST(`/user/logout`)
}

export async function getUserStatus() {
    return await POST(`/user/status`);
}

export async function getCosts(filter) {
    const params = {}
    if (filter) params.filter = JSON.stringify(filter)
    return await POST(`/cost/get`, params)
}

export async function getTags() {
    return await POST(`/tag/get`)
}

export async function getUser() {
    return await POST(`/user/get`)
}

export async function logoutUser() {
    return await POST(`/user/logout`)
}

export async function addCost(price, tid, note) {
    return await POST(`/cost/add`, {
        price, tid, note
    })
}

export async function delCost(cid) {
    return await POST(`/cost/del`, { cid })
}

export async function editCost(cid, price, tid, note) {
    return await POST(`/cost/edit`, {
        cid, price, tid, note
    })
}

export async function costStat() {
    return await POST(`/cost/stat`)
}

export async function delTag(tid) {
    return await POST(`/tag/del`, { tid })
}

export async function addTag(name) {
    return await POST(`/tag/add`, { name })
}

/**
 * 封装统一的 fetch 函数
 */
export async function Fetch(input, init) {
    init.credentials = 'include' // 跨域传递 Cookie
    init.headers = {
        // Use `x-www-form-urlencoded` bcz Servlet < v3 cannot handle `multipart/form-data` directly
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    }

    // 请求操作
    const resp = await fetch((process?.env?.API_BASE || '') + input, init)
  
    if (!resp.ok) {
        notify(`请求错误 ${resp.status}`, 'e')
        throw new Error(`请求错误 ${resp.status}`)
    }
  
    // 解析获取响应的 json
    let json = await resp.json()

    if (!json.success) {
        if (!!json.msg) notify(json.msg, 'e')
        throw json
    }
  
    return json
}

export function ToFormData(object) {
    const formData = new FormData()
    Object.keys(object).forEach(key => formData.append(key, String(object[key])))
    return formData
}

/** 公共 POST 请求 */
export async function POST(url, data) {
    const init = {
      method: 'POST',
    }
    if (data) init.body = new URLSearchParams(data)
  
    const json = await Fetch(url, init)
    return json.data
}
  
/** 公共 GET 请求 */
export async function GET(url, data) {
    const json = await Fetch(url + (data ? (`?${new URLSearchParams(data)}`) : ''), {
      method: 'GET',
    })
    return json.data
}