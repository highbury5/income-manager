package manager.test.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class WinOsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String os = conditionContext.getEnvironment().getProperty("OS");
        if(!StringUtils.isEmpty(os)){
            return os.contains("win");
        }
        return false;
    }

}