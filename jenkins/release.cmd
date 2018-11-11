@ECHO OFF

SET mydate=
for /f "skip=1" %%d in ('wmic os get localdatetime') do if not defined mydate set mydate=%%d
SET today=%mydate:~0,4%%mydate:~4,2%%mydate:~6,2%-%mydate:~8,2%%mydate:~10,2%%mydate:~12,2%

SET SVCNAME="Vas Accounting Receiptfile Import"
SET SYSNAME=vas-accounting-receiptfile-import
SET SYSVERSION=1.0
SET PRODUCT=accounting
SET SYSDIR=D:\PXS\pxs_services\bin\%PRODUCT%\%SYSNAME%\%SYSVERSION%
SET CONFDIR=D:\PXS\pxs_services\etc\%PRODUCT%\%SYSNAME%\%SYSVERSION%
SET BACKUPDIR=D:\backup\vas-evc\%SYSNAME%\%today%


SET LogDir=E:\Jenkins\DeployLogs\%SYSNAME%\%today%
SET LogFile=%LogDir%\"%~n0".log

SET CurrentDir=%~dp0
SET DeplRoot=%CurrentDir%

if not exist %LogDir% mkdir %LogDir%
if not exist %SYSDIR%\stdOut mkdir %SYSDIR%\stdOut

echo ####################   START  [%TIME%]     ####################### 1>> %LogFile% 2>&1
echo Script is running on %COMPUTERNAME% 1>> %LogFile% 2>&1

call %CurrentDir%/helpers/backup.cmd
call %CurrentDir%/helpers/deploy.cmd
