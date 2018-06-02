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
cadena@@494 DB "Resta var = -cte anda Long", 0
cadena@@495 DB "Resta var = -cte no anda Long", 0
cadena@@496 DB "Resta var-var = -cte anda Long", 0
cadena@@497 DB "Resta var-var = -cte no anda Long", 0
cadena@@498 DB "Resta var-cte = -cte anda Long", 0
cadena@@499 DB "Resta var-cte = -cte no anda Long", 0
cadena@@500 DB "Resta -cte-var = -cte anda Long", 0
cadena@@501 DB "Resta -cte-var = -cte no anda Long", 0
cadena@@502 DB "Resta -cte-cte = -cte anda Long", 0
cadena@@503 DB "Resta -cte-cte = -cte no anda Long", 0
cadena@@504 DB "Resta var-fn = -cte anda Long", 0
cadena@@505 DB "Resta var-fn = -cte no anda Long", 0
cadena@@506 DB "Resta fn-var = -cte anda Long", 0
cadena@@507 DB "Resta fn-var = -cte no anda Long", 0
cadena@@508 DB "Resta -cte-fn = -cte anda Long", 0
cadena@@509 DB "Resta -cte-fn = -cte no anda Long", 0
cadena@@510 DB "Resta fn-cte = -cte anda Long", 0
cadena@@511 DB "Resta fn-cte = -cte no anda Long", 0
cadena@@512 DB "Resta fn-fn = -cte anda Long", 0
cadena@@513 DB "Resta fn-fn = -cte no anda Long", 0
cadena@@514 DB "Resta var-fn = var anda Long", 0
cadena@@515 DB "Resta var-fn = var no anda Long", 0
cadena@@516 DB "Resta -cte-cte = fn anda Long", 0
cadena@@517 DB "Resta -cte-cte = fn no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
MOV ab1@Variable,3
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
MOV b@Variable,3
MOV EAX,a@Variable
SUB EAX,b@Variable
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,-13
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@494, addr cadena@@494, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@495, addr cadena@@495, MB_OK
Label9:
MOV a@Variable,-10
MOV b@Variable,3
MOV EAX,a@Variable
SUB EAX,b@Variable
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-13
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@496, addr cadena@@496, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@497, addr cadena@@497, MB_OK
Label17:
MOV a@Variable,-10
MOV EAX,a@Variable
SUB EAX,3
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@498, addr cadena@@498, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@499, addr cadena@@499, MB_OK
Label24:
MOV b@Variable,3
MOV EAX,-10
SUB EAX,b@Variable
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,-13
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@500, addr cadena@@500, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@501, addr cadena@@501, MB_OK
Label31:
MOV EAX,-10
SUB EAX,3
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,-13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@502, addr cadena@@502, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@503, addr cadena@@503, MB_OK
Label37:
Label39:
MOV a@Variable,-10
CALL salida3
MOV EAX,a@Variable
SUB EAX,salida3@Funcion
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-13
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@504, addr cadena@@504, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@505, addr cadena@@505, MB_OK
Label49:
MOV b@Variable,3
CALL salida10
MOV EAX,salida10@Funcion

SUB EAX,b@Variable
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,-13
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@506, addr cadena@@506, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@507, addr cadena@@507, MB_OK
Label57:
CALL salida3
MOV EAX,-10
SUB EAX,salida3@Funcion
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-13
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@508, addr cadena@@508, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@509, addr cadena@@509, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion

SUB EAX,3
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,-13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@510, addr cadena@@510, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@511, addr cadena@@511, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion

SUB EAX,salida3@Funcion
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,-13
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@512, addr cadena@@512, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@513, addr cadena@@513, MB_OK
Label79:
MOV a@Variable,-10
MOV c@Variable,-13
CALL salida3
MOV EAX,a@Variable
SUB EAX,salida3@Funcion
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@514, addr cadena@@514, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@515, addr cadena@@515, MB_OK
Label88:
MOV EAX,-10
SUB EAX,3
MOV var@@aux90,EAX
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@516, addr cadena@@516, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@517, addr cadena@@517, MB_OK
Label97:
invoke ExitProcess, 0
end start