## Scala Testing example

Note. I didn't have time to right actual classesq

To run the tests type `test` at the sbt prompt. To run a single test type `testOnly` followed by the fully qualified class name. In IntelliJ right click in the test class

[build.sbt](build.sbt) Notice that the test dependencies are scoped as `test` This means the classes are only avalaible under `src/test/scala` and not `src/main/scala`. Also at the `sbt` prompt,  `compile`
won't flag any compilations in classes under `src/test/scala`, you need `test:compile` 

[FunSuiteExample](src/test/scala/com/gu/nathaniel/FunSuiteExample.scala) Funsuite example. Each test wrap `assert` statements. If any fail the test fails. Test equality and whether an exception is trown

[FlatSpecExample](src/test/scala/com/gu/nathaniel/FlatSpecExample.scala) As recommended by scalatest dudes. Flat because the tests are unnessted. The `should` keyword facillitates BDD style testing, the `it` keyword grouping of tests

[AnyFunSpecExample](src/test/scala/com/gu/nathaniel/AnyFunSpecExample.scala) a deeper BDD approach. Nest tests together

[BeforeAndAfterExampleSpec](src/test/scala/com/gu/nathaniel/BeforeAndAfterExampleSpec.scala) use the `BeforeAndAfter` trait to set up ( and reset ) global objedct before and after each test

[FlatSpecTraitExample](src/test/scala/com/gu/nathaniel/FlatSpecTraitExample.scala) Use a trait to set up objects for each test. Useful when you need to setup ( or mock ) several objects for your tests, but with slight differences ( extend the trait)

[FlatSpecMatchingExample](src/test/scala/com/gu/nathaniel/FlatSpecMatchingExample) Matchers are a DSL for expressing assertions, using the `should` keyword. Use them by mixing in the `should.Matchers` trait. Note there's also a `must.Matchers` trait but this a semantic difference.   

[CustomMatchingSpecExample](src/test/scala/com/gu/nathaniel/CustomMatchingSpecExample) `matchers` provide a whole suite of stuff for testing primatives, strings and collections, but we can write custom matchers to test whatever we like. As a simple example [MyCustomMatchers](src/test/scala/com/gu/nathaniel/MyCustomMatchers) tests the extension of a `File` object
