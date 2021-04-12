# Reporter

REST service for sending reports by a given template. 

Service allows sending text reports using HTTP POST requests and email, 
also it supports scheduled delivery using cron expression.

## Configuration

If you use standard `application.properties`, you need to create a `secret-application.properties` 
file in the resource root that will contain secret values.

### Mailing

By default, sending via email is disabled and configuration is not necessary, 
to enable it you can specify `app.sender.mail.enabled=true`

You need to provide login and password for the mail servce to Spring application, 
for example, you can specify them in the secret properties file.

Default mail server is yandex.ru. If you want to change the mail server, 
you need to specify its parameters in the properties `spring.mail`.
### Template

In `application.properties` you can change the opening and closing separators 
that are used in the template as placeholders. The default values are `$`.

Opening and closing separator values are different to support asymmetrical placeholders, like `${test}` 

## How to run

The project contains a gradle wrapper, so you can use the command to build the jar file:

`./gradlew bootJar`

and to run the application:

`./gradlew bootRun`

You can also launch the application in other standard ways. Additional parameters are optional.

## Using

You can use any utility for HTTP requests like curl or Postman.

### Template

Template body example:

```
{
    "templateId" : "test",
    "templateText" : "test $var1$ $var2:string$ $var3:float$ \$",
    "recipients" : [
          {
              "type" : "http",
              "endpoint" : "https://httpbin.org/post"
          },
          {
              "type" : "email",
              "endpoint" : "some.email@yandex.com"
          }
    ]
}
```
      
Default placeholder separators are `$`, escape symbol is `\ `. 

You can specify the type of the value by using `:` after its key, default type is`string` (no validation). 
Supported types: `string`, `int`, `boolean`, `float` (actually backed by double).

Supported recipient types: `http`, `email`
        
#### template API

`GET localhost:8080/api/v1/template/{templateId}` - get by templateId

`GET localhost:8080/api/v1/template/` - get list of all

`POST / PUT localhost:8080/api/v1/template` - create / update

`DELETE localhost:8080/api/v1/template/{templateId}` - delete by templateId

### Report parameters

Report parameters body example:
```
{
    "templateId" : "test",
    "variables" : {"var1" : "hello", "var2" : "world", "var3" : "1.0"}
}
```

In `variables` specify keys of the variables and their values. 

#### report API

`POST/PUT localhost:8080/api/v1/report` - send report

### Scheduled report parameters

ScheduledReportParameters body example:

```
{
    "name": "shceduledreport",
    "cronExpression": "0 * * * * ?",
    "templateId" : "test",
    "variables" : {"var1" : "hello", "var2" : "world", "var3" : "1.0"}
}
```
 
`cronExpression` describes the periodicity of sending the report. 
How to work with this can be found in the 
[official guide](http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger).
In the example above, report will be sent every minute at 0 seconds.

#### API for scheduled report bb

`GET localhost:8080/api/v1/shceduledreport/{name}` - get by name

`GET localhost:8080/api/v1/shceduledreport` - get list of all

`POST / PUT localhost:8080/api/v1/shceduledreport` - create / update

`DELETE localhost:8080/api/v1/shceduledreport/{name}` - delete by name 


## How it works

* Web service - Kotlin, Spring Boot, Spring Web
* HTTP client - Spring WebFlux
* SMTP client - Spring Mail
* Template DB - H2 file
* Scheduling - Spring Quartz with jdbc store

To add another sender option, define the bean implementing `SenderService`.
