# Spring Boot Swagger 2.0 Integration
This project demonstrates integration of Swagger 2.0 with Spring Boot. It is based on 
[kongchen's swagger-gradle-plugin](https://github.com/gigaSproule/swagger-gradle-plugin). This 
enables your Swagger-annotated project to generate Swagger specs and customizable static 
documents during the gradle build phase.  

If the requirement is to host the Swagger document on the same server, 
[SpringFox](http://springfox.github.io/springfox/docs/snapshot/) can be used. 
### Common Swagger 2.0 Annotations
#### @ApiOperation
This annotation is used on the method level to identify an endpoint. If 'springmvc' is configured as 
true in build.gradle, then @RequestMapping does the job. However this annotation can be used to 
supply additional information.  
This also adds response code 200 as default for successful operation. However, in some case like a 
create operation if a different default code is required it can be overridden by supplying value
for 'code' argument.
#### @ApiResponse
This annotation is used to define different response codes an endpoint can return and their reasons.
#### @ApiParam
This annotation is in addition to @RequestBody. Swagger does not understand the 'required' argument 
of @RequestBody which can be supplied using this annotation.
#### @ApiModelPropery
This annotation is used on attribute level. One can use this to supply information like description 
and example values.
### Generate Swagger Document
This is the easiest part. Go to the root of the project and run below command.
```
./gradlew clean generateSwaggerDocumentation
```
HTML document will be generated under ./generated directory. Use any web browser to open the 
document.
### Output
Currently, the project is configured to generate template-driven static HTML document based on
[handlebars](https://github.com/kongchen/api-doc-template) default template. However, it can be 
configured to generate JSON, which can be used by 
[asciidoctor-gradle-plugin](https://asciidoctor.org/docs/asciidoctor-gradle-plugin/) to generate
either PDF or HTML. 