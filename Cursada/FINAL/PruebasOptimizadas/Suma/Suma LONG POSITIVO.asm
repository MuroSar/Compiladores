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
cadena@@132 DB "Suma var = cte anda Long", 0
cadena@@133 DB "Suma var = cte no anda Long", 0
cadena@@134 DB "Suma var+var = cte anda Long", 0
cadena@@135 DB "Suma var+var = cte no anda Long", 0
cadena@@136 DB "Suma var+cte = cte anda Long", 0
cadena@@137 DB "Suma var+cte = cte no anda Long", 0
cadena@@138 DB "Suma cte+var = cte anda Long", 0
cadena@@139 DB "Suma cte+var = cte no anda Long", 0
cadena@@140 DB "Suma cte+cte = cte anda Long", 0
cadena@@141 DB "Suma cte+cte = cte no anda Long", 0
cadena@@142 DB "Suma var+fn = cte anda Long", 0
cadena@@143 DB "Suma var+fn = cte no anda Long", 0
cadena@@144 DB "Suma fn+var = cte anda Long", 0
cadena@@145 DB "Suma fn+var = cte no anda Long", 0
cadena@@146 DB "Suma cte+fn = cte anda Long", 0
cadena@@147 DB "Suma cte+fn = cte no anda Long", 0
cadena@@148 DB "Suma fn+cte = cte anda Long", 0
cadena@@149 DB "Suma fn+cte = cte no anda Long", 0
cadena@@150 DB "Suma fn+fn = cte anda Long", 0
cadena@@151 DB "Suma fn+fn = cte no anda Long", 0
cadena@@152 DB "Suma var+fn = var anda Long", 0
cadena@@153 DB "Suma var+fn = var no anda Long", 0
cadena@@154 DB "Suma cte+cte = fn anda Long", 0
cadena@@155 DB "Suma cte+cte = fn no anda Long", 0
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
salida13 proc
MOV ab1@Variable,13
MOV EAX,ab1@Variable
MOV salida13@Funcion,EAX
RET
salida13 endp
start:
MOV a@Variable,10
MOV b@Variable,3
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,13
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@132, addr cadena@@132, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@133, addr cadena@@133, MB_OK
Label9:
MOV a@Variable,10
MOV b@Variable,3
MOV EAX,var@@aux11
MOV EDX,13
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@134, addr cadena@@134, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@135, addr cadena@@135, MB_OK
Label17:
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux11,EAX
Label17:
MOV a@Variable,10
MOV EAX,var@@aux18
MOV EDX,13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@136, addr cadena@@136, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@137, addr cadena@@137, MB_OK
Label24:
MOV EAX,a@Variable
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
Label24:
MOV b@Variable,3
MOV EAX,var@@aux25
MOV EDX,13
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@138, addr cadena@@138, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@139, addr cadena@@139, MB_OK
Label31:
MOV EAX,var@@aux31
MOV EDX,13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@140, addr cadena@@140, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@141, addr cadena@@141, MB_OK
Label37:
MOV EAX,10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
Label31:
MOV EAX,10
ADD EAX,3
JO _overflow
MOV var@@aux31,EAX
Label37:
Label39:
MOV a@Variable,10
CALL salida3
MOV EAX,var@@aux43
MOV EDX,13
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@142, addr cadena@@142, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@143, addr cadena@@143, MB_OK
Label49:
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux43,EAX
Label49:
MOV b@Variable,3
CALL salida10
MOV EAX,var@@aux51
MOV EDX,13
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@144, addr cadena@@144, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@145, addr cadena@@145, MB_OK
Label57:
CALL salida3
MOV EAX,var@@aux58
MOV EDX,13
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@146, addr cadena@@146, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@147, addr cadena@@147, MB_OK
Label64:
CALL salida10
MOV EAX,var@@aux65
MOV EDX,13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@148, addr cadena@@148, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@149, addr cadena@@149, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,var@@aux73
MOV EDX,13
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@150, addr cadena@@150, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@151, addr cadena@@151, MB_OK
Label79:
MOV EAX,salida10@Funcion
ADD EAX,b@Variable
JO _overflow
MOV var@@aux51,EAX
MOV EAX,10
ADD EAX,salida3@Funcion
MOV var@@aux58,EAX
MOV EAX,salida10@Funcion
ADD EAX,3
JO _overflow
MOV var@@aux65,EAX
MOV EAX,salida10@Funcion
ADD EAX,salida3@Funcion
MOV var@@aux73,EAX
Label79:
MOV a@Variable,10
MOV c@Variable,13
CALL salida3
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@152, addr cadena@@152, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@153, addr cadena@@153, MB_OK
Label88:
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux82,EAX
Label88:
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@154, addr cadena@@154, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@155, addr cadena@@155, MB_OK
Label97:
invoke ExitProcess, 0
end start