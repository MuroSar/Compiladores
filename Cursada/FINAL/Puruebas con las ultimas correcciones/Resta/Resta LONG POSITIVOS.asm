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
c@Variable@main DD ?
var@@aux90 DD ?
var@@aux51 DD ?
var@@aux11 DD ?
salida10@Funcion@main DD ?
var@@aux38 DD ?
ab1@Variable@main@salida7 DD ?
ab1@Variable@main@salida3 DD ?
ab1@Variable@main@salida10 DD ?
var@@aux82 DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux89 DD ?
var@@aux25 DD ?
b@Variable@main DD ?
var@@aux73 DD ?
var@@aux31 DD ?
salida7@Funcion@main DD ?
cadena@@408 DB "Resta var = cte anda Long", 0
cadena@@409 DB "Resta var = cte no anda Long", 0
cadena@@410 DB "Resta var-var = cte anda Long", 0
cadena@@411 DB "Resta var-var = cte no anda Long", 0
cadena@@412 DB "Resta var-cte = cte anda Long", 0
cadena@@413 DB "Resta var-cte = cte no anda Long", 0
cadena@@414 DB "Resta cte-var = cte anda Long", 0
cadena@@415 DB "Resta cte-var = cte no anda Long", 0
cadena@@416 DB "Resta cte-cte = cte anda Long", 0
cadena@@417 DB "Resta cte-cte = cte no anda Long", 0
cadena@@418 DB "Resta var-fn = cte anda Long", 0
cadena@@419 DB "Resta var-fn = cte no anda Long", 0
cadena@@420 DB "Resta fn-var = cte anda Long", 0
cadena@@421 DB "Resta fn-var = cte no anda Long", 0
cadena@@422 DB "Resta cte-fn = cte anda Long", 0
cadena@@423 DB "Resta cte-fn = cte no anda Long", 0
cadena@@424 DB "Resta fn-cte = cte anda Long", 0
cadena@@425 DB "Resta fn-cte = cte no anda Long", 0
cadena@@426 DB "Resta fn-fn = cte anda Long", 0
cadena@@427 DB "Resta fn-fn = cte no anda Long", 0
cadena@@428 DB "Resta var-fn = var anda Long", 0
cadena@@429 DB "Resta var-fn = var no anda Long", 0
cadena@@430 DB "Resta cte-cte = fn anda Long", 0
cadena@@431 DB "Resta cte-cte = fn no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
MOV ab1@Variable@main@salida3,3
MOV EAX,ab1@Variable@main@salida3
MOV salida3@Funcion@main,EAX
RET
salida3 endp
salida10 proc
MOV ab1@Variable@main@salida10,10
MOV EAX,ab1@Variable@main@salida10
MOV salida10@Funcion@main,EAX
RET
salida10 endp
salida7 proc
MOV ab1@Variable@main@salida7,7
MOV EAX,ab1@Variable@main@salida7
MOV salida7@Funcion@main,EAX
RET
salida7 endp
start:
MOV a@Variable@main,10
MOV b@Variable@main,3
MOV EAX,a@Variable@main
SUB EAX,b@Variable@main
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,7
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@408, addr cadena@@408, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@409, addr cadena@@409, MB_OK
Label9:
MOV a@Variable@main,10
MOV b@Variable@main,3
MOV EAX,a@Variable@main
SUB EAX,b@Variable@main
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,7
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@410, addr cadena@@410, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@411, addr cadena@@411, MB_OK
Label17:
MOV a@Variable@main,10
MOV EAX,a@Variable@main
SUB EAX,3
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,7
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@412, addr cadena@@412, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@413, addr cadena@@413, MB_OK
Label24:
MOV b@Variable@main,3
MOV EAX,10
SUB EAX,b@Variable@main
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,7
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@414, addr cadena@@414, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@415, addr cadena@@415, MB_OK
Label31:
MOV EAX,10
SUB EAX,3
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,7
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@416, addr cadena@@416, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@417, addr cadena@@417, MB_OK
Label37:
Label39:
MOV a@Variable@main,10
CALL salida3
MOV EAX,a@Variable@main
SUB EAX,salida3@Funcion@main
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,7
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@418, addr cadena@@418, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@419, addr cadena@@419, MB_OK
Label49:
MOV b@Variable@main,3
CALL salida10
MOV EAX,salida10@Funcion@main

SUB EAX,b@Variable@main
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,7
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@420, addr cadena@@420, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@421, addr cadena@@421, MB_OK
Label57:
CALL salida3
MOV EAX,10
SUB EAX,salida3@Funcion@main
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,7
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@422, addr cadena@@422, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@423, addr cadena@@423, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion@main

SUB EAX,3
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,7
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@424, addr cadena@@424, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@425, addr cadena@@425, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion@main

SUB EAX,salida3@Funcion@main
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,7
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@426, addr cadena@@426, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@427, addr cadena@@427, MB_OK
Label79:
MOV a@Variable@main,10
MOV c@Variable@main,7
CALL salida3
MOV EAX,a@Variable@main
SUB EAX,salida3@Funcion@main
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable@main
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@428, addr cadena@@428, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@429, addr cadena@@429, MB_OK
Label88:
MOV EAX,10
SUB EAX,3
MOV var@@aux90,EAX
CALL salida7
MOV EAX,var@@aux90
CMP EAX,salida7@Funcion@main
JNE Label96
invoke MessageBox, NULL, addr cadena@@430, addr cadena@@430, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@431, addr cadena@@431, MB_OK
Label97:
invoke ExitProcess, 0
end start