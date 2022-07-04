package sharma.sahil.learning.java.article.backend.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnExpression("${aspect.logging.enabled:true}")
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);


    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllers() {
        // Marker
    }


    @Around("controllers()")
    public Object logMethodStartEndPoints(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();
        log.debug("Enter method :  {}.{}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
        Object o = jp.proceed();
        log.debug("Exit method :  {}.{}. Execution Time ({}) ms.", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), System.currentTimeMillis() - start);
        return o;
    }

}
