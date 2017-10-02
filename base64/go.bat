"%jdk%/javac.exe" *.java
if errorlevel 1 exit
java -cp . JarekBase e D:\instal\linux\msys\MSYS-1.0.11.exe c:\temp\1\mingw.txt
fc /b D:\instal\linux\msys\MSYS-1.0.11.exe C:\Temp\1\msys.exe
rem java -cp . JarekBase e D:\instal\linux\msys\show_args.exe c:\temp\1\msys.txt
rem java -cp . JarekBase d c:\temp\1\msys.txt c:\temp\1\msys.exe
rem fc /b D:\instal\linux\msys\show_args.exe c:\temp\1\msys.exe