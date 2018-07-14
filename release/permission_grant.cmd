@echo off
SET ADB=D:\window\android_sdk\platform-tools\adb.exe
SET PACKAGE=com.karrel.colloc
SET PERMISSION=android.permission.ACCESS_FINE_LOCATION
SET DEVICE=21215958f00d7ece
echo Grant permission : %PERMISSION%
%ADB% -d -s %DEVICE% shell pm grant %PACKAGE% %PERMISSION%
