name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"), 
  "org.hibernate" % "hibernate-entitymanager" % "4.3.0.Final",
  "org.apache.commons" % "commons-csv" % "1.1",
  "com.restfb" % "restfb" % "1.14.0",  
  "com.google.guava" % "guava" % "18.0",
  cache,
  javaWs
  
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


fork in run := true