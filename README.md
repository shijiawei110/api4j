#Api4j : 您的接口文档生成小助手

###还在为写接口文档烦恼吗！还在为产品和联调方的催促苦恼吗,快来使用您的接口文档小助手Api4j吧!
----------------
##1：创作初衷
* 业务开发任务紧,手写接口文档麻烦!
* swagger用起来比较麻烦,代码侵入较大,而且有一些类型嵌套的方面的不支持
* 可以自定义输出文档的类型和结构去贴合自己公司的文档类型
* 想帮助大家可以轻松自由又舒服的生成接口文档

##2：目前提供的功能
* 支持springmvc的接口
* 支持dubbo-rest的接口
* 支持jsr和hibernate validator的校验注解
* 支持泛型以及嵌套对象的输出
* 可以生成相应的模拟json请求报文以供调试(curl,postman等)
* 支持控制台打印
* 支持输出md文档


##3：Getting Started
####3.1：添加maven依赖:

```
<dependency>
    <groupId>com.sjw.base</groupId>
    <artifactId>api4j</artifactId>
    <version>1.0.5</version>
</dependency>

```
####3.2：开始使用
1. 在您的mvc或者dubbo-rest等接口的class或者method(推荐)上添加注解@ApiTag
2. 推荐在method上添加注解
3. 注解参数 : name[接口名],value[接口标注],author[接口作者]
4. 可以在方法上添加注释,生成文档的时候会输出注释,但是@author @params 等的doc注释不会被录入输出
5. 使用我们 ApiDocUtil 工具类一键运行输出

```
	/**
     * 这是mvc get test注释，注释会被文档输出
     */
    @RequestMapping("/test")
    @ApiTag(name = "mvc测试接口", value = "这是mvc get test", author = "sjw")
    public Integer getTest1(@RequestParam(value = "test_name", required = false) String name) {
        return null;
    }
    
```

```
	//直接使用即可,将会扫描当前项目下的带有注解的类或者方法,输出文档打印到控制台
	ApiDocUtil.makeApiDoc();
```

```
	//需要输出md文档(默认生成路径在项目路径下的 /src/test/resources/apidoc)
	ApiDocUtil.makeApiDoc(ApiDocConf.defaultConf().mdSet());
	
	//如果需要指定输出目录
	ApiDocConf apiDocConf = new ApiDocConf().mdSet();
	apiDocConf.setDocOutputPath("/Users/download/apidoc");
	ApiDocUtil.makeApiDoc(apiDocConf);
```

```
	//如果需要指定请求地址的公用根路径:
	//比如 指定的请求url为 /getTest/getName 会变成/service/getTest/getName
	ApiDocUtil.makeApiDoc(ApiDocConf.defaultConf("service").mdSet());
```
####3.3：如果你嫌麻烦不想在每个项目依赖api4j,我们可以在当前项目指定去输出其他项目的接口文档

```
		//设置扫描的根路径
		String rootPath = "/Users/javaproject/project";
       ApiDocConf apiDocConf = ApiDocConf.customConf(rootPath);
       //添加类名加方法名 中间用.隔开
       apiDocConf.addMethod("ClassName1.MethodName1");
       apiDocConf.addMethod("ClassName1.MethodName2");
       //同理可以再控制台或者md文档输出
       ApiDocUtil.makeApiDoc(apiDocConf);
```


##4：未来开发计划
* 支持对外网关接口的文档生成
* 支持生成更加丰富的文档形式(比如html等)


## 如果您使用了我的项目请给一个赞吧,怒求赞！如果有建议和BUG请联系我
##联系方式 微信:shijiawei110 
