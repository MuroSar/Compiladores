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
cadena@@560 DB "Suma var = -cte anda Long", 0
cadena@@561 DB "Suma var = -cte no anda Long", 0
cadena@@562 DB "Suma var+var = -cte anda Long", 0
cadena@@563 DB "Suma var+var = -cte no anda Long", 0
cadena@@564 DB "Suma var+-cte = -cte anda Long", 0
cadena@@565 DB "Suma var+-cte = -cte no anda Long", 0
cadena@@566 DB "Suma -cte+var = -cte anda Long", 0
cadena@@567 DB "Suma -cte+var = -cte no anda Long", 0
cadena@@568 DB "Suma -cte+-cte = -cte anda Long", 0
cadena@@569 DB "Suma -cte+-cte = -cte no anda Long", 0
cadena@@570 DB "Suma var+fn = -cte anda Long", 0
cadena@@571 DB "Suma var+fn = -cte no anda Long", 0
cadena@@572 DB "Suma fn+var = -cte anda Long", 0
cadena@@573 DB "Suma fn+var = -cte no anda Long", 0
cadena@@574 DB "Suma -cte+fn = -cte anda Long", 0
cadena@@575 DB "Suma -cte+fn = -cte no anda Long", 0
cadena@@576 DB "Suma fn+-cte = -cte anda Long", 0
cadena@@577 DB "Suma fn+-cte = -cte no anda Long", 0
cadena@@578 DB "Suma fn+fn = -cte anda Long", 0
cadena@@579 DB "Suma fn+fn = -cte no anda Long", 0
cadena@@580 DB "Suma var+fn = var anda Long", 0
cadena@@581 DB "Suma var+fn = var no anda Long", 0
cadena@@582 DB "Suma -cte+-cte = fn anda Long", 0
cadena@@583 DB "Suma -cte+-cte = fn no anda Long", 0
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
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,-13
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@560, addr cadena@@560, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@561, addr cadena@@561, MB_OK
Label9:
MOV a@Variable@main,-10
MOV b@Variable@main,-3
MOV EAX,a@Variable@main
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-13
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@562, addr cadena@@562, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@563, addr cadena@@563, MB_OK
Label17:
MOV a@Variable@main,-10
MOV EAX,a@Variable@main
ADD EAX,-3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@564, addr cadena@@564, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@565, addr cadena@@565, MB_OK
Label24:
MOV b@Variable@main,-3
MOV EAX,-10
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,-13
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@566, addr cadena@@566, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@567, addr cadena@@567, MB_OK
Label31:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,-13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@568, addr cadena@@568, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@569, addr cadena@@569, MB_OK
Label37:
Label39:
MOV a@Variable@main,-10
CALL salida3
MOV EAX,a@Variable@main
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-13
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@570, addr cadena@@570, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@571, addr cadena@@571, MB_OK
Label49:
MOV b@Variable@main,-3
CALL salida10
MOV EAX,salida10@Funcion@main
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,-13
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@572, addr cadena@@572, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@573, addr cadena@@573, MB_OK
Label57:
CALL salida3
MOV EAX,-10
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-13
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@574, addr cadena@@574, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@575, addr cadena@@575, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion@main
ADD EAX,-3
JO _overflow
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,-13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@576, addr cadena@@576, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@577, addr cadena@@577, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion@main
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,-13
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@578, addr cadena@@578, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@579, addr cadena@@579, MB_OK
Label79:
MOV a@Variable@main,-10
MOV c@Variable@main,-13
CALL salida3
MOV EAX,a@Variable@main
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable@main
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@580, addr cadena@@580, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@581, addr cadena@@581, MB_OK
Label88:
MOV EAX,-10
ADD EAX,-3
JO _overflow
MOV var@@aux90,EAX
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion@main
JNE Label96
invoke MessageBox, NULL, addr cadena@@582, addr cadena@@582, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@583, addr cadena@@583, MB_OK
Label97:
invoke ExitProcess, 0
end start