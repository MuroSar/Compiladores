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
cadena@@458 DB "Resta var = -cte anda Long", 0
cadena@@459 DB "Resta var = -cte no anda Long", 0
cadena@@460 DB "Resta var-var = -cte anda Long", 0
cadena@@461 DB "Resta var-var = -cte no anda Long", 0
cadena@@462 DB "Resta var-cte = -cte anda Long", 0
cadena@@463 DB "Resta var-cte = -cte no anda Long", 0
cadena@@464 DB "Resta -cte-var = -cte anda Long", 0
cadena@@465 DB "Resta -cte-var = -cte no anda Long", 0
cadena@@466 DB "Resta -cte-cte = -cte anda Long", 0
cadena@@467 DB "Resta -cte-cte = -cte no anda Long", 0
cadena@@468 DB "Resta var-fn = -cte anda Long", 0
cadena@@469 DB "Resta var-fn = -cte no anda Long", 0
cadena@@470 DB "Resta fn-var = -cte anda Long", 0
cadena@@471 DB "Resta fn-var = -cte no anda Long", 0
cadena@@472 DB "Resta -cte-fn = -cte anda Long", 0
cadena@@473 DB "Resta -cte-fn = -cte no anda Long", 0
cadena@@474 DB "Resta fn-cte = -cte anda Long", 0
cadena@@475 DB "Resta fn-cte = -cte no anda Long", 0
cadena@@476 DB "Resta fn-fn = -cte anda Long", 0
cadena@@477 DB "Resta fn-fn = -cte no anda Long", 0
cadena@@478 DB "Resta var-fn = var anda Long", 0
cadena@@479 DB "Resta var-fn = var no anda Long", 0
cadena@@480 DB "Resta -cte-cte = fn anda Long", 0
cadena@@481 DB "Resta -cte-cte = fn no anda Long", 0
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
MOV b@Variable@main,3
MOV EAX,a@Variable@main
SUB EAX,b@Variable@main
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,-13
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@458, addr cadena@@458, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@459, addr cadena@@459, MB_OK
Label9:
MOV a@Variable@main,-10
MOV b@Variable@main,3
MOV EAX,a@Variable@main
SUB EAX,b@Variable@main
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-13
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@460, addr cadena@@460, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@461, addr cadena@@461, MB_OK
Label17:
MOV a@Variable@main,-10
MOV EAX,a@Variable@main
SUB EAX,3
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@462, addr cadena@@462, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@463, addr cadena@@463, MB_OK
Label24:
MOV b@Variable@main,3
MOV EAX,-10
SUB EAX,b@Variable@main
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,-13
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@464, addr cadena@@464, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@465, addr cadena@@465, MB_OK
Label31:
MOV EAX,-10
SUB EAX,3
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,-13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@466, addr cadena@@466, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@467, addr cadena@@467, MB_OK
Label37:
Label39:
MOV a@Variable@main,-10
CALL salida3
MOV EAX,a@Variable@main
SUB EAX,salida3@Funcion@main
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-13
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@468, addr cadena@@468, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@469, addr cadena@@469, MB_OK
Label49:
MOV b@Variable@main,3
CALL salida10
MOV EAX,salida10@Funcion@main

SUB EAX,b@Variable@main
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,-13
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@470, addr cadena@@470, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@471, addr cadena@@471, MB_OK
Label57:
CALL salida3
MOV EAX,-10
SUB EAX,salida3@Funcion@main
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-13
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@472, addr cadena@@472, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@473, addr cadena@@473, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion@main

SUB EAX,3
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,-13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@474, addr cadena@@474, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@475, addr cadena@@475, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion@main

SUB EAX,salida3@Funcion@main
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,-13
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@476, addr cadena@@476, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@477, addr cadena@@477, MB_OK
Label79:
MOV a@Variable@main,-10
MOV c@Variable@main,-13
CALL salida3
MOV EAX,a@Variable@main
SUB EAX,salida3@Funcion@main
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable@main
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@478, addr cadena@@478, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@479, addr cadena@@479, MB_OK
Label88:
MOV EAX,-10
SUB EAX,3
MOV var@@aux90,EAX
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion@main
JNE Label96
invoke MessageBox, NULL, addr cadena@@480, addr cadena@@480, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@481, addr cadena@@481, MB_OK
Label97:
invoke ExitProcess, 0
end start