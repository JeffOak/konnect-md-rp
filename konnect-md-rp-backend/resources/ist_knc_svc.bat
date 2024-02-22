@echo off
SC QUERY KncMdRp > NUL
IF ERRORLEVEL 1060 GOTO MISSING
sc stop KncMdRp
GOTO END

:MISSING
C:\KonnectMDRP\lib\bin\kncmdrp\service.exe install

:END