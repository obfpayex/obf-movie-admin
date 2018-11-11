@echo off

set REMOTE_CMD=/cygdrive/d/deploy/vas-evc/vas-accounting-receiptfile-import/release.cmd
set REMOTE_USR=uadminevc
set REMOTE_PSW=eVCacc1nt

set PLINK=D:\toolz\PuTTY\plink.exe
set TARGET_SERVER=noutv002as.utvnet.net
set PSEXEC_FLAGS= -h -u

echo redeploying on %TARGET_SERVER%
set DEPL_CMD=%PLINK% -ssh -batch -l %REMOTE_USR% -pw %REMOTE_PSW% %TARGET_SERVER% %REMOTE_CMD%
echo %DEPL_CMD%
call %DEPL_CMD%

if NOT %ERRORLEVEL% == 0 cmd /c "exit /b %ERRORLEVEL%"

cmd /c "exit /b %ERRORLEVEL%"
