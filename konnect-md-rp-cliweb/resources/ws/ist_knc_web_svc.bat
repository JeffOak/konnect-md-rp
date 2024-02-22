@echo off
SC QUERY KncMdRpCliWeb > NUL
IF ERRORLEVEL 1060 GOTO MISSING
sc stop KncMdRpCliWeb
GOTO END

:MISSING
C:\KonnectMDRP\lib\bin\ws\service.exe install

:END