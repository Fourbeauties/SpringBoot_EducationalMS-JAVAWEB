package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZUOZHE
 * @since 04-16
 */
@Getter
@Setter
@TableName("eas_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能id
     */
    private Integer id;

    /**
     * 功能名称
     */
    private String text;

    /**
     * 功能类型
     */
    private String type;

    /**
     * 路径
     */
    private String url;

    /**
     * 别名
     */
    private String percode;

    /**
     * 父级编号
     */
    private Integer parentid;

    /**
     * 进行排序
     */
    private Integer sortstring;

    /**
     * 是否启用
     */
    private Integer available;


}
