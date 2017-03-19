[![Build Status](https://travis-ci.org/PatternFM/valex.svg?branch=master)](https://travis-ci.org/PatternFM/valex)
[![Coverage Status](https://coveralls.io/repos/github/PatternFM/valex/badge.svg?branch=master)](https://coveralls.io/github/PatternFM/valex?branch=master)  

# Introduction

Valex is a YAML-based validation and fluent exception management API for Java.

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
The Valex API relies on an underlying BeanValidation Validator implementation to execute valiation logic on annotated models. In the example above we're bootstrapping the Spring LocalValidatorFactory bean, but you can bootstrap your own validator as well:

```
@Bean(name = "validator")
public Validator validator() {
    return Validation.buildDefaultValidatorFactory().getValidator();
}

```

# Validation Configuration
You can configure your application's error messages, codes and exceptions using a YAML configuration file or a properties configuration file. It's simply a matter of syntax preference in terms of which method you choose. The properties file is available to support an easier migration path if you have an existing BeanValidation implementation with a ValidationMessages.properties file already defined.

### YAML Configuration
If you choose to go down the YAML configuration path, place a file named **ValidationMessages.yml** on the root of your classath. An example YAML configuration file will look like this:


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

If you choose to go down the Java properties file configuration path, place a file named **ValidationMessages.properties** on the root of your classath. An example properties configuration file will look like this:

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

Let's look at a specific configuration element in detail:

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

### Message Interpolation (Dynamic Error Messages)
Valex supports two kinds of message interpolation so that you can produce data driven error messages.

#### BeanValidation Interpolation
BeanValidation message interpolation allows you to inject *annotation attribute values* into your validation messages. As an example, let's take the @Size annotated password field with a **min** and **max** attributes defined below:

```java
@Size(min = 8, max = 255, message = "{account.password.size}")
private String password;
```

If you want to inject the min and max attribute values into your error message, you can do so by addressing the attributes in {braces}, like so:
```
account.password.size: 
  message: "An account password must be between {min} and {max} characters."
  code: ACC-0005
```

One special message interpolation feature you can use is the {validatedValue} expression. This expression be used to inject the current value of the field being validated into your error message.

```java
@Size(min = 8, max = 255, message = "{account.username.size}")
private String username;
```

```
account.username.size: 
  message: "The username {validatedValue} must be between {min} and {max} characters."
```


# Triggering Validation

Validation events will produce Generic **Result** objects which contain the state of the validation result. While there are many approaches to handle the Result object after a validation event, the recommended apporach is to return the Result to callers. 

Let's look at the following AccountService interface as an example:

```java

public interface AccountService {

   Account create(Account account);

   Account update(Account account);

   Account delete(Account account);

   Account findById(String id);

   Account findByUsername(String username);

}
```

The contract isn't expressive enough to report on both success and failure conditions in a consistent fashion. If a validation error were to occur within the create(), update() or delete() methods, errors would have to be propagated through a RuntimeException. 

The findById() and findByUsername() methods will return accounts if found, but the negative case is subjective - these methods could throw a RuntimeException to communicate that a result wasn't found, or return null as a convention.

A more expressive contract can provide a simple, consistent approach to handling success and error conditions:

```java

public interface AccountService {

   Result<Account> create(Account account);

   Result<Account> update(Account account);

   Result<Account> delete(Account account);

   Result<Account> findById(String id);

   Result<Account> findByUsername(String username);

}
```

Using the fluent interface, any methods returning a Result<T> will allow API clients to immediatley retrieve the target object if validation was successful, or throw an exception if a validation error occured:

```java
Account pending = new Account();
...
Account account = accountService.create(pending).orThrow();

```

Another benefit of this approach is that it doesn't force API clients to implement try/catch logic if they choose to do something other than propagate exceptions.
```java
Account pending = new Account();
...
Result<Account> result = accountService.create(pending);
if(result.accepted()) {
  // do something
}
if(result.rejected()) {
  // do something else
}

```

Finally, applying this mechanism consistently across a program can help reduce congitive load; API clients can expect a consistent response from service calls without having to delve into the specific nuances of an API to learn how to deal with success or error conditions.

### Explict validation using the ValidationService

You can explicitly trigger validation events by invoking the ValidationService.validate() method on the target object to validate.

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

Valex also provides a parameter-scoped @Valid annoation that triggers validation and returns a Result immediatley if validation fails. To use this annotation your method must have a signature that returns Result<T>.

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
        // If we get to this point validation has succeeded.
        return Result.accept(repository.save(account));
    }

}      
```

# Conditional Validation

### Validation Groups

I've met a number of people who have used the BeanValidation API but haven't had much exposure to conditional validation using validation groups. Validation groups are a great feature of the BeanValidation API, and Valex provides first class support for Validation groups.

```java
@UniqueValue(property = "username", message = "{account.username.conflict}", groups = { CreateLevel3.class, UpdateLevel3.class })
public class Account {

  @NotBlank(message = "{account.id.required}", groups = { UpdateLevel1.class, DeleteLevel1.class })
  @Size(min = 3, max = 128, message = "{account.username.size}", groups = { UpdateLevel2.class, DeleteLevel2.class })
  private String id;

  @NotBlank(message = "{account.username.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
  @Size(min = 3, max = 128, message = "{account.username.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
  private String username;

  @NotBlank(message = "{account.password.required}", groups = { CreateLevel1.class, UpdateLevel1.class })
  @Size(min = 8, max = 255, message = "{account.password.size}", groups = { CreateLevel2.class, UpdateLevel2.class })
  private String password;

}
```

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
