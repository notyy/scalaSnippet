sbtTemplate
===========


scala sbt工程模板，配置了单元测试scaltest,日志引擎logback,eclipse项目生成等插件

* 运行sbt test会编译并运行自动化单元测试
* 运行sbt it:test运行src/it/scala下的集成测试
* 运行sbt it:test运行src/ft/scala下的功能测试
* 自动化测试生成的报告文件可在target/html-(test|it|ft)-report下打开index.html查看

* 注意，idea13支持直接导入sbt项目（用idea打开build.sbt文件），因此去掉了sbt-idea插件
* 注意，idea默认不认识新加入的src/it和src/ft目录，需要手动将其中的子目录设置为测试代码路径

release note
----------------------
2014.05.03 将集成测试和功能测试分别放在src/it/scala和src/ft/scala下，现在用户可以执行sbt ft:test和sbt it:test运行不同的测试，
sbt test仍然运行默认的src/test/scala下的单元测试。

2014.04.05 升级到sbt1.3.1, scala 2.10.4，之前的版本打了个2.9.2的tag，将不再维护

