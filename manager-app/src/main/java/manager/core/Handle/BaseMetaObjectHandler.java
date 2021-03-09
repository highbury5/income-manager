package manager.core.Handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * <p>
 * 公共字段自动填充处理器
 * </p>
 *
 * @author yanghy
 * @since 2018-11-27
 */
public class BaseMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = this.getFieldValByName("createTime", metaObject);
        if(createTime == null) {
            createTime = LocalDateTime.now();
        }
        this.setFieldValByName("createTime", createTime, metaObject);

        Object updateTime = this.getFieldValByName("updateTime", metaObject);
        if(updateTime == null) {
            updateTime = LocalDateTime.now();
        }
        this.setFieldValByName("updateTime", updateTime, metaObject);

       }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

}
