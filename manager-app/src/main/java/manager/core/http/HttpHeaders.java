package manager.core.http;


/**
 * 名称: HttpHeaders
 * 描述: 一些常用http头名
 *
 * @author YangHongyu
 * @since 2018/9/15 17:10
 */
public class HttpHeaders {

    /**
     * 集群编号（String）
     */
    public static final String CLUSTER_ID = "X-Cluster-Id";

    /**
     * 路由标签（String）
     */
    public static final String ROUTE_TAGS = "X-Route-Tags";

    /**
     * 权限Token（String）
     */
    public static final String AUTH_TOKEN = "X-Auth-Token";

    /**
     * Request上下文变量（String）
     */
    public static final String INVOKE_ATTRIBUTE = "X-Invoke-Attribute";

    /**
     * 是否在白名单中（1|0）
     */
    public static final String IN_WHITELIST = "X-In-Whitelist";

    /**
     * 是否三段式包装（1|0）
     */
    public static final String IS_PACKAGED_RESULT = "X-Is-Packaged-Result";

    /**
     * 业务异常标记（1|0）
     */
    public static final String IS_BIZ_EXCEPTION = "X-Is-Biz-Exception";

    /**
     * Zipkin Trace Id（String）
     */
    public static final String ZIPKIN_TRACE_ID = "X-Zipkin-Trace-Id";

    /**
     * 应用类型（gateway|service）
     */
    public static final String FORWARDED_APPLICATION_TYPE = "X-Forwarded-Application-Type";

    /**
     * 用户ID（Long）
     */
    public static final String LOGIN_USER_ID = "X-Login-User-Id";

    /**
     * 用户类型（Integer）
     */
    public static final String LOGIN_USER_TYPE = "X-Login-User-Type";

    /**
     * 组织ID（Long）
     */
    public static final String LOGIN_ORG_ID = "X-Login-Org-Id";

    /**
     * 用户角色ID（Long）
     */
    public static final String LOGIN_USER_ROLE_ID = "X-Login-User-Role-Id";

}
