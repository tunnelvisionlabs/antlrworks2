@ECHO OFF

set ANTLR4_VERSION=4.0-SNAPSHOT
SET ANTLR3_VERSION=3.5-rc-1
SET ST4_VERSION=4.0.7-SNAPSHOT

SET ANTLR3_MAVEN_HOME=%USERPROFILE%\.m2\repository\org\antlr
SET ANTLR4_MAVEN_HOME=%USERPROFILE%\.m2\repository\com\tunnelvisionlabs

set ANTLR3_RUNTIME_CLASSPATH=%ANTLR3_MAVEN_HOME%\antlr-runtime\%ANTLR3_VERSION%\antlr-runtime-%ANTLR3_VERSION%.jar
set ANTLR4_RUNTIME_CLASSPATH=%ANTLR4_MAVEN_HOME%\antlr4-runtime\%ANTLR4_VERSION%\antlr4-runtime-%ANTLR4_VERSION%.jar

set ST4_CLASSPATH=%ANTLR3_MAVEN_HOME%\ST4\%ST4_VERSION%\ST4-%ST4_VERSION%.jar;%ANTLR3_RUNTIME_CLASSPATH%

set ANTLR3_CLASSPATH=%ANTLR3_MAVEN_HOME%\antlr\%ANTLR3_VERSION%\antlr-%ANTLR3_VERSION%.jar;%ST4_CLASSPATH%
set ANTLR4_CLASSPATH=%ANTLR4_MAVEN_HOME%\antlr4\%ANTLR4_VERSION%\antlr4-%ANTLR4_VERSION%.jar;%ST4_CLASSPATH%;%ANTLR4_RUNTIME_CLASSPATH%

set ANTLR3_TOOL=-cp "%ANTLR3_CLASSPATH%" org.antlr.Tool
set ANTLR4_TOOL=-cp "%ANTLR4_CLASSPATH%" org.antlr.v4.Tool

set SOURCE_ROOT=%CD%

set CURRENT_SOURCE=%SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\grammar\experimental
start /D %CURRENT_SOURCE% /B "Title" "%JAVA_HOME%\bin\java.exe" %ANTLR4_TOOL% -package org.antlr.works.editor.grammar.experimental -visitor -Xforce-atn *.g4

set CURRENT_SOURCE=%SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\grammar\highlighter4
start /D %CURRENT_SOURCE% /B "Title" "%JAVA_HOME%\bin\java.exe" %ANTLR4_TOOL% -package org.antlr.works.editor.grammar.highlighter4 *.g4

set CURRENT_SOURCE=%SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\st4\experimental
start /D %CURRENT_SOURCE% /B "Title" "%JAVA_HOME%\bin\java.exe" %ANTLR4_TOOL% -package org.antlr.works.editor.st4.experimental -visitor -Xforce-atn *.g4

set CURRENT_SOURCE=%SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\st4\highlighter4
start /D %CURRENT_SOURCE% /WAIT /B "Title" "%JAVA_HOME%\bin\java.exe" %ANTLR4_TOOL% -package org.antlr.works.editor.st4.highlighter4 *.g4

cd %SOURCE_ROOT%
