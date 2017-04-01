[![Build Status](https://travis-ci.org/PatternFM/valex.svg?branch=master)](https://travis-ci.org/PatternFM/valex)
[![Coverage Status](https://coveralls.io/repos/github/PatternFM/valex/badge.svg?branch=master)](https://coveralls.io/github/PatternFM/valex?branch=master) 
[![codebeat badge](https://codebeat.co/badges/8bfc9729-9eb3-4527-893d-3e7407fea5d6)](https://codebeat.co/projects/github-com-patternfm-valex-master) 

# Introduction

Produce predictable and consumable REST error payloads with Valex, a YAML-based validation and exception management library for Java.

An API should provide a useful error responses in a known, consumable format. The representation of an error should be no different than the representation of any other resource, just with its own set of fields. An error response should provide a few things for a developer - a useful error message, a unique error code, and a meaningful HTTP response code. 

A JSON representation of an error payload would look like:

```json
{
  "errors": [
    {
      "code": "ACC-0001",
      "message": "An account username is required."
    }
  ]
}
```

Valex builds on top of the BeanValidation API to give devlopers the ability to configure an error message, error code, and an exception to throw when a validation check fails, all in one spot.

```yaml
account.username.required: 
  message: An account username is required.
  code: ACC-0001
  exception: fm.pattern.valex.UnprocessableEntityException

```
The exception, when thrown, will carry with it the error message and error code as specified in the configuration. If you're using Spring, you can use the @ExceptionHandler to easily convert the exception into the JSON format specified above:

```java
@RestController
public class Endpoint {

	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(UnprocessableEntityException.class)
	public ErrorsRepresentation handleUnprocessableEntity(UnprocessableEntityException exception) {
		return exception.toRepresentation();
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AuthenticationException.class)
	public ErrorsRepresentation handleAuthentication(AuthenticationException exception) {
		return exception.toRepresentation();
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(AuthorizationException.class)
	public ErrorsRepresentation handleAuthorization(AuthorizationException exception) {
		return exception.toRepresentation();
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public ErrorsRepresentation handleEntityNotFound(EntityNotFoundException exception) {
		return exception.toRepresentation();
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalErrorException.class)
	public ErrorsRepresentation handleInternalError(EntityNotFoundException exception) {
		return exception.toRepresentation();
	}

}
```
Because Valex builds on top of the [JSR-303 BeanValidation API](http://beanvalidation.org/1.0/), you can annotate your models with standard BeanValidation annotations. A ValidationMessages.properties file can also be used with Valex to minimise the migration path for existing JSR-303 implementations that want to use Valex for more robust API error reporting.


# Getting Started

To get started, add the following dependency to your depedency list:
```xml
<dependency>
    <groupId>fm.pattern</groupId>
    <artifactId>valex</artifactId>
    <version>1.0.6</version>
</dependency>
```


# Documentation
Documentation is hosted on the [Valex Project Page](http://pattern.fm/valex/#documentation).


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
