# scalaSnippet  [ ![Codeship Status for notyy/sbtTemplate](https://codeship.io/projects/6547f7b0-1ee8-0132-7537-727422619b42/status)](https://codeship.io/projects/35587)

***scala各种代码片段，是我在培训和指导scala开发的过程中提取而来***

* 运行sbt test会编译并运行自动化单元测试
* sbt test-only -- -n FunctionTest 可运行打了FunctionTest tag的测试

## release note
2014.11.02 工程改名为scalaSnippet，我将另外弄一个giter8格式的工程模板

2014.10.21 增加了例子展示怎么使用par集合

2014.09.15 intellij的scala插件自动导入的sbt工程还是有种种问题，因此还是恢复了gen-idea插件,另外，删除了it和ft目录，还是用tag来区分吧

2014.08.24 配置了FunctionTest tag，作为用tag来区分测试的例子

2014.08.21 发现logback配置错误，现已改正

2014.08.15 在build.properties里指定sbt为1.3.5

2014.08.07  升级到scala2.11.2，相应的升级了第三方库，同时添加了大量代码例子
             
2014.05.03 将集成测试和功能测试分别放在src/it/scala和src/ft/scala下，现在用户可以执行sbt ft:test和sbt it:test运行不同的测试，
sbt test仍然运行默认的src/test/scala下的单元测试。

2014.04.05 升级到sbt1.3.1, scala 2.10.4，之前的版本打了个2.9.2的tag，将不再维护

