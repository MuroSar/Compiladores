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
var@@aux82 DD ?
salida30@Funcion DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux89 DD ?
var@@aux25 DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@124 DB "Multiplicaci�n var = cte anda Long", 0
cadena@@125 DB "Multiplicaci�n var = cte no anda Long", 0
cadena@@126 DB "Multiplicaci�n var*var = cte anda Long", 0
cadena@@127 DB "Multiplicaci�n var*var = cte no anda Long", 0
cadena@@128 DB "Multiplicaci�n var*cte = cte anda Long", 0
cadena@@129 DB "Multiplicaci�n var*cte = cte no anda Long", 0
cadena@@130 DB "Multiplicaci�n cte*var = cte anda Long", 0
cadena@@131 DB "Multiplicaci�n cte*var = cte no anda Long", 0
cadena@@132 DB "Multiplicaci�n cte*cte = cte anda Long", 0
cadena@@133 DB "Multiplicaci�n cte*cte = cte no anda Long", 0
cadena@@134 DB "Multiplicaci�n var*fn = cte anda Long", 0
cadena@@135 DB "Multiplicaci�n var*fn = cte no anda Long", 0
cadena@@136 DB "Multiplicaci�n fn*var = cte anda Long", 0
cadena@@137 DB "Multiplicaci�n fn*var = cte no anda Long", 0
cadena@@138 DB "Multiplicaci�n cte*fn = cte anda Long", 0
cadena@@139 DB "Multiplicaci�n cte*fn = cte no anda Long", 0
cadena@@140 DB "Multiplicaci�n fn*cte = cte anda Long", 0
cadena@@141 DB "Multiplicaci�n fn*cte = cte no anda Long", 0
cadena@@142 DB "Multiplicaci�n fn*fn = cte anda Long", 0
cadena@@143 DB "Multiplicaci�n fn*fn = cte no anda Long", 0
cadena@@144 DB "Multiplicaci�n var*fn = var anda Long", 0
cadena@@145 DB "Multiplicaci�n var*fn = var no anda Long", 0
cadena@@146 DB "Multiplicaci�n cte*cte = fn anda Long", 0
cadena@@147 DB "Multiplicaci�n cte*cte = fn no anda Long", 0
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
MOV ab1@Variable,10
MOV EAX,ab1@Variable
MOV salida10@Funcion,EAX
RET
salida10 endp
salida30 proc
MOV ab1@Variable,30
MOV EAX,ab1@Variable
MOV salida30@Funcion,EAX
RET
salida30 endp
start:
MOV a@Variable,10
MOV b@Variable,3
MOV EAX,a@Variable
MOV EDX,b@Variable
MUL EDX
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,30
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@124, addr cadena@@124, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@125, addr cadena@@125, MB_OK
Label9:
MOV a@Variable,10
MOV b@Variable,3
MOV EAX,a@Variable
MOV EDX,b@Variable
MUL EDX
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,30
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@126, addr cadena@@126, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@127, addr cadena@@127, MB_OK
Label17:
MOV a@Variable,10
MOV EAX,a@Variable
MOV EDX,3
MUL EDX
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,30
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@128, addr cadena@@128, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@129, addr cadena@@129, MB_OK
Label24:
MOV b@Variable,3
MOV EAX,10
MOV EDX,b@Variable
MUL EDX
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,30
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@130, addr cadena@@130, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@131, addr cadena@@131, MB_OK
Label31:
MOV EAX,10
MOV EDX,3
MUL EDX
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,30
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@132, addr cadena@@132, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@133, addr cadena@@133, MB_OK
Label37:
Label39:
MOV a@Variable,10
CALL salida3
MOV EAX,a@Variable
MOV EDX,salida3@Funcion
MUL EDX
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,30
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@134, addr cadena@@134, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@135, addr cadena@@135, MB_OK
Label49:
MOV b@Variable,3
CALL salida10
MOV EAX,salida10@Funcion
MOV EDX,b@Variable
MUL EDX
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,30
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@136, addr cadena@@136, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@137, addr cadena@@137, MB_OK
Label57:
CALL salida3
MOV EAX,10
MOV EDX,salida3@Funcion
MUL EDX
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,30
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@138, addr cadena@@138, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@139, addr cadena@@139, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion
MOV EDX,3
MUL EDX
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,30
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@140, addr cadena@@140, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@141, addr cadena@@141, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion
MOV EDX,salida3@Funcion
MUL EDX
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,30
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@142, addr cadena@@142, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@143, addr cadena@@143, MB_OK
Label79:
MOV a@Variable,10
MOV c@Variable,30
CALL salida3
MOV EAX,a@Variable
MOV EDX,salida3@Funcion
MUL EDX
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@144, addr cadena@@144, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@145, addr cadena@@145, MB_OK
Label88:
MOV EAX,10
MOV EDX,3
MUL EDX
MOV var@@aux90,EAX
CALL salida30
MOV EAX,var@@aux90
CMP EAX,salida30@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@146, addr cadena@@146, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@147, addr cadena@@147, MB_OK
Label97:
invoke ExitProcess, 0
end start