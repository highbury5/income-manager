package manager.mapper;

import manager.entity.Custom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-05
 */
public interface CustomMapper extends BaseMapper<Custom> {

    List<Custom> getByNameAndPerson(@Param("corpName") String corpName,@Param("legalPerson") String legalPerson);

}
