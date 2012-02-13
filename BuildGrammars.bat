set ANTLR4_VERSION=4.0-SNAPSHOT
set ANTLR3_VERSION=3.4.1-SNAPSHOT
set ST4_VERSION=4.0.4-SNAPSHOT

set ANTLR_MAVEN_HOME=%USERPROFILE%\.m2\repository\org\antlr

set ANTLR3_RUNTIME_CLASSPATH=%ANTLR_MAVEN_HOME%\antlr-runtime\%ANTLR3_VERSION%\antlr-runtime-%ANTLR3_VERSION%.jar
set ANTLR4_RUNTIME_CLASSPATH=%ANTLR_MAVEN_HOME%\antlr4-runtime\%ANTLR4_VERSION%\antlr4-runtime-%ANTLR4_VERSION%.jar

set ST4_CLASSPATH=%ANTLR_MAVEN_HOME%\ST4\%ST4_VERSION%\ST4-%ST4_VERSION%.jar;%ANTLR3_RUNTIME_CLASSPATH%

set ANTLR3_CLASSPATH=%ANTLR_MAVEN_HOME%\antlr\%ANTLR3_VERSION%\antlr-%ANTLR3_VERSION%.jar;%ST4_CLASSPATH%
set ANTLR4_CLASSPATH=%ANTLR_MAVEN_HOME%\antlr4\%ANTLR4_VERSION%\antlr4-%ANTLR4_VERSION%.jar;%ST4_CLASSPATH%;%ANTLR4_RUNTIME_CLASSPATH%

set ANTLR3_TOOL=-cp %ANTLR3_CLASSPATH% org.antlr.Tool
set ANTLR4_TOOL=-cp %ANTLR4_CLASSPATH% org.antlr.v4.Tool

set SOURCE_ROOT=%CD%

cd %SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\grammar\experimental
java %ANTLR4_TOOL% -Xforce-atn *.g

cd %SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\grammar\highlighter4
java %ANTLR4_TOOL% *.g4

cd %SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\st4\experimental
java %ANTLR4_TOOL% -Xforce-atn *.g4

cd %SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\st4\highlighter4
java %ANTLR4_TOOL% *.g4

cd %SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\grammar\highlighter
java %ANTLR3_TOOL% *.g

cd %SOURCE_ROOT%\org-antlr-works-editor\src\org\antlr\works\editor\st4\highlighter
java %ANTLR3_TOOL% *.g

cd %SOURCE_ROOT%
