/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.pattern.valex.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import fm.pattern.valex.Result;
import fm.pattern.valex.ValidationService;
import fm.pattern.valex.sequences.Create;
import fm.pattern.valex.sequences.Delete;
import fm.pattern.valex.sequences.Update;

@Aspect
@SuppressWarnings("unchecked")
public class ValidationAdvisor {

    private final ValidationService validationService;

    public ValidationAdvisor(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Around("execution(* *(@fm.pattern.valex.annotations.Create (*)))")
    public Object aroundCreate(ProceedingJoinPoint pjp) throws Throwable {
        fm.pattern.valex.annotations.Create annotation = getAnnotation(pjp, fm.pattern.valex.annotations.Create.class);
        return around(pjp, annotation.throwException(), Create.class);
    }

    @Around("execution(* *(@fm.pattern.valex.annotations.Update (*)))")
    public Object aroundUpdate(ProceedingJoinPoint pjp) throws Throwable {
        fm.pattern.valex.annotations.Update annotation = getAnnotation(pjp, fm.pattern.valex.annotations.Update.class);
        return around(pjp, annotation.throwException(), Update.class);
    }

    @Around("execution(* *(@fm.pattern.valex.annotations.Delete (*)))")
    public Object aroundDelete(ProceedingJoinPoint pjp) throws Throwable {
        fm.pattern.valex.annotations.Delete annotation = getAnnotation(pjp, fm.pattern.valex.annotations.Delete.class);
        return around(pjp, annotation.throwException(), Delete.class);
    }

    // TODO: Check for optional 'Type' passed in as an annotation parameter.
    @Around("execution(* *(@fm.pattern.valex.annotations.Valid (*)))")
    public Object aroundValid(ProceedingJoinPoint pjp) throws Throwable {
        fm.pattern.valex.annotations.Valid annotation = getAnnotation(pjp, fm.pattern.valex.annotations.Valid.class);
        return around(pjp, annotation.throwException(), annotation.value());
    }

    private <T> T getAnnotation(ProceedingJoinPoint pjp, Class<T> annotationType) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getDeclaringType().getDeclaredMethod(signature.getName(), signature.getParameterTypes());

        for (Annotation annotation : method.getParameterAnnotations()[0]) {
            if (annotationType.isInstance(annotation)) {
                return (T) annotation;
            }
        }

        return null;
    }

    private Object around(ProceedingJoinPoint pjp, boolean throwException, Class<?>... groups) throws Throwable {
        Object[] arguments = pjp.getArgs();
        if (arguments.length == 0) {
            return pjp.proceed();
        }

        Result<Object> result = validate(pjp.getArgs()[0], groups);
        if (result.rejected()) {
            if (throwException) {
                throw result.getException();
            }
            return result;
        }

        return pjp.proceed();
    }

    private Result<Object> validate(Object instance, Class<?>... groups) {
        return groups == null ? validationService.validate(instance) : validationService.validate(instance, groups);
    }

}
