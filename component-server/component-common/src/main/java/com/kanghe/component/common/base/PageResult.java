package com.kanghe.component.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: W_jun1
 * @Date: 2019/4/10 10:34
 * @Description: 分页对象
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分页总记录数
     */
    private int count;

    /**
     * 查询的列表内容
     */
    private List<T> list;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 页码
     */
    private int pageNo;

    /**
     * 页大小
     */
    private int pageSize;

    /**
     * 分页附加对象
     * eg:根据商户信息表ID查询支付渠道列表时，同时需要返回商户信息内容，则通过attach对象进行传递
     */
    private Object attach;

    public PageResult(int count, List<T> list, int pageNo, int pageSize) {
        this.count = count;
        this.list = list;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        if (count % pageSize == 0) {
            this.totalPage = count / pageSize;
        } else {
            this.totalPage = count / pageSize + 1;
        }
    }

    public PageResult(int count, List<T> list, Object attach, int pageNo, int pageSize) {
        this.count = count;
        this.list = list;
        this.attach = attach;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        if (count % pageSize == 0) {
            this.totalPage = count / pageSize;
        } else {
            this.totalPage = count / pageSize + 1;
        }
    }
}
