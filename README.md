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

# Quick Start Guide & Documentation
A quick start guide and reference documentation for Valex is hosted on the [Valex Project Page](http://pattern.fm/valex/#documentation).


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
