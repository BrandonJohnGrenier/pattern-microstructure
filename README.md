[![Build Status](https://travis-ci.org/PatternFM/valex.svg?branch=master)](https://travis-ci.org/PatternFM/valex)
[![Coverage Status](https://coveralls.io/repos/github/PatternFM/valex/badge.svg?branch=master)](https://coveralls.io/github/PatternFM/valex?branch=master) 
[![codebeat badge](https://codebeat.co/badges/8bfc9729-9eb3-4527-893d-3e7407fea5d6)](https://codebeat.co/projects/github-com-patternfm-valex-master) 

# Introduction

An API should provide useful error responses in a predictable and consumable format. An error response should provide a few things for a developer - a useful error message, a unique error code, and a meaningful HTTP response code.

Valex can help you produce meaningful responses that include an appropriate HTTP response code as well as a JSON payload that looks like this:

```
{
  "errors": [
    {
      "code": "ACC-1000",
      "message": "An account username is required."
    },
    {
      "code": "ADD-1001",
      "message": "An account password is required."
    }
  ]
}
```

The JSON payload is entirely configuration driven, so you can produce a JSON payload that looks like this just as easily:

```
{
  "errors": [
    {
      "code": "ACC-1000",
      "message": "An account username is required.",
      "field": "account.username",
      "support_url": "https://support.yoursite.com/kb/articles/acc-1000.html"
    },
    {
      "code": "ADD-1001",
      "message": "An account password is required.",
      "field": "account.password",
      "support_url": "https://support.yoursite.com/kb/articles/add-1001.html"
    }
  ]
}
```

# Quick Start

First, add Valex to your project dependency list:

```xml
<dependency>
    <groupId>fm.pattern</groupId>
    <artifactId>valex</artifactId>
    <version>1.0.7</version>
</dependency>
```

Second, create a file named ```ValidationMessages.yml``` on the root of your classpath with the following configuration:

```yaml
account.username.required:
  code: ACC-1000
  message: An account username is required.
  exception: fm.pattern.valex.UnprocessableEntityException

account.password.required:
  code: ACC-1001
  message: An account password is required.
  exception: fm.pattern.valex.UnprocessableEntityException
```

Third, annotate your model with BeanValidation annotations. The ```message``` annotation attribute is used to resolve properties from the ```ValidationMessages.yml``` file.

```java
public class Account {

  @NotBlank(message = "{account.username.required}")
  private String username;

  @NotBlank(message = "{account.password.required}")
  private String password;

}
```

Fourth, setup your Spring ```ExceptionHandler``` to handle the exceptions you've configured in your ```ValidationMessages.yml``` file. This allows you to map the appropriate HTTP response code against the exception classes you've configured. The toRepresentation() method of the Valex UnprocessableEntityException returns an ```ErrorsRepresentation``` object, which produces the appropriate JSON payload described in the introduction.

```java
@RestController
public class Endpoint {

	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(UnprocessableEntityException.class)
	public ErrorsRepresentation handleUnprocessableEntity(UnprocessableEntityException exception) {
		return exception.toRepresentation();
	}

}
```

Fifth, configure and wire up the Valex validation APIs. Valex exposes a ValidationService API that can be used to explicitly trigger validation, as well as @Valid annotations that can be used to perform validation declaratively. You can wire these services into your Spring application using the following configuration:

```java
@Configuration
@EnableAspectJAutoProxy
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
The Valex API relies on an underlying [BeanValidation](http://beanvalidation.org/1.0/) Validator implementation to execute valiation logic on annotated models. In the example above we're using the Spring LocalValidatorFactory bean, but you can use your own validator as well:

```java
@Bean(name = "validator")
public Validator validator() {
    return Validation.buildDefaultValidatorFactory().getValidator();
}
```


Last, trigger validation. Valex provides a few ways to trigger validation, but we'll start with the Valex @Valid annotation. In this case, we instruct the annotation to throw an exception if validation fails by passing a ```throwException = true``` argument to the annotation.

```java
public interface AccountService {

    public Result<Account> create(@Valid(throwException = true) Account account);

}
```



# Documentation
Complete documentation for Valex is hosted on the [Valex Project Page](http://pattern.fm/valex/#documentation).


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
