package com.achobeta.types.support.postprocessor;

import com.achobeta.types.common.Constants;
import lombok.*;

import java.util.Map;

/**
 * @author chensongmin
 * @description Post扩展点数据上下文
 * 用于在主流程与分支流程的数据传递
 * @create 2024/11/3
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostContext<T> {

    /**
     * 业务名称
     * <p>新开一个业务模块，就需要在 {@link Constants.BizModule} 多开一个枚举映射上</p>
     */
    private String bizName;

    /**
     * 业务数据
     */
    private T bizData;

    /**
     * 额外业务数据
     * <p>如果在 processor 处理过程中出现了一些新数据需要承载
     * 而不想修改原本定义好的 BO 对象时，可以往 extra 里放元素
     * </p>
     * <pre>数据格式：
     * Key：变量名，小写开头，可以用常量封装
     * Value：变量值</pre>
     */
    private Map<String, Object> extra;

}
