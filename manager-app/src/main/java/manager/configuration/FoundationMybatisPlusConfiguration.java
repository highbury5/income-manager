package manager.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import manager.core.Handle.BaseMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
/**
 * 名称: FoundationMybatisPlusConfiguration
 * 描述: Mybatis Plus 配置
 *
 * @author huangzx
 * @version 1.0
 * @since 2020/1/8 23:48
 *
 */
@Slf4j
@EnableTransactionManagement
@Configuration
public class FoundationMybatisPlusConfiguration {

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.info("初始化PaginationInterceptor...");
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

    /**
     * 公共字段自动填充
     */
    @Bean
    public BaseMetaObjectHandler idMetaObjectHandler() {
        log.info("初始化IdMetaObjectHandler...");
        return new BaseMetaObjectHandler();
    }
}
