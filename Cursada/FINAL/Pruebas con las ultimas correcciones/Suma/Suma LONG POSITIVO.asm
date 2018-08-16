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
cadena@@590 DB "Suma var = cte anda Long", 0
cadena@@591 DB "Suma var = cte no anda Long", 0
cadena@@592 DB "Suma var+var = cte anda Long", 0
cadena@@593 DB "Suma var+var = cte no anda Long", 0
cadena@@594 DB "Suma var+cte = cte anda Long", 0
cadena@@595 DB "Suma var+cte = cte no anda Long", 0
cadena@@596 DB "Suma cte+var = cte anda Long", 0
cadena@@597 DB "Suma cte+var = cte no anda Long", 0
cadena@@598 DB "Suma cte+cte = cte anda Long", 0
cadena@@599 DB "Suma cte+cte = cte no anda Long", 0
cadena@@600 DB "Suma var+fn = cte anda Long", 0
cadena@@601 DB "Suma var+fn = cte no anda Long", 0
cadena@@602 DB "Suma fn+var = cte anda Long", 0
cadena@@603 DB "Suma fn+var = cte no anda Long", 0
cadena@@604 DB "Suma cte+fn = cte anda Long", 0
cadena@@605 DB "Suma cte+fn = cte no anda Long", 0
cadena@@606 DB "Suma fn+cte = cte anda Long", 0
cadena@@607 DB "Suma fn+cte = cte no anda Long", 0
cadena@@608 DB "Suma fn+fn = cte anda Long", 0
cadena@@609 DB "Suma fn+fn = cte no anda Long", 0
cadena@@610 DB "Suma var+fn = var anda Long", 0
cadena@@611 DB "Suma var+fn = var no anda Long", 0
cadena@@612 DB "Suma cte+cte = fn anda Long", 0
cadena@@613 DB "Suma cte+cte = fn no anda Long", 0
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
salida13 proc
MOV ab1@Variable@main@salida13,13
MOV EAX,ab1@Variable@main@salida13
MOV salida13@Funcion@main,EAX
RET
salida13 endp
start:
MOV a@Variable@main,10
MOV b@Variable@main,3
MOV EAX,a@Variable@main
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,13
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@590, addr cadena@@590, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@591, addr cadena@@591, MB_OK
Label9:
MOV a@Variable@main,10
MOV b@Variable@main,3
MOV EAX,a@Variable@main
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,13
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@592, addr cadena@@592, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@593, addr cadena@@593, MB_OK
Label17:
MOV a@Variable@main,10
MOV EAX,a@Variable@main
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,13
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@594, addr cadena@@594, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@595, addr cadena@@595, MB_OK
Label24:
MOV b@Variable@main,3
MOV EAX,10
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,13
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@596, addr cadena@@596, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@597, addr cadena@@597, MB_OK
Label31:
MOV EAX,10
ADD EAX,3
JO _overflow
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,13
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@598, addr cadena@@598, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@599, addr cadena@@599, MB_OK
Label37:
Label39:
MOV a@Variable@main,10
CALL salida3
MOV EAX,a@Variable@main
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,13
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@600, addr cadena@@600, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@601, addr cadena@@601, MB_OK
Label49:
MOV b@Variable@main,3
CALL salida10
MOV EAX,salida10@Funcion@main
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,13
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@602, addr cadena@@602, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@603, addr cadena@@603, MB_OK
Label57:
CALL salida3
MOV EAX,10
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,13
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@604, addr cadena@@604, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@605, addr cadena@@605, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion@main
ADD EAX,3
JO _overflow
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,13
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@606, addr cadena@@606, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@607, addr cadena@@607, MB_OK
Label71:
CALL salida10
CALL salida3
MOV EAX,salida10@Funcion@main
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux73,EAX
MOV EAX,var@@aux73
MOV EDX,13
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@608, addr cadena@@608, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@609, addr cadena@@609, MB_OK
Label79:
MOV a@Variable@main,10
MOV c@Variable@main,13
CALL salida3
MOV EAX,a@Variable@main
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable@main
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@610, addr cadena@@610, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@611, addr cadena@@611, MB_OK
Label88:
MOV EAX,10
ADD EAX,3
JO _overflow
MOV var@@aux90,EAX
CALL salida13
MOV EAX,var@@aux90
CMP EAX,salida13@Funcion@main
JNE Label96
invoke MessageBox, NULL, addr cadena@@612, addr cadena@@612, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@613, addr cadena@@613, MB_OK
Label97:
invoke ExitProcess, 0
end start