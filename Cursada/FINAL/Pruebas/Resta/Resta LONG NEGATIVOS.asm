.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__MIN_LONG DD -2147483648
__MAX_LONG DD 2147483647
__MIN_DOUBLE DQ 2.2250738585072014E-308
__MAX_DOUBLE DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
salida10@Funcion DD ?
var@@aux58 DD ?
var@@aux18 DD ?
var@@aux2 DD ?
var@@aux65 DD ?
b@Variable DD ?
salida3@Funcion DD ?
var@@aux90 DD ?
c@Variable DD ?
var@@aux51 DD ?
var@@aux11 DD ?
var@@aux38 DD ?
ab1@Variable DD ?
a@Variable DD ?
salida13@Funcion DD ?
var@@aux82 DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux89 DD ?
var@@aux25 DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@24 DB "Resta var = -cte anda Long", 0
cadena@@25 DB "Resta var = -cte no anda Long", 0
cadena@@26 DB "Resta var-var = -cte anda Long", 0
cadena@@27 DB "Resta var-var = -cte no anda Long", 0
cadena@@28 DB "Resta var-cte = -cte anda Long", 0
cadena@@29 DB "Resta var-cte = -cte no anda Long", 0
cadena@@30 DB "Resta -cte-var = -cte anda Long", 0
cadena@@31 DB "Resta -cte-var = -cte no anda Long", 0
cadena@@32 DB "Resta -cte-cte = -cte anda Long", 0
cadena@@33 DB "Resta -cte-cte = -cte no anda Long", 0
cadena@@34 DB "Resta var-fn = -cte anda Long", 0
cadena@@35 DB "Resta var-fn = -cte no anda Long", 0
cadena@@36 DB "Resta fn-var = -cte anda Long", 0
cadena@@37 DB "Resta fn-var = -cte no anda Long", 0
cadena@@38 DB "Resta -cte-fn = -cte anda Long", 0
cadena@@39 DB "Resta -cte-fn = -cte no anda Long", 0
cadena@@40 DB "Resta fn-cte = -cte anda Long", 0
cadena@@41 DB "Resta fn-cte = -cte no anda Long", 0
cadena@@42 DB "Resta fn-fn = -cte anda Long", 0
cadena@@43 DB "Resta fn-fn = -cte no anda Long", 0
cadena@@44 DB "Resta var-fn = var anda Long", 0
cadena@@45 DB "Resta var-fn = var no anda Long", 0
cadena@@46 DB "Resta -cte-cte = fn anda Long", 0
cadena@@47 DB "Resta -cte-cte = fn no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
MOV ab1@Variable,-3
MOV EAX,ab1@Variable
MOV salida3@Funcion,EAX
RET
salida3 endp
salida10 proc
MOV ab1@Variable,-10
MOV EAX,ab1@Variable
MOV salida10@Funcion,EAX
RET
salida10 endp
salida13 proc
MOV ab1@Variable,-13
MOV EAX,ab1@Variable
MOV salida13@Funcion,EAX
RET
salida13 endp
start:
MOV a@Variable,-10
MOV b@Variable,-3
MOV EAX,a@Variable
SUB EAX,b@Variable
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,-7
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@24, addr cadena@@24, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@25, addr cadena@@25, MB_OK
Label9:
MOV a@Variable,-10
MOV b@Variable,-3
MOV EAX,a@Variable
SUB EAX,b@Variable
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-7
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@26, addr cadena@@26, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@27, addr cadena@@27, MB_OK
Label17:
MOV a@Variable,-10
MOV EAX,a@Variable
SUB EAX,3
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@28, addr cadena@@28, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@29, addr cadena@@29, MB_OK
Label24:
MOV b@Variable,-3
MOV EAX,-10
SUB EAX,b@Variable
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,-7
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@30, addr cadena@@30, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@31, addr cadena@@31, MB_OK
Label31:
MOV EAX,-10
SUB EAX,3
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,-13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@32, addr cadena@@32, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@33, addr cadena@@33, MB_OK
Label37:
Label39:
MOV a@Variable,-10
CALL salida3
MOV EAX,a@Variable
SUB EAX,salida3@Funcion
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-7
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@34, addr cadena@@34, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@35, addr cadena@@35, MB_OK
Label49:
MOV b@Variable,-3
CALL salida10
MOV EAX,salida10@Funcion

SUB EAX,b@Variable
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,-7
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@36, addr cadena@@36, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@37, addr cadena@@37, MB_OK
Label57:
CALL salida3
MOV EAX,-10
SUB EAX,salida3@Funcion
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-7
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@38, addr cadena@@38, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@39, addr cadena@@39, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion

SUB EAX,3
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,-13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@40, addr cadena@@40, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@41, addr cadena@@41, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion

SUB EAX,salida3@Funcion
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,-7
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@42, addr cadena@@42, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@43, addr cadena@@43, MB_OK
Label79:
MOV a@Variable,-10
MOV c@Variable,-7
CALL salida3
MOV EAX,a@Variable
SUB EAX,salida3@Funcion
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@44, addr cadena@@44, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@45, addr cadena@@45, MB_OK
Label88:
MOV EAX,-10
SUB EAX,3
MOV var@@aux90,EAX
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@46, addr cadena@@46, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@47, addr cadena@@47, MB_OK
Label97:
invoke ExitProcess, 0
end start