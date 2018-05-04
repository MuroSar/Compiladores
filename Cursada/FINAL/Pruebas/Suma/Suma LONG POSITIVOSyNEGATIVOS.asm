.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
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
salida7@Funcion DD ?
var@@aux90 DD ?
c@Variable DD ?
var@@aux51 DD ?
var@@aux11 DD ?
var@@aux38 DD ?
ab1@Variable DD ?
a@Variable DD ?
var@@aux82 DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux89 DD ?
var@@aux25 DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@0 DB "Suma var = cte anda Long", 0
cadena@@1 DB "Suma var = cte no anda Long", 0
cadena@@2 DB "Suma var+var = -cte anda Long", 0
cadena@@3 DB "Suma var+var = -cte no anda Long", 0
cadena@@4 DB "Suma var+cte = -cte anda Long", 0
cadena@@5 DB "Suma var+cte = -cte no anda Long", 0
cadena@@6 DB "Suma cte+var = cte anda Long", 0
cadena@@7 DB "Suma cte+var = cte no anda Long", 0
cadena@@8 DB "Suma cte+-cte = cte anda Long", 0
cadena@@9 DB "Suma cte+-cte = cte no anda Long", 0
cadena@@10 DB "Suma -var+fn = -cte anda Long", 0
cadena@@11 DB "Suma -var+fn = -cte no anda Long", 0
cadena@@12 DB "Suma fn+var = cte anda Long", 0
cadena@@13 DB "Suma fn+var = cte no anda Long", 0
cadena@@14 DB "Suma -cte+fn = -cte anda Long", 0
cadena@@15 DB "Suma -cte+fn = -cte no anda Long", 0
cadena@@16 DB "Suma fn+-cte = cte anda Long", 0
cadena@@17 DB "Suma fn+-cte = cte no anda Long", 0
cadena@@18 DB "Suma fn+fn = cte anda Long", 0
cadena@@19 DB "Suma fn+fn = cte no anda Long", 0
cadena@@20 DB "Suma var+fn = var anda Long", 0
cadena@@21 DB "Suma var+fn = var no anda Long", 0
cadena@@22 DB "Suma -cte+cte = fn anda Long", 0
cadena@@23 DB "Suma -cte+cte = fn no anda Long", 0
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
salida7 proc
MOV ab1@Variable,-7
MOV EAX,ab1@Variable
MOV salida7@Funcion,EAX
RET
salida7 endp
start:
MOV a@Variable,10
MOV b@Variable,-3
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EAX,c@Variable
MOV EDX,7
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@0, addr cadena@@0, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@1, addr cadena@@1, MB_OK
Label9:
MOV a@Variable,-10
MOV b@Variable,3
MOV EAX,a@Variable
ADD EAX,b@Variable
JO _overflow
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-7
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@2, addr cadena@@2, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@3, addr cadena@@3, MB_OK
Label17:
MOV a@Variable,-10
MOV EAX,a@Variable
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-7
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@4, addr cadena@@4, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@5, addr cadena@@5, MB_OK
Label24:
MOV b@Variable,-3
MOV EAX,10
ADD EAX,b@Variable
JO _overflow
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,7
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@6, addr cadena@@6, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@7, addr cadena@@7, MB_OK
Label31:
MOV EAX,10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,7
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@8, addr cadena@@8, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@9, addr cadena@@9, MB_OK
Label37:
Label39:
MOV a@Variable,-10
CALL salida3
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-7
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@10, addr cadena@@10, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@11, addr cadena@@11, MB_OK
Label49:
MOV b@Variable,-3
CALL salida10
MOV EAX,salida10@Funcion
ADD EAX,b@Variable
JO _overflow
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,7
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@12, addr cadena@@12, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@13, addr cadena@@13, MB_OK
Label57:
CALL salida3
MOV EAX,-10
ADD EAX,salida3@Funcion
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-7
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@14, addr cadena@@14, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@15, addr cadena@@15, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion
ADD EAX,-3
JO _overflow
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,7
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@16, addr cadena@@16, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@17, addr cadena@@17, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion
ADD EAX,salida3@Funcion
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,13
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@18, addr cadena@@18, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@19, addr cadena@@19, MB_OK
Label79:
MOV a@Variable,-10
MOV c@Variable,-7
CALL salida3
MOV EAX,a@Variable
ADD EAX,salida3@Funcion
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@20, addr cadena@@20, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@21, addr cadena@@21, MB_OK
Label88:
MOV EAX,-10
ADD EAX,3
JO _overflow
MOV var@@aux90,EAX
CALL salida7
MOV EAX,var@@aux90
CMP EAX,salida7@Funcion
JNE Label96
invoke MessageBox, NULL, addr cadena@@22, addr cadena@@22, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@23, addr cadena@@23, MB_OK
Label97:
invoke ExitProcess, 0
end start