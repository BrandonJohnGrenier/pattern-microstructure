[![Build Status](https://travis-ci.org/PatternFM/valex.svg?branch=master)](https://travis-ci.org/PatternFM/valex)
[![Coverage Status](https://coveralls.io/repos/github/PatternFM/valex/badge.svg?branch=master)](https://coveralls.io/github/PatternFM/valex?branch=master)  

# Introduction

Valex provides YAML-based validation configuration and fluent exception management for Java.

To get started, add the following dependency to your depedency list:
```xml
<dependency>
    <groupId>fm.pattern</groupId>
    <artifactId>valex</artifactId>
    <version>1.0.3</version>
</dependency>
```

Valex exposes a ValidationService API that can be used to explicitly trigger validation, as well as @Valid annotations that can be used to perform validation declaratively. You can wire these services into your Spring application using the following configuration:

```java
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import fm.pattern.valex.SimpleValidationService;
import fm.pattern.valex.ValidationService;
import fm.pattern.valex.annotations.ValidationAdvisor;

@Configuration
public class ValidationConfiguration {

    @Bean(name = "validator")
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidationService validationService() {
        return new SimpleValidationService(validator());
    }

    @Bean
    public ValidationAdvisor validationAdvisor() {
        return new ValidationAdvisor(validationService());
    }

}
```

# Validation Configuration
You can configure your application's error messages, codes and exceptions using a YAML configuration file or a properties configuration file. It's simply a matter of syntax preference in terms of which method you choose. The properties file is available to support an easier migration path if you have an existing BeanValidation implementation with a ValidationMessages.properties file already defined.

### YAML Configuration
If you choose to go down the YAML configuration path, place a file named **ValidationMessages.yml** on the root of your classath. An example YAML configuration file will look like:


```yaml

default:
  exception: fm.pattern.valex.UnprocessableEntityException
  
account.id.required: 
  message: "An account id is required."
  code: ACC-0006

account.username.required: 
  message: "An account username is required."
  code: ACC-0001

account.username.size: 
  message: "An account username must be between {min} and {max} characters."
  code: ACC-0002

account.username.conflict:
  message: "The username {validatedValue} is already in use."
  code: ACC-0003
  exception: fm.pattern.valex.ResourceConflictException

account.password.required: 
  message: "An account password is required."
  code: ACC-0004

account.password.size: 
  message: "An account password must be between {min} and {max} characters."
  code: ACC-0005

account.username.not_found: 
  message: "No such username: %s"
  code: ACC-0008
  exception: fm.pattern.valex.EntityNotFoundException

``` 


### Properties Configuration

If you choose to go down the Java properties file configuration path, place a file named **ValidationMessages.properties** on the root of your classath. An example properties configuration file will look like:

```
default.exception=fm.pattern.valex.UnprocessableEntityException

account.id.required=An account id is required.
account.id.required.code=ACC-0006

account.username.required=An account username is required.
account.username.required.code=ACC-0001

account.username.size=An account username must be between {min} and {max} characters.
account.username.size.code=ACC-0002

account.username.conflict=The username {validatedValue} is already in use.
account.username.conflict.code=ACC-0003
account.username.conflict.exception=fm.pattern.valex.ResourceConflictException

account.password.required=An account password is required.
account.password.required.code=ACC-0004

account.password.size=An account password must be between {min} and {max} characters.
account.password.size.code=ACC-0005

account.username.not_found=No such username: %s
account.username.not_found.code=ACC-0008
account.username.not_found.exception=fm.pattern.valex.EntityNotFoundException

```

### Configuration in Detail

We'll take a specific example of a configuration element and describe it in detail:

```yaml
account.id.required: 
  message: "An account id is required."
  code: ACC-0006
  exception: fm.pattern.valex.UnprocessableEntityException
``` 

**account.id.required**  
The *key* used to resolve the error message, code and exception for this particular validation error. The dot notatation in the key name is purely conventional; you can you use the most appropriate key format for your needs - Valex treats the key as an opaque value.

**message**   
The error message that should be returned when validation fails for this key. The YAML messages should generally be surrounded by double quotes (double quotes are required when you use *interpolated messaging* - more on this shortly). 

**code**   
The error code that should be returned when validation fails for this key. The error code presented above is conventional; choose an error code scheme to suit your needs.

**exception**    
The _ReportableException_ that should be returned when validation fails for this key. Valex provides a number of ReportableException implementation classes for you to use out of the box:
* UnprocessableEntityException 
* AuthenticationException 
* AuthorizationException 
* BadRequestException 
* ResourceConflictException
* EntityNotFoundException

You can roll your own ReportableException implementation classes to suit your needs, use the existing Valex exceptions, or use a mix of both. 

### Default Exceptions
You can configure a default exception class to return when a validation element does not contain an explicit *exception* property.

**YAML Configuration**
```yaml
default:
  exception: fm.pattern.valex.UnprocessableEntityException
```  

**Properties Configuration**
```
default.exception=fm.pattern.valex.UnprocessableEntityException
```

In both cases, an UnprocessableEntityException will be returned when a validation element does not explicitly define an exception property.

### Message Interpolation


# Triggering Validation

```java

public interface AccountService {

   Result<Account> create(Account account);

   Result<Account> update(Account account);

   Result<Account> delete(Account account);

   Result<Account> findById(String id);

   Result<Account> findByUsername(String username);

}
```

### Explict validation using the ValidationService
```java

import fm.pattern.valex.Result;
import fm.pattern.valex.ValidationService;

@Service
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ValidationService validationService;
    
    public AccountServiceImpl(AccountRepository repository, ValidationService validationService) {
        repository = repository;
        this.validationService = validationService;
    }

    public Result<Account> create(Account account) {
        Result<Account> result = validationService.validate(account);
        if(result.rejected()) {
            return result;
        }
        return Result.accept(repository.save(account));
    }
    
}
```

### Declarative Validation using Annotations
```java

import fm.pattern.valex.Result;
import fm.pattern.valex.annotations.Valid;

@Service
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    
    public AccountServiceImpl(AccountRepository repository) {
        repository = repository;
    }

    public Result<Account> create(@Valid Account account) {
        return Result.accept(repository.save(account));
    }

}      
```

# Conditional Validation

### Conditional validation using the ValidationService
```java

import fm.pattern.valex.Result;
import fm.pattern.valex.ValidationService;

import fm.pattern.valex.sequences.Create;
import fm.pattern.valex.sequences.Update;
import fm.pattern.valex.sequences.Delete;

@Service
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ValidationService validationService;
    
    public AccountServiceImpl(AccountRepository repository, ValidationService validationService) {
        repository = repository;
        this.validationService = validationService;
    }

    public Result<Account> create(Account account) {
        Result<Account> result = validationService.validate(account, Create.class);
        if(result.rejected()) {
            return result;
        }
        return Result.accept(repository.save(account));
    }
    
    public Result<Account> update(Account account) {
        Result<Account> result = validationService.validate(account, Update.class);
        if(result.rejected()) {
            return result;
        }
        return Result.accept(repository.update(account));
    }
    
    public Result<Account> delete(Account account) {
        Result<Account> result = validationService.validate(account, Delete.class);
        if(result.rejected()) {
            return result;
        }
        return Result.accept(repository.delete(account));
    }
            
}
```


### Conditional validation using Annotations
```java

import fm.pattern.valex.Result;

import fm.pattern.valex.annotations.Create;
import fm.pattern.valex.annotations.Update;
import fm.pattern.valex.annotations.Delete;

@Service
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    
    public AccountServiceImpl(AccountRepository repository) {
        repository = repository;
        this.validationService = validationService;
    }

    public Result<Account> create(@Create Account account) {
        return Result.accept(repository.save(account));
    }
    
    public Result<Account> update(@Update Account account) {
        return Result.accept(repository.update(account));
    }
    
    public Result<Account> delete(@Delete Account account) {
        return Result.accept(repository.delete(account));
    }
            
}
```

# Explicit Rejection

```java

import fm.pattern.valex.Result;

@Service
class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    
    public AccountServiceImpl(AccountRepository repository) {
        repository = repository;
    }

    public Result<Account> findById(String id) {
        if (StringUtils.isBlank(id)) {
            return Result.reject("account.id.required");
        }

        Account account = repository.findById(username);
        if(account == null) {
            Result.reject("account.id.not_found", username);
        }
        
        return Result.accept(account): 
    }

    public Result<Account> findByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return Result.reject("account.username.required");
        }

        Account account = repository.findByUsername(username);
        if(account == null) {
            Result.reject("account.username.not_found", username);
        }
        
        return Result.accept(account): 
    }
            
}
```


# Building from Source

Both JDK 8 and Maven 3 are required to build Valex from source. With these prerequisites in place you can build Valex by:
```
git clone https://github.com/PatternFM/valex.git
cd valex
mvn clean install
```

# Continuous Integration

The Continuous Integration service for the project is hosted on [Travis](https://travis-ci.org/PatternFM/valex) 


# Licensing

This software is provided and distributed under the Apache Software License 2.0. Refer to LICENSE.txt for more information.
