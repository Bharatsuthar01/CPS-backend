package com.bank.cps.common.audit;

import com.bank.cps.audit.document.AuditLogDocument;
import com.bank.cps.common.security.CurrentUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AuditAspect {

    private final MongoTemplate mongoTemplate;

    public AuditAspect(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Around("@annotation(auditEvent)")
    public Object auditMethodExecution(ProceedingJoinPoint joinPoint, AuditEvent auditEvent) throws Throwable {
        long startMs = System.currentTimeMillis();
        AuditLogDocument logDoc = new AuditLogDocument();
        logDoc.setMethodName(joinPoint.getSignature().getName());
        logDoc.setTimestamp(Instant.now());
        
        try {
            logDoc.setUserId(CurrentUser.username());
        } catch (Exception ignored) {
            logDoc.setUserId("system");
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        Map<String, Object> argMap = new HashMap<>();
        
        if (parameterNames != null && args != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                argMap.put(parameterNames[i], args[i]);
            }
        }
        logDoc.setArguments(argMap);

        try {
            Object result = joinPoint.proceed();
            logDoc.setResult(result);
            return result;
        } catch (Throwable ex) {
            logDoc.setExceptionMessage(ex.getMessage());
            throw ex;
        } finally {
            logDoc.setExecutionTimeMs(System.currentTimeMillis() - startMs);
            mongoTemplate.save(logDoc);
        }
    }
}
