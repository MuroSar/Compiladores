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
cadena@@614 DB "Suma var = cte anda Long", 0
cadena@@615 DB "Suma var = cte no anda Long", 0
cadena@@616 DB "Suma var+var = -cte anda Long", 0
cadena@@617 DB "Suma var+var = -cte no anda Long", 0
cadena@@618 DB "Suma var+cte = -cte anda Long", 0
cadena@@619 DB "Suma var+cte = -cte no anda Long", 0
cadena@@620 DB "Suma cte+var = cte anda Long", 0
cadena@@621 DB "Suma cte+var = cte no anda Long", 0
cadena@@622 DB "Suma cte+-cte = cte anda Long", 0
cadena@@623 DB "Suma cte+-cte = cte no anda Long", 0
cadena@@624 DB "Suma -var+fn = -cte anda Long", 0
cadena@@625 DB "Suma -var+fn = -cte no anda Long", 0
cadena@@626 DB "Suma fn+var = cte anda Long", 0
cadena@@627 DB "Suma fn+var = cte no anda Long", 0
cadena@@628 DB "Suma -cte+fn = -cte anda Long", 0
cadena@@629 DB "Suma -cte+fn = -cte no anda Long", 0
cadena@@630 DB "Suma fn+-cte = cte anda Long", 0
cadena@@631 DB "Suma fn+-cte = cte no anda Long", 0
cadena@@632 DB "Suma fn+fn = cte anda Long", 0
cadena@@633 DB "Suma fn+fn = cte no anda Long", 0
cadena@@634 DB "Suma var+fn = var anda Long", 0
cadena@@635 DB "Suma var+fn = var no anda Long", 0
cadena@@636 DB "Suma -cte+cte = fn anda Long", 0
cadena@@637 DB "Suma -cte+cte = fn no anda Long", 0
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
MOV ab1@Variable@main@salida7,-7
MOV EAX,ab1@Variable@main@salida7
MOV salida7@Funcion@main,EAX
RET
salida7 endp
start:
MOV a@Variable@main,10
MOV b@Variable@main,-3
MOV EAX,a@Variable@main
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable@main,EAX
MOV EAX,c@Variable@main
MOV EDX,7
CMP c@Variable@main,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@614, addr cadena@@614, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@615, addr cadena@@615, MB_OK
Label9:
MOV a@Variable@main,-10
MOV b@Variable@main,3
MOV EAX,a@Variable@main
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux11,EAX
MOV EAX,var@@aux11
MOV EDX,-7
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@616, addr cadena@@616, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@617, addr cadena@@617, MB_OK
Label17:
MOV a@Variable@main,-10
MOV EAX,a@Variable@main
ADD EAX,3
JO _overflow
MOV var@@aux18,EAX
MOV EAX,var@@aux18
MOV EDX,-7
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@618, addr cadena@@618, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@619, addr cadena@@619, MB_OK
Label24:
MOV b@Variable@main,-3
MOV EAX,10
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux25,EAX
MOV EAX,var@@aux25
MOV EDX,7
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@620, addr cadena@@620, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@621, addr cadena@@621, MB_OK
Label31:
MOV EAX,10
ADD EAX,-3
JO _overflow
MOV var@@aux31,EAX
MOV EAX,var@@aux31
MOV EDX,7
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@622, addr cadena@@622, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@623, addr cadena@@623, MB_OK
Label37:
Label39:
MOV a@Variable@main,-10
CALL salida3
MOV EAX,a@Variable@main
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux43,EAX
MOV EAX,var@@aux43
MOV EDX,-7
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@624, addr cadena@@624, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@625, addr cadena@@625, MB_OK
Label49:
MOV b@Variable@main,-3
CALL salida10
MOV EAX,salida10@Funcion@main
ADD EAX,b@Variable@main
JO _overflow
MOV var@@aux51,EAX
MOV EAX,var@@aux51
MOV EDX,7
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@626, addr cadena@@626, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@627, addr cadena@@627, MB_OK
Label57:
CALL salida3
MOV EAX,-10
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux58,EAX
MOV EAX,var@@aux58
MOV EDX,-7
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@628, addr cadena@@628, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@629, addr cadena@@629, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion@main
ADD EAX,-3
JO _overflow
MOV var@@aux65,EAX
MOV EAX,var@@aux65
MOV EDX,7
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@630, addr cadena@@630, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@631, addr cadena@@631, MB_OK
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
invoke MessageBox, NULL, addr cadena@@632, addr cadena@@632, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@633, addr cadena@@633, MB_OK
Label79:
MOV a@Variable@main,-10
MOV c@Variable@main,-7
CALL salida3
MOV EAX,a@Variable@main
ADD EAX,salida3@Funcion@main
JO _overflow
MOV var@@aux82,EAX
MOV EAX,var@@aux82
MOV EAX,c@Variable@main
CMP var@@aux82,EAX
JNE Label87
invoke MessageBox, NULL, addr cadena@@634, addr cadena@@634, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@635, addr cadena@@635, MB_OK
Label88:
MOV EAX,-10
ADD EAX,3
JO _overflow
MOV var@@aux90,EAX
CALL salida7
MOV EAX,var@@aux90
CMP EAX,salida7@Funcion@main
JNE Label96
invoke MessageBox, NULL, addr cadena@@636, addr cadena@@636, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@637, addr cadena@@637, MB_OK
Label97:
invoke ExitProcess, 0
end start