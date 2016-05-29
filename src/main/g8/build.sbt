lazy val root = project.in(file(".")).aggregate($name;format="camel"$JS, $name;format="camel"$JVM).
  settings(
    publishArtifact := false,
    crossScalaVersions := Seq("$scalaVersion$"),
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    sonatypeProfileName := "$organization$",
    {
      import ReleaseTransformations._
      releaseProcess := Seq[ReleaseStep](
        checkSnapshotDependencies,
        inquireVersions,
        runClean,
        runTest,
        setReleaseVersion,
        commitReleaseVersion,
        tagRelease,
        ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
        setNextVersion,
        commitNextVersion,
        ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
        pushChanges
    )}
  )

lazy val $name;format="camel"$Cross = crossProject.in(file(".")).
  settings(
    name := "$name$",
    organization := "$organization$",
    version := "$version$",
    scalaVersion := "$scalaVersion$",
    libraryDependencies ++= Seq(
      $compileDep$,
      $testDep$ %  "test"
    ),
    publishTo <<= version { (v: String) =>
      Some("releases"  at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
    },
    publishMavenStyle := true,
    pomExtra :=
      <url>https://github.com/$developer$/$name;format="camel"$</url>
      <scm>
        <url>git://github.com/$developer$/$name;format="camel"$.git</url>
      </scm>
      <developers>
        <developer>
          <id>$developer$</id>
          <name>$developer$</name>
          <url>https://github.com/$developer$</url>
        </developer>
      </developers>,
    licenses += $license$
  )

lazy val $name;format="camel"$JVM = $name;format="camel"$Cross.jvm
lazy val $name;format="camel"$JS = $name;format="camel"$Cross.js