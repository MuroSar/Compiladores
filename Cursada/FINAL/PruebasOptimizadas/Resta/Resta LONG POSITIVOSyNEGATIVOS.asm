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
cadena@@296 DB "Resta var = -cte anda Long", 0
cadena@@297 DB "Resta var = -cte no anda Long", 0
cadena@@298 DB "Resta var-var = -cte anda Long", 0
cadena@@299 DB "Resta var-var = -cte no anda Long", 0
cadena@@300 DB "Resta var-cte = -cte anda Long", 0
cadena@@301 DB "Resta var-cte = -cte no anda Long", 0
cadena@@302 DB "Resta -cte-var = -cte anda Long", 0
cadena@@303 DB "Resta -cte-var = -cte no anda Long", 0
cadena@@304 DB "Resta -cte-cte = -cte anda Long", 0
cadena@@305 DB "Resta -cte-cte = -cte no anda Long", 0
cadena@@306 DB "Resta var-fn = -cte anda Long", 0
cadena@@307 DB "Resta var-fn = -cte no anda Long", 0
cadena@@308 DB "Resta fn-var = -cte anda Long", 0
cadena@@309 DB "Resta fn-var = -cte no anda Long", 0
cadena@@310 DB "Resta -cte-fn = -cte anda Long", 0
cadena@@311 DB "Resta -cte-fn = -cte no anda Long", 0
cadena@@312 DB "Resta fn-cte = -cte anda Long", 0
cadena@@313 DB "Resta fn-cte = -cte no anda Long", 0
cadena@@314 DB "Resta fn-fn = -cte anda Long", 0
cadena@@315 DB "Resta fn-fn = -cte no anda Long", 0
cadena@@316 DB "Resta var-fn = var anda Long", 0
cadena@@317 DB "Resta var-fn = var no anda Long", 0
cadena@@318 DB "Resta -cte-cte = fn anda Long", 0
cadena@@319 DB "Resta -cte-cte = fn no anda Long", 0
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
invoke MessageBox, NULL, addr cadena@@296, addr cadena@@296, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@297, addr cadena@@297, MB_OK
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
invoke MessageBox, NULL, addr cadena@@298, addr cadena@@298, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@299, addr cadena@@299, MB_OK
Label17:
MOV a@Variable,-10
MOV EAX,a@Variable
SUB EAX,3
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@300, addr cadena@@300, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@301, addr cadena@@301, MB_OK
Label24:
MOV b@Variable,3
MOV EAX,-10
SUB EAX,b@Variable
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,-13
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@302, addr cadena@@302, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@303, addr cadena@@303, MB_OK
Label31:
MOV EAX,-10
SUB EAX,3
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,-13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@304, addr cadena@@304, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@305, addr cadena@@305, MB_OK
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
invoke MessageBox, NULL, addr cadena@@306, addr cadena@@306, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@307, addr cadena@@307, MB_OK
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
invoke MessageBox, NULL, addr cadena@@308, addr cadena@@308, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@309, addr cadena@@309, MB_OK
Label57:
CALL salida3
MOV EAX,-10
SUB EAX,salida3@Funcion
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-13
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@310, addr cadena@@310, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@311, addr cadena@@311, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion

SUB EAX,3
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,-13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@312, addr cadena@@312, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@313, addr cadena@@313, MB_OK
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
invoke MessageBox, NULL, addr cadena@@314, addr cadena@@314, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@315, addr cadena@@315, MB_OK
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
invoke MessageBox, NULL, addr cadena@@316, addr cadena@@316, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@317, addr cadena@@317, MB_OK
Label88:
MOV EAX,-10
SUB EAX,3
MOV var@@aux90,EAX
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@318, addr cadena@@318, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@319, addr cadena@@319, MB_OK
Label97:
invoke ExitProcess, 0
end start