.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__CERO DQ 0.0
__MIN_DOUBLE DQ 2.2250738585072014E-308
__MAX_DOUBLE DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
var@@aux58 DD ?
var@@aux18 DD ?
a@Variable@main DD ?
salida3@Funcion@main DD ?
var@@aux2 DD ?
var@@aux65 DD ?
salida13@Funcion@main DD ?
c@Variable@main DD ?
var@@aux90 DD ?
var@@aux51 DD ?
var@@aux11 DD ?
salida10@Funcion@main DD ?
var@@aux38 DD ?
ab1@Variable@main@salida3 DD ?
ab1@Variable@main@salida13 DD ?
ab1@Variable@main@salida10 DD ?
var@@aux82 DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux89 DD ?
var@@aux25 DD ?
b@Variable@main DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@384 DB "Resta var = -cte anda Long", 0
cadena@@385 DB "Resta var = -cte no anda Long", 0
cadena@@386 DB "Resta var-var = -cte anda Long", 0
cadena@@387 DB "Resta var-var = -cte no anda Long", 0
cadena@@388 DB "Resta var-cte = -cte anda Long", 0
cadena@@389 DB "Resta var-cte = -cte no anda Long", 0
cadena@@390 DB "Resta -cte-var = -cte anda Long", 0
cadena@@391 DB "Resta -cte-var = -cte no anda Long", 0
cadena@@392 DB "Resta -cte-cte = -cte anda Long", 0
cadena@@393 DB "Resta -cte-cte = -cte no anda Long", 0
cadena@@394 DB "Resta var-fn = -cte anda Long", 0
cadena@@395 DB "Resta var-fn = -cte no anda Long", 0
cadena@@396 DB "Resta fn-var = -cte anda Long", 0
cadena@@397 DB "Resta fn-var = -cte no anda Long", 0
cadena@@398 DB "Resta -cte-fn = -cte anda Long", 0
cadena@@399 DB "Resta -cte-fn = -cte no anda Long", 0
cadena@@400 DB "Resta fn-cte = -cte anda Long", 0
cadena@@401 DB "Resta fn-cte = -cte no anda Long", 0
cadena@@402 DB "Resta fn-fn = -cte anda Long", 0
cadena@@403 DB "Resta fn-fn = -cte no anda Long", 0
cadena@@404 DB "Resta var-fn = var anda Long", 0
cadena@@405 DB "Resta var-fn = var no anda Long", 0
cadena@@406 DB "Resta -cte-cte = fn anda Long", 0
cadena@@407 DB "Resta -cte-cte = fn no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
MOV ab1@Variable@main@salida3,-3
MOV EAX,ab1@Variable@main@salida3
MOV salida3@Funcion@main,EAX
RET
salida3 endp
salida10 proc
MOV ab1@Variable@main@salida10,-10
MOV EAX,ab1@Variable@main@salida10
MOV salida10@Funcion@main,EAX
RET
salida10 endp
salida13 proc
MOV ab1@Variable@main@salida13,-13
MOV EAX,ab1@Variable@main@salida13
MOV salida13@Funcion@main,EAX
RET
salida13 endp
start:
MOV a@Variable@main,-10
MOV b@Variable@main,-3
MOV EAX,a@Variable@main
SUB EAX,b@Variable@main
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,-7
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@384, addr cadena@@384, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@385, addr cadena@@385, MB_OK
Label9:
MOV a@Variable@main,-10
MOV b@Variable@main,-3
MOV EAX,a@Variable@main
SUB EAX,b@Variable@main
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-7
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@386, addr cadena@@386, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@387, addr cadena@@387, MB_OK
Label17:
MOV a@Variable@main,-10
MOV EAX,a@Variable@main
SUB EAX,3
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@388, addr cadena@@388, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@389, addr cadena@@389, MB_OK
Label24:
MOV b@Variable@main,-3
MOV EAX,-10
SUB EAX,b@Variable@main
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,-7
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@390, addr cadena@@390, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@391, addr cadena@@391, MB_OK
Label31:
MOV EAX,-10
SUB EAX,3
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,-13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@392, addr cadena@@392, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@393, addr cadena@@393, MB_OK
Label37:
Label39:
MOV a@Variable@main,-10
CALL salida3
MOV EAX,a@Variable@main
SUB EAX,salida3@Funcion@main
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-7
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@394, addr cadena@@394, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@395, addr cadena@@395, MB_OK
Label49:
MOV b@Variable@main,-3
CALL salida10
MOV EAX,salida10@Funcion@main

SUB EAX,b@Variable@main
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,-7
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@396, addr cadena@@396, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@397, addr cadena@@397, MB_OK
Label57:
CALL salida3
MOV EAX,-10
SUB EAX,salida3@Funcion@main
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-7
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@398, addr cadena@@398, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@399, addr cadena@@399, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion@main

SUB EAX,3
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,-13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@400, addr cadena@@400, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@401, addr cadena@@401, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion@main

SUB EAX,salida3@Funcion@main
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,-7
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@402, addr cadena@@402, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@403, addr cadena@@403, MB_OK
Label79:
MOV a@Variable@main,-10
MOV c@Variable@main,-7
CALL salida3
MOV EAX,a@Variable@main
SUB EAX,salida3@Funcion@main
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable@main
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@404, addr cadena@@404, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@405, addr cadena@@405, MB_OK
Label88:
MOV EAX,-10
SUB EAX,3
MOV var@@aux90,EAX
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion@main
JNE Label96
invoke MessageBox, NULL, addr cadena@@406, addr cadena@@406, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@407, addr cadena@@407, MB_OK
Label97:
invoke ExitProcess, 0
end start